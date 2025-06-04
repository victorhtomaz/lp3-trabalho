package com.mycompany.easytrip;

import com.mycompany.easytrip.telas.TelaPrincipal;
import javax.swing.JFrame;

public class EasyTrip {

    public static void main(String[] args) {
        var tela = new TelaPrincipal();
       
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setLocationRelativeTo(null);

        tela.setVisible(true);
    }
}
