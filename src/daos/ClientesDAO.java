/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import config.HibernateUtil;
import entidades.Cidades;
import entidades.Clientes;
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
public class ClientesDAO implements IDAO {

    @Override
    public boolean salvar(Object o) {
        boolean deucerto = false;
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction t = sessao.beginTransaction();

            Clientes cli = (Clientes) o;
            sessao.save(cli);
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

            Clientes cli = (Clientes) o;

            sessao.update(cli);
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

            Clientes cli = (Clientes) o;

            sessao.delete(cli);
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
        ArrayList<Object> clientes = new ArrayList<>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        try {

            // busca todos os registros
            // observar: a classe Pessoa no from -> P maiúsculo
            org.hibernate.Query q = sessao.createQuery("from Clientes");
            resultado = q.list();

            for (Object o : resultado) {

                clientes.add(o);
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            sessao.close();
        }

        return clientes;
    }

    @Override
    public Object consultarId(int id) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        try {

            // busca por código
            org.hibernate.Query q = sessao.createQuery("from Clientes where id = " + id);

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
        Clientes cli = (Clientes) o;
        boolean ok = true;
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        try {
            org.hibernate.Query q;

            // busca por item cadastrado
            if (cli.getId() != null) {
                q = sessao.createQuery("FROM Clientes WHERE cpf_cnpj ilike '" + cli.getCpfCnpj() + "' "
                        + "AND id != " + cli.getId() + " "
                        + "AND delete is null");
            } else {
                q = sessao.createQuery("FROM Clientes WHERE cpf_cnpj ilike '" + cli.getCpfCnpj() + "' "
                        + "AND delete is null");
            }
            System.out.println("sql: " + q);
            if (!q.list().isEmpty()) {
                System.out.println("CPF/CNPJ já existe");
                ok = false;
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
        Object[] cabecalho = new Object[4];
        cabecalho[0] = "Código";
        cabecalho[1] = "Nome";
        cabecalho[2] = "CPF/CNPJ";
        cabecalho[3] = "Cidade";

        // cria matriz de acordo com nº de registros da tabela
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();
            org.hibernate.Query q = sessao.createQuery("SELECT count(*) FROM Clientes WHERE retira_acentuacao(nome) ILIKE retira_acentuacao('%" + criterio + "%') AND delete is null");
            int count = Integer.parseInt(String.valueOf(q.uniqueResult()));

            dadosTabela = new Object[count][4];

        } catch (Exception e) {
            System.out.println("Erro ao consultar: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

            org.hibernate.Query q = sessao.createQuery("FROM Clientes WHERE retira_acentuacao(nome) ILIKE retira_acentuacao('%" + criterio + "%') AND delete is null ORDER BY nome");
            List resultado = q.list();

            for (Object o : resultado) {
                Clientes cli = (Clientes) resultado.get(lin);
                dadosTabela[lin][0] = cli.getId();
                dadosTabela[lin][1] = cli.getNome();
                dadosTabela[lin][2] = cli.getCpfCnpj();
                Cidades c = cli.getRefCidades();
                dadosTabela[lin][3] = c.getCidade();
                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela Clientes...");
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
