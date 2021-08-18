package com.pedeagua.dao;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import com.pedeagua.entity.Plano;

@Transactional
@Repository
public class PlanoDAO implements IPlanoDAO {
	@Autowired
	private HibernateTemplate  hibernateTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public List<Plano> getAllPlanos() {
		String hql = "FROM Plano as p ORDER BY p.cod_plano";
		return (List<Plano>) hibernateTemplate.find(hql);
	}

	@Override
	public Plano getPlanoById(int cod_plano) {
		// TODO Auto-generated method stub
		return hibernateTemplate.get(Plano.class, cod_plano);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Plano getPlanoByHash(String hash) {
		String hql = "FROM Plano as p WHERE p.hash = ?";	
		List<Plano> planos = (List<Plano>) hibernateTemplate.find(hql, hash);
		return planos.get(0);
	}

	@Override
	public long addPlano(Plano plano) {
		
		return (long) hibernateTemplate.save(plano);
	}

	@Override
	public void updatePlano(Plano plano) {
		Plano p = getPlanoById(plano.getCod_plano());
		hibernateTemplate.update(p);
		
	}

	@Override
	public void deletePlano(int cod_plano) {
		hibernateTemplate.delete(getPlanoById(cod_plano));
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean planoExists(String hash) {
		String hql = "FROM Plano as p WHERE p.hash = ?";
		List<Plano> planos = (List<Plano>) hibernateTemplate.find(hql, hash);
		return planos.size() > 0 ? true : false;
	}
	
}
