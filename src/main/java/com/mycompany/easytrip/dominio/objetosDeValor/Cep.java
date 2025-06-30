package com.mycompany.easytrip.dominio.objetosDeValor;

import com.mycompany.easytrip.dominio.excecoes.CpfException;
import com.mycompany.easytrip.dominio.excecoes.DominioException;
import com.mycompany.easytrip.dominio.interfaces.Validacao;
import jakarta.persistence.*;

@Embeddable
public class Cep implements Validacao {
    @Column(name = "Cep")
    private String valor;
    public static final int TAMANHO = 8;
    
    protected Cep(){}
    
    public Cep(String valor) throws DominioException{
        StringBuilder aux = new StringBuilder();
        for (char c : valor.toCharArray()){
            if (Character.isDigit(c))
                aux.append(c);
        }
        
        this.valor = aux.toString();
        
        validar();
    }

    @Override
    public void validar() throws DominioException {
        if (valor.isBlank() || valor.length() != TAMANHO)
            throw new CpfException("Um Cep deve conter: " + TAMANHO + " digítos");
    }

    public String getValor(){
        return valor;
    }
    
    @Override
    public String toString() {
        return valor;
    }
    
    
}
