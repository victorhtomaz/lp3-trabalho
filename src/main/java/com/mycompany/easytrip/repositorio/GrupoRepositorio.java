package com.mycompany.easytrip.repositorio;

import com.mycompany.easytrip.dominio.entidades.Grupo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;

public class GrupoRepositorio {
    private final EntityManager em;
    
    public GrupoRepositorio(EntityManager em){
        this.em = em;
    }
    
    public void criarGrupo(Grupo grupo) throws PersistenceException{
        em.persist(grupo);
    }
    
    public Grupo getGrupo(int grupoId) throws EntityNotFoundException{
        return em.find(Grupo.class, grupoId);
    }
    
    public void atualizarGrupo(Grupo grupo) throws IllegalArgumentException{
        em.merge(grupo);
    }
}
