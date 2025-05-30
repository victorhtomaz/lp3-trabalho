package com.mycompany.easytrip.dominio.objetosDeValor;

import com.mycompany.easytrip.dominio.excecoes.CpfException;
import com.mycompany.easytrip.dominio.interfaces.Validacao;

public class Cpf implements Validacao {

    private String valor;
    public static final int TAMANHO = 11;

    public Cpf(String valor) throws CpfException{
        StringBuilder aux = new StringBuilder();
        for (char c : valor.toCharArray()){
            if (Character.isDigit(c))
                aux.append(c);
        }

        this.valor = aux.toString();

        if (!eValido())
            throw new CpfException("Um CPF deve conter: " + TAMANHO + " digítos");
    }

    @Override
    public boolean eValido() {
        if (valor.isBlank() || valor.length() != TAMANHO)
            return false;

        if (valor.chars().distinct().count() == 1)
            return false;

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
            return false;

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
            return false;

        return true;
    }
    
    public String getValor(){
        return valor;
    }

    @Override
    public String toString() {
        return valor;
    }
}