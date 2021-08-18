package com.pedeagua.service;

import java.util.List;

import org.json.JSONArray;

import com.pedeagua.entity.Empresa;
import com.pedeagua.entity.EmpresaUsuario;
import com.pedeagua.entity.Vendedor;

public interface IEmpresaService {
     List<Empresa> getAllEmpresas();
     List<Vendedor> getEmpresasPorCoordenadas(String latitude, String longitude, JSONArray codigos);
     Empresa getEmpresaById(int cod_empresa);
     Empresa getEmpresaByEmail(String email);
     boolean addEmpresa(Empresa empresa);
     boolean addEmpresaUsuario(EmpresaUsuario empresaUsuario);
     boolean updateEmpresaUsuario(EmpresaUsuario empresaUsuario);
     void updateEmpresa(Empresa empresa);
     void deleteEmpresa(int cod_empresa);
     boolean empresaUsuarioExists(String email, String senha);
}
