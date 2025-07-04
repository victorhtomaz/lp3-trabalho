package com.mycompany.easytrip.telas.hospedagem;

import com.mycompany.easytrip.controllers.DetalhesHospedagemController;
import com.mycompany.easytrip.controllers.FavoritarHospedagemController;
import com.mycompany.easytrip.telas.TelaPrincipal;
import javax.swing.SwingUtilities;

public class TelaDetalhesHospedagem extends javax.swing.JPanel {

    private int hospedagemId;
    private DetalhesHospedagemController controller;
    private FavoritarHospedagemController favoritarController;

    
    public TelaDetalhesHospedagem() {
        initComponents();
    }
    
    public TelaDetalhesHospedagem(int hospedagemId){
        initComponents();
        this.hospedagemId = hospedagemId;
        this.controller = new DetalhesHospedagemController(this);
        this.favoritarController = new FavoritarHospedagemController();
        
        controller.carregarHospedagem(hospedagemId);
    }

    public void atualizarTela(){
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
        detalhesPanel = new javax.swing.JPanel();
        detalhesTituloLabel = new javax.swing.JLabel();
        tituloLabel = new javax.swing.JLabel();
        enderecoLabel = new javax.swing.JLabel();
        imagemPanel = new javax.swing.JPanel();
        imagemPanel1 = new com.mycompany.easytrip.telas.componentes.ImagemPanel();
        anteriorImagemButton = new javax.swing.JButton();
        proximaImagemButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        infosLateralPanel = new javax.swing.JPanel();
        tipoLabel = new javax.swing.JLabel();
        capacidadeMaximaLabel = new javax.swing.JLabel();
        checkInLabel = new javax.swing.JLabel();
        checkOutLabel = new javax.swing.JLabel();
        precoLabel = new javax.swing.JLabel();
        favoritarButton = new javax.swing.JButton();
        reservarButton = new javax.swing.JButton();
        descricaoLabel = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        descricaoTextArea = new javax.swing.JTextArea();
        voltarButton1 = new com.mycompany.easytrip.telas.componentes.VoltarButton();

        setBackground(new java.awt.Color(163, 187, 229));
        setLayout(new java.awt.GridLayout(1, 0));

        jScrollPane1.setBackground(new java.awt.Color(163, 187, 229));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        detalhesPanel.setBackground(new java.awt.Color(163, 187, 229));
        detalhesPanel.setLayout(new java.awt.GridBagLayout());

        detalhesTituloLabel.setFont(new java.awt.Font("JetBrainsMono NF", 1, 18)); // NOI18N
        detalhesTituloLabel.setText("Detalhes da hospedagem");
        detalhesTituloLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 10);
        detalhesPanel.add(detalhesTituloLabel, gridBagConstraints);

