package com.pedeagua.service;

import java.util.List;

import com.pedeagua.entity.EmpresaProduto;
import com.pedeagua.entity.Produto;

public interface IProdutoService {
	 List<Produto> getAllProdutosPorEmpresa(int cod_empresa);
	 boolean addProdutoPorEmpresa(EmpresaProduto produto);
	 boolean updateProdutoPorEmpresa(EmpresaProduto produto);
     List<Produto> getAllProdutos();     
     Produto getProdutoById(int cod_produto);
     boolean addProduto(Produto produto);
     void updateProduto(Produto produto);
     void deleteProduto(int cod_produto);
}
