package com.pedeagua.controller;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.pedeagua.entity.Pedido;
import com.pedeagua.entity.PedidoItens;
import com.pedeagua.service.IPedidoService;

@Controller
@RequestMapping("/info")
public class PedidoController {
	
	@Autowired
	private IPedidoService pedidoService;
	private JSONObject my_obj;
	
	// Recuperar pedido
	@RequestMapping(value="/pedido", params = "id", method = RequestMethod.GET, produces = { "application/json"})
	public ResponseEntity<Pedido> getPedidoById(@RequestParam("id") Integer id) {
		Pedido pedido = pedidoService.getPedidoById(id);
		return new ResponseEntity<Pedido>(pedido, HttpStatus.OK);
	}
	
	// Recuperar todos os pedidos
	@RequestMapping(value= "/pedido", method = RequestMethod.GET)
	public ResponseEntity<List<Pedido>> getAllPedidos() {
		List<Pedido> list = pedidoService.getAllPedidos();
		return new ResponseEntity<List<Pedido>>(list, HttpStatus.OK);
	}
	
	// Recuperar todos os pedidos por empresa
	@RequestMapping(value= "/pedido", params ="cod_empresa", method = RequestMethod.GET)
	public ResponseEntity<List<Pedido>> getAllPedidosPorEmpresa(@RequestParam("cod_empresa") Integer cod_empresa) {
		List<Pedido> list = pedidoService.getAllPedidosPorEmpresa(cod_empresa);
		return new ResponseEntity<List<Pedido>>(list, HttpStatus.OK);
	}
	
//	// Salvar pedido
//	@RequestMapping(value= "/pedidoPorAgente", method = RequestMethod.POST, headers = "Accept=application/json")
//	@ResponseBody
//	public String addPedidoPorAgente(@RequestBody Pedido pedido) throws JSONException {		
//		my_obj = new JSONObject();
//		JSONArray itens = new JSONArray();
//		my_obj.put("resposta", "false");
//				
//        long cod_pedido = pedidoService.addPedidoPorAgente(pedido);
//        Pedido p = pedidoService.getPedidoById(cod_pedido);
//               
//        
//        //List<PedidoItens> itens = n;
//        //Gson g = new Gson();
//        for(PedidoItens pi:p.getItens()) {        	
//        	JSONObject item = new JSONObject();
//        	item.put("cod_pedido_itens", pi.getCod_pedido_itens());
//        	item.put("cod_produto", pi.getCod_produto());
//        	item.put("cancelado", pi.getCancelado());
//        	item.put("nome_produto", pi.getNome_produto());
//        	item.put("quantidade", pi.getQuantidade());
//        	item.put("preco", pi.getPreco());
//        	itens.put(item);        	
//        }
//        
//        if (cod_pedido == 0) {
//        	return my_obj.toString();
//        } else {        
//        	my_obj.put("resposta", "true");
//        	my_obj.put("cod_pedido", String.valueOf(cod_pedido));
//        	my_obj.put("itens", itens);
//        	return my_obj.toString();
//        }
//               
//	}
	
	
// Atualizar pedido
	@RequestMapping(value="/pedidoPorAgente", method = RequestMethod.POST, headers = "Accept=application/json" )
	public ResponseEntity<Pedido> addPedidoPorAgente(@RequestBody Pedido pedido) {
		long cod_pedido = pedidoService.addPedidoPorAgente(pedido);	
		return new ResponseEntity<Pedido>(pedidoService.getPedidoById(cod_pedido), HttpStatus.CREATED);
	}
	
	
	// Atualizar pedido
	@RequestMapping(value="/updatePedido", method = RequestMethod.PUT, headers = "Accept=application/json" )
	public ResponseEntity<Pedido> updatePedido(@RequestBody Pedido pedido) {
		pedidoService.updatePedido(pedido);
		return new ResponseEntity<Pedido>(pedido, HttpStatus.OK);
	}
	
	// Deletar pedido
	@RequestMapping(value="/pedido/{id}", method = RequestMethod.DELETE )
	public ResponseEntity<Void> deletePedido(@PathVariable("id") Integer id) {
		pedidoService.deletePedido(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
} 