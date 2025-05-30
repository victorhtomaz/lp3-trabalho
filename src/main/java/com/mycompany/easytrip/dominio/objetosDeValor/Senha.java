package com.mycompany.easytrip.dominio.objetosDeValor;

import com.mycompany.easytrip.dominio.excecoes.SenhaException;
import com.mycompany.easytrip.dominio.interfaces.Validacao;

public class Senha implements Validacao {

    private String valor;
    public static final int TAMANHO_MINIMO = 6;

    public Senha(String valor) throws SenhaException{
        this.valor = valor;

        if (!eValido())
            throw new SenhaException("A senha precisa conter no minimo: " + TAMANHO_MINIMO + " caracteres");
    }

    @Override
    public boolean eValido() {
        if (valor.isBlank() || valor.length() < TAMANHO_MINIMO)
            return false;

        return true;
    }
    
    public String getValor(){
        return valor;
    }

    @Override
    public String toString() {
        return valor;
    }
}
