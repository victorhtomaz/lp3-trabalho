package com.mycompany.easytrip.dominio.excecoes;

public class HospedeGrupoException extends DominioException {
    public HospedeGrupoException() {
        super("A entidade HospedeGrupo está inconsistente");
    }
    public HospedeGrupoException(String mensagem) {
        super(mensagem);
    }
}
