package com.pedeagua.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/info")
public class MainTemplateController {
	@RequestMapping("/index")
	public String home() {
 		return "index";
 	}
	
	@RequestMapping(value="/inicial")
    public String getInicialTemplate() {
    	return "templates/inicial";	
    }
	
	@RequestMapping(value="/pedidoNotification")
    public String getPedidoNotificationTemplate() {
    	return "templates/pedidoNotification";	
    }
	
	@RequestMapping(value="/googleMaps")
    public String getGoogleMapsTemplate() {
    	return "templates/googleMaps";	
    }
	
	@RequestMapping(value="/pedidoDetalhes")
    public String getPedidoDetalhesTemplate() {
    	return "templates/pedidoDetalhes";	
    }
	
	@RequestMapping(value="/login")
    public String getLoginTemplate() {
    	return "templates/login";	
    }
	
	@RequestMapping(value="/pedidos")
    public String getPedidosTemplate() {
    	return "templates/pedidos";	
    }
	
	@RequestMapping(value="/clienteDetalhes")
    public String getClienteDetalhesTemplate() {
    	return "templates/clienteDetalhes";	
    }

    @RequestMapping(value="/produtos")
    public String getProdutosTemplate() {
    	return "templates/produtos";	
    }

    @RequestMapping(value="/minhaConta")
    public String getMinhaContaTemplate() {
    	return "templates/minhaConta";	
    }
	
} 