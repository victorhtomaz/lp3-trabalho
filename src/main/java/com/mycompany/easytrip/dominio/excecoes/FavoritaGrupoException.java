package com.mycompany.easytrip.dominio.excecoes;

public class FavoritaGrupoException extends DominioException {
    public FavoritaGrupoException() {
        super("A entidade FavoritaGrupo está inconsistente");
    }
    public FavoritaGrupoException(String mensagem) {
        super(mensagem);
    }
}
