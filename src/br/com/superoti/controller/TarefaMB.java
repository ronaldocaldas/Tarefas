package br.com.superoti.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.springframework.dao.DataAccessException;

import br.com.superoti.model.Tarefa;
import br.com.superoti.service.TarefaService;

/**
 * Tarefa ManagedBean
 * 
 * @author ronaldocaldas
 *
 */
@ManagedBean(name = "tarefaMB")
@RequestScoped
public class TarefaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "successo";
	private static final String ERROR = "erro";

	@ManagedProperty(value = "#{TarefaService}")
	TarefaService tarefaService;

	List<Tarefa> tarefaList;

	private Tarefa tarefa;

	/**
	 * Adiciona uma tarefa
	 *
	 * @return String - Response Message
	 */
	public String addTarefa() {
		try {
			Tarefa tarefa = new Tarefa();
			tarefa.setTitulo(getTarefa().getTitulo());
			tarefa.setDesc(getTarefa().getDesc());
			tarefa.setDataCriacao(new Date());
			getTarefaService().addTarefa(getTarefa());
			return SUCCESS;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		return ERROR;
	}

	/**
	 * Reseta campos do formulário
	 *
	 */
	public void reset() {
		setTarefa(new Tarefa());
	}

	/**
	 * Obtem a lista de todas as tarefas cadastradas
	 *
	 * @return tarefaList
	 */
	public List<Tarefa> getTarefaList() {
		tarefaList = new ArrayList<Tarefa>();
		tarefaList.addAll(getTarefaService().getTarefas());
		return tarefaList;
	}

	/**
	 * Obtem o TarefaService
	 *
	 * @return tarefaService
	 */
	public TarefaService getTarefaService() {
		return tarefaService;
	}

	/**
	 * Seta o TarefaService
	 *
	 * @param tarefaService
	 */
	public void setTarefaService(TarefaService tarefaService) {
		this.tarefaService = tarefaService;
	}

	/**
	 * Seta a tarefaList
	 *
	 * @param tarefaList
	 */
	public void setTarefaList(List<Tarefa> tarefaList) {
		this.tarefaList = tarefaList;
	}

	/**
	 * Obtem a tarefa do formulario
	 * @return tarefa
	 */
	public Tarefa getTarefa() {
		return tarefa;
	}

	/**
	 * Seta os dados no formulario
	 * 
	 * @param tarefa
	 */
	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
}
