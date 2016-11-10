/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import Apoio.Email;
import config.HibernateUtil;
import entidades.Servicos;
import interfaces.IDAO;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.apache.commons.mail.EmailException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Fernanda Finkler
 */
public class ServicosDAO implements IDAO {

    @Override
    public boolean salvar(Object s) {
        boolean deucerto = false;
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction t = sessao.beginTransaction();

            Servicos up = (Servicos) s;
            sessao.save(up);
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

            Servicos s = (Servicos) o;

            sessao.update(s);
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

            Servicos s = (Servicos) o;

            sessao.delete(s);
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
        ArrayList<Object> servicos = new ArrayList<>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        try {

            org.hibernate.Query q = sessao.createQuery("from servicos");
            resultado = q.list();

            for (Object o : resultado) {

                servicos.add(o);
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            sessao.close();
        }

        return servicos;
    }

    @Override
    public Object consultarId(int id) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        try {
            // busca por código
            org.hibernate.Query q = sessao.createQuery("from Servicos where id = " + id);

            return q.list().get(0);

        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            sessao.close();
        }
        return null;

    }

    @Override
    public boolean registroUnico(Object o) {
        Servicos s = (Servicos) o;
        boolean ok = false;
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        try {

            org.hibernate.Query q;

            // busca por item cadastrado
            if (s.getId() != null) {
                q = sessao.createQuery("FROM Servicos WHERE descricao ilike '" + s.getDescricao() + "' "
                        + "AND id != " + s.getId() + " "
                        + "AND delete is null");
            } else {
                q = sessao.createQuery("FROM Servicos WHERE descricao ilike '" + s.getDescricao() + "' "
                        + "AND delete is null");
            }
            System.out.println("sql: " + q);

            if (q.list().isEmpty()) {
                ok = true;
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            sessao.close();
        }
        return ok;
    }

    public void popularTabela(JTable tabela, String criterio) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[2];
        cabecalho[0] = "Código";
        cabecalho[1] = "Descrição";

        // cria matriz de acordo com nº de registros da tabela
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();
            // busca por código
            org.hibernate.Query q = sessao.createQuery("SELECT count(*) FROM Servicos WHERE retira_acentuacao(descricao) ILIKE retira_acentuacao('%" + criterio + "%') AND delete is null");
            System.out.println("SQL: " + q);
            int c = Integer.parseInt(String.valueOf(q.uniqueResult()));
            //int count = (Integer) q.list().get(0);

            dadosTabela = new Object[c][2];

        } catch (Exception e) {
            System.out.println("Erro ao contar/consultar: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

            org.hibernate.Query q = sessao.createQuery("FROM Servicos WHERE retira_acentuacao(descricao) ILIKE retira_acentuacao('%" + criterio + "%') AND delete is null ORDER BY descricao");
            List resultado = q.list();

            for (Object o : resultado) {
                Servicos s = (Servicos) resultado.get(lin);
                dadosTabela[lin][0] = s.getId();
                dadosTabela[lin][1] = s.getDescricao();
                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela Serviços...");
            System.out.println(e);
        }

        // configuracoes adicionais no componente tabela
        tabela.setModel(new DefaultTableModel(dadosTabela, cabecalho) {

            @Override
            // quando retorno for FALSE, a tabela nao é editavel
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        // permite seleção de apenas uma linha da tabela
        tabela.setSelectionMode(0);

        //alinhamento da conteúdo de uma coluna
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        tabela.getColumnModel().getColumn(0).setCellRenderer(direita);

        // redimensiona as colunas de uma tabela
        TableColumn column = null;
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            column = tabela.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(60);
                    break;
                case 1:
                    column.setPreferredWidth(240);
                    break;
                case 2:
                    column.setPreferredWidth(107);
                    break;
                case 3:
                    column.setPreferredWidth(200);
                    break;
                case 4:
                    column.setPreferredWidth(50);
                    break;
            }
        }
    }
}
