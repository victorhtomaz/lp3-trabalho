package com.mycompany.easytrip.telas.grupos;

import com.mycompany.easytrip.controllers.VisualizarFavoritasGrupoController;

public class TelaFavoritasGrupo extends javax.swing.JPanel {
    private int usuarioId;
    private int pagina = 0;
    private VisualizarFavoritasGrupoController controller;
    public static final int QUANTIDADE_COLUNAS = 2;
    public static final int QUANTIDADE_POR_PAGINA = 8;
    public TelaFavoritasGrupo() {
        initComponents();
    }
    
    public TelaFavoritasGrupo(int usuarioId){
        initComponents();
        
        desligarBotaoAnterior();
        this.usuarioId = usuarioId;
        this.controller = new VisualizarFavoritasGrupoController(this);
        
        controller.carregarHospedagens(usuarioId);
    }

    public int getPaginaAtual(){
        return pagina;
    }
    
    public void setPaginaAtualParaZero(){
        this.pagina = 0;
    }
    
    public void desligarBotaoAnterior(){
        this.anteriorButton.setVisible(false);
        this.anteriorButton.setEnabled(false);
    }
    
    public void ligarBotaoAnterior(){
        this.anteriorButton.setVisible(true);
        this.anteriorButton.setEnabled(true);
    }
    
    public void atualizarPagina(){
        this.revalidate();
        this.repaint();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        visualizacaoPanel = new javax.swing.JPanel();
        favoritasTituloLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        anteriorButton = new javax.swing.JButton();
        proximoButton = new javax.swing.JButton();
        selecionarGrupoButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(163, 187, 229));
        setLayout(new java.awt.GridLayout(1, 0));

        visualizacaoPanel.setBackground(new java.awt.Color(163, 187, 229));
        visualizacaoPanel.setLayout(new java.awt.GridBagLayout());

        favoritasTituloLabel.setFont(new java.awt.Font("JetBrainsMono NF", 1, 18)); // NOI18N
        favoritasTituloLabel.setText("Favoritas do grupo");
        favoritasTituloLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(15, 10, 10, 10);
        visualizacaoPanel.add(favoritasTituloLabel, gridBagConstraints);

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(102, 102, 102));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        visualizacaoPanel.add(jSeparator1, gridBagConstraints);

        anteriorButton.setFont(new java.awt.Font("JetBrainsMono NF", 0, 12)); // NOI18N
        anteriorButton.setText("Anterior");
        anteriorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anteriorButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 20, 5, 10);
        visualizacaoPanel.add(anteriorButton, gridBagConstraints);

        proximoButton.setFont(new java.awt.Font("JetBrainsMono NF", 0, 12)); // NOI18N
        proximoButton.setText("Próximo");
        proximoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proximoButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(15, 20, 5, 10);
        visualizacaoPanel.add(proximoButton, gridBagConstraints);

        selecionarGrupoButton.setFont(new java.awt.Font("JetBrainsMono NF", 0, 12)); // NOI18N
        selecionarGrupoButton.setText("Selecionar grupo");
        selecionarGrupoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selecionarGrupoButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(15, 10, 10, 10);
        visualizacaoPanel.add(selecionarGrupoButton, gridBagConstraints);

        jScrollPane1.setViewportView(visualizacaoPanel);

        add(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents

    private void selecionarGrupoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selecionarGrupoButtonActionPerformed
        // TODO add your handling code here:
        controller.alterarGrupo(usuarioId);
    }//GEN-LAST:event_selecionarGrupoButtonActionPerformed

    private void proximoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proximoButtonActionPerformed
        // TODO add your handling code here:
        controller.paginaSeguinte(usuarioId);
    }//GEN-LAST:event_proximoButtonActionPerformed

    private void anteriorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anteriorButtonActionPerformed
        // TODO add your handling code here:
        controller.paginaAnterior(usuarioId);
    }//GEN-LAST:event_anteriorButtonActionPerformed

    public void incrementarPagina(){
        pagina++;
    }
    
    public void decrementarPagina(){
        pagina--;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton anteriorButton;
    public javax.swing.JLabel favoritasTituloLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton proximoButton;
    private javax.swing.JButton selecionarGrupoButton;
    public javax.swing.JPanel visualizacaoPanel;
    // End of variables declaration//GEN-END:variables
}
