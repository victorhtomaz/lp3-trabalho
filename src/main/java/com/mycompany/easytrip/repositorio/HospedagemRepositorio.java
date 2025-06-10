package com.mycompany.easytrip.repositorio;

import com.mycompany.easytrip.dominio.entidades.Hospedagem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;

public class HospedagemRepositorio {
    private final EntityManager em;
    
    public HospedagemRepositorio(EntityManager em){
        this.em = em;
    }
    
    public void criarHospedagem(Hospedagem hospedagem) throws PersistenceException{
        em.persist(hospedagem.getEndereco());
        em.persist(hospedagem);
    }
}
