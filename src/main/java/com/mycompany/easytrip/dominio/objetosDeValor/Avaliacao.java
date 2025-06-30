package com.mycompany.easytrip.dominio.objetosDeValor;

import com.mycompany.easytrip.dominio.excecoes.AvaliacaoException;
import com.mycompany.easytrip.dominio.excecoes.DominioException;
import com.mycompany.easytrip.dominio.interfaces.Validacao;
import jakarta.persistence.*;

@Embeddable
public class Avaliacao implements Validacao{
    private static final float NOTA_MINIMA = 0.00f;
    private static final float NOTA_MAXIMA = 5.00f;
    
    @Column(name = "Avaliacao")
    private float nota;
    
    protected Avaliacao(){}
    
    public Avaliacao(float nota)throws DominioException {
        this.nota = nota;
        
        validar();
    }
   
    @Override
    public void validar() throws DominioException {
        if(nota < NOTA_MINIMA || nota > NOTA_MAXIMA)
            throw new AvaliacaoException("A nota deve ser entre: " + NOTA_MINIMA +" e " + NOTA_MAXIMA);
    }
    
    public float getNota(){
        return nota;
    }
    
    @Override
    public String toString() {
        return Float.toString(nota);
    }
}
