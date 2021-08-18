package com.pedeagua.dao;
import java.util.List;



import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pedeagua.entity.Agente;
import com.pedeagua.entity.AgenteUsuario;

@Transactional
@Repository
public class AgenteDAO implements IAgenteDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Agente getAgenteById(int cod_agente) {
		return (Agente) sessionFactory.getCurrentSession().get(Agente.class, cod_agente);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Agente> getAllAgentes() {
		//String hql = "FROM Agente as p ORDER BY p.cod_agente";
		return sessionFactory.getCurrentSession().createCriteria(Agente.class).
				addOrder(Order.asc("cod_agente")).list();
	}	
	
	@Override
	public long addAgente(Agente agente) {	
		return (long) sessionFactory.getCurrentSession().save(agente);
	}
	
	@Override
	public void updateAgente(Agente agente) {
		//Agente p = getAgenteById(agente.getCod_agente());
		sessionFactory.getCurrentSession().update(agente);
	}
	
	@Override
	public void deleteAgente(int cod_agente) {
		sessionFactory.getCurrentSession().delete(getAgenteById(cod_agente));
	}
	
	@Override
	public boolean agenteExists(String email) {
		String hql = "FROM Agente as p WHERE p.email = :email";
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		q.setParameter("email", email);
		return q.list().size() > 0 ? true : false;
	}
	
	@Override
	public boolean agenteUsuarioExists(String email, String senha) {
		String hql = "FROM AgenteUsuario as a WHERE a.email = :email AND a.senha = :senha";
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		q.setParameter("email", email);
		q.setParameter("senha", senha);	
		return q.list().size() > 0 ? true : false;
	}
	
	@Override
	public Agente getAgenteByEmail(String email) {
		String hql = "FROM Agente as p WHERE p.email = :email";	
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		q.setParameter("email", email);
		return (Agente) q.list().get(0);
	}
	
	@Override
	public long addAgenteUsuario(AgenteUsuario agenteUsuario) {
			
		return (int) sessionFactory.getCurrentSession().save(agenteUsuario);
	}
	
	@Override
	public boolean updateAgenteUsuario(AgenteUsuario agenteUsuario) { 	
		
		AgenteUsuario ag = getAgenteUsuarioByEmail(agenteUsuario.getEmail());
		if((agenteUsuario.getSenha() != null) && (agenteUsuario.getSenha_anterior() != null)) {
			if(agenteUsuario.getSenha().equals(ag.getSenha())) {
				agenteUsuario.setSenha(agenteUsuario.getSenha_anterior());
				agenteUsuario.setSenha_anterior(ag.getSenha());			
				agenteUsuario.setCod_agente_usuario(ag.getCod_agente_usuario());					
				sessionFactory.getCurrentSession().update(agenteUsuario);					
				return true;
			} else {
				return false;
			}
		} else if((agenteUsuario.getSenha() != null) && (agenteUsuario.getSenha_anterior() == null) ) {
			agenteUsuario.setSenha(agenteUsuario.getSenha());		
			agenteUsuario.setCod_agente_usuario(ag.getCod_agente_usuario());		
			sessionFactory.getCurrentSession().update(agenteUsuario);				
			return true;
		} else {
			agenteUsuario.setSenha(ag.getSenha());
			agenteUsuario.setSenha_anterior(ag.getSenha_anterior());			
			agenteUsuario.setCod_agente_usuario(ag.getCod_agente_usuario());		
			sessionFactory.getCurrentSession().update(agenteUsuario);				
			return true;
		}	
		
	}

	@Override
	public AgenteUsuario getAgenteUsuarioByEmail(String email) {
		String hql = "FROM AgenteUsuario as p WHERE p.email = :email";	
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		q.setParameter("email", email);
		return (AgenteUsuario) q.list().get(0);
		
	}
}
