package com.mycompany.easytrip.dominio.excecoes;

public class HospedagemException extends DominioException {

    public HospedagemException() {
        super("A entidade Hospedagem está inconsistente");
    }
    public HospedagemException(String mensagem) {
        super(mensagem);
    }
}
