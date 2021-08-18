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

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="configuracao")
public class Configuracao implements Serializable { 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="COD_CONFIGURACAO")
    private int cod_configuracao;  
	
	@Basic(optional = false)
	@Column(name="MOMENTO", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(value=TemporalType.TIMESTAMP)    
	private Date momento;
	
	@Column(name = "NOME", nullable = true, length = 40)
	private String nome;
	
	@Column(name = "ATIVA")
	private int ativa;
	
	@Column(name = "EXCLUIDA", nullable = true, length = 16)
	private int excluida;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="COD_AGENTE")
	private Agente agente;


	public int getCod_configuracao() {
		return cod_configuracao;
	}
	public void setCod_configuracao(int cod_configuracao) {
		this.cod_configuracao = cod_configuracao;
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

	public int getAtiva() {
		return ativa;
	}

	public void setAtiva(int ativa) {
		this.ativa = ativa;
	}
	
	public int getExcluida() {
		return excluida;
	}
	
	public void setExcluida(int excluida) {
		this.excluida = excluida;
	}

	@JsonBackReference
	public Agente getAgente() {
		return agente;
	}

	public void setAgente(Agente agente) {
		this.agente = agente;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
		
} 