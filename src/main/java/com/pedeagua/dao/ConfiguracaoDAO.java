package com.pedeagua.dao;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import com.pedeagua.entity.Configuracao;

@Transactional
@Repository
public class ConfiguracaoDAO implements IConfiguracaoDAO {
	@Autowired
	private HibernateTemplate  hibernateTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public List<Configuracao> getAllConfiguracoes() {
		String hql = "FROM Configuracao as c ORDER BY c.cod_configuracao";
		return (List<Configuracao>) hibernateTemplate.find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Configuracao> getAllConfiguracoesPorEmpresa(int cod_empresa) {
		String hql = "FROM Configuracao as c WHERE c.agente.cod_agente = ? ORDER BY c.cod_configuracao";
		return (List<Configuracao>) hibernateTemplate.find(hql, cod_empresa);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Configuracao> getAllConfiguracoesPorCliente(int cod_cliente) {
		String hql = "FROM Configuracao as c WHERE c.agente.cod_agente = ? ORDER BY c.cod_configuracao";
		return (List<Configuracao>) hibernateTemplate.find(hql, cod_cliente);
	}

	@Override
	public long addConfiguracaoPorEmpresa(Configuracao configuracao) {
		// TODO Auto-generated method stub
		return (Integer)hibernateTemplate.save(configuracao);
	}

	@Override
	public long addConfiguracaoPorCliente(Configuracao configuracao) {
		// TODO Auto-generated method stub
		return (Integer)hibernateTemplate.save(configuracao);
	}

	@Override
	public boolean updateConfiguracao(Configuracao configuracao) {
		try{
			hibernateTemplate.update(configuracao);
			return true;	
		} catch(Exception e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean ConfiguracaoExists(int cod_configuracao, int cod_agente) {
		String hql = "FROM Configuracao as c WHERE c.cod_configuracao = ? and c.agente.cod_agente = ?";
		List<Configuracao> configuracoes = (List<Configuracao>) hibernateTemplate.find(hql, cod_agente);
		return configuracoes.size() > 0 ? true : false;
	}

	@Override
	public Configuracao getConfiguracaoById(int cod_configuracao) {
		// TODO Auto-generated method stub
		return hibernateTemplate.get(Configuracao.class, cod_configuracao);
	}

	
	
}
