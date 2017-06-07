package br.com.superoti.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.superoti.model.Tarefa;

@Repository
public class TarefaDAO  {
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Obtem o  Hibernate Session Factory
     *
     * @return SessionFactory - Hibernate Session Factory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Seta o  Hibernate Session Factory
     *
     * @param sessionFactory SessionFactory - Hibernate Session Factory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Adiciona tarefa
     *
     * @param   Tarefa   tarefa
     */

    public void adicionaTarefa(Tarefa tarefa) {
    	tarefa.setDataCriacao(new Date());
        getSessionFactory().getCurrentSession().save(tarefa);
    }

    /**
     * Exclui tarefa
     *
     * @param   Tarefa  tarefa
     */

    public void excluiTarefa(Tarefa tarefa) {
        getSessionFactory().getCurrentSession().delete(tarefa);
    }

    /**
     * Atualiza tarefa
     *
     * @param  Tarefa tarefa
     */

    public void atualizaTarefa(Tarefa tarefa) {
    	tarefa.setDataEdicao(new Date());
        getSessionFactory().getCurrentSession().update(tarefa);
    }

    /**
     * Consulta tarefa pelo Id
     *
     * @param  id int
     * @return Tarefa
     */

    public Tarefa getTarefaById(Long id) {
        List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from Tarefa  where id=?")
                                            .setParameter(0, id).list();
        return (Tarefa)list.get(0);
    }

    /**
     * Obtem todos os tarefas cadastradas
     * 
     * @return List - Tarefa list
     */

    public List<Tarefa> getTarefas() {
		List list = getSessionFactory().getCurrentSession().createQuery("from Tarefa").list();
        return list;
    }

}