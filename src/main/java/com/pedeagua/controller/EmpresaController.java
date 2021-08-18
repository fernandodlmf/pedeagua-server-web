package com.pedeagua.controller;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.pedeagua.entity.Empresa;
import com.pedeagua.entity.EmpresaUsuario;
import com.pedeagua.entity.Vendedor;
import com.pedeagua.security.CriptografaDados;
import com.pedeagua.security.SecuritySingleton;
import com.pedeagua.service.IEmpresaService;

@Controller
@RequestMapping("/info")
public class EmpresaController {
	@Autowired
	private IEmpresaService empresaService;	
	private JSONObject my_obj;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value= "/empresaLogin", params = {"email", "senha"}, method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public String empresaLogin(@RequestParam("email") String email, @RequestParam("senha") String senha) throws JSONException {		
		my_obj = null;
		my_obj = new JSONObject();	
		if(empresaService.empresaUsuarioExists(email, CriptografaDados.gerarHash(senha, "MD5"))) {
			Map map;		
			String valor = CriptografaDados.gerarHash(email, "SHA-1");			
			map = SecuritySingleton.getInstance()
					.armazenaChaves(email, valor);
			my_obj.put(email, (String) map.get(email));			
		}		
		
		return my_obj.toString();		
	}
	
	@RequestMapping(value= "/deslogarEmpresa", params = {"chave", "valor"}, method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<String> deslogarEmpresa(@RequestParam("chave") String chave, @RequestParam("valor") String valor) throws JSONException {		
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
		
	@RequestMapping(value="/getEmpresaPorCodigo", params="cod_empresa", method = RequestMethod.GET )
	public ResponseEntity<Empresa> getEmpresaById(@RequestParam("cod_empresa") Integer cod_empresa) {
		Empresa empresa = empresaService.getEmpresaById(cod_empresa);
		return new ResponseEntity<Empresa>(empresa, HttpStatus.OK);
	}
	
	// Retorna os dados da Empresa
	@RequestMapping(value="/getEmpresaPorEmail", params = "email", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Empresa> getEmpresaByToken(@RequestParam("email") String email) {		
		
		if (SecuritySingleton.getInstance().existeChave(email) == true) {			
			Empresa empresa = empresaService.getEmpresaByEmail(email);
			return new ResponseEntity<Empresa>(empresa, HttpStatus.OK);
		} else {
			return new ResponseEntity<Empresa>(new Empresa(), HttpStatus.OK);
		}
		
	}
	
	// Salvar ou Atualiza empresaUsuario
		@RequestMapping(value= "/addEmpresaUsuario", method = RequestMethod.POST, headers = "Accept=application/json")
		@ResponseBody
		public String addEmpresaUsuario(@RequestBody EmpresaUsuario empresaUsuario, UriComponentsBuilder builder) throws JSONException {
			my_obj = null;
			my_obj = new JSONObject();
			my_obj.put("resposta", "false");
			System.out.println("empresaUsuario: "+empresaUsuario.toString());
			if(empresaUsuario.getSenha() != null )
				empresaUsuario.setSenha(CriptografaDados.gerarHash(empresaUsuario.getSenha(), "MD5"));							
			
			if(empresaUsuario.getSenha_anterior() != null)
				empresaUsuario.setSenha_anterior(CriptografaDados.gerarHash(empresaUsuario.getSenha_anterior(), "MD5"));

			
			boolean flag = empresaService.addEmpresaUsuario(empresaUsuario);
	        if (flag == false) {
	        	return my_obj.toString();
	        } else {
	        	my_obj.put("resposta", "true");
	        	return my_obj.toString();
	        }        
		}
	
	// Retorna todos os vendedores cadastrados	
	@RequestMapping(value= "/getAllEmpresas", method = RequestMethod.GET)
	public ResponseEntity<List<Empresa>> getAllEmpresas() {
		List<Empresa> list = empresaService.getAllEmpresas();
		return new ResponseEntity<List<Empresa>>(list, HttpStatus.OK);
	}
	
	// Retorna todas os vendedores por coordenadas
	@RequestMapping(value= "/empresasPorCoodenadas", params = {"latitude", "longitude", "codigos"}, method = RequestMethod.GET)
	public ResponseEntity<List<Vendedor>> getAllEmpresasPorCoordenadas(@RequestParam("latitude") String latitude, 
			@RequestParam("longitude") String longitude, @RequestParam("codigos") JSONArray codigos) {
		List<Vendedor> list = empresaService.getEmpresasPorCoordenadas(latitude, longitude, codigos);
		return new ResponseEntity<List<Vendedor>>(list, HttpStatus.OK);
	}
	
	

} 