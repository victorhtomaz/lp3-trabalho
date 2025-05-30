package com.mycompany.easytrip;

import com.mycompany.easytrip.telas.TelaPrincipal;
import com.mycompany.easytrip.telas.TelaFavoritasHospede;

public class EasyTrip {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        var a = new TelaPrincipal();
       
        a.configurarEstadoMenu(true);
        var b = new TelaFavoritasHospede();
        
        a.limparTela();
        a.add(b);
        
        a.setVisible(true);
    }
}
