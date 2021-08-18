package com.pedeagua.entity;

import java.util.List;

public class Vendedor {
	private int codigo;
	private String nome;
	private String bairro;
	private String rua;
	private int aceitaCartão;
    private int aceitaDinheiro;
    private int qualificacao;
    private String perfil_url;
	private List<EmpresaProduto> listaProdutos;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
		
	public int getAceitaCartão() {
		return aceitaCartão;
	}
	public void setAceitaCartão(int aceitaCartão) {
		this.aceitaCartão = aceitaCartão;
	}
	public int getAceitaDinheiro() {
		return aceitaDinheiro;
	}
	public void setAceitaDinheiro(int aceitaDinheiro) {
		this.aceitaDinheiro = aceitaDinheiro;
	}
	public int getQualificacao() {
		return qualificacao;
	}
	public void setQualificacao(int qualificacao) {
		this.qualificacao = qualificacao;
	}
	public String getPerfil_url() {
		return perfil_url;
	}
	public void setPerfil_url(String perfil_url) {
		this.perfil_url = perfil_url;
	}
	public List<EmpresaProduto> getListaProdutos() {
		return listaProdutos;
	}
	public void setListaProdutos(List<EmpresaProduto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
	@Override
	public String toString() {
		return "Vendedor [codigo=" + codigo + ", nome=" + nome + ", bairro=" + bairro + ", rua=" + rua
				+ ", aceitaCartão=" + aceitaCartão + ", aceitaDinheiro=" + aceitaDinheiro + ", qualificacao="
				+ qualificacao + ", perfil_url=" + perfil_url + ", listaProdutos=" + listaProdutos + "]";
	}
	
	
	

}
