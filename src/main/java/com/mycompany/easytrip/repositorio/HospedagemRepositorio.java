package com.mycompany.easytrip.repositorio;

import com.mycompany.easytrip.dominio.entidades.Hospedagem;
import com.mycompany.easytrip.dominio.enums.EstadoBrasil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class HospedagemRepositorio {
    private final EntityManager em;
    
    public HospedagemRepositorio(EntityManager em){
        this.em = em;
    }
    
    public void criarHospedagem(Hospedagem hospedagem) throws PersistenceException{
        em.persist(hospedagem);
    }
    
    public void deletarHospedagem(Hospedagem hospedagem){
        em.remove(hospedagem);
    }
    
    public Hospedagem getHospedagem(int hospedagemId) throws EntityNotFoundException{
        return em.find(Hospedagem.class, hospedagemId);
    }
    
    public List<Hospedagem> getHospedagensDoUsuario(int usuarioId){
        String consulta = "SELECT h FROM Hospedagem h WHERE h.usuarioId = :usuarioId";
        
        TypedQuery<Hospedagem> query = em.createQuery(consulta, Hospedagem.class);
        query.setParameter("usuarioId", usuarioId);
        
        return query.getResultList();
    }
    
    public void atualizarHospedagem(Hospedagem hospedagem) throws IllegalArgumentException{
        em.merge(hospedagem);
    }
    
    public List<Hospedagem> getHospedagens(int pagina, int quantidadePorPagina, String cidade, EstadoBrasil estado){
        StringBuilder construtorConsulta = new StringBuilder("SELECT h FROM Hospedagem h WHERE SIZE(h.imagens) >= 1");
        
        boolean cidadeNaoNulo = (cidade != null);
        boolean estadoNaoNulo = (estado != null);
        
        if (cidadeNaoNulo)
            construtorConsulta.append(" AND h.endereco.cidade LIKE :cidade");
        
        if (estadoNaoNulo)
            construtorConsulta.append(" AND h.endereco.estado = :estado");
            
        String consulta = construtorConsulta.toString();
        TypedQuery<Hospedagem> query = em.createQuery(consulta, Hospedagem.class);
        
        if (cidadeNaoNulo)
            query.setParameter("cidade", "%"+cidade+"%");
        
        if (estadoNaoNulo)
            query.setParameter("estado", estado);
        
        query.setFirstResult(pagina * quantidadePorPagina);
        query.setMaxResults(quantidadePorPagina);
        
        return query.getResultList();
    }    
}