        tituloLabel.setFont(new java.awt.Font("JetBrainsMono NF", 0, 12)); // NOI18N
        tituloLabel.setText("Apartamento perto da praia, aiskasdaksdaskdasd");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 10);
        detalhesPanel.add(tituloLabel, gridBagConstraints);

        enderecoLabel.setFont(new java.awt.Font("JetBrainsMono NF", 0, 12)); // NOI18N
        enderecoLabel.setText("Rua da amizade, 135, ap 101, cabo frio, rj");
        enderecoLabel.setAutoscrolls(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 10, 10);
        detalhesPanel.add(enderecoLabel, gridBagConstraints);

        imagemPanel.setBackground(new java.awt.Color(163, 187, 229));
        imagemPanel.setMaximumSize(new java.awt.Dimension(205, 195));
        imagemPanel.setMinimumSize(new java.awt.Dimension(205, 195));

        javax.swing.GroupLayout imagemPanel1Layout = new javax.swing.GroupLayout(imagemPanel1);
        imagemPanel1.setLayout(imagemPanel1Layout);
        imagemPanel1Layout.setHorizontalGroup(
            imagemPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        imagemPanel1Layout.setVerticalGroup(
            imagemPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 178, Short.MAX_VALUE)
        );

        anteriorImagemButton.setFont(new java.awt.Font("JetBrainsMono NF", 0, 12)); // NOI18N
        anteriorImagemButton.setText("<-");
        anteriorImagemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anteriorImagemButtonActionPerformed(evt);
            }
        });

        proximaImagemButton.setFont(new java.awt.Font("JetBrainsMono NF", 0, 12)); // NOI18N
        proximaImagemButton.setText("->");
        proximaImagemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proximaImagemButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout imagemPanelLayout = new javax.swing.GroupLayout(imagemPanel);
        imagemPanel.setLayout(imagemPanelLayout);
        imagemPanelLayout.setHorizontalGroup(
            imagemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagemPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(anteriorImagemButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 152, Short.MAX_VALUE)
                .addComponent(proximaImagemButton)
                .addContainerGap())
            .addComponent(imagemPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        imagemPanelLayout.setVerticalGroup(
            imagemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagemPanelLayout.createSequentialGroup()
                .addComponent(imagemPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(imagemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(anteriorImagemButton)
                    .addComponent(proximaImagemButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 10);
        detalhesPanel.add(imagemPanel, gridBagConstraints);

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(102, 102, 102));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        detalhesPanel.add(jSeparator1, gridBagConstraints);

        infosLateralPanel.setBackground(new java.awt.Color(163, 187, 229));
        infosLateralPanel.setPreferredSize(new java.awt.Dimension(170, 170));
        infosLateralPanel.setLayout(new java.awt.GridLayout(7, 0, 0, 4));

        tipoLabel.setFont(new java.awt.Font("JetBrainsMono NF", 0, 12)); // NOI18N
        tipoLabel.setText("Tipo: Apartamento");
        infosLateralPanel.add(tipoLabel);

        capacidadeMaximaLabel.setFont(new java.awt.Font("JetBrainsMono NF", 0, 12)); // NOI18N
        capacidadeMaximaLabel.setText("Capacidade Maxima: 5");
        infosLateralPanel.add(capacidadeMaximaLabel);

        checkInLabel.setFont(new java.awt.Font("JetBrainsMono NF", 0, 12)); // NOI18N
        checkInLabel.setText("CheckIn: 12:00:00");
        infosLateralPanel.add(checkInLabel);

        checkOutLabel.setFont(new java.awt.Font("JetBrainsMono NF", 0, 12)); // NOI18N
        checkOutLabel.setText("CheckOut: 15:00:00");
        infosLateralPanel.add(checkOutLabel);

        precoLabel.setFont(new java.awt.Font("JetBrainsMono NF", 0, 12)); // NOI18N
        precoLabel.setText("Preço: R$500.00");
        infosLateralPanel.add(precoLabel);

        favoritarButton.setFont(new java.awt.Font("JetBrainsMono NF", 0, 12)); // NOI18N
        favoritarButton.setText("Favoritar");
        favoritarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoritarButtonActionPerformed(evt);
            }
        });
        infosLateralPanel.add(favoritarButton);

        reservarButton.setFont(new java.awt.Font("JetBrainsMono NF", 0, 12)); // NOI18N
        reservarButton.setText("Reservar");
        reservarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reservarButtonActionPerformed(evt);
            }
        });
        infosLateralPanel.add(reservarButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 10);
        detalhesPanel.add(infosLateralPanel, gridBagConstraints);

        descricaoLabel.setFont(new java.awt.Font("JetBrainsMono NF", 0, 12)); // NOI18N
        descricaoLabel.setText("Descrição");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 10);
        detalhesPanel.add(descricaoLabel, gridBagConstraints);

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        detalhesPanel.add(jSeparator2, gridBagConstraints);

        descricaoTextArea.setColumns(20);
        descricaoTextArea.setRows(5);
        descricaoTextArea.setEnabled(false);
        jScrollPane2.setViewportView(descricaoTextArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 5, 10);
        detalhesPanel.add(jScrollPane2, gridBagConstraints);

        voltarButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 10);
        detalhesPanel.add(voltarButton1, gridBagConstraints);

        jScrollPane1.setViewportView(detalhesPanel);

        add(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents

    private void proximaImagemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proximaImagemButtonActionPerformed
        // TODO add your handling code here:
        controller.proximaImagem();
    }//GEN-LAST:event_proximaImagemButtonActionPerformed

    private void reservarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reservarButtonActionPerformed
        // TODO add your handling code here:
        abrirTelaDeRealizarHospedagem();
    }//GEN-LAST:event_reservarButtonActionPerformed

    private void anteriorImagemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anteriorImagemButtonActionPerformed
        // TODO add your handling code here:
        controller.anteriorImagem();
    }//GEN-LAST:event_anteriorImagemButtonActionPerformed

    private void voltarButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarButton1ActionPerformed
        // TODO add your handling code here:
        voltarParaTelaVisualizacao();
    }//GEN-LAST:event_voltarButton1ActionPerformed

    private void favoritarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoritarButtonActionPerformed
        // TODO add your handling code here:
        favoritarController.favoritarHospedagem(getUsuarioLogadoId(), hospedagemId);
    }//GEN-LAST:event_favoritarButtonActionPerformed

    private int getUsuarioLogadoId(){
        TelaPrincipal telaPrincipal = (TelaPrincipal)SwingUtilities.getWindowAncestor(this);
        return telaPrincipal.getUsuarioLogado();
    }
    
    private void voltarParaTelaVisualizacao(){
        TelaPrincipal telaPrincipal = (TelaPrincipal)SwingUtilities.getWindowAncestor(this);
        telaPrincipal.voltarPagina();
    }
    
    private void abrirTelaDeRealizarHospedagem(){
        TelaPrincipal telaPrincipal = (TelaPrincipal)SwingUtilities.getWindowAncestor(this);
        telaPrincipal.mudarParaTelaRealizarReserva(this.hospedagemId);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton anteriorImagemButton;
    public javax.swing.JLabel capacidadeMaximaLabel;
    public javax.swing.JLabel checkInLabel;
    public javax.swing.JLabel checkOutLabel;
    private javax.swing.JLabel descricaoLabel;
    public javax.swing.JTextArea descricaoTextArea;
    private javax.swing.JPanel detalhesPanel;
    private javax.swing.JLabel detalhesTituloLabel;
    public javax.swing.JLabel enderecoLabel;
    private javax.swing.JButton favoritarButton;
    private javax.swing.JPanel imagemPanel;
    public com.mycompany.easytrip.telas.componentes.ImagemPanel imagemPanel1;
    private javax.swing.JPanel infosLateralPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    public javax.swing.JLabel precoLabel;
    private javax.swing.JButton proximaImagemButton;
    private javax.swing.JButton reservarButton;
    public javax.swing.JLabel tipoLabel;
    public javax.swing.JLabel tituloLabel;
    private com.mycompany.easytrip.telas.componentes.VoltarButton voltarButton1;
    // End of variables declaration//GEN-END:variables
}
