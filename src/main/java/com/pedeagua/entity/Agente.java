package com.pedeagua.entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="agente")
public class Agente implements Serializable { 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="COD_AGENTE")
    private int cod_agente;
	
	@Basic(optional = false)
	@Column(name="MOMENTO", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(value=TemporalType.TIMESTAMP)
    private Date momento;	
	
	@Column(name="NOME")
    private String name;
	
	@Column(name="SOBRENOME")
    private String sobrenome;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="TELEFONE")	
	private String telefone;

	@Column(name="DATA_NASCIMENTO")	
	private Date data_nascimento;
	
	@Column(name="SEXO")	
	private String sexo;
	
	@Column(name="BLOQUEADO")	
	private String bloqueado;
	
	@Column(name="EXCLUIDO")	
	private String excluido;
	
	@Column(name="PERFIL_URL")	
	private String perfil_url;	
	
	@OneToMany(targetEntity = AgenteEndereco.class, mappedBy = "agente", 
			cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@Where(clause = "excluido = 0")
	private List<AgenteEndereco> enderecos = new ArrayList<AgenteEndereco>();
	
	@OneToMany
	@JoinColumn(name="COD_CLIENTE", referencedColumnName="COD_AGENTE")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Pedido> pedidos = new ArrayList<Pedido>();
	
	@OneToMany(targetEntity = Configuracao.class, mappedBy = "agente", 
			cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Configuracao> configuracoes = new ArrayList<Configuracao>();
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="COD_AGENTE_USUARIO")
	private AgenteUsuario agenteUsuario;

	public int getCod_agente() {
		return cod_agente;
	}

	public void setCod_agente(int cod_agente) {
		this.cod_agente = cod_agente;
	}

	public Date getMomento() {
		return momento;
	}

	public void setMomento(Date momento) {
		this.momento = momento;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(Date data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(String bloqueado) {
		this.bloqueado = bloqueado;
	}	

	public String getExcluido() {
		return excluido;
	}

	public void setExcluido(String excluido) {
		this.excluido = excluido;
	}		

	public String getPerfil_url() {
		return perfil_url;
	}

	public void setPerfil_url(String perfil_url) {
		this.perfil_url = perfil_url;
	}

	@JsonManagedReference
	public List<AgenteEndereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<AgenteEndereco> enderecos) {
		this.enderecos = enderecos;
	}
	
	@JsonBackReference
	public AgenteUsuario getAgenteUsuario() {
		return agenteUsuario;
	}

	public void setAgenteUsuario(AgenteUsuario agenteUsuario) {
		this.agenteUsuario = agenteUsuario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	//@JsonManagedReference
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@JsonManagedReference
	public List<Configuracao> getConfiguracoes() {
		return configuracoes;
	}

	public void setConfiguracoes(List<Configuracao> configuracoes) {
		this.configuracoes = configuracoes;
	}
	
	
	
	
		
} 