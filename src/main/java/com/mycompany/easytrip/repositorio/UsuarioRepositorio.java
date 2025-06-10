package com.mycompany.easytrip.repositorio;

import com.mycompany.easytrip.dominio.entidades.Usuario;
import com.mycompany.easytrip.dominio.objetosDeValor.Cpf;
import com.mycompany.easytrip.dominio.objetosDeValor.Email;
import com.mycompany.easytrip.dominio.objetosDeValor.Senha;
import jakarta.persistence.EntityManager;
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
    
    /*public void criarUsuario(Usuario usuario) throws SQLException, DominioException {
        try(Connection conexao = MinhaConexao.obterConexao()){
            String comando = "INSERT INTO Usuario(Nome, Email, Senha, DataNascimento, CPF)"
                    + "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement stmt = conexao.prepareStatement(comando);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEnderecoEmail());
            stmt.setString(3, usuario.getValorSenha());
            stmt.setDate(4, Date.valueOf(usuario.getDataNascimento()));
            stmt.setString(5, usuario.getValorCpf());

            stmt.executeUpdate();
            
            
        }
    }*/
    
    public Usuario verificarLogin(Email email, Senha senha) throws PersistenceException{
        
        String consulta = "SELECT u FROM Usuario u WHERE u.email.endereco = :email AND u.senha.valor = :senha";
        TypedQuery<Usuario> query = em.createQuery(consulta, Usuario.class);
        query.setParameter("email", email.getEndereco());
        query.setParameter("senha", senha.getValor());
        
        return query.getSingleResultOrNull();
    }
    
    public boolean existeUmUsuarioComEmail(Email email) throws PersistenceException{
        
        String consulta = "SELECT COUNT(u) > 0 FROM Usuario u WHERE u.email.endereco = :email";
        Query query = em.createQuery(consulta, Boolean.class);
        query.setParameter("email", email.getEndereco());
        
        boolean resultado = (boolean) query.getSingleResult();
        
        return resultado;
    }
    
    public boolean existeUmUsuarioComCpf(Cpf cpf) throws PersistenceException{
        String consulta = "SELECT COUNT(u) > 0 FROM Usuario u WHERE u.cpf.valor = :cpf";
        Query query = em.createQuery(consulta, Boolean.class);
        query.setParameter("cpf", cpf.getValor());
        
        boolean resultado = (boolean) query.getSingleResult();
        
        return resultado;
    }
}
