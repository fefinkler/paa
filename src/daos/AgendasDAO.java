/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import config.HibernateUtil;
import entidades.Agendas;
import entidades.Prestadores;
import interfaces.IDAO;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Fernanda Finkler
 */
public class AgendasDAO implements IDAO{

    @Override
    public boolean salvar(Object o) {
        boolean deucerto = false;
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction t = sessao.beginTransaction();

            Agendas a = (Agendas) o;
            sessao.save(a);
            t.commit();
            deucerto = true;

        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            sessao.close();
        }
        return deucerto;
    }

    @Override
    public boolean atualizar(Object o) {
        boolean deucerto = false;
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction t = sessao.beginTransaction();

            Agendas a = (Agendas) o;

            sessao.update(a);
            t.commit();

            deucerto = true;

        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            sessao.close();
        }
        return deucerto;
    }

    @Override
    public boolean excluir(Object o) {
        boolean deucerto = false;
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction t = sessao.beginTransaction();

            Agendas a = (Agendas) o;

            sessao.delete(a);
            t.commit();

            deucerto = true;

        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            sessao.close();
        }
        return deucerto;
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        List resultado = null;
        ArrayList<Object> agendas = new ArrayList<>();
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

            org.hibernate.Query q = sessao.createQuery("from agendas");
            resultado = q.list();

            for (Object o : resultado) {

                agendas.add(o);
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        }

        return agendas;
    }

    @Override
    public Object consultarId(int id) {
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

            // busca por código
            org.hibernate.Query q = sessao.createQuery("from Agendas where id = " + id);

            return q.list().get(0);

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean registroUnico(Object o) {
        Agendas a = (Agendas) o;
        Prestadores p = a.getRefServicosHasPrestadores().getRefPrestadores();
        boolean ok = false;

        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();
            org.hibernate.Query q;

            // busca por item cadastrado
            if (a.getId() != null) {
                q = sessao.createQuery("FROM Agendas a, ServicosHasPrestadores sp\n" +
                                        "WHERE a.ref_servicos_has_prestadores = sp.id \n" +
                                        "AND a.id != " + a.getId() + "\n" +
                                        "AND " + p.getId() + " = sp.ref_prestadores \n" +
                                        "AND '" + a.getInicioEstimado() + "' between a.inicio_estimado AND a.fim_estimado\n" +
                                        "OR '" + a.getFimEstimado() + "' between a.inicio_estimado AND a.fim_estimado");
            } else {
                q = sessao.createQuery("FROM Agendas a, ServicosHasPrestadores sp\n" +
                                        "WHERE a.ref_servicos_has_prestadores = sp.id \n" +
                                        "AND " + p.getId() + " = sp.ref_prestadores \n" +
                                        "AND '" + a.getInicioEstimado() + "' between a.inicio_estimado AND a.fim_estimado\n" +
                                        "OR '" + a.getFimEstimado() + "' between a.inicio_estimado AND a.fim_estimado");
            }
            System.out.println("sql: " + q);

            if (q.list().isEmpty()) {
                ok = true;
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return ok;
    }
    
}
