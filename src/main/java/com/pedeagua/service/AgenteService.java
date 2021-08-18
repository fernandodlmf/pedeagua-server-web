package com.pedeagua.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedeagua.dao.IAgenteDAO;
import com.pedeagua.entity.Agente;
import com.pedeagua.entity.AgenteUsuario;
@Service
public class AgenteService implements IAgenteService {
	@Autowired
	private IAgenteDAO agenteDAO;
	@Override
	public Agente getAgenteById(int cod_agente) {
		return agenteDAO.getAgenteById(cod_agente);
	}	
	@Override
	public List<Agente> getAllAgentes(){
		return agenteDAO.getAllAgentes();
	}
	@Override
	public synchronized long addAgente(Agente agente){
       if (agenteDAO.agenteExists(agente.getEmail())) {
    	   return 0;
       } else {    	  
    	   return agenteDAO.addAgente(agente);
       }
	}
	@Override
	public void updateAgente(Agente agente) {
		agenteDAO.updateAgente(agente);
	}
	@Override
	public void deleteAgente(int cod_agente) {
		agenteDAO.deleteAgente(cod_agente);
	}
	@Override
	public boolean agenteUsuarioExists(String email, String senha) {
		// TODO Auto-generated method stub
		return agenteDAO.agenteUsuarioExists(email, senha);
	}
	@Override
	public Agente getAgenteByEmail(String email) {
		// TODO Auto-generated method stub
		return agenteDAO.getAgenteByEmail(email);
	}
	@Override
	public long addAgenteUsuario(AgenteUsuario agenteUsuario) {
		
		if (agenteDAO.agenteExists(agenteUsuario.getEmail())) {			
			return agenteDAO.updateAgenteUsuario(agenteUsuario) ? agenteUsuario.getCod_agente_usuario() : 0 ;
	       } else {	    	  
	    	   return agenteDAO.addAgenteUsuario(agenteUsuario);
	       }
	}
	@Override
	public boolean updateAgenteUsuario(AgenteUsuario agenteUsuario) {		
		return agenteDAO.updateAgenteUsuario(agenteUsuario);
	}
	@Override
	public boolean agenteExists(String email) {
		// TODO Auto-generated method stub
		return agenteDAO.agenteExists(email);
	}
}
