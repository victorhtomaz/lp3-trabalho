package com.mycompany.easytrip.dominio.excecoes;

public class SenhaException extends DominioException {
    public SenhaException(){
        super("O objeto Senha está inconsistente.");
    }
    public SenhaException(String mensagem) {
        super(mensagem);
    }
}
