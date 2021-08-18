package com.pedeagua.service;

import java.util.List;

import com.pedeagua.entity.AgenteEndereco;
import com.pedeagua.entity.Configuracao;

public interface IConfiguracaoService {
	List<Configuracao> getAllConfiguracoes();
    List<Configuracao> getAllConfiguracoesPorEmpresa(int cod_empresa);
    List<Configuracao> getAllConfiguracoesPorCliente(int cod_cliente);
    long addConfiguracaoPorEmpresa(Configuracao configuracao);
    long addConfiguracaoPorCliente(Configuracao configuracao);
    boolean updateConfiguracao(Configuracao configuracao);
    Configuracao getConfiguracaoById(int cod_configuracao);

}
