package com.mycompany.easytrip.dominio.excecoes;

public class PrecoDiariaException extends DominioException {

    public PrecoDiariaException() {
        super("O objeto PrecoDiaria está inconsistente");
    }
    public PrecoDiariaException(String mensagem) {
        super(mensagem);
    }
}
