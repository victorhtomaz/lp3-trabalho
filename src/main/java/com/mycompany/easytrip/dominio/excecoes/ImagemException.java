package com.mycompany.easytrip.dominio.excecoes;

public class ImagemException extends DominioException {

    public ImagemException() {
        super("A entidade Imagem está inconsistente");
    }
    public ImagemException(String mensagem) {
        super(mensagem);
    }
}
