package com.pedeagua.service;

import java.util.List;

import com.pedeagua.entity.AgenteEndereco;
import com.pedeagua.entity.Configuracao;

public interface IEnderecoService {
	List<AgenteEndereco> getAllEnderecos();
	List<AgenteEndereco> getAllEnderecosPorEmpresa(int cod_empresa);
	List<AgenteEndereco> getAllEnderecosPorCliente(int cod_cliente);
    long addEnderecoPorEmpresa(AgenteEndereco agenteEndereco);
    long addEnderecosPorCliente(AgenteEndereco agenteEndereco);
    boolean updateEndereco(AgenteEndereco agenteEndereco);    
    AgenteEndereco getEnderecoById(int cod_endereco);   
}
