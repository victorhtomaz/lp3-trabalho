package com.mycompany.easytrip.telas.componentes;

import com.mycompany.easytrip.telas.TelaPrincipal;
import java.awt.Font;
import javax.swing.SwingUtilities;

public class GerenciarGrupoButton extends javax.swing.JButton{
    public GerenciarGrupoButton(){
        super("Gerenciar");
        
        this.setFont(new Font("JetBrainsMono NF", Font.PLAIN, 12));
    }
    
    public void mudarParaTelaGerenciar(int grupoId){
        TelaPrincipal telaPrincipal = (TelaPrincipal)SwingUtilities.getWindowAncestor(this);
        telaPrincipal.mudarParaTelaGerenciarGrupo(grupoId);
    }
}
