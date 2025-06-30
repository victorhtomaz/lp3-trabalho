package com.mycompany.easytrip.dominio.excecoes;

public class GrupoException extends DominioException {
    public GrupoException() {
        super("A entidade Grupo está inconsistente");
    }
    public GrupoException(String mensagem) {
        super(mensagem);
    }
}
