package br.com.superoti.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
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
	private static final String SUCCESS = "successo";
	private static final String ERROR = "erro";

	@ManagedProperty(value = "#{TarefaService}")
	TarefaService tarefaService;

	DataModel listaTaref;
	List<Tarefa> tarefaList;

	private Tarefa tarefa = new Tarefa();

	/**
	 * Adiciona uma tarefa
	 *
	 * @return String - Response Message
	 */
	public String adicionarTarefa() {
		try {
			getTarefaService().adicionaTarefa(tarefa);
			return SUCCESS;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		return ERROR;
	}
	
	public String alterarTarefa() {
		try {
			getTarefaService().updateTarefa(tarefa);
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

//	/**
//	 * Obtem a lista de todas as tarefas cadastradas
//	 *
//	 * @return tarefaList
//	 */
//	public List<Tarefa> getTarefaList() {
// 		tarefaList = new ArrayList<Tarefa>();
//		tarefaList.addAll(getTarefaService().getTarefas());
//		return tarefaList;
//	}
	
	public DataModel getTarefaList() {
		List<Tarefa> lista = getTarefaService().getTarefas();
				listaTaref = new ListDataModel<>(lista);
		return listaTaref;
		}
	
	
	public String excluirTarefa(){
		Tarefa tarefaTemp = (Tarefa)(listaTaref.getRowData());
		getTarefaService().deleteTarefa(tarefaTemp);
		return "index";
		}
	
	
	public void prepararAdicionarTarefa(ActionEvent actionEvent){
		tarefa = new Tarefa();
		}
	
	public void prepararAlterarTarefa(ActionEvent actionEvent){
		tarefa = (Tarefa)(listaTaref.getRowData());
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
