package com.mycompany.easytrip.dominio.excecoes;

public class EnderecoException extends DominioException {

    public EnderecoException() {
        super("A entidade Endereço está inconsistente");
    }
    public EnderecoException(String mensagem) {
        super(mensagem);
    }
}
