package com.pedeagua.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedeagua.dao.IEnderecoDAO;
import com.pedeagua.entity.AgenteEndereco;
@Service
public class EnderecoService implements IEnderecoService {
	
	@Autowired
	private IEnderecoDAO enderecoDAO;

	@Override
	public List<AgenteEndereco> getAllEnderecos() {
		// TODO Auto-generated method stub
		return enderecoDAO.getAllEnderecos();
	}

	@Override
	public List<AgenteEndereco> getAllEnderecosPorEmpresa(int cod_empresa) {
		// TODO Auto-generated method stub
		return enderecoDAO.getAllEnderecosPorEmpresa(cod_empresa);
	}

	@Override
	public List<AgenteEndereco> getAllEnderecosPorCliente(int cod_cliente) {
		// TODO Auto-generated method stub
		return enderecoDAO.getAllEnderecosPorAgente(cod_cliente);
	}

	@Override
	public synchronized long addEnderecoPorEmpresa(AgenteEndereco agenteEndereco) {
		// TODO Auto-generated method stub
		return enderecoDAO.addEnderecoPorEmpresa(agenteEndereco);
	}

	@Override
	public synchronized long addEnderecosPorCliente(AgenteEndereco agenteEndereco) {
		if(enderecoDAO.EnderecoExists(agenteEndereco.getCod_agente_endereco(), agenteEndereco.getAgente().getCod_agente())) {
			boolean status = enderecoDAO.updateEndereco(agenteEndereco);				
			return status == true ? agenteEndereco.getCod_agente_endereco() : 0;
        } else{        	
    	   return enderecoDAO.addEnderecoPorAgente(agenteEndereco);
        }
	}

	@Override
	public boolean updateEndereco(AgenteEndereco agenteEndereco) {
		// TODO Auto-generated method stub
		return enderecoDAO.updateEndereco(agenteEndereco);
	}

	@Override
	public AgenteEndereco getEnderecoById(int cod_endereco) {
		// TODO Auto-generated method stub
		return enderecoDAO.getEnderecoById(cod_endereco);
	}
	
}
