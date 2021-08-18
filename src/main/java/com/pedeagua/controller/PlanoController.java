package com.pedeagua.controller;
import java.util.List;

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
import org.springframework.web.util.UriComponentsBuilder;

import com.pedeagua.entity.Plano;
import com.pedeagua.service.IPlanoService;

@Controller
@RequestMapping("/info")
public class PlanoController {
	@Autowired
	private IPlanoService planoService;
	
	// Recuperar plano pelo id
	@RequestMapping(value="/plano", params = "id", method = RequestMethod.GET, produces = { "application/json"})
	public ResponseEntity<Plano> getPlanoById(@RequestParam("id") Integer id) {
		Plano plano = planoService.getPlanoById(id);
		return new ResponseEntity<Plano>(plano, HttpStatus.OK);
	}
	
	// Recuperar plano pelo hash
		@RequestMapping(value="/plano", params = "hash", method = RequestMethod.GET, produces = { "application/json"})
		public ResponseEntity<Plano> getPlanoByHash(@RequestParam("hash") String hash) {
			Plano plano = planoService.getPlanoByHash(hash);
			return new ResponseEntity<Plano>(plano, HttpStatus.OK);
		}
	
	// Recuperar todos os planos
	@RequestMapping(value= "/plano", method = RequestMethod.GET)
	public ResponseEntity<List<Plano>> getAllPlanos() {
		List<Plano> list = planoService.getAllPlanos();
		return new ResponseEntity<List<Plano>>(list, HttpStatus.OK);
	}
	
	// Salvar plano
	@RequestMapping(value= "/plano", method = RequestMethod.POST)
	public ResponseEntity<Void> addPlano(@RequestBody Plano plano, UriComponentsBuilder builder) {
		
        if (planoService.addPlano(plano)< 1) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/plano/{id}").buildAndExpand(plano.getCod_plano()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}			
	
	// deletar plano
	@RequestMapping(value="/plano/{id}", method = RequestMethod.DELETE )
	public ResponseEntity<Void> deletePlano(@PathVariable("id") Integer id) {
		planoService.deletePlano(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
} 