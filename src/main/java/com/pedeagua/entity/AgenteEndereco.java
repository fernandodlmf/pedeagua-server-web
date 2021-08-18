package com.pedeagua.entity;



import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "AGENTE_ENDERECO") 
public class AgenteEndereco {
	
	@Id
	@GeneratedValue	
	@Column(name = "cod_agente_endereco")
	private int cod_agente_endereco;
	
	@Basic(optional = false)
	@Column(name="MOMENTO", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(value=TemporalType.TIMESTAMP)
    private Date momento;
	
	@Column(name = "RUA", nullable = true, length = 100)
	private String rua;
	
	@Column(name = "ENDERECO", nullable = true, length = 200)
	private String endereco;
	
	@Column(name = "BAIRRO", nullable = true, length = 40)
	private String bairro;
	
	@Column(name = "NUMERO", nullable = true, length = 10)
	private String numero;
	
	@Column(name = "COMPLEMENTO", nullable = true, length = 40)
	private String complemento;
	
	@Column(name = "REFERENCIA", nullable = true, length = 200)
	private String referencia;
	
	@Column(name = "TIPO", nullable = true, length = 40)
	private String tipo;
	
	@Column(name = "CIDADE", nullable = true, length = 40)
	private String cidade;
	
	@Column(name = "UF", nullable = true, length = 40)
	private String uf;
	
	@Column(name = "CEP", nullable = true, length = 15)
	private String cep;
	
	@Column(name = "LATITUDE", nullable = true, length = 100)
	private double latitude;
	
	@Column(name = "LONGITUDE", nullable = true, length = 100)
	private double longitude;
	
	@Column(name = "EM_UPDATE", nullable = true, length = 2)
	private int em_update;
	
	@Column(name="EXCLUIDO")	
	private int excluido;
	
	@Column(name = "PADRAO", nullable = true, length = 2)
	private int padrao;
		
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="COD_AGENTE")
	private Agente agente;
	
	public int getCod_agente_endereco() {
		return cod_agente_endereco;
	}

	public void setCod_agente_endereco(int cod_agente_endereco) {
		this.cod_agente_endereco = cod_agente_endereco;
	}

	public Date getMomento() {
		return momento;
	}

	public void setMomento(Date momento) {
		this.momento = momento;
	}	

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
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

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	
	@JsonBackReference
	public Agente getAgente() {
		return agente;
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

	public void setAgente(Agente agente) {
		this.agente = agente;
	}

	public int getEm_update() {
		return em_update;
	}

	public void setEm_update(int em_update) {
		this.em_update = em_update;
	}

	public int getExcluido() {
		return excluido;
	}

	public void setExcluido(int excluido) {
		this.excluido = excluido;
	}

	public int getPadrao() {
		return padrao;
	}

	public void setPadrao(int padrao) {
		this.padrao = padrao;
	}

	@Override
	public String toString() {
		return "AgenteEndereco [cod_agente_endereco=" + cod_agente_endereco + ", momento=" + momento + ", rua=" + rua
				+ ", endereco=" + endereco + ", bairro=" + bairro + ", numero=" + numero + ", complemento="
				+ complemento + ", referencia=" + referencia + ", tipo=" + tipo + ", cidade=" + cidade + ", uf=" + uf
				+ ", cep=" + cep + ", latitude=" + latitude + ", longitude=" + longitude + ", em_update=" + em_update
				+ ", excluido=" + excluido + ", padrao=" + padrao + ", agente=" + agente + "]";
	}

	

	

	
	
}
