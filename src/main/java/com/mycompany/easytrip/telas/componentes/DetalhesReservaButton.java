package com.mycompany.easytrip.telas.componentes;

import com.mycompany.easytrip.telas.TelaPrincipal;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;

public class DetalhesReservaButton extends javax.swing.JButton{
    
    public DetalhesReservaButton(){
        super("Detalhes");
        
        this.setFont(new Font("JetBrainsMono NF", Font.PLAIN, 12));
        
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaDetalhesReserva();
            }
        });
    }
    
    private void abrirTelaDetalhesReserva(){
        TelaPrincipal telaPrincipal = (TelaPrincipal)SwingUtilities.getWindowAncestor(this);
        telaPrincipal.mudarParaTelaDetalhesReserva();
    }
}
