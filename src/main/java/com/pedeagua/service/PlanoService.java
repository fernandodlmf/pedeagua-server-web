package com.pedeagua.service;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.pedeagua.dao.IPlanoDAO;
import com.pedeagua.entity.Plano;

@Transactional
@Repository
public class PlanoService implements IPlanoService {
	@Autowired
	private IPlanoDAO  planoDao;

	@Override
	public List<Plano> getAllPlanos() {
		// TODO Auto-generated method stub
		return planoDao.getAllPlanos();
	}

	@Override
	public Plano getPlanoById(int cod_plano) {
		// TODO Auto-generated method stub
		return planoDao.getPlanoById(cod_plano);
	}

	@Override
	public Plano getPlanoByHash(String hash) {
		// TODO Auto-generated method stub
		return planoDao.getPlanoByHash(hash);
	}

	@Override
	public long addPlano(Plano plano) {
		if (planoDao.planoExists(plano.getHash())) {
			planoDao.updatePlano(plano);
			return plano.getCod_plano();
       } else {    	  
    	   return planoDao.addPlano(plano);
       }		
	}

	@Override
	public void updatePlano(Plano plano) {
		planoDao.updatePlano(plano);			
	}

	@Override
	public void deletePlano(int cod_plano) {
		planoDao.deletePlano(cod_plano);
		
	}

	
	
}
