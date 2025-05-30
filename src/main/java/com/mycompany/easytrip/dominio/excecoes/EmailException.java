package com.mycompany.easytrip.dominio.excecoes;

public class EmailException extends DominioException {
    public EmailException(){
        super("O objeto Email está inconsistente.");
    }
    public EmailException(String mensagem) {
        super(mensagem);
    }
}
