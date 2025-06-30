package com.mycompany.easytrip.dominio.objetosDeValor;

import com.mycompany.easytrip.dominio.excecoes.CpfException;
import com.mycompany.easytrip.dominio.interfaces.Validacao;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Cpf implements Validacao {
    @Column(name = "Cpf")
    private String valor;
    public static final int TAMANHO = 11;

    protected Cpf(){  }

    public Cpf(String valor) throws CpfException{
        StringBuilder aux = new StringBuilder();
        for (char c : valor.toCharArray()){
            if (Character.isDigit(c))
                aux.append(c);
        }

        this.valor = aux.toString();

        validar();
    }

    @Override
    public void validar() throws CpfException{
        if (valor.isBlank() || valor.length() != TAMANHO)
            throw new CpfException("Um CPF deve conter: " + TAMANHO + " digítos");

        if (valor.chars().distinct().count() == 1)
            throw new CpfException("Um cpf nao pode conter todos digítos iguais");

        int digitoValidador;
        int multiplicador = 10;
        int soma = 0, resto;
        for (int i = 0; i < 9; i++) {
            int aux = Character.getNumericValue(valor.charAt(i));

            soma += aux * multiplicador;
            multiplicador--;
        }
        resto = soma % TAMANHO;
        digitoValidador = (resto < 2) ? 0 : TAMANHO - resto;

        if (digitoValidador != Character.getNumericValue(valor.charAt(TAMANHO - 2)))
            throw new CpfException("O cpf é inválido");

        multiplicador = 11;
        soma = 0;
        for (int i = 0; i < 10; i++) {
            int aux = Character.getNumericValue(valor.charAt(i));

            soma += aux * multiplicador;
            multiplicador--;
        }
        resto = soma % TAMANHO;
        digitoValidador = (resto < 2) ? 0 : TAMANHO - resto;

        if (digitoValidador != Character.getNumericValue(valor.charAt(TAMANHO - 1)))
            throw new CpfException("O cpf é inválido");
    }
    
    public String getValor(){
        return valor;
    }

    @Override
    public String toString() {
        return valor.substring(0, 3) + "." + valor.substring(3, 6) + "." + valor.substring(6, 9) + "-" +valor.substring(9, 11);
    }
}