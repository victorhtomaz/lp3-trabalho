package com.mycompany.easytrip.telas.grupos;

import com.mycompany.easytrip.controllers.VisualizarGruposController;

public class TelaVisualizarGrupos extends javax.swing.JPanel {
    private int usuarioId;
    private VisualizarGruposController controller;
    
    public TelaVisualizarGrupos() {
        initComponents();
    }

    public TelaVisualizarGrupos(int usuarioId){
        initComponents();
        this.usuarioId = usuarioId;
        this.controller = new VisualizarGruposController(this);
        
        controller.carregarGrupos(usuarioId);
    }
    
    public int receberGrupoSelecionado(){
        int linha = grupoTable.getSelectedRow();
        int grupoId = 0;

        if(linha != -1)
            grupoId = (Integer)grupoTable.getValueAt(linha, 0);
        
        return grupoId;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jButton1 = new javax.swing.JButton();
        minhasFavoritasTituloLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        criarGrupoButton = new javax.swing.JButton();
        legendaPanel = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jScrollPane1 = new javax.swing.JScrollPane();
        grupoTable = new javax.swing.JTable();
        gerenciarGrupoButton1 = new com.mycompany.easytrip.telas.componentes.GerenciarGrupoButton();
        sairGrupoButton1 = new com.mycompany.easytrip.telas.componentes.SairGrupoButton();

        jButton1.setText("jButton1");

        setBackground(new java.awt.Color(163, 187, 229));
        setLayout(new java.awt.GridBagLayout());

        minhasFavoritasTituloLabel.setFont(new java.awt.Font("JetBrainsMono NF", 1, 18)); // NOI18N
        minhasFavoritasTituloLabel.setText("Grupos");
        minhasFavoritasTituloLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(15, 20, 10, 10);
        add(minhasFavoritasTituloLabel, gridBagConstraints);

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(102, 102, 102));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        add(jSeparator1, gridBagConstraints);

        criarGrupoButton.setFont(new java.awt.Font("JetBrainsMono NF", 0, 12)); // NOI18N
        criarGrupoButton.setText("Criar Grupo");
        criarGrupoButton.setToolTipText("");
        criarGrupoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criarGrupoButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(15, 10, 10, 10);
        add(criarGrupoButton, gridBagConstraints);

        legendaPanel.setBackground(new java.awt.Color(163, 187, 229));
        legendaPanel.setLayout(new java.awt.GridLayout(1, 4, 4, 0));
        legendaPanel.add(filler1);
        legendaPanel.add(filler2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(15, 20, 0, 10);
        add(legendaPanel, gridBagConstraints);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(300, 80));

        grupoTable.setFont(new java.awt.Font("JetBrainsMono NF", 0, 12)); // NOI18N
        grupoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nome", "Minha função", "Meus acompanhantes"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        grupoTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        grupoTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        grupoTable.setShowGrid(false);
        jScrollPane1.setViewportView(grupoTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 150.0;
        gridBagConstraints.weighty = 150.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 20, 0, 10);
        add(jScrollPane1, gridBagConstraints);

        gerenciarGrupoButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gerenciarGrupoButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 40;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 20, 5, 10);
        add(gerenciarGrupoButton1, gridBagConstraints);

        sairGrupoButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairGrupoButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.insets = new java.awt.Insets(15, 20, 5, 10);
        add(sairGrupoButton1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void criarGrupoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criarGrupoButtonActionPerformed
        // TODO add your handling code here:
        controller.criarGrupo(usuarioId);
    }//GEN-LAST:event_criarGrupoButtonActionPerformed

    private void sairGrupoButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairGrupoButton1ActionPerformed
        // TODO add your handling code here:
        controller.sairDoGrupo(usuarioId);
    }//GEN-LAST:event_sairGrupoButton1ActionPerformed

    private void gerenciarGrupoButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gerenciarGrupoButton1ActionPerformed
        // TODO add your handling code here:
        controller.mudarParaTelaGerenciarGrupo();
    }//GEN-LAST:event_gerenciarGrupoButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton criarGrupoButton;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    public com.mycompany.easytrip.telas.componentes.GerenciarGrupoButton gerenciarGrupoButton1;
    public javax.swing.JTable grupoTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel legendaPanel;
    private javax.swing.JLabel minhasFavoritasTituloLabel;
    public com.mycompany.easytrip.telas.componentes.SairGrupoButton sairGrupoButton1;
    // End of variables declaration//GEN-END:variables
}
