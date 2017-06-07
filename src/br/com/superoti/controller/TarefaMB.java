package br.com.superoti.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

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
	private static final String SUCCESS = "index";
	private static final String ERROR = "erro";

	@ManagedProperty(value = "#{TarefaService}")
	TarefaService tarefaService;

	DataModel listaTarefa;
	boolean atualizaTarefa;

	private Tarefa tarefa = new Tarefa();
	private Tarefa tarefaAtualizada = new Tarefa();

	/*
	 * Adiciona uma tarefa
	 *
	 * @return String - Response Message
	 */
	public String adicionarTarefa() {
		try {
			getTarefaService().adicionaTarefa(tarefa);
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Tarefa adicionada!."));
			return SUCCESS;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		return ERROR;
	}
	
	public String alterarTarefa() {
		try {
			getTarefaService().updateTarefa(tarefaAtualizada);
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Tarefa alterada!."));
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

	@SuppressWarnings("rawtypes")
	public DataModel getTarefaList() {
		List<Tarefa> lista = getTarefaService().getTarefas();
				listaTarefa = new ListDataModel<>(lista);
		return listaTarefa;
		}
	
	
	public String excluirTarefa(){
		Tarefa tarefaTemp = (Tarefa)(listaTarefa.getRowData());
		getTarefaService().deleteTarefa(tarefaTemp);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Tarefa excluida!."));

		return "index";
		}
	
	
	public void prepararAdicionarTarefa(ActionEvent actionEvent){
		atualizaTarefa = false;
		tarefa = new Tarefa();
		}
	
	public void prepararAlterarTarefa(ActionEvent actionEvent){
		atualizaTarefa = true;
		tarefaAtualizada = (Tarefa)(listaTarefa.getRowData());
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

	public boolean isAtualizaTarefa() {
		return atualizaTarefa;
	}

	public void setAtualizaTarefa(boolean novaTarefa) {
		this.atualizaTarefa = novaTarefa;
	}

	public Tarefa getTarefaAtualizada() {
		return tarefaAtualizada;
	}

	public void setTarefaAtualizada(Tarefa tarefaAtualizada) {
		this.tarefaAtualizada = tarefaAtualizada;
	}
}
