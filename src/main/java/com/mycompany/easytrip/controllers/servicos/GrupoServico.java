package com.mycompany.easytrip.controllers.servicos;

import com.mycompany.easytrip.controllers.servicos.excecoes.GrupoServicoException;
import com.mycompany.easytrip.dominio.entidades.Grupo;
import com.mycompany.easytrip.dominio.entidades.Hospedagem;
import com.mycompany.easytrip.dominio.entidades.HospedeGrupo;
import com.mycompany.easytrip.dominio.entidades.Usuario;
import com.mycompany.easytrip.dominio.enums.FuncaoGrupo;
import com.mycompany.easytrip.dominio.excecoes.DominioException;
import com.mycompany.easytrip.dominio.excecoes.GrupoException;
import com.mycompany.easytrip.dominio.objetosDeValor.Email;
import com.mycompany.easytrip.repositorio.FavoritaGrupoRepositorio;
import com.mycompany.easytrip.repositorio.GrupoRepositorio;
import com.mycompany.easytrip.repositorio.HospedagemRepositorio;
import com.mycompany.easytrip.repositorio.HospedeGrupoRepositorio;
import com.mycompany.easytrip.repositorio.Transacao;
import com.mycompany.easytrip.repositorio.UsuarioRepositorio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GrupoServico {
    private final Transacao transacao;
    private final GrupoRepositorio grupoRepositorio;
    private final HospedeGrupoRepositorio hospedeGrupoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final FavoritaGrupoRepositorio favoritaGrupoRepositorio;
    private final HospedagemRepositorio hospedagemRepositorio;
    
   public GrupoServico(EntityManager entityManager){
       this.transacao = new Transacao(entityManager);
       this.grupoRepositorio = new GrupoRepositorio(entityManager);
       this.hospedeGrupoRepositorio = new HospedeGrupoRepositorio(entityManager);
       this.usuarioRepositorio = new UsuarioRepositorio(entityManager);
       this.favoritaGrupoRepositorio = new FavoritaGrupoRepositorio(entityManager);
       this.hospedagemRepositorio = new HospedagemRepositorio(entityManager);
   }
   
   public List<HospedeGrupo> listarVinculosDoUsuario(int usuarioId){
       return hospedeGrupoRepositorio.getLigacoesDoUsuario(usuarioId);
   }
   
   public Grupo getGrupo(int grupoId) throws GrupoServicoException {
       try{
           return grupoRepositorio.getGrupo(grupoId);
       }
       catch(EntityNotFoundException e){
           throw new GrupoServicoException("O grupo não existe");
       }
   }
   
   public void criarGrupo(int usuarioId, String nomeGrupo) throws GrupoServicoException{
       try{
           Grupo grupo = new Grupo(nomeGrupo);
           
           Usuario usuario = usuarioRepositorio.getUsuario(usuarioId);
           
           grupo.adicionarResponsavel(usuario);
           
           transacao.iniciar();
           grupoRepositorio.criarGrupo(grupo);
           transacao.confirmar();
       }
       catch(DominioException e){
           throw new GrupoServicoException(e.getMessage());
       }
       catch(PersistenceException e){
           throw new GrupoServicoException("Falha ao criar grupo");
       }
   }
   
   public void removerUsuarioDoGrupo(int grupoId, int usuarioId) throws GrupoServicoException{
       try{
           Grupo grupo = grupoRepositorio.getGrupo(grupoId);
           Usuario usuario = usuarioRepositorio.getUsuario(usuarioId);
           
           boolean eUmResponsavel = grupo.oUsuarioEUmResponsavel(usuario);
           if (eUmResponsavel){
               Usuario candidato = grupo.getCandidatoAResponsavel();
               if (candidato != null)
                   grupo.alterarFuncaoDoUsuario(candidato, FuncaoGrupo.RESPONSAVEL);
           }
           
           grupo.removerUsuario(usuario);
           
           transacao.iniciar();
           grupoRepositorio.atualizarGrupo(grupo);
           transacao.confirmar();
       }
       catch(DominioException e){
           throw new GrupoServicoException(e.getMessage());
       }
       catch(EntityNotFoundException e){
           throw new GrupoServicoException("O grupo não existe");
       }
       catch(IllegalArgumentException e){
           throw new GrupoServicoException("Falha ao atualizar grupo");
       }
   }
   
   public void removerUsuarioDoGrupo(int grupoId, int usuarioLogadoId, int usuarioRemoverId) throws GrupoServicoException{
       try{
           Grupo grupo = grupoRepositorio.getGrupo(grupoId);
           Usuario usuarioLogado = usuarioRepositorio.getUsuario(usuarioLogadoId);
           Usuario usuarioARemover = usuarioRepositorio.getUsuario(usuarioRemoverId);
           
           if (usuarioLogado.equals(usuarioARemover))
                throw new GrupoServicoException("Não pode remover a si mesmo");
           
           boolean usuarioLogadoEAdministrador = grupo.OUsuarioEUmAdministrador(usuarioLogado);
           boolean usuarioARemoverEAdministrador = grupo.OUsuarioEUmAdministrador(usuarioARemover);
           boolean usuarioARemoverEResponsavel = grupo.oUsuarioEUmResponsavel(usuarioARemover);
           if ((usuarioLogadoEAdministrador && usuarioARemoverEAdministrador) || usuarioARemoverEResponsavel)
               throw new GrupoServicoException("Não tem permissão para remover o usuário");
           
           grupo.removerUsuario(usuarioARemover);
           
           transacao.iniciar();
           grupoRepositorio.atualizarGrupo(grupo);
           transacao.confirmar();
       }
       catch(DominioException e){
           throw new GrupoServicoException(e.getMessage());
       }
       catch(EntityNotFoundException e){
           throw new GrupoServicoException("O grupo não existe");
       }
       catch(IllegalArgumentException e){
           throw new GrupoServicoException("Falha ao atualizar grupo");
       }
   }
   
   public void adicionarUsuario(int grupoId, String emailEndereco) throws GrupoServicoException{
        try{
            Email email = new Email(emailEndereco);
            
            Usuario usuario = usuarioRepositorio.getUsuarioPeloEmail(email.getEndereco());
            if (usuario == null)
               throw new GrupoServicoException("O usuario com esse email não existe");
           
            Grupo grupo = grupoRepositorio.getGrupo(grupoId);
           
            grupo.adicionarMembro(usuario);
           
            transacao.iniciar();
            grupoRepositorio.atualizarGrupo(grupo);
            transacao.confirmar();
       }
       catch(DominioException e){
           throw new GrupoServicoException(e.getMessage());
       }
       catch(EntityNotFoundException e){
           throw new GrupoServicoException("O grupo não existe");
       }
   }
   
   public void alterarFuncaoDoUsuario(int grupoId, int usuarioLogadoId,int usuarioAlterarId, FuncaoGrupo funcao) throws GrupoServicoException{
       try{
           Grupo grupo = grupoRepositorio.getGrupo(grupoId);
           Usuario usuarioLogado = usuarioRepositorio.getUsuario(usuarioLogadoId);
           Usuario usuarioAlterar = usuarioRepositorio.getUsuario(usuarioAlterarId);
           
           if (usuarioLogado.equals(usuarioAlterar))
                throw new GrupoServicoException("Não pode ser alterado a propria função");
           
           
           boolean usuarioAlterarEResponsavel = grupo.oUsuarioEUmResponsavel(usuarioAlterar);
           boolean usuarioLogadoEResponsavel = grupo.oUsuarioEUmResponsavel(usuarioLogado);
           if (usuarioAlterarEResponsavel || (!usuarioLogadoEResponsavel && funcao == FuncaoGrupo.RESPONSAVEL))
               throw new GrupoServicoException("O usuário não tem acesso para essa alteração");
           
           if(usuarioLogadoEResponsavel && funcao == FuncaoGrupo.RESPONSAVEL)
               grupo.alterarFuncaoDoUsuario(usuarioLogado, FuncaoGrupo.ADMINISTRADOR);

           grupo.alterarFuncaoDoUsuario(usuarioAlterar, funcao);
           
           transacao.iniciar();
           grupoRepositorio.atualizarGrupo(grupo);
           transacao.confirmar();
       }
       catch(DominioException e){
           throw new GrupoServicoException(e.getMessage());
       }
       catch(NoResultException e){
           throw new GrupoServicoException("O vinculo não foi encontrado");
       }
       catch(IllegalArgumentException e){
           throw new GrupoServicoException("Falha ao atualizar vinculo");
       }
   }
   
    public void alterarQuantidadeDeAcompanhantesUsuario(int grupoId, int usuarioId, int qAcompanhantes) throws GrupoServicoException{
        try{
            Grupo grupo = grupoRepositorio.getGrupo(grupoId);
            Usuario usuario = usuarioRepositorio.getUsuario(usuarioId);

           grupo.alterarQuantidadeDeAcompanhantesDoUsuario(usuario, qAcompanhantes);
           
           transacao.iniciar();
           grupoRepositorio.atualizarGrupo(grupo);
           transacao.confirmar();
        }
        catch(DominioException e){
            throw new GrupoServicoException(e.getMessage());
        }    
        catch(NoResultException e){
           throw new GrupoServicoException("O vinculo não foi encontrado");
        }
        catch(IllegalArgumentException e){
           throw new GrupoServicoException("Falha ao atualizar vinculo");
        }
    }
    
    public List<Grupo> listarGruposDoUsuario(int usuarioId){
        return hospedeGrupoRepositorio.getGruposDoUsuario(usuarioId);
    }
    
    public List<Hospedagem> listarHospedagensFavoritasDoGrupo(int grupoId, int pagina, int quantidadePorPagina){
        List<Hospedagem> favoritas = favoritaGrupoRepositorio.getHospedagensFavoritasDoGrupo(grupoId);
        
        return favoritas.stream().skip(pagina * quantidadePorPagina).limit(quantidadePorPagina).toList();
    }
    
    public boolean grupoFavoritouAHospedagem(int grupoId, int hospedagemId) throws GrupoServicoException{
        try{
            Grupo grupo = grupoRepositorio.getGrupo(grupoId);
            Hospedagem hospedagem = hospedagemRepositorio.getHospedagem(hospedagemId);
            
            return grupo.aHospedagemEstaFavoritada(hospedagem);
        }
        catch(EntityNotFoundException e){
           throw new GrupoServicoException("Não foi encontrado a entidade");
       }
    }
    
    public void favoritarHospedagem(int grupoId, int hospedagemId) throws GrupoServicoException{
        try{
            Grupo grupo = grupoRepositorio.getGrupo(grupoId);
            Hospedagem hospedagem = hospedagemRepositorio.getHospedagem(hospedagemId);
            
            grupo.favoritarHospedagem(hospedagem);
            
            transacao.iniciar();
            grupoRepositorio.atualizarGrupo(grupo);
            transacao.confirmar();
        }
        catch(DominioException e){
            throw new GrupoServicoException(e.getMessage());
        }
        catch(EntityNotFoundException | IllegalArgumentException e){
           throw new GrupoServicoException("Falha ao persistir dados");
       }
    }
    
    public void desfavoritarHospedagem(int grupoId, int hospedagemId) throws GrupoServicoException{
        try{
            Grupo grupo = grupoRepositorio.getGrupo(grupoId);
            Hospedagem hospedagem = hospedagemRepositorio.getHospedagem(hospedagemId);
            
            grupo.removerHospedagemFavorita(hospedagem);
            
            transacao.iniciar();
            grupoRepositorio.atualizarGrupo(grupo);
            transacao.confirmar();
        }
        catch(DominioException e){
            throw new GrupoServicoException(e.getMessage());
        }
        catch(EntityNotFoundException | IllegalArgumentException e){
           throw new GrupoServicoException("Falha ao persistir dados");
       }
    }
    
    public void votarHospedagem(int usuarioId, int grupoId, int hospedagemId) throws GrupoServicoException{
        try{
            Usuario usuario = usuarioRepositorio.getUsuario(usuarioId);
            Grupo grupo = grupoRepositorio.getGrupo(grupoId);
            Hospedagem hospedagem = hospedagemRepositorio.getHospedagem(hospedagemId);
            
            grupo.votarNaHospedagem(hospedagem, usuario);
            
            transacao.iniciar();
            grupoRepositorio.atualizarGrupo(grupo);
            transacao.confirmar();
        }
        catch(DominioException e){
            throw new GrupoServicoException(e.getMessage());
        }
        catch(EntityNotFoundException | IllegalArgumentException e){
           throw new GrupoServicoException("Falha ao persistir dados");
       }
    }
    
    public boolean verificarSeUsuarioJaVotouNaHospedagem(int usuarioId, int grupoId, int hospedagemId) throws GrupoServicoException, GrupoException{
        try{
            Usuario usuario = usuarioRepositorio.getUsuario(usuarioId);
            Grupo grupo = grupoRepositorio.getGrupo(grupoId);
            Hospedagem hospedagem = hospedagemRepositorio.getHospedagem(hospedagemId);
            
            return grupo.usuarioJaVotouNaHospedagem(hospedagem, usuario);
        }
        catch(EntityNotFoundException | IllegalArgumentException e){
           throw new GrupoServicoException("Falha ao persistir dados");
       }
    }
    
    public void retirarVotoDaHospedagem(int usuarioId, int grupoId, int hospedagemId) throws GrupoServicoException{
        try{
            Usuario usuario = usuarioRepositorio.getUsuario(usuarioId);
            Grupo grupo = grupoRepositorio.getGrupo(grupoId);
            Hospedagem hospedagem = hospedagemRepositorio.getHospedagem(hospedagemId);
            
            grupo.retirarVotoNaHospedagem(hospedagem, usuario);
            
            transacao.iniciar();
            grupoRepositorio.atualizarGrupo(grupo);
            transacao.confirmar();
        }
        catch(DominioException e){
            throw new GrupoServicoException(e.getMessage());
        }
        catch(EntityNotFoundException | IllegalArgumentException e){
           throw new GrupoServicoException("Falha ao persistir dados");
       }
    }
    
    public Map<Hospedagem, Long> listarRankingHospedagensFavoritadas(int grupoId, int quantidadeNoRanking){
        Map<Hospedagem, Long> hospedagens = favoritaGrupoRepositorio.getHospedagensRankingVotos(grupoId);
        
        Map<Hospedagem, Long> hospedagensSelecionadas = new LinkedHashMap<>();
        
        int contador = 0;
        for (Map.Entry<Hospedagem, Long> entry : hospedagens.entrySet()){
            if (contador++ == 10)
                break;
            hospedagensSelecionadas.put(entry.getKey(), entry.getValue());
        } 
        
        return hospedagensSelecionadas;
    }
    
    public boolean checaSeUsuarioEResponsavel(int grupoId, int usuarioId) throws GrupoServicoException{
        try{
            Grupo grupo = grupoRepositorio.getGrupo(grupoId);
            Usuario usuario = usuarioRepositorio.getUsuario(usuarioId);
            
            return grupo.oUsuarioEUmResponsavel(usuario);
        }
        catch(DominioException e){
            throw new GrupoServicoException(e.getMessage());
        }
        catch(EntityNotFoundException e){
           throw new GrupoServicoException("Falha ao persistir dados");
       }
    }
}
