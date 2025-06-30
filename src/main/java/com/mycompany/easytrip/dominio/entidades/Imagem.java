package com.mycompany.easytrip.dominio.entidades;

import com.mycompany.easytrip.dominio.excecoes.ImagemException;
import com.mycompany.easytrip.dominio.interfaces.Validacao;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Imagem")
public class Imagem implements Validacao{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    
    @Column(name = "Url")
    private String url;
    
    protected Imagem(){}
    
    public Imagem(String url) throws ImagemException{
        this.url = url;
        
        validar();
    }

    @Override
    public void validar() throws ImagemException {
        if (url.isBlank())
            throw new ImagemException("A url não pode ser vazia");
    }
    
    public int getId(){
        return id;
    }
    
    public String getUrl(){
        return url;
    }
    
    @Override
    public String toString() {
        return url;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == null || this.getClass() != obj.getClass())
            return false;
        
        Imagem that = (Imagem) obj;
        
        return this.id == that.id;
}

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
