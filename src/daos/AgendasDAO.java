/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import Apoio.Email;
import config.HibernateUtil;
import entidades.Agendas;
import entidades.LogErros;
import entidades.Prestadores;
import interfaces.IDAO;
import java.text.SimpleDateFormat;
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
import org.hibernate.Session;
import org.hibernate.Transaction;
import telas.IfrLogin;

/**
 *
 * @author Fernanda Finkler
 */
public class AgendasDAO implements IDAO {

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
            try {
                Email.sendEmail(he.toString());
            } catch (EmailException ex) {
                Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            LogErros logErro = new LogErros();
            logErro.setDescricao(he.toString());
            logErro.setDataHora(new Date());
            logErro.setUsuariosId(IfrLogin.userAtivo);

            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction t = sessao.beginTransaction();
            sessao.save(logErro);
            t.commit();

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
            try {
                Email.sendEmail(he.toString());
            } catch (EmailException ex) {
                Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            LogErros logErro = new LogErros();
            logErro.setDescricao(he.toString());
            logErro.setDataHora(new Date());
            logErro.setUsuariosId(IfrLogin.userAtivo);

            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction t = sessao.beginTransaction();
            sessao.save(logErro);
            t.commit();

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
            try {
                Email.sendEmail(he.toString());
            } catch (EmailException ex) {
                Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            LogErros logErro = new LogErros();
            logErro.setDescricao(he.toString());
            logErro.setDataHora(new Date());
            logErro.setUsuariosId(IfrLogin.userAtivo);

            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction t = sessao.beginTransaction();
            sessao.save(logErro);
            t.commit();

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
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        try {
            org.hibernate.Query q = sessao.createQuery("from agendas");
            resultado = q.list();

            for (Object o : resultado) {

                agendas.add(o);
            }

        } catch (HibernateException he) {
            try {
                Email.sendEmail(he.toString());
            } catch (EmailException ex) {
                Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            LogErros logErro = new LogErros();
            logErro.setDescricao(he.toString());
            logErro.setDataHora(new Date());
            logErro.setUsuariosId(IfrLogin.userAtivo);

            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction t = sessao.beginTransaction();
            sessao.save(logErro);
            t.commit();

            he.printStackTrace();
        }

        return agendas;
    }

    @Override
    public Object consultarId(int id) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        try {
            // busca por código
            org.hibernate.Query q = sessao.createQuery("from Agendas where id = " + id);

            return q.uniqueResult();

        } catch (HibernateException he) {
            try {
                Email.sendEmail(he.toString());
            } catch (EmailException ex) {
                Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            LogErros logErro = new LogErros();
            logErro.setDescricao(he.toString());
            logErro.setDataHora(new Date());
            logErro.setUsuariosId(IfrLogin.userAtivo);

            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction t = sessao.beginTransaction();
            sessao.save(logErro);
            t.commit();

            he.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean registroUnico(Object o) {
        Agendas a = (Agendas) o;
        Prestadores p = a.getRefServicosHasPrestadores().getRefPrestadores();
        boolean ok = true;

        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();

        try {
            org.hibernate.Query q;

            SimpleDateFormat df = new SimpleDateFormat("yyy/MM/dd HH:mm:ss");
            String dt_inicio = df.format(a.getInicioEstimado());
            String dt_fim= df.format(a.getFimEstimado() );
            
            // busca por item cadastrado
            if (a.getId() != null) {
                q = sessao.createQuery("FROM Agendas a, entidades.Servicos_Has_Prestadores sp\n"
                        + "WHERE a.ref_servicos_has_prestadores = sp.id \n"
                        + "AND a.id != " + a.getId() + "\n"
                        + "AND " + a.getId() + " = sp.ref_prestadores \n"
                        + "AND a.inicio_estimado between '" + dt_inicio + "' AND '" + dt_fim + "'\n"
                        + "OR a.ref_servicos_has_prestadores = sp.id \n"
                        + "AND a.id != " + a.getId() + "\n"
                        + "AND " + a.getId() + " = sp.ref_prestadores \n"
                        + "AND a.fim_estimado between '" + dt_inicio + "' AND '" + dt_fim + "'");
            } else {
                
                q = sessao.createQuery("FROM Agendas a, Servicos_Has_Prestadores sp\n"
                        + "WHERE a.ref_servicos_has_prestadores = sp.id \n"
                        + "AND " + p.getId() + " = sp.ref_prestadores \n"
                        + "AND a.inicio_estimado between '" + dt_inicio + "' AND '" + dt_fim + "'\n"
                        + "OR a.ref_servicos_has_prestadores = sp.id \n"
                        + "AND " + p.getId() + " = sp.ref_prestadores \n"
                        + "AND a.fim_estimado between '" + dt_inicio + "' AND '" + dt_fim + "'");
            }
            System.out.println("sql: " + q);

            if (q.list().isEmpty()) {
                ok = false;
            }

        } catch (HibernateException he) {
            try {
                Email.sendEmail(he.toString());
            } catch (EmailException ex) {
                Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            LogErros logErro = new LogErros();
            logErro.setDescricao(he.toString());
            logErro.setDataHora(new Date());
            logErro.setUsuariosId(IfrLogin.userAtivo);

            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction t = sessao.beginTransaction();
            sessao.save(logErro);
            t.commit();

            he.printStackTrace();
        }
        return ok;
    }

    public void popularTabela(JTable tabela, String criterio) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[7];
        cabecalho[0] = "Código";
        cabecalho[1] = "Data Hora Início";
        cabecalho[2] = "Data Hora Fim";
        cabecalho[3] = "Cliente";
        cabecalho[4] = "Serviço";
        cabecalho[5] = "Prestador";
        cabecalho[6] = "Nota";

        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();

        // cria matriz de acordo com nº de registros da tabela
        try {
            org.hibernate.Query q = sessao.createQuery("SELECT count(*) FROM Agendas WHERE retira_acentuacao(descricao) ILIKE retira_acentuacao('%" + criterio + "%') AND delete is null");
            int count = Integer.parseInt(String.valueOf(q.uniqueResult()));

            dadosTabela = new Object[count][7];

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

            System.out.println("Erro ao consultar: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            org.hibernate.Query q = sessao.createQuery("FROM Agendas WHERE retira_acentuacao(descricao) ILIKE retira_acentuacao('%" + criterio + "%') AND delete is null ORDER BY id");
            List resultado = q.list();

            for (Object o : resultado) {
                Agendas a = (Agendas) resultado.get(lin);
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                String dt_inicio = df.format(a.getInicioEstimado());
                String dt_fim = df.format(a.getFimEstimado());
                dadosTabela[lin][0] = a.getId();
                dadosTabela[lin][1] = dt_inicio;
                dadosTabela[lin][2] = dt_fim;
                dadosTabela[lin][3] = a.getRefClientes().getNome();
                dadosTabela[lin][4] = a.getRefServicosHasPrestadores().getRefServicos().getDescricao();
                dadosTabela[lin][5] = a.getRefServicosHasPrestadores().getRefPrestadores().getNome();
                dadosTabela[lin][6] = a.getNota();
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
                    column.setPreferredWidth(50);
                    break;
                case 1:
                    column.setPreferredWidth(115);
                    break;
                case 2:
                    column.setPreferredWidth(115);
                    break;
                case 3:
                    column.setPreferredWidth(200);
                    break;
                case 4:
                    column.setPreferredWidth(120);
                    break;
                case 5:
                    column.setPreferredWidth(120);
                    break;
                case 6:
                    column.setPreferredWidth(30);
                    break;
            }
        }
    }
}
