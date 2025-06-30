package com.mycompany.easytrip.dominio.objetosDeValor;

import com.mycompany.easytrip.dominio.excecoes.EmailException;
import com.mycompany.easytrip.dominio.interfaces.Validacao;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.regex.Pattern;

@Embeddable
public class Email implements Validacao {
    public static final Pattern PATTERN = Pattern.compile("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
    
    @Column(name = "Email")
    private String endereco;

    //Para Hibernate
    protected Email(){  }
    
    public Email(String endereço) throws EmailException{
        this.endereco = endereço.toLowerCase();

        validar();
    }

    @Override
    public void validar() throws EmailException{
        if (endereco.isBlank() || !PATTERN.matcher(endereco).matches())
            throw new EmailException("O Email não é valido");
    }

    public String getEndereco() {
        return endereco;
    }

    @Override
    public String toString() {
        return endereco;
    }
}