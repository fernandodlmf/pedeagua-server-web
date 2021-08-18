package com.pedeagua.entity;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import com.fasterxml.jackson.annotation.JsonBackReference;

import aj.org.objectweb.asm.Type;
@Entity
@Table(name="empresa_produto")
public class EmpresaProduto implements Serializable { 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="COD_EMPRESA_PRODUTO")
    private int cod_empresa_produto;  
	
	@Basic(optional = false)
	@Column(name="MOMENTO", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(value=TemporalType.TIMESTAMP)    
	private Date momento;
	
	@Column(name="COD_PRODUTO")
    private int cod_produto; 

	@Column(name="ESTOQUE")
    private int estoque; 
	
	@Column(name = "PRECO", nullable = true, length = 15)
	private String preco;

	@Column(name = "ATIVO", nullable = true)
	private String ativo;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="COD_EMPRESA")
	private Empresa empresa;
	

	public int getCod_empresa_produto() {
		return cod_empresa_produto;
	}

	public void setCod_empresa_produto(int cod_empresa_produto) {
		this.cod_empresa_produto = cod_empresa_produto;
	}

	public Date getMomento() {
		return momento;
	}

	public void setMomento(Date momento) {
		this.momento = momento;
	}	

	public int getCod_produto() {
		return cod_produto;
	}

	public void setCod_produto(int cod_produto) {
		this.cod_produto = cod_produto;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}	
	
	@JsonBackReference
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
		
} 