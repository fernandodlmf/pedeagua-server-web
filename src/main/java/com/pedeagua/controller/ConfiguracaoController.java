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
import com.pedeagua.entity.Configuracao;
import com.pedeagua.entity.Pedido;
import com.pedeagua.service.IConfiguracaoService;
import com.pedeagua.service.IEnderecoService;
import com.pedeagua.service.IPedidoService;

@Controller
@RequestMapping("/info")
public class ConfiguracaoController {
	
	@Autowired
	private IConfiguracaoService configuracaoService;
	private JSONObject my_obj;
	
	// Recuperar configuracao by id
	@RequestMapping(value="/getConfiguracaoById", params = "cod_configuracao", method = RequestMethod.GET, produces = { "application/json"})
	public ResponseEntity<Configuracao> getConfiguracoesById(@RequestParam("cod_configuracao") Integer cod_configuracao) {
		Configuracao configuracao = configuracaoService.getConfiguracaoById(cod_configuracao);
		return new ResponseEntity<Configuracao>(configuracao, HttpStatus.OK);
	}
	
	// Recuperar todas as configuracoes do banco
	@RequestMapping(value= "/getAllConfiguracoes", method = RequestMethod.GET)
	public ResponseEntity<List<Configuracao>> getAllConfiguracoes() {
		List<Configuracao> list = configuracaoService.getAllConfiguracoes();
		return new ResponseEntity<List<Configuracao>>(list, HttpStatus.OK);
	}
	
	// Recuperar todas as configuracoes por empresa
	// OBS: O BANCO NÃO POSSUI A COLUNA COD_EMPRESA NA TABELA CONFIGURACAO
	@RequestMapping(value= "/getAllConfiguracoesPorEmpresa", params ="cod_empresa", method = RequestMethod.GET)
	public ResponseEntity<List<Configuracao>> getAllConfiguracoesPorEmpresa(@RequestParam("cod_empresa") Integer cod_empresa) {
		List<Configuracao> list = configuracaoService.getAllConfiguracoesPorEmpresa(cod_empresa);
		return new ResponseEntity<List<Configuracao>>(list, HttpStatus.OK);
	}
	
	// Salvar configuracao por empresa
	@RequestMapping(value= "configuracaoPorEmpresa", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public String addConfiguracaoPorEmpresa(@RequestBody Configuracao configuracao) throws JSONException {		
		my_obj = new JSONObject();
		my_obj.put("resposta", "false");
				
        long cod_configuracao = configuracaoService.addConfiguracaoPorEmpresa(configuracao);
        if (cod_configuracao == 0) {
        	return my_obj.toString();
        } else {        
        	my_obj.put("resposta", "true");
        	my_obj.put("cod_configuracao", String.valueOf(configuracao));
        	return my_obj.toString();
        }
               
	}	
	
	// Salvar configuracao por cliente
	@RequestMapping(value= "/configuracaoPorCliente", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public String addConfiguracaoPorCliente(@RequestBody Configuracao configuracao) throws JSONException {		
		my_obj = new JSONObject();
		my_obj.put("resposta", "false");
				
        long cod_configuracao = configuracaoService.addConfiguracaoPorCliente(configuracao);
        if (cod_configuracao == 0) {
        	return my_obj.toString();
        } else {        
        	my_obj.put("resposta", "true");
        	my_obj.put("cod_configuracao", String.valueOf(configuracao));
        	return my_obj.toString();
        }
               
	}	
	
		
} 