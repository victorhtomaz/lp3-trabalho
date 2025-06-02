package com.mycompany.easytrip;

import com.mycompany.easytrip.telas.TelaPrincipal;
import com.mycompany.easytrip.telas.grupos.TelaVisualizarGrupos;

public class EasyTrip {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        var a = new TelaPrincipal();
       
        a.configurarEstadoMenu(true);
        a.limparTela();
        var b = new TelaVisualizarGrupos();
        
        a.add(b);
        a.setVisible(true);
    }
}
