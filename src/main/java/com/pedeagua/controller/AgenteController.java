package com.pedeagua.controller;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.pedeagua.entity.Agente;
import com.pedeagua.entity.AgenteEndereco;
import com.pedeagua.entity.AgenteUsuario;
import com.pedeagua.security.CriptografaDados;
import com.pedeagua.security.SecuritySingleton;
import com.pedeagua.service.IAgenteService;

@Controller
@RequestMapping("/info")
public class AgenteController {
	@Autowired
	private IAgenteService agenteService;	
	private JSONObject my_obj;
	
	@RequestMapping(value= "/agenteExiste", params = "email", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public String agenteLogin(@RequestParam("email") String email) throws JSONException {		
		my_obj = null;
		my_obj = new JSONObject();	
		boolean flag = agenteService.agenteExists(email); 
		if(flag == true) {						
			my_obj.put("resposta", "true");			
		} else {
			my_obj.put("resposta", "false");
		}				
		return my_obj.toString();		
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value= "/agenteLogin", params = {"email", "senha"}, method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public String agenteLogin(@RequestParam("email") String email, @RequestParam("senha") String senha) throws JSONException {		
		my_obj = null;
		my_obj = new JSONObject();	
		if(agenteService.agenteUsuarioExists(email, CriptografaDados.gerarHash(senha, "MD5"))) {
			Map map;		
			String valor = CriptografaDados.gerarHash(email, "SHA-1");			
			map = SecuritySingleton.getInstance()
					.armazenaChaves(email, valor);
			my_obj.put(email, (String) map.get(email));			
		} 	
		
		return my_obj.toString();
				
	}
	
	@RequestMapping(value= "/deslogarAgente", params = {"chave", "valor"}, method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<String> deslogarAgente(@RequestParam("chave") String chave, @RequestParam("valor") String valor) throws JSONException {		
		my_obj = null;
		my_obj = new JSONObject();
		my_obj.put("resposta", "false");
	
		if (SecuritySingleton.getInstance().existeChave(chave) == true){			
			if (SecuritySingleton.getInstance().recuperaValor(chave).equals(valor)) 			
				my_obj.put("resposta", SecuritySingleton.getInstance().retiraChave(chave, valor));					
		} else {
			my_obj.put("resposta", "false");			
		}		
		return new ResponseEntity<String>(my_obj.toString(), HttpStatus.OK);
	}
		
	@RequestMapping(value="/getAgentePorCodigo", params="cod_agente", method = RequestMethod.GET )
	public ResponseEntity<Agente> getAgenteById(@RequestParam("cod_agente") Integer cod_agente) {
		Agente agente = agenteService.getAgenteById(cod_agente);
		return new ResponseEntity<Agente>(agente, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getAgentePorEmail",params = "email", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Agente> getAgenteByToken(@RequestParam("email") String email) {		
		
		//if (SecuritySingleton.getInstance().existeChave(email) == true) {			
			return new ResponseEntity<Agente>(agenteService.getAgenteByEmail(email), HttpStatus.OK);
		//} else {
			//return new ResponseEntity<Agente>(new Agente(), HttpStatus.OK);
		//}
		
	}
	
	// Recuperar todos os agentes
	@RequestMapping(value= "/getAllAgentes", method = RequestMethod.GET)
	public ResponseEntity<List<Agente>> getAllAgentes() {
		List<Agente> list = agenteService.getAllAgentes();
		return new ResponseEntity<List<Agente>>(list, HttpStatus.OK);
	}
	
	// Salvar agenteUsuario // MUDAR FORMA DE RESPOSTA
	@RequestMapping(value= "/agenteUsuario", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public String addAgenteUsuario(@RequestBody AgenteUsuario agenteUsuario, UriComponentsBuilder builder) throws JSONException {
		my_obj = null;
		my_obj = new JSONObject();
		JSONObject agenteEndereco;
		JSONArray arrayEnderecos;
		my_obj.put("resposta", "false");
		//System.out.println("agenteUsuarioRecebido: "+agenteUsuario.toString());
		if(agenteUsuario.getSenha() != null )
			agenteUsuario.setSenha(CriptografaDados.gerarHash(agenteUsuario.getSenha(), "MD5"));							
		
		if(agenteUsuario.getSenha_anterior() != null)
			agenteUsuario.setSenha_anterior(CriptografaDados.gerarHash(agenteUsuario.getSenha_anterior(), "MD5"));
		
        if (agenteService.addAgenteUsuario(agenteUsuario) < 1) {        
        	return my_obj.toString();
        } else {    
        	Agente a = agenteService.getAgenteByEmail(agenteUsuario.getEmail());
        	my_obj.put("resposta", "true");
        	my_obj.put("cod_agente", a.getCod_agente()); 
        	my_obj.put("email", a.getEmail());
        	my_obj.put("nome", a.getName());
        	my_obj.put("telefone", a.getTelefone());
        	my_obj.put("perfil_url", a.getPerfil_url());                	        
        	arrayEnderecos = new JSONArray();
        	
        	for(int i =0; i < a.getEnderecos().size() ;i++) {
    			AgenteEndereco endereco = a.getEnderecos().get(i);
    			agenteEndereco = new JSONObject();
    			agenteEndereco.put("cod_agente_endereco", endereco.getCod_agente_endereco());
    			agenteEndereco.put("rua", endereco.getRua());
    			agenteEndereco.put("endereco", endereco.getEndereco());
    			agenteEndereco.put("bairro", endereco.getBairro());
    			agenteEndereco.put("numero", endereco.getNumero());
    			agenteEndereco.put("complemento", endereco.getComplemento());
    			agenteEndereco.put("referencia", endereco.getReferencia());
    			agenteEndereco.put("tipo", endereco.getTipo());
    			agenteEndereco.put("cidade", endereco.getCidade());
    			agenteEndereco.put("uf", endereco.getUf());
    			agenteEndereco.put("cep", endereco.getCep());
    			agenteEndereco.put("latitude", endereco.getLatitude());
    			agenteEndereco.put("longitude", endereco.getLongitude());
    		
    			arrayEnderecos.put(agenteEndereco);
        	}
        	my_obj.put("enderecos", arrayEnderecos);}        	        
        	
        	return my_obj.toString();
        }        	
	
} 