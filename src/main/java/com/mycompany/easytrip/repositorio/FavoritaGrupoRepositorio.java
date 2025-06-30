package com.mycompany.easytrip.repositorio;

import com.mycompany.easytrip.dominio.entidades.Hospedagem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FavoritaGrupoRepositorio {
    private final EntityManager em;
    
    public FavoritaGrupoRepositorio(EntityManager em){
        this.em = em;
    }
    
    public List<Hospedagem> getHospedagensFavoritasDoGrupo(int grupoId){
        String consulta = "SELECT fg.hospedagem FROM FavoritaGrupo fg WHERE fg.grupo.id = :grupoId";
        
        TypedQuery<Hospedagem> query = em.createQuery(consulta, Hospedagem.class);
        query.setParameter("grupoId", grupoId);
        
        return query.getResultList();
    }
    
    public Map<Hospedagem, Long> getHospedagensRankingVotos(int grupoId){
        String consulta = "SELECT fg.hospedagem, COUNT(vf) as totalVotos FROM FavoritaGrupo fg LEFT JOIN fg.votacoes vf "
                + "WHERE fg.grupo.id = :grupoId "
                + "GROUP BY fg.hospedagem ORDER BY totalVotos DESC";
        TypedQuery<Object[]> query = em.createQuery(consulta, Object[].class);
        query.setParameter("grupoId", grupoId);
        List<Object[]> resultados = query.getResultList();
        
        Map<Hospedagem, Long> hospedagemEVotos = new LinkedHashMap<>();
        for (Object[] resultado : resultados){
            Hospedagem hospedagem = (Hospedagem) resultado[0];
            Long totalVotos = (Long) resultado[1];
            hospedagemEVotos.put(hospedagem, totalVotos);
        }
        
        return hospedagemEVotos;
    }
}
