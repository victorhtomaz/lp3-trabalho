package com.mycompany.easytrip.dominio.objetosDeValor;

import com.mycompany.easytrip.dominio.excecoes.DominioException;
import com.mycompany.easytrip.dominio.excecoes.PrecoDiariaException;
import com.mycompany.easytrip.dominio.interfaces.Validacao;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

@Embeddable
public class PrecoDiaria implements Validacao{
    private static final Currency moeda = Currency.getInstance("BRL");
    
    @Column(name = "PrecoDiaria")
    private BigDecimal valor;
    
    protected PrecoDiaria(){}
    
    public PrecoDiaria(BigDecimal valor) throws DominioException{
        this.valor = valor.setScale(2, RoundingMode.HALF_UP);
        
        validar();
    }

    @Override
    public void validar() throws DominioException {
        if (valor.compareTo(BigDecimal.ZERO) < 0)
            throw new PrecoDiariaException("O valor não pode ser negativo");
    }

    public BigDecimal getValor(){
        return valor;
    }
    
    @Override
    public String toString() {
        return moeda.getSymbol() + " " + valor.toString();
    }
}
