package com.mycompany.easytrip.telas.componentes;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeletarHospedagemButton extends javax.swing.JButton{
    private int hospedagemId;
    
    private DeletarHospedagemButton(){
        super("Deletar");
        
        this.setFont(new Font("JetBrainsMono NF", Font.PLAIN, 12));
        
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }
    
    public DeletarHospedagemButton(int hospedagemId){
        this.hospedagemId = hospedagemId;
        this();
    }
}
