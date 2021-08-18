package com.pedeagua.service;

import java.util.List;

import com.pedeagua.entity.Agente;
import com.pedeagua.entity.AgenteUsuario;
import com.pedeagua.entity.Pedido;

public interface IAgenteService {
     List<Agente> getAllAgentes();
     Agente getAgenteById(int cod_agente);
     Agente getAgenteByEmail(String email);
     long addAgente(Agente agente);
     long addAgenteUsuario(AgenteUsuario agenteUsuario);
     boolean updateAgenteUsuario(AgenteUsuario agenteUsuario);
     void updateAgente(Agente agente);
     void deleteAgente(int cod_agente);
     boolean agenteExists(String email);
     boolean agenteUsuarioExists(String email, String senha);
}
