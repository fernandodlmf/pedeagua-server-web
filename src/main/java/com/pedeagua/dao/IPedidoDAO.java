package com.pedeagua.dao;
import java.util.List;

import com.pedeagua.entity.Pedido;
public interface IPedidoDAO {
    List<Pedido> getAllPedidos();
    List<Pedido> getAllPedidosPorEmpresa(int cod_empresa);
    List<Pedido> getAllPedidosPorAgente(int cod_agente);
    long addPedidoPorEmpresa(Pedido pedido);
    long addPedidoPorAgente(Pedido pedido);
    boolean updatePedidoPorEmpresa(Pedido pedido);
    boolean pedidoPorEmpresaExists(int cod_pedido, int cod_empresa, int cod_cliente);
    Pedido getPedidoById(long cod_pedido);
    long addPedido(Pedido pedido);
    void updatePedido(Pedido pedido);
    void deletePedido(int cod_pedido);   
}
 