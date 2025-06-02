package com.mycompany.easytrip.telas.componentes;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GerenciarGrupoButton extends javax.swing.JButton{
    public GerenciarGrupoButton(){
        super("Gerenciar");
        
        this.setFont(new Font("JetBrainsMono NF", Font.PLAIN, 12));
        
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }
}
