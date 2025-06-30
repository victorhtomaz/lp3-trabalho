package com.mycompany.easytrip.dominio.excecoes;

public class AvaliacaoException extends DominioException {

    public AvaliacaoException() {
        super("O objeto Avaliacao está inconsistente");
    }
    public AvaliacaoException(String mensagem) {
        super(mensagem);
    }
}
