/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import config.HibernateUtil;
import entidades.Clientes;
import java.util.List;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author asimon
 */
public class SelecionarClientesDAO {

    public Object consultarId(int id) {
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

            // busca por código
            org.hibernate.Query q = sessao.createQuery("from Clientes where id = " + id);

            return q.uniqueResult();

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return null;
    }

    public void popularTabela(JTable tabela, String criterio) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[3];
        cabecalho[0] = "Código";
        cabecalho[1] = "Nome";
        cabecalho[2] = "CPF/CNPJ";

        // cria matriz de acordo com nº de registros da tabela
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();
            // busca por código
            org.hibernate.Query q = sessao.createQuery("SELECT count(*) FROM clientes WHERE retira_acentuacao(nome) ILIKE retira_acentuacao('%" + criterio + "%') ");
            int c = Integer.parseInt(String.valueOf(q.uniqueResult()));
            System.out.println("resultado count = " + c);

            dadosTabela = new Object[c][3];

        } catch (Exception e) {
            System.out.println("Erro ao consultar count Clientes: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

            org.hibernate.Query q = sessao.createQuery("FROM Cliengtes WHERE retira_acentuacao(nome) ILIKE retira_acentuacao('%" + criterio + "%') ORDER BY nome");
            List resultado = q.list();

            for (Object o : resultado) {
                Clientes c = (Clientes) resultado.get(lin);
                dadosTabela[lin][0] = c.getId();
                dadosTabela[lin][1] = c.getNome();
                dadosTabela[lin][2] = c.getCpfCnpj();
                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela Clientes...");
            System.out.println(e);
        }

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
            }
        }
    }

}
