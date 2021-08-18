package com.pedeagua.dao;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.pedeagua.entity.AgenteEndereco;
import com.pedeagua.entity.Pedido;
import com.pedeagua.entity.Produto;
@Transactional
@Repository
public class EnderecoDAO implements IEnderecoDAO {
	@Autowired
	private HibernateTemplate  hibernateTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public List<AgenteEndereco> getAllEnderecos() {
		String hql = "FROM AgenteEndereco as p ORDER BY p.cod_agente_endereco";
		return (List<AgenteEndereco>) hibernateTemplate.find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AgenteEndereco> getAllEnderecosPorEmpresa(int cod_empresa) {
		String hql = "FROM AgenteEndereco as p WHERE p.agente.cod_agente = ? ORDER BY p.cod_agente_endereco";
		return (List<AgenteEndereco>) hibernateTemplate.find(hql, cod_empresa);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AgenteEndereco> getAllEnderecosPorAgente(int cod_agente) {
		String hql = "FROM AgenteEndereco as p WHERE p.agente.cod_agente = ? ORDER BY p.cod_agente_endereco";
		return (List<AgenteEndereco>) hibernateTemplate.find(hql, cod_agente);
	}

	@Override
	public long addEnderecoPorEmpresa(AgenteEndereco agenteEndereco) {
		// TODO Auto-generated method stub
		return (Integer)hibernateTemplate.save(agenteEndereco);
	}

	@Override
	public long addEnderecoPorAgente(AgenteEndereco agenteEndereco) {
		try {
			if (agenteEndereco.getPadrao() == 1) {
				removeEnderecosPadrao(agenteEndereco.getAgente().getCod_agente());
			}
			
			return (Integer)hibernateTemplate.save(agenteEndereco);
			
		} catch (Exception e) {
			return 0;
		}
		
	}

	@Override
	public boolean updateEndereco(AgenteEndereco agenteEndereco) {
		try{	
			if (agenteEndereco.getPadrao() == 1 && agenteEndereco.getExcluido() == 0 ) {
						
				Query q =  hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("update AgenteEndereco set padrao = :npadrao where padrao = :padrao and agente.cod_agente = :cod_agente");
				q.setInteger("padrao", 1);
				q.setInteger("npadrao", 0);
				q.setInteger("cod_agente", agenteEndereco.getAgente().getCod_agente());			
				q.executeUpdate();
			}
			agenteEndereco.setEm_update(0);
			hibernateTemplate.update(agenteEndereco);
			return true;	
		} catch(Exception e) {
			System.out.println("Erro: " +e.getMessage());
			return false;
		}
	}
	
	@Override
	public boolean removeEnderecosPadrao(int cod_agente) {
		
		try{				
			for(AgenteEndereco agenteEndereco: getAllEnderecosPorAgente(cod_agente)) {
				agenteEndereco.setPadrao(0);
				updateEndereco(agenteEndereco);
				
			}	
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean EnderecoExists(int cod_endereco, int cod_agente) {
		String hql = "FROM AgenteEndereco as p WHERE p.cod_agente_endereco = ? and p.agente.cod_agente = ?";
		List<AgenteEndereco> agenteEnderecos = (List<AgenteEndereco>) hibernateTemplate.find(hql, cod_endereco, cod_agente);
		return agenteEnderecos.size() > 0 ? true : false;
	}

	@Override
	public AgenteEndereco getEnderecoById(int cod_endereco) {
		// TODO Auto-generated method stub
		return hibernateTemplate.get(AgenteEndereco.class, cod_endereco);
	}

	@Override
	public void deletePedido(int cod_endereco) {
		// TODO Auto-generated method stub

	}
	
}
