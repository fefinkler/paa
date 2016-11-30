/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import Apoio.Email;
import config.HibernateUtil;
import entidades.LogErros;
import entidades.ServicosHasPrestadores;
import interfaces.IDAO;
import java.util.ArrayList;
import java.util.Date;
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
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import telas.IfrLogin;

/**
 *
 * @author Fernanda Finkler
 */
public class ServicosHasPrestadoresDAO implements IDAO{

    @Override
    public boolean salvar(Object o) {
        boolean deucerto = false;
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction t = sessao.beginTransaction();

            ServicosHasPrestadores up = (ServicosHasPrestadores) o;
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

            ServicosHasPrestadores s = (ServicosHasPrestadores) o;

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

            ServicosHasPrestadores s = (ServicosHasPrestadores) o;

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
        ArrayList<Object> ServicosHasPrestadores = new ArrayList<>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        try {

            org.hibernate.Query q = sessao.createQuery("from ServicosHasPrestadores");
            resultado = q.list();

            for (Object o : resultado) {

                ServicosHasPrestadores.add(o);
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            sessao.close();
        }

        return ServicosHasPrestadores;
    }

    @Override
    public Object consultarId(int id) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        try {

            // busca por código
            org.hibernate.Query q = sessao.createQuery("from ServicosHasPrestadores where id = " + id);

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void popularTabelaTodos(JTable tabela, String criterio) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[3];
        cabecalho[0] = "Código";
        cabecalho[1] = "Descrição";
        cabecalho[2] = "Valor Hora";

        // cria matriz de acordo com nº de registros da tabela
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();
            // busca por código
//            org.hibernate.Query q = sessao.createQuery("SELECT count(*) FROM ServicosHasPrestadores WHERE ref_prestadores ILIKE ('%" + criterio + "%')");
//            System.out.println("SQL: " + q);

            String sql = "SELECT count(*) FROM Servicos_Has_Prestadores";// WHERE ref_prestadores ILIKE ('%" + criterio + "%')";
            SQLQuery query = sessao.createSQLQuery(sql);
            query.addEntity(ServicosHasPrestadores.class);
            List results = query.list();

            System.out.println("antes: " +results.get(0).toString());
            int c = Integer.parseInt(String.valueOf(results.get(0)));
            System.out.println("LINHASSSSSSSSSSS: " + c);

//            int c = Integer.parseInt(String.valueOf(q.uniqueResult()));
            dadosTabela = new Object[c][3];

        } catch (Exception e) {
            System.out.println("Erro ao contar/consultar: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

//            org.hibernate.Query q = sessao.createQuery("FROM ServicosHasPrestadores WHERE ref_prestadores ILIKE ('%" + criterio + "%') AND delete is null");
//            List resultado = q.list();
            String sql = "select * FROM Servicos_Has_Prestadores WHERE ref_prestadores ILIKE ('%" + criterio + "%')";
            SQLQuery query = sessao.createSQLQuery(sql);
            query.addEntity(ServicosHasPrestadores.class);
            List resultado = query.list();

            for (Object o : resultado) {
                ServicosHasPrestadores s = (ServicosHasPrestadores) resultado.get(lin);
                dadosTabela[lin][0] = s.getId();
                dadosTabela[lin][1] = s.getRefServicos();
                dadosTabela[lin][1] = s.getValorHora();
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
    
    public void popularTabelaPorPrestador(JTable tabela, int prestador) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[3];
        cabecalho[0] = "Código";
        cabecalho[1] = "Descrição";
        cabecalho[2] = "Valor Hora";

        // cria matriz de acordo com nº de registros da tabela
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();
            // busca por código
//            org.hibernate.Query q = sessao.createQuery("SELECT count(*) FROM ServicosHasPrestadores WHERE ref_prestadores ILIKE ('%" + criterio + "%')");
//            System.out.println("SQL: " + q);

            String sql = "SELECT count(*) FROM Servicos_Has_Prestadores";// WHERE ref_prestadores ILIKE ('%" + criterio + "%')";
            SQLQuery query = sessao.createSQLQuery(sql);
            query.addEntity(ServicosHasPrestadores.class);
            List results = query.list();

            System.out.println("antes: " +results.get(0).toString());
            int c = Integer.parseInt(String.valueOf(results.get(0)));
            System.out.println("LINHASSSSSSSSSSS: " + c);

//            int c = Integer.parseInt(String.valueOf(q.uniqueResult()));
            dadosTabela = new Object[c][3];

        } catch (Exception e) {
            System.out.println("Erro ao contar/consultar: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

//            org.hibernate.Query q = sessao.createQuery("FROM ServicosHasPrestadores WHERE ref_prestadores ILIKE ('%" + criterio + "%') AND delete is null");
//            List resultado = q.list();
            String sql = "select * FROM Servicos_Has_Prestadores WHERE ref_prestadores ILIKE ('%" + prestador + "%')";
            SQLQuery query = sessao.createSQLQuery(sql);
            query.addEntity(ServicosHasPrestadores.class);
            List resultado = query.list();

            for (Object o : resultado) {
                ServicosHasPrestadores s = (ServicosHasPrestadores) resultado.get(lin);
                dadosTabela[lin][0] = s.getId();
                dadosTabela[lin][1] = s.getRefServicos();
                dadosTabela[lin][1] = s.getValorHora();
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
    
    public void popularTabela(JTable tabela, String criterio) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[6];
        cabecalho[0] = "Código";
        cabecalho[1] = "Cód Serviço";
        cabecalho[2] = "Serviço";
        cabecalho[3] = "Cód Prestador";
        cabecalho[4] = "Prestador";
        cabecalho[5] = "Vlr Hora";

        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();

        // cria matriz de acordo com nº de registros da tabela
        try {
            // busca por código
            org.hibernate.Query q = sessao.createQuery("SELECT count(*) FROM ServicosHasPrestadores ");
            System.out.println("SQL: " + q);
            int c = Integer.parseInt(String.valueOf(q.uniqueResult()));
            //int count = (Integer) q.list().get(0);

            dadosTabela = new Object[c][6];

        } catch (Exception e) {
            try {
                Email.sendEmail(e.toString());
            } catch (EmailException ex) {
                Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            LogErros logErro = new LogErros();
            logErro.setDescricao(e.toString());
            logErro.setDataHora(new Date());
            logErro.setUsuariosId(IfrLogin.userAtivo);

            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction t = sessao.beginTransaction();
            sessao.save(logErro);
            t.commit();

            System.out.println("Erro ao contar/consultar: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            org.hibernate.Query q = sessao.createQuery("FROM ServicosHasPrestadores ");
            List resultado = q.list();

            for (Object o : resultado) {
                ServicosHasPrestadores sp = (ServicosHasPrestadores) resultado.get(lin);
                dadosTabela[lin][0] = sp.getId();
                dadosTabela[lin][1] = sp.getRefServicos().getId();
                dadosTabela[lin][2] = sp.getRefServicos().getDescricao();
                dadosTabela[lin][3] = sp.getRefPrestadores().getId();
                dadosTabela[lin][4] = sp.getRefPrestadores().getNome();
                dadosTabela[lin][5] = sp.getValorHora();
                lin++;
            }
        } catch (Exception e) {
            try {
                Email.sendEmail(e.toString());
            } catch (EmailException ex) {
                Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            LogErros logErro = new LogErros();
            logErro.setDescricao(e.toString());
            logErro.setDataHora(new Date());
            logErro.setUsuariosId(IfrLogin.userAtivo);

            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction t = sessao.beginTransaction();
            sessao.save(logErro);
            t.commit();

            e.printStackTrace();
            System.out.println("problemas para popular tabela Serviços/Prestadores...");
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
