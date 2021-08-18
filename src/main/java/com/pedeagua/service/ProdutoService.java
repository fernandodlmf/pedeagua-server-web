package com.pedeagua.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedeagua.dao.IProdutoDAO;
import com.pedeagua.entity.EmpresaProduto;
import com.pedeagua.entity.Produto;
@Service
public class ProdutoService implements IProdutoService {
	@Autowired
	private IProdutoDAO produtoDAO;
	@Override
	public Produto getProdutoById(int cod_produto) {
		Produto obj = produtoDAO.getProdutoById(cod_produto);
		return obj;
	}	
	@Override
	public List<Produto> getAllProdutos(){
		return produtoDAO.getAllProdutos();
	}
	@Override
	public synchronized boolean addProduto(Produto produto){
       if (produtoDAO.produtoExists(produto.getName(), produto.getCod_produto())) {
    	   return false;
       } else {
    	   produtoDAO.addProduto(produto);
    	   return true;
       }
	}
	@Override
	public void updateProduto(Produto produto) {
		produtoDAO.updateProduto(produto);
	}
	@Override
	public void deleteProduto(int cod_produto) {
		produtoDAO.deleteProduto(cod_produto);
	}
	@Override
	public List<Produto> getAllProdutosPorEmpresa(int cod_empresa) {
		return produtoDAO.getAllProdutosPorEmpresa(cod_empresa);
	}
	
	@Override
	public boolean addProdutoPorEmpresa(EmpresaProduto produto) {
		if(produtoDAO.produtoPorEmpresaExists(produto.getEmpresa().getCod_empresa(), produto.getCod_produto())) {
			boolean status = produtoDAO.updateProdutoPorEmpresa(produto);//updateProdutoPorEmpresa(produto);		
			return status;
        } else{
    	   produtoDAO.addProdutoPorEmpresa(produto);
    	   return true;
        }
	}
	@Override
	public boolean updateProdutoPorEmpresa(EmpresaProduto produto) {
		// TODO Auto-generated method stub
		return produtoDAO.updateProdutoPorEmpresa(produto);
	}
}
