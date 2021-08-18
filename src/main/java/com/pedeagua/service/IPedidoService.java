package com.pedeagua.service;

import java.util.List;

import com.pedeagua.entity.Pedido;

public interface IPedidoService {
     List<Pedido> getAllPedidos();
     List<Pedido> getAllPedidosPorEmpresa(int cod_empresa);
     List<Pedido> getAllPedidosPorAgente(int cod_agente);
     long addPedidoPorEmpresa(Pedido pedido);
     long addPedidoPorAgente(Pedido pedido);
     boolean updatePedidoPorEmpresa(Pedido pedido);
     Pedido getPedidoById(long cod_pedido);
     long addPedido(Pedido pedido);
     void updatePedido(Pedido pedido);
     void deletePedido(int cod_pedido);
}
