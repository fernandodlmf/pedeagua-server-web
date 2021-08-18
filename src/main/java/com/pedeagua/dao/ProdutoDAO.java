package com.pedeagua.dao;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.pedeagua.entity.EmpresaProduto;
import com.pedeagua.entity.Produto;
@Transactional
@Repository
public class ProdutoDAO implements IProdutoDAO {
	@Autowired
	private HibernateTemplate  hibernateTemplate;
	@Override
	public Produto getProdutoById(int cod_produto) {
		return hibernateTemplate.get(Produto.class, cod_produto);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> getAllProdutos() {
		String hql = "FROM Produto as p ORDER BY p.cod_produto";
		return (List<Produto>) hibernateTemplate.find(hql);
	}	
	@Override
	public boolean addProduto(Produto produto) {
		hibernateTemplate.save(produto);
		return true;
	}
	@Override
	public void updateProduto(Produto produto) {
		Produto p = getProdutoById(produto.getCod_produto());
		p.setName(produto.getName());
		p.setPreco(produto.getPreco());
		hibernateTemplate.update(p);
	}
	@Override
	public void deleteProduto(int cod_produto) {
		hibernateTemplate.delete(getProdutoById(cod_produto));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean produtoExists(String name, int cod_produto) {
		String hql = "FROM Produto as p WHERE p.name = ? and p.cod_produto = ?";
		List<Produto> produtos = (List<Produto>) hibernateTemplate.find(hql, name, cod_produto);
		return produtos.size() > 0 ? true : false;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> getAllProdutosPorEmpresa(int cod_empresa) {
		String hql = "FROM EmpresaProduto as ep WHERE ep.empresa.cod_empresa = ? ORDER BY ep.cod_produto";
		return (List<Produto>) hibernateTemplate.find(hql, cod_empresa);
	}
	@Override
	public boolean addProdutoPorEmpresa(EmpresaProduto produto) {
		hibernateTemplate.save(produto);
		return true;
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean produtoPorEmpresaExists(int cod_empresa, int cod_produto) {
		String hql = "FROM EmpresaProduto as p WHERE p.empresa.cod_empresa = ? and p.cod_produto = ?";
		List<Produto> produtos = (List<Produto>) hibernateTemplate.find(hql, cod_empresa, cod_produto);
		return produtos.size() > 0 ? true : false;
	}
	@Override
	public boolean updateProdutoPorEmpresa(EmpresaProduto produto) {
		hibernateTemplate.update(produto);
		return true;
	}
}
