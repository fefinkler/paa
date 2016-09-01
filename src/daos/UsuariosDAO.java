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
public class UsuariosDAO implements IDAO {

    @Override
    public boolean salvar(Object o) {
        boolean deucerto = false;
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction t = sessao.beginTransaction();

            Usuarios up = (Usuarios) o;
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
        boolean ok = false;
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();
            // busca por código
            org.hibernate.Query q = sessao.createQuery("FROM Usuarios WHERE login = '" + login + "' AND senha = '" + senha + "'");

            if (q.list().get(0) != null) {
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
        Object[] cabecalho = new Object[3];
        cabecalho[0] = "Código";
        cabecalho[1] = "Nome";
        cabecalho[2] = "Login";

        // cria matriz de acordo com nº de registros da tabela
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();
            // busca por código
            org.hibernate.Query q = sessao.createQuery("SELECT count(*) FROM Usuarios WHERE retira_acentuacao(nome) ILIKE '%" + criterio + "%' AND delete is null");          
            int c = Integer.parseInt(String.valueOf(q.uniqueResult()));
            //int count = (Integer) q.list().get(0);
            
            dadosTabela = new Object[c][3]; //diz quantas linhas e colunas serão criadas: getInt(1) pega o resultado da primeira coluna do Select; e "3" pq serão duas colunas (Nome,Telefone,Cidade).
            
        } catch (Exception e) {
            System.out.println("Erro ao consultar: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();
            
            org.hibernate.Query q = sessao.createQuery("FROM Usuarios WHERE nome ILIKE '%" + criterio + "%' AND delete is null ORDER BY nome");
            List resultado = q.list();
            
            for (Object o : resultado) {
                Usuarios u = (Usuarios) resultado.get(lin);
                dadosTabela[lin][0] = u.getId();
                dadosTabela[lin][1] = u.getNome();
                dadosTabela[lin][2] = u.getLogin();
                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela Usuários...");
            System.out.println(e);
        }

        // efetua consulta na tabela
//        try {
//            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
//                    + "SELECT atleta.*, cidade.nome as cidade FROM atleta, cidade"
//                    + "WHERE atleta.ref_cidade = cidade.idcidade AND atleta.nome ILIKE '%" + criterio + "%' ORDER BY atleta.nome");
//
//            while (resultadoQ.next()) {
//                
//                dadosTabela[lin][0] = resultadoQ.getString("nome"); //poderia colocar o número da coluna (1)
//                dadosTabela[lin][1] = resultadoQ.getString("telefone");
//                dadosTabela[lin][2] = resultadoQ.getString(16);
//                lin++;
//            }
//        } catch (Exception e) {
//            System.out.println("problemas para popular tabela...");
//            System.out.println(e);
//        }
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
    
    
    //Usuarios u = (Usuarios) q.list().get(0);
    
}
