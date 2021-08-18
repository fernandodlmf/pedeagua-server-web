package com.pedeagua.entity;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="plano")
public class Plano implements Serializable { 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="COD_PLANO")
    private int cod_plano;  
	
	@Basic(optional = false)
	@Column(name="MOMENTO", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(value=TemporalType.TIMESTAMP)    
	private Date momento;
	
	@Column(name = "NOME", nullable = true, length = 40)
	private String nome;
	
	@Column(name = "PRECO", nullable = true, length = 15)
	private String preco;
	
	@Column(name = "IMAGE_URL", nullable = true, length = 200)
	private String image_url;
	
	@Column(name = "HASH", nullable = true, length = 100)
	private String hash;

	@Column(name = "ATIVO", nullable = true)
	private String ativo;

	public int getCod_plano() {
		return cod_plano;
	}

	public void setCod_plano(int cod_plano) {
		this.cod_plano = cod_plano;
	}

	public Date getMomento() {
		return momento;
	}

	public void setMomento(Date momento) {
		this.momento = momento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
		
} 