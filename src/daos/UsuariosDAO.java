/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import config.HibernateUtil;
import entidades.Usuarios;
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
public class UsuariosDAO implements IDAO {
    
    @Override
    public boolean salvar(Object o) {
        boolean deucerto = false;
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction t = sessao.beginTransaction();
            
            Usuarios u = (Usuarios) o;
            
            sessao.save(u);
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
    public ArrayList<Object> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object consultarId(int id) {
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

            // busca por código
            org.hibernate.Query q = sessao.createQuery("from Usuarios where id = " + id);
            
            return q.getClass();

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return null;
        
    }
}
