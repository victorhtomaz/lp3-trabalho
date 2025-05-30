package com.mycompany.easytrip.telas.componentes;

import com.mycompany.easytrip.telas.TelaPrincipal;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;

public class GerenciarHospedagemButton extends javax.swing.JButton{
    private int hospedagemId;
    
    private GerenciarHospedagemButton(){
        super("Gerenciar");
        
        this.setFont(new Font("JetBrainsMono NF", Font.PLAIN, 12));
        
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirPaginaGerenciamento();
            }
        });
    }
    
    public GerenciarHospedagemButton(int hospedagemId){
        this.hospedagemId = hospedagemId;
        this();
    }
    
    private void abrirPaginaGerenciamento(){
        TelaPrincipal telaPrincipal = (TelaPrincipal)SwingUtilities.getWindowAncestor(this);
        telaPrincipal.mudarParaTelaGerenciamentoHospedagem(hospedagemId);
    }
}
