/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import config.HibernateUtil;
import entidades.Prestadores;
import interfaces.IDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Fernanda Finkler
 */
public class PrestadoresDAO implements IDAO {

    @Override
    public boolean salvar(Object o) {
        boolean deucerto = false;
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction t = sessao.beginTransaction();

            Prestadores p = (Prestadores) o;
            sessao.save(p);
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

            Prestadores p = (Prestadores) o;

            sessao.update(p);
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

            Prestadores p = (Prestadores) o;

            sessao.delete(p);
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
        ArrayList<Object> prestadores = new ArrayList<>();
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

            // busca todos os registros
            // observar: a classe Pessoa no from -> P maiúsculo
            org.hibernate.Query q = sessao.createQuery("from Prestadores");
            resultado = q.list();

            for (Object o : resultado) {

                prestadores.add(o);
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        }

        return prestadores;
    }

    @Override
    public Object consultarId(int id) {
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

            // busca por código
            org.hibernate.Query q = sessao.createQuery("from Prestadores where id = " + id);

            return q.list().get(0);

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean registroUnico(Object o) {
        Prestadores p = (Prestadores) o;
        boolean ok = false;

        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();
            org.hibernate.Query q;

            // busca por item cadastrado
            if (p.getId() != null) {
                q = sessao.createQuery("FROM Prestadores WHERE nome ilike '" + p.getCpf() + "' "
                        + "AND id != " + p.getId() + " "
                        + "AND delete is null");
            } else {
                q = sessao.createQuery("FROM Prestadores WHERE nome ilike '" + p.getCpf()+ "' "
                        + "AND delete is null");
            }
            System.out.println("sql: " + q);
            if (!q.list().isEmpty()) {
                ok = true;
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return true;
    }
    
    public void popularTabela(JTable tabela, String criterio) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[4];
        cabecalho[0] = "Código";
        cabecalho[1] = "Nome";
        cabecalho[2] = "Telefone";
        cabecalho[3] = "Cidade";

        // cria matriz de acordo com nº de registros da tabela
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();
            
            org.hibernate.Query q = sessao.createQuery("SELECT count(*) FROM Prestadores WHERE retira_acentuacao(nome) ILIKE retira_acentuacao('%" + criterio + "%') AND delete is null");
            int c = Integer.parseInt(String.valueOf(q.uniqueResult()));
            //int count = (Integer) q.list().get(0);

            dadosTabela = new Object[c][4];

        } catch (Exception e) {
            System.out.println("Erro ao consultar: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

            org.hibernate.Query q = sessao.createQuery("FROM Prestadores WHERE retira_acentuacao(nome) ILIKE retira_acentuacao('%" + criterio + "%') AND delete is null ORDER BY nome");
            List resultado = q.list();

            for (Object o : resultado) {
                Prestadores p = (Prestadores) resultado.get(lin);
                dadosTabela[lin][0] = p.getId();
                dadosTabela[lin][1] = p.getNome();
                dadosTabela[lin][2] = p.getTelefone();
                dadosTabela[lin][3] = p.getRefCidades().getCidade();
                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela Prestadores...");
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
                    column.setPreferredWidth(180);
                    break;    
            }
        }
    }
}

