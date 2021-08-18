package com.pedeagua.entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Calendar;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="pedido")
public class Pedido implements Serializable { 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="COD_PEDIDO")
    private int cod_pedido;  
	
	@Column(name="MOMENTO", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(value=TemporalType.TIMESTAMP)    
	private Calendar momento;
	
//	@Column(name = "COD_CLIENTE")
//	private int cod_cliente;
	
	@Column(name="COD_EMPRESA")
    private int cod_empresa; 
	
	@Column(name = "NOME_CLIENTE", nullable = true, length = 60)
	private String nome_cliente;
	
	@Column(name = "NOME_VENDEDOR", nullable = true, length = 60)
	private String nome_vendedor;
	
	@Column(name = "DESCRICAO", nullable = true, length = 150)
	private String descricao;
	
	@Column(name = "DATA_PEDIDO", nullable = true)
	@Temporal(TemporalType.DATE)
	private Calendar data_pedido;
	
	@Column(name = "DATA_ENTREGA", nullable = true)
	@Temporal(TemporalType.DATE)
	private Calendar data_entrega;
		
	@Column(name = "HORA_PEDIDO", nullable = true)
	@Temporal(TemporalType.TIME)
	private Date hora_pedido;
	
	@Column(name = "HORA_ENTREGA", nullable = true)
	@Temporal(TemporalType.TIME)
	private Date hora_entrega;
	
	@Column(name = "STATUS", nullable = true, length = 10)
	private String status;

	@Column(name = "VALOR_TOTAL", nullable = true, length = 16)
	private String valor_total;
	
	@Column(name = "TROCO", nullable = true, length = 16)
	private String troco;
	
	@Column(name = "ENDERECO", nullable = true, length = 100)
	private String endereco;
	
	@Column(name = "CANCELADA", nullable = true, length = 3)
	private String cancelada;
	
	@Column(name = "PAGAMENTO", nullable = true, length = 15)
	private String pagamento;
	
	@Column(name = "PAGAMENTO_TIPO", nullable = true)
	private int pagamento_tipo;
	
	@Column(name="PERFIL_URL")	
	private String perfil_url;
	
	@OneToMany(targetEntity = PedidoItens.class, mappedBy = "pedido", 
			cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<PedidoItens> itens = new ArrayList<PedidoItens>();
	
	@Column(name="COD_CLIENTE")
	private int cod_agente;
	
//	@ManyToOne
//	@JsonBackReference
//	@JoinColumn(name="COD_CLIENTE")
//	private Agente agente;

	public int getCod_pedido() {
		return cod_pedido;
	}

	public void setCod_pedido(int cod_pedido) {
		this.cod_pedido = cod_pedido;
	}

	public Calendar getMomento() {
		return momento;
	}


	public void setMomento(Calendar momento) {
		this.momento = momento;
	}
	
	

//	public int getCod_cliente() {
//		return cod_cliente;
//	}
//
//	public void setCod_cliente(int cod_cliente) {
//		this.cod_cliente = cod_cliente;
//	}

	public String getPagamento() {
		return pagamento;
	}

	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}

	public int getPagamento_tipo() {
		return pagamento_tipo;
	}

	public void setPagamento_tipo(int pagamento_tipo) {
		this.pagamento_tipo = pagamento_tipo;
	}

	public int getCod_empresa() {
		return cod_empresa;
	}

	public void setCod_empresa(int cod_empresa) {
		this.cod_empresa = cod_empresa;
	}

	public String getNome_cliente() {
		return nome_cliente;
	}

	public void setNome_cliente(String nome_cliente) {
		this.nome_cliente = nome_cliente;
	}	

	public String getNome_vendedor() {
		return nome_vendedor;
	}

	public void setNome_vendedor(String nome_vendedor) {
		this.nome_vendedor = nome_vendedor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Calendar getData_pedido() {
		return data_pedido;
	}

	public void setData_pedido(Calendar data_pedido) {
		this.data_pedido = data_pedido;
	}

	public Calendar getData_entrega() {
		return data_entrega;
	}

	public void setData_entrega(Calendar data_entrega) {
		this.data_entrega = data_entrega;
	}

	public Date getHora_pedido() {
		return hora_pedido;
	}

	public void setHora_pedido(Date hora_pedido) {
		this.hora_pedido = hora_pedido;
	}

	public Date getHora_entrega() {
		return hora_entrega;
	}

	public void setHora_entrega(Date hora_entrega) {
		this.hora_entrega = hora_entrega;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getValor_total() {
		return valor_total;
	}

	public void setValor_total(String valor_total) {
		this.valor_total = valor_total;
	}		

	public String getTroco() {
		return troco;
	}

	public void setTroco(String troco) {
		this.troco = troco;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCancelada() {
		return cancelada;
	}

	public void setCancelada(String cancelada) {
		this.cancelada = cancelada;
	}

	@JsonManagedReference
	public List<PedidoItens> getItens() {
		return itens;
	}

	public void setItens(List<PedidoItens> itens) {
		this.itens = itens;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
//	@JsonBackReference
//	public Agente getAgente() {
//		return agente;
//	}
//
//	public void setAgente(Agente agente) {
//		this.agente = agente;
//	}

	public String getPerfil_url() {
		return perfil_url;
	}

	public int getCod_agente() {
		return cod_agente;
	}

	public void setCod_agente(int cod_agente) {
		this.cod_agente = cod_agente;
	}

	public void setPerfil_url(String perfil_url) {
		this.perfil_url = perfil_url;
	}	
	
	
} 