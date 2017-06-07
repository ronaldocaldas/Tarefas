package br.com.superoti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.superoti.dao.TarefaDAO;
import br.com.superoti.model.Tarefa;

/**
 * Tarefa Service
 * 
 * @author ronaldocaldas
 *
 */
@Service("TarefaService")
@Transactional(readOnly = true)
public class TarefaService {

    @Autowired
    TarefaDAO tarefaDAO;

    /**
     * Adiciona tarefa
     *
     * @param  Tarefa tarefa
     */
    @Transactional(readOnly = false)
    public void adicionaTarefa(Tarefa tarefa) {
        getTarefaDAO().adicionaTarefa(tarefa);
    }

    /**
     * Exclui tarefa
     *
     * @param   Tarefa  tarefa
     */
    @Transactional(readOnly = false)
    public void deleteTarefa(Tarefa tarefa) {
        getTarefaDAO().excluiTarefa(tarefa);
    }

    /**
     * Atualiza tarefa
     *
     * @param Tarefa  tarefa
     */
    @Transactional(readOnly = false)
    public void updateTarefa(Tarefa tarefa) {
        getTarefaDAO().atualizaTarefa(tarefa);
    }

    /**
     * Obtem a tarefa com o id consultado
     *
     * @param  id int Tarefa Id
     */

    public Tarefa getTarefaById(Long id) {
        return getTarefaDAO().getTarefaById(id);
    }

    /**
     *  Obtem todas as tarefas cadastradas
     *
     */
    public List<Tarefa> getTarefas() {
        return getTarefaDAO().getTarefas();
    }

    /**
     * Obtem a  TarefaDAO
     *
     * @return tarefaDAO
     */
    public TarefaDAO getTarefaDAO() {
        return tarefaDAO;
    }

    /**
     * Seta a TarefaDAO
     *
     * @param   tarefaDAO
     */
    public void setTarefaDAO(TarefaDAO tarefaDAO) {
        this.tarefaDAO = tarefaDAO;
    }
}
