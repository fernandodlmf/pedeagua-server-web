package com.pedeagua.entity;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name="pedido_itens")
public class PedidoItens implements Serializable { 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="COD_PEDIDO_ITENS")
    private int cod_pedido_itens;  
	
	@Basic(optional = false)
	@Column(name="MOMENTO", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(value=TemporalType.TIMESTAMP)    
	private Date momento;
	
	@Column(name = "NOME_PRODUTO", nullable = true, length = 40)
	private String nome_produto;
	
	@Column(name = "COD_PRODUTO")
	private int cod_produto;
	
	@Column(name = "PRECO", nullable = true, length = 16)
	private String preco;
	
	@Column(name = "QUANTIDADE", nullable = true, length = 16)
	private String quantidade;

	@Column(name = "CANCELADO", nullable = true, length = 3)
	private String cancelado;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="COD_PEDIDO")
	private Pedido pedido;

	public int getCod_pedido_itens() {
		return cod_pedido_itens;
	}

	public void setCod_pedido_itens(int cod_pedido_itens) {
		this.cod_pedido_itens = cod_pedido_itens;
	}

	public int getCod_produto() {
		return cod_produto;
	}

	public void setCod_produto(int cod_produto) {
		this.cod_produto = cod_produto;
	}

	public Date getMomento() {
		return momento;
	}

	public void setMomento(Date momento) {
		this.momento = momento;
	}

	public String getNome_produto() {
		return nome_produto;
	}

	public void setNome_produto(String nome_produto) {
		this.nome_produto = nome_produto;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getCancelado() {
		return cancelado;
	}

	public void setCancelado(String cancelado) {
		this.cancelado = cancelado;
	}
	
	@JsonBackReference
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
		
} 