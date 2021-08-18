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

import com.pedeagua.entity.EmpresaProduto;
import com.pedeagua.entity.Produto;
import com.pedeagua.service.IProdutoService;

@Controller
@RequestMapping("/info")
public class ProdutoController {
	@Autowired
	private IProdutoService produtoService;
	
	// Recuperar produto
	@RequestMapping(value="/produto", params = "id", method = RequestMethod.GET, produces = { "application/json"})
	public ResponseEntity<Produto> getProdutoById(@RequestParam("id") Integer id) {
		Produto produto = produtoService.getProdutoById(id);
		return new ResponseEntity<Produto>(produto, HttpStatus.OK);
	}
	
	// Recuperar todos os produtos
	@RequestMapping(value= "/produto", method = RequestMethod.GET)
	public ResponseEntity<List<Produto>> getAllProdutos() {
		List<Produto> list = produtoService.getAllProdutos();
		return new ResponseEntity<List<Produto>>(list, HttpStatus.OK);
	}
	
	// Salvar produto
	@RequestMapping(value= "/produto", method = RequestMethod.POST)
	public ResponseEntity<Void> addProduto(@RequestBody Produto produto, UriComponentsBuilder builder) {
        boolean flag = produtoService.addProduto(produto);
        if (flag == false) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/produto/{id}").buildAndExpand(produto.getCod_produto()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	// Recuperar todos os produtos por empresa
	@RequestMapping(value= "/produto", params ="cod_empresa", method = RequestMethod.GET)
	public ResponseEntity<List<Produto>> getAllProdutosPorEmpresa(@RequestParam("cod_empresa") Integer cod_empresa) {
		List<Produto> list = produtoService.getAllProdutosPorEmpresa(cod_empresa);
		return new ResponseEntity<List<Produto>>(list, HttpStatus.OK);
	}
	
	// Salvar e atualizar produto por empresa
	@RequestMapping(value= "/produtoPorEmpresa", method = RequestMethod.POST)
	public ResponseEntity<Void> addProdutoPorEmpresa(@RequestBody EmpresaProduto produto, UriComponentsBuilder builder) {
        boolean flag = produtoService.addProdutoPorEmpresa(produto);
        if (flag == false) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/produtoPorEmpresa/{id}").buildAndExpand(produto.getCod_produto()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	// Atualizar produto
	@RequestMapping(value="/produto/{id}", method = RequestMethod.PUT )
	public ResponseEntity<Produto> updateProduto(@RequestBody Produto produto) {
		produtoService.updateProduto(produto);
		return new ResponseEntity<Produto>(produto, HttpStatus.OK);
	}
	
	// Atualizar produto
	@RequestMapping(value="/produto/{id}", method = RequestMethod.DELETE )
	public ResponseEntity<Void> deleteProduto(@PathVariable("id") Integer id) {
		produtoService.deleteProduto(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
} 