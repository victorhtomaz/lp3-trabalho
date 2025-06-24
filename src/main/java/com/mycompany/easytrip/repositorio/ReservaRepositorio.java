package com.mycompany.easytrip.repositorio;

import com.mycompany.easytrip.dominio.entidades.Reserva;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ReservaRepositorio {
    private final EntityManager em;
    
    public ReservaRepositorio(EntityManager em){
        this.em = em;
    }
    
    public void criarReserva(Reserva reserva) throws PersistenceException{
        em.persist(reserva);
    }
    
    public List<Reserva> getReservasDoUsuario(int usuarioId){
        String consulta = "SELECT r FROM Reserva r WHERE r.usuario.id = :usuarioId ORDER BY r.dataEntrada DESC";
        
        TypedQuery<Reserva> query = em.createQuery(consulta, Reserva.class);
        query.setParameter("usuarioId", usuarioId);
        
        return query.getResultList();
    }
    
    public List<Reserva> getReservasDaHospedagem(int hospedagemId){
        String consulta = "SELECT r FROM Reserva r WHERE r.hospedagem.id = :hospedagemId ORDER BY r.dataEntrada DESC";
        
        TypedQuery<Reserva> query = em.createQuery(consulta, Reserva.class);
        query.setParameter("hospedagemId", hospedagemId);
        
        return query.getResultList();
    }
    
    public List<Reserva> getReservasPendentes(int usuarioId){
        String consulta = "SELECT r FROM Reserva r WHERE r.hospedagem.usuarioId = :usuarioId AND r.status = 'PENDENTE' "
                + "ORDER BY r.dataEntrada ASC";
        
        TypedQuery<Reserva> query = em.createQuery(consulta, Reserva.class);
        query.setParameter("usuarioId", usuarioId);
        
        return query.getResultList();
    }
    
    public Reserva getReserva(int reservaId) throws EntityNotFoundException{
        return em.find(Reserva.class, reservaId);
    }
    
    public void atualizarReserva(Reserva reserva) throws IllegalArgumentException{
        em.merge(reserva);
    }
    
    public int getQuantidadeReservasAvaliadasDaHospedagem(int hospedagemId){
        String consulta = "SELECT COUNT(r) FROM Reserva r WHERE r.hospedagem.id = :hospedagemId AND r.avaliacao IS NOT NULL";
        
        TypedQuery<Long> query = em.createQuery(consulta, Long.class);
        query.setParameter("hospedagemId", hospedagemId);
        
        return query.getSingleResult().intValue();
    }
}
