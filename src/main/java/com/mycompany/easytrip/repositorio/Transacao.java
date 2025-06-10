package com.mycompany.easytrip.repositorio;

import jakarta.persistence.EntityManager;

public class Transacao {
    private final EntityManager entityManager;
    
    public Transacao(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    
    public void iniciar(){
        entityManager.getTransaction().begin();
    }
    
    public void confirmar(){
        entityManager.getTransaction().commit();
    }
}
