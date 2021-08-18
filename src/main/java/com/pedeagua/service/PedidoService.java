package com.pedeagua.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedeagua.dao.IPedidoDAO;
import com.pedeagua.entity.Pedido;
@Service
public class PedidoService implements IPedidoService {
	
	@Autowired
	private IPedidoDAO pedidoDAO;
	
	@Override
	public Pedido getPedidoById(long cod_pedido) {
		Pedido obj = pedidoDAO.getPedidoById(cod_pedido);
		return obj;
	}	
	@Override
	public List<Pedido> getAllPedidos(){
		return pedidoDAO.getAllPedidos();
	}
	
	@Override
	public synchronized long addPedido(Pedido pedido){
	    
	    return pedidoDAO.addPedido(pedido);       
	}
	
	@Override
	public void updatePedido(Pedido pedido) {
		pedidoDAO.updatePedido(pedido);
	}
	
	@Override
	public void deletePedido(int cod_pedido) {
		pedidoDAO.deletePedido(cod_pedido);
	}
	@Override
	public List<Pedido> getAllPedidosPorEmpresa(int cod_empresa) {
		return pedidoDAO.getAllPedidosPorEmpresa(cod_empresa);
	}
	@Override
	public boolean updatePedidoPorEmpresa(Pedido pedido) {
		// TODO Auto-generated method stub
		return pedidoDAO.updatePedidoPorEmpresa(pedido);
	}
	@Override
	public long addPedidoPorEmpresa(Pedido pedido) {
		if(pedidoDAO.pedidoPorEmpresaExists(pedido.getCod_pedido(), pedido.getCod_empresa(), pedido.getCod_agente())) {				
			return pedidoDAO.updatePedidoPorEmpresa(pedido) ? pedido.getCod_pedido() : 0;
        } else{        	
        	return pedidoDAO.addPedidoPorEmpresa(pedido);
        }
	}
	@Override
	public List<Pedido> getAllPedidosPorAgente(int cod_agente) {
		return pedidoDAO.getAllPedidosPorAgente(cod_agente);
	}
	
	@Override
	public long addPedidoPorAgente(Pedido pedido) {
		if(pedidoDAO.pedidoPorEmpresaExists(pedido.getCod_pedido(), pedido.getCod_empresa(), pedido.getCod_agente())) {				
			return pedidoDAO.updatePedidoPorEmpresa(pedido) ? pedido.getCod_pedido() : 0;
        } else{        	
        	return pedidoDAO.addPedidoPorAgente(pedido);
        }
	}
}
