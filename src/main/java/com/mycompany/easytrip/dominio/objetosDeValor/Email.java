package com.mycompany.easytrip.dominio.objetosDeValor;

import com.mycompany.easytrip.dominio.excecoes.EmailException;
import com.mycompany.easytrip.dominio.interfaces.Validacao;
import java.util.regex.Pattern;

public class Email implements Validacao {
    public static final Pattern PATTERN = Pattern.compile("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
    private String endereco;

    public Email(String endereço) throws EmailException{
        this.endereco = endereço.toLowerCase();

        if (!eValido())
            throw new EmailException("O Email não é valido");
    }

    @Override
    public boolean eValido(){
        if (endereco.isBlank() || !PATTERN.matcher(endereco).matches())
            return false;

        return true;
    }

    public String getEndereco() {
        return endereco;
    }

    @Override
    public String toString() {
        return endereco;
    }
}