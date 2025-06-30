package com.mycompany.easytrip.telas.componentes;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class ImagemPanel extends javax.swing.JPanel{
    private Image imagem;
    
    public ImagemPanel(){
        super();
    }
    
    public ImagemPanel(String caminhoDaImagem){
        super();

        setImagem(caminhoDaImagem);
    }
    
    public final void setImagem(String caminhoDaImagem){
        try{
            this.imagem = ImageIO.read(new File(caminhoDaImagem));
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "Erro ao ler imagem", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g.drawImage(imagem, 0, 0, getWidth(), getHeight(), null);
    }
}
