package com.pedeagua.dao;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pedeagua.entity.Pedido;

@Transactional
@Repository
public class PedidoDAO implements IPedidoDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public Pedido getPedidoById(long cod_pedido) throws HibernateException {
		Session s = sessionFactory.getCurrentSession();	 
		return (Pedido) s.get(Pedido.class, (int) cod_pedido);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> getAllPedidos() {
		Session s = sessionFactory.getCurrentSession();	
		//String hql = "FROM Pedido as p ORDER BY p.cod_pedido";
		return s.createCriteria(Pedido.class).addOrder(Order.asc("cod_pedido")).list();
	}	
	
	@Override
	public long addPedido(Pedido pedido) {
		Session s = sessionFactory.getCurrentSession();
		
		return (long) s.save(pedido);
	}
	
	@Override
	public void updatePedido(Pedido pedido) {
		//Pedido p = getPedidoById(pedido.getCod_pedido());
		sessionFactory.getCurrentSession().evict(pedido);
		sessionFactory.getCurrentSession().update(pedido);
	}
	@Override
	public void deletePedido(int cod_pedido) {
		sessionFactory.getCurrentSession().delete(getPedidoById(cod_pedido));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> getAllPedidosPorEmpresa(int cod_empresa) {	
		String hql = "FROM Pedido as p WHERE p.cod_empresa = :cod_empresa ORDER BY p.cod_pedido";		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("cod_empresa", cod_empresa);
		//return (List<Pedido>) hibernateTemplate.find(hql, cod_empresa);
		return query.list();
	}
	@Override
	public boolean updatePedidoPorEmpresa(Pedido pedido) {
		sessionFactory.getCurrentSession().update(pedido);
		return true;	
	}
	
	@Override
	public long addPedidoPorEmpresa(Pedido pedido) {
		
		return (long) sessionFactory.getCurrentSession().save(pedido);	
	}
	
	@Override
	public boolean pedidoPorEmpresaExists(int cod_pedido, int cod_empresa, int cod_cliente) {
		String hql = "FROM Pedido as p WHERE p.cod_pedido = :cod_pedido and p.cod_empresa = :cod_empresa and p.cod_agente = :cod_agente";
		//List<Pedido> pedidos = (List<Pedido>) hibernateTemplate.find(hql, cod_pedido, cod_empresa, cod_cliente);
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		q.setParameter("cod_pedido", cod_pedido);
		q.setParameter("cod_empresa", cod_empresa);
		q.setParameter("cod_agente", cod_cliente);
		
		return q.list().size() > 0 ? true : false;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> getAllPedidosPorAgente(int cod_agente) {		
		String hql = "FROM Pedido as p WHERE p.cod_agente = :cod_agente ORDER BY p.cod_pedido";
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		q.setParameter("cod_agente", cod_agente);
		return q.list();
	}
	
	@Override
	public long addPedidoPorAgente(Pedido pedido) {
		return (Integer)sessionFactory.getCurrentSession().save(pedido); 
	}
}
