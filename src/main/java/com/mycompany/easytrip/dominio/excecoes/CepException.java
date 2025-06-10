package com.mycompany.easytrip.dominio.excecoes;

public class CepException extends DominioException {

    public CepException() {
        super("O objeto Cep está inconsistente");
    }
    public CepException(String mensagem) {
        super(mensagem);
    }
}
