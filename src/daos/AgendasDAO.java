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
            org.hibernate.Query q = sessao.createQuery("SELECT count(*) FROM Agendas WHERE retira_acentuacao(descricao) ILIKE retira_acentuacao('%" + criterio + "%') AND delete is null");
            int count = Integer.parseInt(String.valueOf(q.uniqueResult()));

            dadosTabela = new Object[count][2];

        } catch (Exception e) {
            System.out.println("Erro ao consultar: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

            org.hibernate.Query q = sessao.createQuery("FROM Agendas WHERE retira_acentuacao(descricao) ILIKE retira_acentuacao('%" + criterio + "%') AND delete is null ORDER BY id");
            List resultado = q.list();

            for (Object o : resultado) {
                Agendas a = (Agendas) resultado.get(lin);
                dadosTabela[lin][0] = a.getId();
                dadosTabela[lin][1] = a.getDescricao();
                //dadosTabela[lin][2] = cli.getCpfCnpj();
                //Cidades c = cli.getRefCidades();
                //dadosTabela[lin][3] = c.getCidade();
                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela Agendas...");
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
            }
        }
    }
}
