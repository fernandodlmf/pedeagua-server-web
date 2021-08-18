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
@Table(name="empresa_usuario")
public class EmpresaUsuario implements Serializable { 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="COD_EMPRESA_USUARIO")
    private int cod_empresa_usuario;
	
	@Basic(optional = false)
	@Column(name="MOMENTO", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(value=TemporalType.TIMESTAMP)
    private Date momento;		
	
	@Column(name="EMAIL")
	private String email;
		
	@Column(name="SENHA")
	private String senha;
	
	@Column(name="SENHA_ANTERIOR")
	private String senha_anterior;
	
	@Column(name="BLOQUEADO")	
	private String bloqueado;
	
	@OneToOne(targetEntity = Empresa.class, mappedBy = "empresaUsuario",
			cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Empresa empresa;

	public int getCod_empresa_usuario() {
		return cod_empresa_usuario;
	}

	public void setCod_empresa_usuario(int cod_empresa_usuario) {
		this.cod_empresa_usuario = cod_empresa_usuario;
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