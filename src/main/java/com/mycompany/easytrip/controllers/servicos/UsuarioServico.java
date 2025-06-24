package com.mycompany.easytrip.controllers.servicos;

import com.mycompany.easytrip.controllers.servicos.excecoes.UsuarioServicoException;
import com.mycompany.easytrip.dominio.entidades.Hospedagem;
import com.mycompany.easytrip.dominio.entidades.Usuario;
import com.mycompany.easytrip.dominio.excecoes.DominioException;
import com.mycompany.easytrip.dominio.objetosDeValor.Email;
import com.mycompany.easytrip.dominio.objetosDeValor.Senha;
import com.mycompany.easytrip.repositorio.HospedagemRepositorio;
import com.mycompany.easytrip.repositorio.Transacao;
import com.mycompany.easytrip.repositorio.UsuarioRepositorio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import java.util.List;

public class UsuarioServico {
    private final Transacao transacao;
    private final UsuarioRepositorio usuarioRepositorio;
    private final HospedagemRepositorio hospedagemRepositorio;
    
    public UsuarioServico(EntityManager entityManager){
        this.transacao = new Transacao(entityManager);
        this.usuarioRepositorio = new UsuarioRepositorio(entityManager);
        this.hospedagemRepositorio = new HospedagemRepositorio(entityManager);
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
    
    public List<Hospedagem> listarHospedagensFavoritasDoUsuario(int usuarioId, int pagina, int quantidadePorPagina){
        Usuario usuario = usuarioRepositorio.getUsuario(usuarioId);
        
        List<Hospedagem> listaFavoritas = usuario.getHospedagensFavoritas();
        
        return listaFavoritas.stream().skip(pagina * quantidadePorPagina).limit(quantidadePorPagina).toList();
    }
    
    public boolean usuarioFavoritouHospedagem(int usuarioId, int hospedagemId){
        Usuario usuario = usuarioRepositorio.getUsuario(usuarioId);
        
        return usuario.aHospedagemEstaFavoritada(hospedagemId);
    }
    
    public void favoritarHospedagem(int usuarioId, int hospedagemId) throws UsuarioServicoException{
        try{
            Usuario usuario = usuarioRepositorio.getUsuario(usuarioId);
            Hospedagem hospedagem = hospedagemRepositorio.getHospedagem(hospedagemId);
            
            usuario.adicionarHospedagemFavorita(hospedagem);
            
            transacao.iniciar();
            usuarioRepositorio.atualizarUsuario(usuario);
            transacao.confirmar();
        }
        catch(DominioException e){
            throw new UsuarioServicoException(e.getMessage());
        }
        catch(EntityNotFoundException | IllegalArgumentException e){
            throw new UsuarioServicoException("Falha ao persitir dados");
        }
    }
    
    public void desfavoritarHospedagem(int usuarioId, int hospedagemId)throws UsuarioServicoException{
        try{
            Usuario usuario = usuarioRepositorio.getUsuario(usuarioId);
            Hospedagem hospedagem = hospedagemRepositorio.getHospedagem(hospedagemId);
            
            usuario.removerHospedagemFavorita(hospedagem);
            
            transacao.iniciar();
            usuarioRepositorio.atualizarUsuario(usuario);
            transacao.confirmar();
        }
        catch(EntityNotFoundException | IllegalArgumentException e){
            throw new UsuarioServicoException("Falha ao persitir dados");
        }
    }
}
