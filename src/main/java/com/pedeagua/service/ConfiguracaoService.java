package com.pedeagua.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedeagua.dao.IConfiguracaoDAO;
import com.pedeagua.dao.IEnderecoDAO;
import com.pedeagua.entity.AgenteEndereco;
import com.pedeagua.entity.Configuracao;
@Service
public class ConfiguracaoService implements IConfiguracaoService {
	
	@Autowired
	private IConfiguracaoDAO configuracaoDAO;

	@Override
	public List<Configuracao> getAllConfiguracoes() {
		// TODO Auto-generated method stub
		return configuracaoDAO.getAllConfiguracoes();
	}

	@Override
	public List<Configuracao> getAllConfiguracoesPorEmpresa(int cod_empresa) {
		// TODO Auto-generated method stub
		return configuracaoDAO.getAllConfiguracoesPorEmpresa(cod_empresa);
	}

	@Override
	public List<Configuracao> getAllConfiguracoesPorCliente(int cod_cliente) {
		// TODO Auto-generated method stub
		return configuracaoDAO.getAllConfiguracoesPorCliente(cod_cliente);
	}

	@Override
	public long addConfiguracaoPorEmpresa(Configuracao configuracao) {
		if(configuracaoDAO.ConfiguracaoExists(configuracao.getCod_configuracao(), configuracao.getAgente().getCod_agente())) {
			boolean status = configuracaoDAO.updateConfiguracao(configuracao);				
			return status == true ? configuracao.getCod_configuracao() : 0;
        } else{        	
    	   return configuracaoDAO.addConfiguracaoPorEmpresa(configuracao);
        }
	}

	@Override
	public synchronized long addConfiguracaoPorCliente(Configuracao configuracao) {
		if(configuracaoDAO.ConfiguracaoExists(configuracao.getCod_configuracao(), configuracao.getAgente().getCod_agente())) {
			boolean status = configuracaoDAO.updateConfiguracao(configuracao);				
			return status == true ? configuracao.getCod_configuracao() : 0;
        } else{        	
    	   return configuracaoDAO.addConfiguracaoPorCliente(configuracao);
        }
	}

	@Override
	public boolean updateConfiguracao(Configuracao configuracao) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Configuracao getConfiguracaoById(int cod_configuracao) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}
