package com.mycompany.easytrip.telas.componentes;

import javax.swing.filechooser.FileNameExtensionFilter;

public class EscolhedorDeImagem extends javax.swing.JFileChooser {
    
    public EscolhedorDeImagem(){
        super();
        
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG ou PNG", "jpg", "png", "jpeg");
        this.setFileFilter(filtro);
    }
}
