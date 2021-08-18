package com.pedeagua.dao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Filter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.pedeagua.entity.Empresa;
import com.pedeagua.entity.EmpresaProduto;
import com.pedeagua.entity.EmpresaUsuario;
import com.pedeagua.entity.Produto;
import com.pedeagua.entity.Vendedor;
@Transactional
@Repository
public class EmpresaDAO implements IEmpresaDAO {
	@Autowired
	private HibernateTemplate  hibernateTemplate;
	@Override
	public Empresa getEmpresaById(int cod_empresa) {
		return hibernateTemplate.get(Empresa.class, cod_empresa);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Empresa> getAllEmpresas() {
		String hql = "FROM Empresa as p ORDER BY p.cod_empresa";
		return (List<Empresa>) hibernateTemplate.find(hql);
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Vendedor> getEmpresasPorCoordenadas(String latitude, String longitude, JSONArray codigos) {
	
		
		String hql = "FROM Empresa as p WHERE (acos( cos( radians(?) ) * cos( radians(p.latitude) ) * cos( radians( p.longitude ) - radians(?) ) + sin( radians(?) ) * sin( radians( p.latitude ) ) )) * 6371 <= 20";
		List<Empresa> listaEmpresas = (List<Empresa>) hibernateTemplate.find(hql, Double.parseDouble(latitude), Double.parseDouble(longitude), Double.parseDouble(latitude));
							
	
		return retornaVendedores(listaEmpresas, codigos);
	}	
	
	@Override
	public boolean addEmpresa(Empresa empresa) {
		hibernateTemplate.save(empresa);
		return false;
	}
	
	@Override
	public void updateEmpresa(Empresa empresa) {
		Empresa p = getEmpresaById(empresa.getCod_empresa());
		hibernateTemplate.update(p);
	}
	
	@Override
	public void deleteEmpresa(int cod_empresa) {
		hibernateTemplate.delete(getEmpresaById(cod_empresa));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean empresaExists(String email) {
		String hql = "FROM Empresa as p WHERE p.email = ?";
		List<Empresa> empresas = (List<Empresa>) hibernateTemplate.find(hql, email);
		return empresas.size() > 0 ? true : false;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean empresaUsuarioExists(String email, String senha) {
		String hql = "FROM EmpresaUsuario as a WHERE a.email = ? AND a.senha = ?";
		List<Empresa> empresas = (List<Empresa>) hibernateTemplate.find(hql, email, senha);
		return empresas.size() > 0 ? true : false;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Empresa getEmpresaByEmail(String email) {
		String hql = "FROM Empresa as p WHERE p.email = ?";	
		List<Empresa> empresas = (List<Empresa>) hibernateTemplate.find(hql, email);
		return empresas.get(0);
	}
	
	@Override
	public boolean addEmpresaUsuario(EmpresaUsuario empresaUsuario) {
		hibernateTemplate.save(empresaUsuario);
		return false;
	}
	
	@Override
	public boolean updateEmpresaUsuario(EmpresaUsuario empresaUsuario) {
		
		EmpresaUsuario ag = getEmpresaUsuarioByEmail(empresaUsuario.getEmail());
		if((empresaUsuario.getSenha() != null) && (empresaUsuario.getSenha_anterior() != null)) {
			if(empresaUsuario.getSenha().equals(ag.getSenha())) {
				empresaUsuario.setSenha(empresaUsuario.getSenha_anterior());
				empresaUsuario.setSenha_anterior(ag.getSenha());			
				empresaUsuario.setCod_empresa_usuario(ag.getCod_empresa_usuario());
				hibernateTemplate.clear();
				hibernateTemplate.update(empresaUsuario);
				return true;
			} else {
				return false;
			}
		} else if((empresaUsuario.getSenha() != null) && (empresaUsuario.getSenha_anterior() == null) ) {
			empresaUsuario.setSenha(empresaUsuario.getSenha());		
			empresaUsuario.setCod_empresa_usuario(ag.getCod_empresa_usuario());
			hibernateTemplate.clear();
			hibernateTemplate.update(empresaUsuario);	
			return true;
		} else {
			empresaUsuario.setSenha(ag.getSenha());
			empresaUsuario.setSenha_anterior(ag.getSenha_anterior());			
			empresaUsuario.setCod_empresa_usuario(ag.getCod_empresa_usuario());
			hibernateTemplate.clear();
			hibernateTemplate.update(empresaUsuario);
			return true;
		}	
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public EmpresaUsuario getEmpresaUsuarioByEmail(String email) {
		String hql = "FROM EmpresaUsuario as p WHERE p.email = ?";	
		List<EmpresaUsuario> empresas = (List<EmpresaUsuario>) hibernateTemplate.find(hql, email);
		return empresas.get(0);
	}
	
	@SuppressWarnings("unchecked")
	private List<Vendedor> retornaVendedores(List<Empresa> lista , JSONArray codigos) {
		int cont_empresa = 0;
		List<Vendedor> listaVendedor = new ArrayList<>();
		List<EmpresaProduto> listaProdutos = new ArrayList<>();
		
//		Query q = hibernateTemplate.getSessionFactory().
//				getCurrentSession().createQuery("FROM EmpresaProduto"
//						+ " WHERE cod_empresa = :cod_empresa and cod_produto"
//						+ " IN (select cod_produto FROM EmpresaProduto WHERE cod_empresa = :cod_empresa and preco > 0 and estoque > 0 and cod_produto in (:cods))");		
//		q.setInteger("cod_empresa", cont_empresa);
		//q.setParameterList("cods", cods);	
//		Integer[]cods = new Integer[codigos.length()];
		//List<EmpresaProduto> produtos = q.list();
//		
//		for(int j = 0; j < codigos.length(); j++){
//			try {
//				cods[j] = codigos.getInt(j);
//				
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}		
//		}
		
	
		
		for(int i = 0; i < lista.size(); i++){		
			cont_empresa = lista.get(i).getCod_empresa();
								
				
			Vendedor v = new Vendedor();				
			v.setCodigo(cont_empresa);
			v.setBairro(lista.get(i).getBairro());
			v.setRua(lista.get(i).getEndereco());
			v.setNome(lista.get(i).getNome_fantasia());	
			v.setPerfil_url(lista.get(i).getPerfil_url());
			v.setAceitaCartão(lista.get(i).getAceita_cartao());
			v.setAceitaDinheiro(lista.get(i).getAceita_dinheiro());		
			listaProdutos = lista.get(i).getProdutos();
			v.setListaProdutos(listaProdutos);
			v.setQualificacao(lista.get(i).getQualificacao());
			listaVendedor.add(v);
												
		}
			
		return listaVendedor;
		
	}
}
