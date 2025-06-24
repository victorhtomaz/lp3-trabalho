package com.mycompany.easytrip.dominio.excecoes;

public class ReservaException extends DominioException {
    public ReservaException() {
        super("A entidade Hospedagem está inconsistente");
    }
    public ReservaException(String mensagem) {
        super(mensagem);
    }
}
