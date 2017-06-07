package br.com.superoti.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tarefa")
public class Tarefa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3579136402658456494L;
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 50)
	private String titulo;

	private boolean status;

	@Column(name = "descricao", nullable = true, length = 500)
	private String desc;

	@Column(name = "data_criacao", nullable = true)
	private Date dataCriacao;

	@Column(name = "data_edicao", nullable = true)
	private Date dataEdicao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataEdicao() {
		return dataEdicao;
	}

	public void setDataEdicao(Date dataEdicao) {
		this.dataEdicao = dataEdicao;
	}
}