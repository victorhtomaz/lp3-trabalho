package com.mycompany.easytrip.telas.hospedagem;

import com.mycompany.easytrip.telas.TelaPrincipal;
import javax.swing.SwingUtilities;

public class TelaDetalhesHospedagem extends javax.swing.JPanel {

    public TelaDetalhesHospedagem() {
        initComponents();
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
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
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
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 10);
        detalhesPanel.add(tituloLabel, gridBagConstraints);

        enderecoLabel.setFont(new java.awt.Font("JetBrainsMono NF", 0, 12)); // NOI18N
        enderecoLabel.setText("Rua da amizade, 135, ap 101, cabo frio, rj");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 10, 10);
        detalhesPanel.add(enderecoLabel, gridBagConstraints);

        imagemPanel.setBackground(new java.awt.Color(163, 187, 229));
        imagemPanel.setMaximumSize(new java.awt.Dimension(205, 195));
        imagemPanel.setMinimumSize(new java.awt.Dimension(205, 195));

        jPanel3.setBackground(new java.awt.Color(0, 0, 51));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );

        jButton1.setFont(new java.awt.Font("JetBrainsMono NF", 0, 12)); // NOI18N
        jButton1.setText("Anterior");

        jButton2.setFont(new java.awt.Font("JetBrainsMono NF", 0, 12)); // NOI18N
        jButton2.setText("Próximo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout imagemPanelLayout = new javax.swing.GroupLayout(imagemPanel);
        imagemPanel.setLayout(imagemPanelLayout);
        imagemPanelLayout.setHorizontalGroup(
            imagemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(imagemPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );
        imagemPanelLayout.setVerticalGroup(
            imagemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagemPanelLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(imagemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
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
        infosLateralPanel.setPreferredSize(new java.awt.Dimension(150, 150));
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
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 10);
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
        jScrollPane2.setViewportView(descricaoTextArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 5, 10);
        detalhesPanel.add(jScrollPane2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 10);
        detalhesPanel.add(voltarButton1, gridBagConstraints);

        jScrollPane1.setViewportView(detalhesPanel);

        add(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void reservarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reservarButtonActionPerformed
        // TODO add your handling code here:
        abrirTelaDeRealizarHospedagem();
    }//GEN-LAST:event_reservarButtonActionPerformed

    private void abrirTelaDeRealizarHospedagem(){
        TelaPrincipal telaPrincipal = (TelaPrincipal)SwingUtilities.getWindowAncestor(this);
        telaPrincipal.mudarParaTelaRealizarReserva();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel capacidadeMaximaLabel;
    private javax.swing.JLabel checkInLabel;
    private javax.swing.JLabel checkOutLabel;
    private javax.swing.JLabel descricaoLabel;
    private javax.swing.JTextArea descricaoTextArea;
    private javax.swing.JPanel detalhesPanel;
    private javax.swing.JLabel detalhesTituloLabel;
    private javax.swing.JLabel enderecoLabel;
    private javax.swing.JButton favoritarButton;
    private javax.swing.JPanel imagemPanel;
    private javax.swing.JPanel infosLateralPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel precoLabel;
    private javax.swing.JButton reservarButton;
    private javax.swing.JLabel tipoLabel;
    private javax.swing.JLabel tituloLabel;
    private com.mycompany.easytrip.telas.componentes.VoltarButton voltarButton1;
    // End of variables declaration//GEN-END:variables
}
