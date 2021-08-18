package com.pedeagua.dao;
import java.util.List;

import com.pedeagua.entity.EmpresaProduto;
import com.pedeagua.entity.Produto;
public interface IProdutoDAO {
	List<Produto> getAllProdutosPorEmpresa(int cod_empresa);
	boolean addProdutoPorEmpresa(EmpresaProduto produto);
	boolean updateProdutoPorEmpresa(EmpresaProduto produto);
	boolean produtoPorEmpresaExists(int cod_empresa, int cod_produto);
    List<Produto> getAllProdutos();    
    Produto getProdutoById(int cod_produto);
    boolean addProduto(Produto produto);    
    void updateProduto(Produto produto);    
    void deleteProduto(int cod_produto);
    boolean produtoExists(String name, int cod_produto);
}
 