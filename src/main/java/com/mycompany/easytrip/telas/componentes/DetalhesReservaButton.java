package com.mycompany.easytrip.telas.componentes;

import com.mycompany.easytrip.telas.TelaPrincipal;
import java.awt.Font;
import javax.swing.SwingUtilities;

public class DetalhesReservaButton extends javax.swing.JButton{
    
    public DetalhesReservaButton(){
        super("Ver detalhes");
        
        this.setFont(new Font("JetBrainsMono NF", Font.PLAIN, 12));
    }
    
    public void abrirTelaDetalhesReserva(int reservaId){
        TelaPrincipal telaPrincipal = (TelaPrincipal)SwingUtilities.getWindowAncestor(this);
        telaPrincipal.mudarParaTelaDetalhesReserva(reservaId);
    }
}
