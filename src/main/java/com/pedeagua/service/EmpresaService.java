package com.pedeagua.service;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedeagua.dao.IEmpresaDAO;
import com.pedeagua.entity.Empresa;
import com.pedeagua.entity.EmpresaUsuario;
import com.pedeagua.entity.Vendedor;
@Service
public class EmpresaService implements IEmpresaService {
	@Autowired
	private IEmpresaDAO empresaDAO;
	@Override
	public Empresa getEmpresaById(int cod_empresa) {
		Empresa obj = empresaDAO.getEmpresaById(cod_empresa);
		return obj;
	}	
	
	@Override
	public List<Empresa> getAllEmpresas(){
		return empresaDAO.getAllEmpresas();
	}
	@Override
	public synchronized boolean addEmpresa(Empresa empresa){
       if (empresaDAO.empresaExists(empresa.getEmail())) {
    	   return false;
       } else {
    	   empresaDAO.addEmpresa(empresa);
    	   return true;
       }
	}
	@Override
	public void updateEmpresa(Empresa empresa) {
		empresaDAO.updateEmpresa(empresa);
	}
	@Override
	public void deleteEmpresa(int cod_empresa) {
		empresaDAO.deleteEmpresa(cod_empresa);
	}
	@Override
	public boolean empresaUsuarioExists(String email, String senha) {
		// TODO Auto-generated method stub
		return empresaDAO.empresaUsuarioExists(email, senha);
	}
	@Override
	public Empresa getEmpresaByEmail(String email) {
		// TODO Auto-generated method stub
		return empresaDAO.getEmpresaByEmail(email);
	}
	@Override
	public boolean addEmpresaUsuario(EmpresaUsuario empresaUsuario) {
		
		if (empresaDAO.empresaExists(empresaUsuario.getEmail())) {
			boolean status = empresaDAO.updateEmpresaUsuario(empresaUsuario);//updateProdutoPorEmpresa(produto);		
			return status;
	       } else {
	    	   empresaDAO.addEmpresaUsuario(empresaUsuario);
	    	   return true;
	       }
	}
	@Override
	public boolean updateEmpresaUsuario(EmpresaUsuario empresaUsuario) {		
		return empresaDAO.updateEmpresaUsuario(empresaUsuario);
	}

	@Override
	public List<Vendedor> getEmpresasPorCoordenadas(String latitude, String longitude, JSONArray codigos) {
		
		return empresaDAO.getEmpresasPorCoordenadas(latitude, longitude, codigos);
	}
}
