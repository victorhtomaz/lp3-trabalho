package com.mycompany.easytrip.telas.componentes;

import com.mycompany.easytrip.telas.TelaPrincipal;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;

public class ReservasDaHospedagemButton extends javax.swing.JButton{
    
    public ReservasDaHospedagemButton(){
        super("Reservas");
        
        this.setFont(new Font("JetBrainsMono NF", Font.PLAIN, 12));
        
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mudarParaTelaReservas();
            }
        });
    }
    
    private void mudarParaTelaReservas(){
        TelaPrincipal telaPrincipal = (TelaPrincipal)SwingUtilities.getWindowAncestor(this);
        telaPrincipal.mudarParaTelaReservasDaHospedagem();
    }
}
