package com.mycompany.easytrip.repositorio;

import com.mycompany.easytrip.dominio.entidades.Grupo;
import com.mycompany.easytrip.dominio.entidades.HospedeGrupo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class HospedeGrupoRepositorio {
    private final EntityManager em;
    
    public HospedeGrupoRepositorio(EntityManager em){
        this.em = em;
    }
    
    public List<HospedeGrupo> getLigacoesDoUsuario(int usuarioId){
        String consulta = "SELECT hg FROM HospedeGrupo hg WHERE hg.usuario.id = :usuarioId";
        
        TypedQuery<HospedeGrupo> query = em.createQuery(consulta, HospedeGrupo.class);
        query.setParameter("usuarioId", usuarioId);
        
        return query.getResultList();
    }
    
    public HospedeGrupo getHospedeGrupo(int grupoId, int usuarioId) throws NoResultException{
        String consulta = "SELECT hg FROM HospedeGrupo hg WHERE hg.grupo.id = :grupoId AND hg.usuario.id = :usuarioId";
        
        TypedQuery<HospedeGrupo> query = em.createQuery(consulta, HospedeGrupo.class);
        query.setParameter("grupoId", grupoId);
        query.setParameter("usuarioId", usuarioId);
        
        return query.getSingleResult();
    }
    
    public List<Grupo> getGruposDoUsuario(int usuarioId){
        String consulta = "SELECT hg.grupo FROM HospedeGrupo hg WHERE hg.usuario.id = :usuarioId ORDER BY hg.grupo.nome";
        
        TypedQuery<Grupo> query = em.createQuery(consulta, Grupo.class);
        query.setParameter("usuarioId", usuarioId);
        
        return query.getResultList();
    }
    
    public void atualizarVinculo(HospedeGrupo hospedeGrupo) throws IllegalArgumentException{
        em.merge(hospedeGrupo);
    }
}
