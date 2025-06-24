package com.mycompany.easytrip.repositorio;

import com.mycompany.easytrip.dominio.entidades.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class UsuarioRepositorio {
    private final EntityManager em;
    
    public UsuarioRepositorio(EntityManager em){
        this.em = em;
    }
    
    public void criarUsuario(Usuario usuario) throws PersistenceException{
            em.persist(usuario);
    }
    
    public int verificarLogin(String email, String senha) throws NoResultException{
        String consulta = "SELECT u.Id FROM Usuario u WHERE u.email.endereco = :email AND u.senha.valor = :senha";
        TypedQuery<Integer> query = em.createQuery(consulta, Integer.class);
        query.setParameter("email", email);
        query.setParameter("senha", senha);
        
        return query.getSingleResult();
    }
    
    public Usuario getUsuario(int usuarioId){
        return em.find(Usuario.class, usuarioId);
    }
    
    public boolean existeUmUsuarioComEmail(String email){
        
        String consulta = "SELECT COUNT(u) > 0 FROM Usuario u WHERE u.email.endereco = :email";
        Query query = em.createQuery(consulta, Boolean.class);
        query.setParameter("email", email);
        
        boolean resultado = (boolean) query.getSingleResult();
        
        return resultado;
    }
    
    public boolean existeUmUsuarioComCpf(String cpf){
        String consulta = "SELECT COUNT(u) > 0 FROM Usuario u WHERE u.cpf.valor = :cpf";
        Query query = em.createQuery(consulta, Boolean.class);
        query.setParameter("cpf", cpf);
        
        boolean resultado = (boolean) query.getSingleResult();
        
        return resultado;
    }
    
    public void atualizarUsuario(Usuario usuario) throws IllegalArgumentException{
        em.merge(usuario);
    }
}
