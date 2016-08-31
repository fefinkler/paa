/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates

 * and open the template in the editor.
 */
package daos;

import config.HibernateUtil;
import entidades.Usuarios;
import interfaces.IDAO;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Fernanda Finkler
 */
public class UsuariosDAO implements IDAO {

    @Override
    public boolean salvar(Object o) {
        boolean deucerto = false;
        Session sessao = null;
        try {
            System.out.println("entrou no try");
            sessao = HibernateUtil.getSessionFactory().openSession();
            System.out.println("criou sessao");
            Transaction t = sessao.beginTransaction();

            System.out.println("passou do transajndgfij");
            Usuarios up = (Usuarios) o;
            System.out.println("vou commitar");
            sessao.save(up);
            t.commit();
            System.out.println("Commitei");
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

            Usuarios u = (Usuarios) o;

            sessao.update(u);
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

            Usuarios u = (Usuarios) o;

            sessao.delete(u);
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
        ArrayList<Object> users = new ArrayList<>();
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

            // busca todos os registros
            // observar: a classe Pessoa no from -> P maiúsculo
            org.hibernate.Query q = sessao.createQuery("from Usuarios");
            resultado = q.list();

            for (Object o : resultado) {

                users.add(o);
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        }

        return users;
    }

    @Override
    public Object consultarId(int id) {
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

            // busca por código
            org.hibernate.Query q = sessao.createQuery("from Usuarios where id = " + id);

            return q.list().get(0);

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return null;

    }

    @Override
    public boolean registroUnico(Object o) {
        Usuarios u = (Usuarios) o;
        boolean ok = false;

        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();
            org.hibernate.Query q;

            // busca por item cadastrado
            if (1 > 0) {
                q = sessao.createQuery("FROM Usuarios WHERE nome ilike '" + u.getNome() + "' "
                        + "AND id != " + u.getId() + " "
                        + "AND delete = f");
            } else {
                q = sessao.createQuery("FROM Usuarios WHERE nome ilike '" + u.getNome() + "' "
                        + "AND delete = f");
            }
            System.out.println("sql: " + q);

            if (q.list().get(0) == null) {
                ok = true;
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return true;

    }

    public boolean validarLogin(String login, String senha) {
        boolean OK = false;
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

            // busca por código
            org.hibernate.Query q = sessao.createQuery("FROM Usuarios WHERE login = '" + login + "' AND senha = '" + senha + "'");

            if (q.list().get(0) != null) {

                OK = true;

            }

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return OK;
    }
}
