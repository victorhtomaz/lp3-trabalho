package com.mycompany.easytrip.dominio.excecoes;

public abstract class DominioException extends Exception {
    public DominioException(String mensagem) {
        super(mensagem);
    }
}
