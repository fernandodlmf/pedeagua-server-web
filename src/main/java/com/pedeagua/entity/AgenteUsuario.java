package com.pedeagua.entity;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="agente_usuario")
public class AgenteUsuario implements Serializable { 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="COD_AGENTE_USUARIO")
    private int cod_agente_usuario;
	
	@Basic(optional = false)
	@Column(name="MOMENTO", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(value=TemporalType.TIMESTAMP)
    private Date momento;		
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="SENHA_ANTERIOR")
	private String senha_anterior;
		
	@Column(name="SENHA")
	private String senha;	
	
	@Column(name="BLOQUEADO")	
	private String bloqueado;
	
	@OneToOne(targetEntity = Agente.class, mappedBy = "agenteUsuario", 
			cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Agente agente;

	public int getCod_agente_usuario() {
		return cod_agente_usuario;
	}

	public void setCod_agente_usuario(int cod_agente_usuario) {
		this.cod_agente_usuario = cod_agente_usuario;
	}

	public Date getMomento() {
		return momento;
	}

	public void setMomento(Date momento) {
		this.momento = momento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenha_anterior() {
		return senha_anterior;
	}

	public void setSenha_anterior(String senha_anterior) {
		this.senha_anterior = senha_anterior;
	}

	public String getBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(String bloqueado) {
		this.bloqueado = bloqueado;
	}
	
	@JsonManagedReference
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