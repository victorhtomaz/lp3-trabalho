package com.mycompany.easytrip.dominio.excecoes;

public class UsuarioException extends DominioException {

    public UsuarioException() {
        super("A entidade Usuario está inconsistente");
    }
    public UsuarioException(String mensagem) {
        super(mensagem);
    }
}
