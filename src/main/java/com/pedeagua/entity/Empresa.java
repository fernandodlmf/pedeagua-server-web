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

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="empresa")
public class Empresa implements Serializable { 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="COD_EMPRESA")
    private int cod_empresa;
	
	@Column(name="COD_PLANO")	
	private String cod_plano;
	
	@Basic(optional = false)
	@Column(name="MOMENTO", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(value=TemporalType.TIMESTAMP)
    private Date momento;	
	
	@Column(name="NOME_FANTASIA")
    private String nome_fantasia;
	
	@Column(name="RESPONSAVEL_LEGAL")
	private String responsavel_legal;
	
	@Column(name="CNPJ")
	private String cnpj;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="TELEFONE")	
	private String telefone;
	
	@Column(name = "ENDERECO", nullable = true, length = 100)
	private String endereco;
	
	@Column(name = "BAIRRO", nullable = true, length = 40)
	private String bairro;
	
	@Column(name = "NUMERO", nullable = true, length = 10)
	private String numero;
	
	@Column(name = "COMPLEMENTO", nullable = true, length = 40)
	private String complemento;
	
	@Column(name = "CIDADE", nullable = true, length = 40)
	private String cidade;
	
	@Column(name = "UF", nullable = true, length = 2)
	private String uf;
	
	@Column(name = "CEP", nullable = true, length = 15)
	private String cep;
	
	@Column(name = "LATITUDE", nullable = true, length = 100)
	private double latitude;
	
	@Column(name = "LONGITUDE", nullable = true, length = 100)
	private double longitude;
	
	@Column(name="ATIVA")	
	private String ativa;

	@Column(name="BLOQUEADA")	
	private String bloqueada;	
	
	@Column(name="ACEITA_CARTAO")	
	private int aceita_cartao;
	
	@Column(name="ACEITA_DINHEIRO")	
	private int aceita_dinheiro;
	
	@Column(name="PERFIL_URL")	
	private String perfil_url;
	
	@Column(name = "QUALIFICACAO")
	private int qualificacao;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="COD_EMPRESA_USUARIO")
	private EmpresaUsuario empresaUsuario;
	    
	@OneToMany(targetEntity = EmpresaProduto.class, mappedBy = "empresa", 
			cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Where(clause = "estoque > 0 and preco > 0")
	private List<EmpresaProduto> produtos = new ArrayList<EmpresaProduto>();

	public int getCod_empresa() {
		return cod_empresa;
	}

	public void setCod_empresa(int cod_empresa) {
		this.cod_empresa = cod_empresa;
	}

	public String getCod_plano() {
		return cod_plano;
	}

	public void setCod_plano(String cod_plano) {
		this.cod_plano = cod_plano;
	}

	public Date getMomento() {
		return momento;
	}

	public void setMomento(Date momento) {
		this.momento = momento;
	}

	public String getNome_fantasia() {
		return nome_fantasia;
	}

	public void setNome_fantasia(String nome_fantasia) {
		this.nome_fantasia = nome_fantasia;
	}

	public String getResponsavel_legal() {
		return responsavel_legal;
	}

	public void setResponsavel_legal(String responsavel_legal) {
		this.responsavel_legal = responsavel_legal;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getAtiva() {
		return ativa;
	}

	public void setAtiva(String ativa) {
		this.ativa = ativa;
	}

	public String getBloqueada() {
		return bloqueada;
	}

	public void setBloqueada(String bloqueada) {
		this.bloqueada = bloqueada;
	}

	public int getAceita_cartao() {
		return aceita_cartao;
	}

	public void setAceita_cartao(int aceita_cartao) {
		this.aceita_cartao = aceita_cartao;
	}

	public int getAceita_dinheiro() {
		return aceita_dinheiro;
	}

	public void setAceita_dinheiro(int aceita_dinheiro) {
		this.aceita_dinheiro = aceita_dinheiro;
	}

	@JsonBackReference
	public EmpresaUsuario getEmpresaUsuario() {
		return empresaUsuario;
	}

	public void setEmpresaUsuario(EmpresaUsuario empresaUsuario) {
		this.empresaUsuario = empresaUsuario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@JsonManagedReference
	public List<EmpresaProduto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<EmpresaProduto> produtos) {
		this.produtos = produtos;
	}	

	public String getPerfil_url() {
		return perfil_url;
	}

	public void setPerfil_url(String perfil_url) {
		this.perfil_url = perfil_url;
	}

	public int getQualificacao() {
		return qualificacao;
	}

	public void setQualificacao(int qualificacao) {
		this.qualificacao = qualificacao;
	}

	@Override
	public String toString() {
		return "Empresa [cod_empresa=" + cod_empresa + ", cod_plano=" + cod_plano + ", momento=" + momento
				+ ", nome_fantasia=" + nome_fantasia + ", responsavel_legal=" + responsavel_legal + ", cnpj=" + cnpj
				+ ", email=" + email + ", telefone=" + telefone + ", endereco=" + endereco + ", bairro=" + bairro
				+ ", numero=" + numero + ", complemento=" + complemento + ", cidade=" + cidade + ", uf=" + uf + ", cep="
				+ cep + ", latitude=" + latitude + ", longitude=" + longitude + ", ativa=" + ativa + ", bloqueada="
				+ bloqueada + ", aceita_cartao=" + aceita_cartao + ", aceita_dinheiro=" + aceita_dinheiro
				+ ", perfil_url=" + perfil_url + ", qualificacao=" + qualificacao + ", empresaUsuario=" + empresaUsuario
				+ ", produtos=" + produtos + "]";
	}

	
	
	
	
	

} 