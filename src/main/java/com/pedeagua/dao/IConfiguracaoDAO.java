package com.pedeagua.dao;
import java.util.List;

import com.pedeagua.entity.Configuracao;
public interface IConfiguracaoDAO {
    List<Configuracao> getAllConfiguracoes();
    List<Configuracao> getAllConfiguracoesPorEmpresa(int cod_empresa);
    List<Configuracao> getAllConfiguracoesPorCliente(int cod_cliente);
    long addConfiguracaoPorEmpresa(Configuracao configuracao);
    long addConfiguracaoPorCliente(Configuracao configuracao);
    boolean updateConfiguracao(Configuracao configuracao);
    boolean ConfiguracaoExists(int cod_configuracao, int cod_agente);
    Configuracao getConfiguracaoById(int cod_configuracao);   
}
 