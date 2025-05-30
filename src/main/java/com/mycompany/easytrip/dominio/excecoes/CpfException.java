package com.mycompany.easytrip.dominio.excecoes;

public class CpfException extends DominioException {
    public CpfException(){
        super("O objeto Cpf está inconsistente.");
    }
    public CpfException(String mensagem) {
        super(mensagem);
    }
}
