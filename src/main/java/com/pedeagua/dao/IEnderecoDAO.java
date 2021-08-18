package com.pedeagua.dao;
import java.util.List;

import com.pedeagua.entity.AgenteEndereco;
public interface IEnderecoDAO {
    List<AgenteEndereco> getAllEnderecos();
    List<AgenteEndereco> getAllEnderecosPorEmpresa(int cod_empresa);
    List<AgenteEndereco> getAllEnderecosPorAgente(int cod_agente);
    long addEnderecoPorEmpresa(AgenteEndereco agenteEndereco);
    long addEnderecoPorAgente(AgenteEndereco agenteEndereco);
    boolean updateEndereco(AgenteEndereco agenteEndereco);
    boolean EnderecoExists(int cod_endereco, int cod_agente);
    AgenteEndereco getEnderecoById(int cod_endereco);
    void deletePedido(int cod_endereco); 
    boolean removeEnderecosPadrao(int cod_agente);
}
 