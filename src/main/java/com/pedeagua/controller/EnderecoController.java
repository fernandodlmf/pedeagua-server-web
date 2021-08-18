package com.pedeagua.controller;
import java.util.List;

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

import com.pedeagua.entity.AgenteEndereco;
import com.pedeagua.entity.Pedido;
import com.pedeagua.service.IEnderecoService;
import com.pedeagua.service.IPedidoService;

@Controller
@RequestMapping("/info")
public class EnderecoController {
	
	@Autowired
	private IEnderecoService enderecoService;
	private JSONObject my_obj;
	
	// Recuperar endereco
	@RequestMapping(value="/getEnderecoById", params = "cod_endereco", method = RequestMethod.GET, produces = { "application/json"})
	public ResponseEntity<AgenteEndereco> getEnderecoById(@RequestParam("cod_endereco") Integer cod_endereco) {
		AgenteEndereco agenteEndereco = enderecoService.getEnderecoById(cod_endereco);
		return new ResponseEntity<AgenteEndereco>(agenteEndereco, HttpStatus.OK);
	}
	
	// Recuperar todos os enderecos
	@RequestMapping(value= "/getAllEnderecos", method = RequestMethod.GET)
	public ResponseEntity<List<AgenteEndereco>> getAllEnderecos() {
		List<AgenteEndereco> list = enderecoService.getAllEnderecos();
		return new ResponseEntity<List<AgenteEndereco>>(list, HttpStatus.OK);
	}
	
	// Recuperar todos os enderecos por empresa
	// OBS: O BANCO NÃO POSSUI A COLUNA COD_EMPRESA NA TABELA AGENTE_ENDERECO
	@RequestMapping(value= "/getAllEnderecosPorEmpresa", params ="cod_empresa", method = RequestMethod.GET)
	public ResponseEntity<List<AgenteEndereco>> getAllEnderecosPorEmpresa(@RequestParam("cod_empresa") Integer cod_empresa) {
		List<AgenteEndereco> list = enderecoService.getAllEnderecosPorEmpresa(cod_empresa);
		return new ResponseEntity<List<AgenteEndereco>>(list, HttpStatus.OK);
	}
	
	// Salvar pedido
	@RequestMapping(value= "/enderecoPorEmpresa", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public String addEnderecoPorEmpresa(@RequestBody AgenteEndereco endereco) throws JSONException {		
		my_obj = new JSONObject();
		my_obj.put("resposta", "false");
				
        long cod_endereco = enderecoService.addEnderecoPorEmpresa(endereco);
        if (cod_endereco == 0) {
        	return my_obj.toString();
        } else {        
        	my_obj.put("resposta", "true");
        	my_obj.put("cod_agente_endereco", String.valueOf(cod_endereco));
        	return my_obj.toString();
        }
               
	}	
	
	// Salvar pedido
	@RequestMapping(value= "/enderecoPorAgente", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public String addEnderecoPorAgente(@RequestBody AgenteEndereco endereco) throws JSONException {		
		my_obj = new JSONObject();
		my_obj.put("resposta", "false");
				
        long cod_endereco = enderecoService.addEnderecosPorCliente(endereco);
        if (cod_endereco == 0) {
        	my_obj.put("cod_agente_endereco", String.valueOf(cod_endereco));
        	return my_obj.toString();
        } else {        
        	my_obj.put("resposta", "true");
        	my_obj.put("cod_agente_endereco", String.valueOf(cod_endereco));
        	return my_obj.toString();
        }
               
	}	
	
		
} 