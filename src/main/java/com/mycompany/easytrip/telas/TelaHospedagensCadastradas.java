package com.mycompany.easytrip.telas;

import com.mycompany.easytrip.telas.componentes.HospedagemCadastradasPanel;
import java.awt.*;
import javax.swing.SwingUtilities;

public class TelaHospedagensCadastradas extends javax.swing.JPanel {

    public TelaHospedagensCadastradas() {
        initComponents();
        teste();
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

        hospedagensTituloLabel = new javax.swing.JLabel();
        adicionarHospedagemButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(163, 187, 229));
        setPreferredSize(new java.awt.Dimension(534, 330));
        setLayout(new java.awt.GridBagLayout());

        hospedagensTituloLabel.setFont(new java.awt.Font("JetBrainsMono NF", 1, 18)); // NOI18N
        hospedagensTituloLabel.setText("Minhas hospedagens");
        hospedagensTituloLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 20, 0, 10);
        add(hospedagensTituloLabel, gridBagConstraints);

        adicionarHospedagemButton.setFont(new java.awt.Font("JetBrainsMono NF", 0, 12)); // NOI18N
        adicionarHospedagemButton.setText("Adicionar Hospedagem");
        adicionarHospedagemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarHospedagemButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 10, 10, 10);
        add(adicionarHospedagemButton, gridBagConstraints);

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(102, 102, 102));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weighty = 1.0;
        add(jSeparator1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void adicionarHospedagemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarHospedagemButtonActionPerformed
        // TODO add your handling code here:
        mudarParaTelaCriarHospedagem();
    }//GEN-LAST:event_adicionarHospedagemButtonActionPerformed


    private void teste(){
        GridBagLayout layout = (GridBagLayout) this.getLayout();
        GridBagConstraints constraints = layout.getConstraints(this);
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridx = 0;
        constraints.insets.set(15, 20, 0, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 2;
        
        
        for(int i = 0; i < 5; i++){
            constraints.gridy = i + 3;
            
            HospedagemCadastradasPanel panel = new HospedagemCadastradasPanel(i);
            
            this.add(panel, constraints);
        }
        /*constraints.gridy = 2;
        var avaliação = new JLabel("Nao há nenhuma hospedagem registrada");
        this.add(avaliação, constraints);*/
        this.revalidate();
        this.repaint();
    }
    
    public void mudarParaTelaCriarHospedagem(){
        TelaPrincipal telaPrincipal = (TelaPrincipal)SwingUtilities.getWindowAncestor(this);
        telaPrincipal.mudarParaTelaCriarHospedagem();
    }
    
    public void mudarParaTelaGerenciamento(int hospedagemId){
        TelaPrincipal telaPrincipal = (TelaPrincipal)SwingUtilities.getWindowAncestor(this);
        telaPrincipal.mudarParaTelaGerenciamentoHospedagem(hospedagemId);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adicionarHospedagemButton;
    private javax.swing.JLabel hospedagensTituloLabel;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
