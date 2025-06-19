package com.mycompany.easytrip.controllers.servicos;

import com.mycompany.easytrip.controllers.servicos.excecoes.UsuarioServicoException;
import com.mycompany.easytrip.dominio.entidades.Usuario;
import com.mycompany.easytrip.dominio.objetosDeValor.Email;
import com.mycompany.easytrip.dominio.objetosDeValor.Senha;
import com.mycompany.easytrip.repositorio.Transacao;
import com.mycompany.easytrip.repositorio.UsuarioRepositorio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;

public class UsuarioServico {
    private final Transacao transacao;
    private final UsuarioRepositorio usuarioRepositorio;
    
    public UsuarioServico(EntityManager entityManager){
        this.transacao = new Transacao(entityManager);
        this.usuarioRepositorio = new UsuarioRepositorio(entityManager);
    }
    
    public void registrarUsuario(Usuario usuario) throws UsuarioServicoException{
            boolean existeEmail = usuarioRepositorio.existeUmUsuarioComEmail(usuario.getEnderecoEmail());
            if (existeEmail)
                throw new UsuarioServicoException("O email inserido já está cadastrado");
            
            boolean existeCpf = usuarioRepositorio.existeUmUsuarioComCpf(usuario.getValorCpf());
            if (existeCpf)
                throw new UsuarioServicoException("O cpf inserido já está cadastrado"); 
           
            
            try{
                transacao.iniciar();
                usuarioRepositorio.criarUsuario(usuario);
                transacao.confirmar();
            }
            catch(PersistenceException e){
                throw new UsuarioServicoException("Erro ao persistir os dados");
            }
    }
    
    public int checarLogin(Email email, Senha senha){
        try{
            return usuarioRepositorio.verificarLogin(email.getEndereco(), senha.getValor());
        }
        catch(NoResultException e){
            return 0;
        }
    }
}
