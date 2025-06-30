package com.mycompany.easytrip.telas.componentes;

import com.mycompany.easytrip.telas.TelaPrincipal;
import java.awt.Font;
import javax.swing.SwingUtilities;

public class GerenciarHospedagemButton extends javax.swing.JButton{
    public GerenciarHospedagemButton(){
        super("Gerenciar");
        
        this.setFont(new Font("JetBrainsMono NF", Font.PLAIN, 12));
    }
    
    public void mudarParaTelaGerenciamentoDeHospedagem(int hospedagemId){
        TelaPrincipal telaPrincipal = (TelaPrincipal)SwingUtilities.getWindowAncestor(this);
        telaPrincipal.mudarParaTelaGerenciamentoHospedagem(hospedagemId);
    }
}
