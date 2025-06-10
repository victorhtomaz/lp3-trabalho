package com.mycompany.easytrip.dominio.excecoes;

public class DisponibilidadeException extends DominioException {

    public DisponibilidadeException() {
        super("A entidade Disponibilidade está inconsistente");
    }
    public DisponibilidadeException(String mensagem) {
        super(mensagem);
    }
}
