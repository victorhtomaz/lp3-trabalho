package com.mycompany.easytrip.dominio.objetosDeValor;

import com.mycompany.easytrip.dominio.excecoes.SenhaException;
import com.mycompany.easytrip.dominio.interfaces.Validacao;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Senha implements Validacao {
    @Column(name = "Senha")
    private String valor;
    public static final int TAMANHO_MINIMO = 6;

    protected Senha(){  }
    
    public Senha(String valor) throws SenhaException{
        this.valor = valor;

        validar();
    }

    @Override
    public void validar() throws SenhaException{
        if (valor.isBlank() || valor.length() < TAMANHO_MINIMO)
            throw new SenhaException("A senha precisa conter no minimo: " + TAMANHO_MINIMO + " caracteres");
    }
    
    public String getValor(){
        return valor;
    }

    @Override
    public String toString() {
        return valor;
    }
}
