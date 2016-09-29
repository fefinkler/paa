/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import static Apoio.Formatacao.removerFormatacao;
import Apoio.LimiteDigitos;
import static Apoio.Validacao.validarCPF;
import Apoio.limpaCampos;
import daos.PrestadoresDAO;
import daos.SelecionarCidadesDAO;
import entidades.Cidades;
import entidades.Prestadores;
import javax.swing.JOptionPane;

/**
 *
 * @author adriano
 */
public class IfrPrestadores extends javax.swing.JInternalFrame {

    PrestadoresDAO prestadoresDAO;
    SelecionarCidadesDAO scDAO;
    char operacao = 'i';
    int idCid = 0;

    /**
     * Creates new form IfrPrestadores
     */
    public IfrPrestadores() {
        initComponents();
        prestadoresDAO = new PrestadoresDAO();
        scDAO = new SelecionarCidadesDAO();
        btnExcluir.setEnabled(false);
        btnEditar.setEnabled(false);
        btnSalvar.setEnabled(false);
        tfdIdCidade.setVisible(false);
        tfdNome.setDocument(new LimiteDigitos(60));
        tfdRG.setDocument(new LimiteDigitos(10));
        tfdEndereco.setDocument(new LimiteDigitos(60));
        tfdEmail.setDocument(new LimiteDigitos(45));
        prestadoresDAO.popularTabela(tblPrestadores, tfdConsulta.getText());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupTipo = new javax.swing.ButtonGroup();
        btnFechar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfdId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfdNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        labelCNPJ = new javax.swing.JLabel();
        tfdSiglaEstado = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfdCidade = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        tfdIdCidade = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tfdEndereco = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        tfdCEP = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tfdTelefone = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        tfdCelular = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        tfdEmail = new javax.swing.JTextField();
        labelCPF = new javax.swing.JLabel();
        tfdCPF = new javax.swing.JFormattedTextField();
        tfdRG = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPrestadores = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        tfdConsulta = new javax.swing.JTextField();
        btnProcurar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();

        setTitle("Cadastro de Prestadores");

        btnFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/telas.apoio/exit.png"))); // NOI18N
        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/telas.apoio/Save.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jLabel1.setText("Id");

        tfdId.setEditable(false);
        tfdId.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("Nome");

        tfdNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfdNomeKeyReleased(evt);
            }
        });

        jLabel4.setText("Estado");

        labelCNPJ.setForeground(new java.awt.Color(255, 0, 0));
        labelCNPJ.setText("CPF");

        tfdSiglaEstado.setEditable(false);
        tfdSiglaEstado.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("Cidade");

        tfdCidade.setEditable(false);
        tfdCidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfdCidadeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfdCidadeKeyTyped(evt);
            }
        });

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/telas.apoio/fin2.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tfdIdCidade.setEditable(false);
        tfdIdCidade.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel7.setText("Endereço");

        jLabel10.setText("CEP");

        try {
            tfdCEP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfdCEP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfdCEP.setToolTipText("");

        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("Campos em vermelho Obrigatórios");

        jLabel8.setText("Telefone");

        try {
            tfdTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfdTelefone.setToolTipText("");

        jLabel9.setText("Celular");

        try {
            tfdCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfdCelular.setToolTipText("");

        jLabel13.setText("E-mail");

        labelCPF.setText("RG");

        try {
            tfdCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfdCPF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfdCPFFocusLost(evt);
            }
        });
        tfdCPF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfdCPFKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(tfdIdCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfdEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tfdCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(2, 2, 2))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfdCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfdSiglaEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel12))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfdNome)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(tfdId, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfdTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(tfdCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(labelCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfdCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(labelCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfdRG, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfdEmail)))
                                .addGap(2, 2, 2)))
                        .addGap(18, 18, 18))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfdId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfdNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCNPJ)
                    .addComponent(tfdCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCPF)
                    .addComponent(tfdRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(tfdTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(tfdCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(tfdCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfdSiglaEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addComponent(jLabel6)
                    .addComponent(tfdCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfdIdCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jLabel12))
        );

        jTabbedPane1.addTab("Cadastro", jPanel1);

        tblPrestadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblPrestadores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPrestadoresMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblPrestadoresMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblPrestadores);

        jLabel3.setText("Nome");

        btnProcurar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/telas.apoio/fin2.png"))); // NOI18N
        btnProcurar.setText("Buscar");
        btnProcurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcurarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfdConsulta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnProcurar)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfdConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProcurar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Consulta", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/telas.apoio/edit.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/telas.apoio/delete2.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSalvar)
                        .addGap(14, 14, 14)
                        .addComponent(btnFechar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFechar)
                    .addComponent(btnSalvar)
                    .addComponent(btnEditar)
                    .addComponent(btnExcluir))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

    //BUSCAR DADOS DO FILTRO
    private void btnProcurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcurarActionPerformed
       prestadoresDAO.popularTabela(tblPrestadores, tfdConsulta.getText());
    }//GEN-LAST:event_btnProcurarActionPerformed

    private void tblPrestadoresMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPrestadoresMouseReleased
        btnExcluir.setEnabled(true);
        btnEditar.setEnabled(true);
    }//GEN-LAST:event_tblPrestadoresMouseReleased

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        DlgSelecionarCidade dlgSelecCidade = new DlgSelecionarCidade(null, true, this);
        dlgSelecCidade.setLocationRelativeTo(null);
        dlgSelecCidade.setModal(true);
        dlgSelecCidade.setVisible(true);
        if (camposObrigatorios() == true) {
            btnSalvar.setEnabled(true);
        } else {
            btnSalvar.setEnabled(false);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private boolean camposObrigatorios() {
        if (tfdNome.getText().length() > 0 && removerFormatacao(tfdCPF.getText()).trim().length() == 11 && tfdIdCidade.getText().length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void pegaValor(Object c) {
        Cidades cid = (Cidades) c;
        System.out.println("id: " + cid.getId());
        System.out.println("nome: " + cid.getCidade());
        idCid = cid.getId();
        tfdIdCidade.setText(String.valueOf(cid.getId()));
        tfdCidade.setText(cid.getCidade());
        tfdSiglaEstado.setText(cid.getEstado());
    }

    private void tfdCidadeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdCidadeKeyReleased

    }//GEN-LAST:event_tfdCidadeKeyReleased

// Verificar Metodo com Fabricio
    private void tfdCidadeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdCidadeKeyTyped

    }//GEN-LAST:event_tfdCidadeKeyTyped

    private void tfdNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdNomeKeyReleased
        if (camposObrigatorios() == true) {
            btnSalvar.setEnabled(true);
        } else {
            btnSalvar.setEnabled(false);
        }
    }//GEN-LAST:event_tfdNomeKeyReleased

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        btnEditar.setEnabled(false);
        btnExcluir.setEnabled(false);
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (tblPrestadores.getSelectedRow() > -1) {
            int id = Integer.parseInt(String.valueOf(tblPrestadores.getValueAt(tblPrestadores.getSelectedRow(), 0)));
            int resposta = JOptionPane.showConfirmDialog(null, "Realmente excluir Prestador?", title, JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                Prestadores p = (Prestadores) prestadoresDAO.consultarId(id);
                p.setDelete('d');
                if (prestadoresDAO.atualizar(p)) {
                    prestadoresDAO.popularTabela(tblPrestadores, tfdConsulta.getText());
                    JOptionPane.showMessageDialog(this, "Registro excluído com sucesso.");
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao excluir registro!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um registro!");
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        editar();
    }//GEN-LAST:event_btnEditarActionPerformed

    public void editar() {
        if (tblPrestadores.getSelectedRow() > -1) {
            int id = Integer.parseInt(String.valueOf(tblPrestadores.getValueAt(tblPrestadores.getSelectedRow(), 0)));
            Prestadores pres = (Prestadores) prestadoresDAO.consultarId(id);

            tfdId.setText(String.valueOf(pres.getId()));
            tfdNome.setText(pres.getNome());
            tfdCPF.setText(pres.getCpf());
            tfdRG.setText(pres.getRg());
            tfdTelefone.setText(pres.getTelefone());
            tfdCelular.setText(pres.getCelular());
            tfdEmail.setText(pres.getEmail());
            tfdEndereco.setText(pres.getEndereco());
            tfdCEP.setText(pres.getCep());
            tfdIdCidade.setText(String.valueOf(pres.getRefCidades().getId()));
            Cidades cid = (Cidades) scDAO.consultarId(Integer.parseInt(tfdIdCidade.getText()));
            idCid = cid.getId();
            tfdCidade.setText(cid.getCidade());
            tfdSiglaEstado.setText(cid.getEstado());
            tblPrestadores.clearSelection();
            jTabbedPane1.setSelectedIndex(0);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um registro!");
        }
    }

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        Prestadores prestador = new Prestadores();
        if (tfdId.getText().length() >= 1) {
            prestador.setId(Integer.parseInt(tfdId.getText()));
        }
        prestador.setNome(tfdNome.getText());
        prestador.setCpf(tfdCPF.getText());
        prestador.setRg(tfdRG.getText());
        prestador.setTelefone(tfdTelefone.getText());
        prestador.setCelular(tfdCelular.getText());
        prestador.setEmail(tfdEmail.getText());
        prestador.setEndereco(tfdEndereco.getText());
        prestador.setCep(tfdCEP.getText());
        prestador.setRefCidades((Cidades) new SelecionarCidadesDAO().consultarId(idCid));

        if (prestadoresDAO.registroUnico(prestador)) {
            if (tfdId.getText().trim().isEmpty()) { //SALVAR
                if (prestadoresDAO.salvar(prestador)) {
                    JOptionPane.showMessageDialog(this, "Prestador salvo com Sucesso!");
                    btnSalvar.setEnabled(false);
                    LimparCamposCadastro();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao salvar novo Prestador!\n");
                }

            } else {  // ATUALIZAR
                prestador.setId(Integer.parseInt(tfdId.getText()));
                if (prestadoresDAO.atualizar(prestador)) {
                    JOptionPane.showMessageDialog(this, "Prestador atualizado com Sucesso!");
                    btnSalvar.setEnabled(false);
                    LimparCamposCadastro();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao atualizar Prestador!\n");
                }
            }
            prestadoresDAO.popularTabela(tblPrestadores, tfdConsulta.getText());
        } else {
            JOptionPane.showMessageDialog(this, "Prestador já cadastrado com este CPF!");
                tfdCPF.requestFocus();
                tfdCPF.setText(null);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void tfdCPFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfdCPFFocusLost
        if (removerFormatacao(tfdCPF.getText()).trim().length() == 11) {
            if (validarCPF(removerFormatacao(tfdCPF.getText())) == true) {
                if (camposObrigatorios() == true) {
                    btnSalvar.setEnabled(true);
                } else {
                    btnSalvar.setEnabled(false);
                }
            } else {
                tfdCPF.setText("");
                JOptionPane.showMessageDialog(this, "CPF Inválido!");
                tfdCPF.requestFocus();
            }
        }
        if (removerFormatacao(tfdCPF.getText()).trim().length() == 0) {
            btnSalvar.setEnabled(false);
        }
        if (removerFormatacao(tfdCPF.getText()).trim().length() != 11 && removerFormatacao(tfdCPF.getText()).trim().length() != 0) {
            JOptionPane.showMessageDialog(this, "CPF Inválido!");
            tfdCPF.setText("");
            tfdCPF.requestFocus();
        }

    }//GEN-LAST:event_tfdCPFFocusLost

    private void tfdCPFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdCPFKeyReleased
        if (removerFormatacao(tfdCPF.getText()).trim().length() == 11) {
            if (validarCPF(removerFormatacao(tfdCPF.getText())) == true) {
                btnSalvar.setEnabled(true);
            } else {
                btnSalvar.setEnabled(false);
            }

        } else {
            btnSalvar.setEnabled(false);
        }
    }//GEN-LAST:event_tfdCPFKeyReleased

    private void tblPrestadoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPrestadoresMouseClicked
        if (evt.getClickCount() >= 2) {
            editar();
        }
    }//GEN-LAST:event_tblPrestadoresMouseClicked

    private void LimparCamposCadastro() {
        limpaCampos.limparCampos(jPanel1);
        tfdNome.requestFocus();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnProcurar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.ButtonGroup buttonGroupTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelCNPJ;
    private javax.swing.JLabel labelCPF;
    private javax.swing.JTable tblPrestadores;
    private javax.swing.JFormattedTextField tfdCEP;
    private javax.swing.JFormattedTextField tfdCPF;
    private javax.swing.JFormattedTextField tfdCelular;
    private javax.swing.JTextField tfdCidade;
    private javax.swing.JTextField tfdConsulta;
    private javax.swing.JTextField tfdEmail;
    private javax.swing.JTextField tfdEndereco;
    private javax.swing.JTextField tfdId;
    private javax.swing.JTextField tfdIdCidade;
    private javax.swing.JTextField tfdNome;
    private javax.swing.JTextField tfdRG;
    private javax.swing.JTextField tfdSiglaEstado;
    private javax.swing.JFormattedTextField tfdTelefone;
    // End of variables declaration//GEN-END:variables
}
