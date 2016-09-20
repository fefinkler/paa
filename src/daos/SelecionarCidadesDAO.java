/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;


import Apoio.ConexaoBD;
import entidades.Cidades;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author asimon
 */
public class SelecionarCidadesDAO {
    
     public static Object consultarId(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM cidade WHERE "
                    + "id = " + id + " ";

            System.out.println("sql: " + sql);

            ResultSet resultado = st.executeQuery(sql);

            if (resultado.next()) {
                Cidades cid = new Cidades();
                cid.setId(resultado.getInt("Id"));
                cid.setCidade(resultado.getString("Cidade"));
                cid.setEstado(resultado.getString("Estado"));
                
                return cid;
            } else {
                return null;
            }
        } catch (Exception c) {
            System.out.println("Erro consultar Cidade = " + c);
            return c.toString();
        }
    }
     
     
     
    
    
    public static void popularTabela(JTable tabela, String criterio) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[3];
        cabecalho[0] = "Código";
        cabecalho[1] = "Cidade";
        cabecalho[2] = "Estado";

        ResultSet resultadoQ;

        // cria matriz de acordo com nº de registros da tabela
        try {
            String sql = "SELECT count(*) FROM cidade WHERE cidade ILIKE '%" + criterio + "%' ";
            
            System.out.println("sql 1: "+ sql);
            
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
            

            resultadoQ.next();

            dadosTabela = new Object[resultadoQ.getInt(1)][3];

        } catch (Exception m) {
            System.out.println("Erro ao consultar Cidade: " + m);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            String sql = "SELECT * FROM cidade WHERE cidade ILIKE '%" + criterio + "%' "
                                  + "ORDER BY siglaestado, cidade";
            
            System.out.println("sql 2: "+ sql);
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultadoQ.next()) {

                dadosTabela[lin][0] = resultadoQ.getInt("IdCidade");
                dadosTabela[lin][1] = resultadoQ.getString("Cidade");
                dadosTabela[lin][2] = resultadoQ.getString("SiglaEstado");

                lin++;
            }
        } catch (Exception c) {
            System.out.println("problemas para popular tabela...");
            System.out.println(c);
        }
 
        // configuracoes adicionais no componente tabela
        tabela.setModel(new DefaultTableModel(dadosTabela, cabecalho) {
           
            // quando retorno for FALSE, a tabela nao é editavel
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            // alteracao no metodo que determina a coluna em que o objeto ImageIcon devera aparecer
        
            public Class
                    getColumnClass(int column) {

                if (column == 4) {
                    //   return ImageIcon.class;
                }
                return Object.class;
            }
        });

        // permite seleção de apenas uma linha da tabela
        tabela.setSelectionMode(0);

        // redimensiona as colunas de uma tabela
        TableColumn column = null;
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            column = tabela.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(17);
                    break;
                case 1:
                    column.setPreferredWidth(140);
                    break;

            }
        } 
    }
    
}
