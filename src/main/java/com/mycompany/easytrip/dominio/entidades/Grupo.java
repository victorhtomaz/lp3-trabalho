package com.mycompany.easytrip.dominio.entidades;

import com.mycompany.easytrip.dominio.enums.FuncaoGrupo;
import static com.mycompany.easytrip.dominio.enums.FuncaoGrupo.ADMINISTRADOR;
import static com.mycompany.easytrip.dominio.enums.FuncaoGrupo.MEMBRO;
import static com.mycompany.easytrip.dominio.enums.FuncaoGrupo.RESPONSAVEL;
import com.mycompany.easytrip.dominio.excecoes.DominioException;
import com.mycompany.easytrip.dominio.excecoes.GrupoException;
import com.mycompany.easytrip.dominio.interfaces.Validacao;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Grupo implements Validacao{
    private static final int TAMANHO_MINIMO_NOME = 4;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    
    @Column(name = "Nome")
    private String nome;
    
    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<HospedeGrupo> hospedesDoGrupo = new ArrayList<>();
    
    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<FavoritaGrupo> favoritasDoGrupo = new ArrayList<>();
    
    protected Grupo(){}
    
    public Grupo(String nome) throws DominioException{
        this.nome = nome;
        
        validar();
    }

    @Override
    public void validar() throws DominioException {
        if (nome.isBlank() || nome.length() < TAMANHO_MINIMO_NOME)
            throw new GrupoException("O nome do grupo precisa conter no minimo: " + TAMANHO_MINIMO_NOME + " caracteres");
    }
    
    public int getId(){
        return id;
    }
    
    public String getNome(){
        return nome;
    } 
    
    public List<HospedeGrupo> getListaHospedesDoGrupo(){
        return hospedesDoGrupo;
    }
    
    private void adicionarUsuario(Usuario usuario, FuncaoGrupo funcao) throws GrupoException, DominioException{
        HospedeGrupo hp = getVinculoDoUsuario(usuario);
        if(hp != null)
            throw new GrupoException("O usuário já está no grupo");
        
        HospedeGrupo vinculo = new HospedeGrupo(usuario, this, funcao);
        hospedesDoGrupo.add(vinculo);
    }
    
    public void removerUsuario(Usuario usuario) throws GrupoException{
        boolean removido = hospedesDoGrupo.removeIf( hp -> hp.getUsuario().equals(usuario));
        
        if (!removido)
            throw new GrupoException("O usuário não está no grupo");
    }
    
    private FavoritaGrupo getFavoritaGrupo(Hospedagem hospedagem){
        for (FavoritaGrupo favoritaGrupo : favoritasDoGrupo)
            if (favoritaGrupo.getHospedagem().equals(hospedagem))
                return favoritaGrupo;
        
        return null;
    }
    
    private HospedeGrupo getVinculoDoUsuario(Usuario usuario) throws GrupoException{
        for (HospedeGrupo vinculo : hospedesDoGrupo)
            if (vinculo.getUsuario().equals(usuario))
                return vinculo;
        
        return null;
    }
    
    public void alterarFuncaoDoUsuario(Usuario usuario,FuncaoGrupo funcao) throws GrupoException{
        HospedeGrupo vinculo = getVinculoDoUsuario(usuario);
        if (vinculo == null)
            throw new GrupoException("O usuário não está no grupo"); 
        
        switch(funcao){
               case ADMINISTRADOR -> vinculo.setFuncaoParaAdministrador();
               case RESPONSAVEL -> vinculo.setFuncaoParaResponsavel();
               case MEMBRO -> vinculo.setFuncaoParaMembro();
           }
    }
    
    public void alterarQuantidadeDeAcompanhantesDoUsuario(Usuario usuario, int quantidadeAcompanhantes) throws GrupoException, DominioException{
        HospedeGrupo vinculo = getVinculoDoUsuario(usuario);
        if (vinculo == null)
            throw new GrupoException("O usuário não está no grupo"); 
        
        vinculo.setQuantidadeDeAcompanhantes(quantidadeAcompanhantes);
    }
    
    public boolean oUsuarioEUmResponsavel(Usuario usuario) throws GrupoException{
        HospedeGrupo vinculo = getVinculoDoUsuario(usuario);
        if (vinculo == null)
            throw new GrupoException("O usuário não está no grupo"); 
        return vinculo.eUmResponsavel();
    }
    
    public boolean OUsuarioEUmAdministrador(Usuario usuario) throws GrupoException{
        HospedeGrupo vinculo = getVinculoDoUsuario(usuario);
        if (vinculo == null)
            throw new GrupoException("O usuário não está no grupo"); 
        return vinculo.eUmAdministrador();
    }
    
    public boolean OUsuarioEUmMembro(Usuario usuario) throws GrupoException{
        HospedeGrupo vinculo = getVinculoDoUsuario(usuario);
        if (vinculo == null)
            throw new GrupoException("O usuário não está no grupo"); 
        return vinculo.eUmMembro();
    }
    
    public void adicionarResponsavel(Usuario usuario) throws GrupoException, DominioException{
        adicionarUsuario(usuario, FuncaoGrupo.RESPONSAVEL);
    }
    
    public void adicionarMembro(Usuario usuario) throws GrupoException, DominioException{
        adicionarUsuario(usuario, FuncaoGrupo.MEMBRO);
    }
    
    public Usuario getCandidatoAResponsavel(){
        for (HospedeGrupo vinculo : hospedesDoGrupo)
            if (vinculo.eUmAdministrador()){
                return vinculo.getUsuario();
            }
        if (!hospedesDoGrupo.isEmpty())
            return hospedesDoGrupo.getLast().getUsuario();
        
        return null;
    }
    
    public int getQuantidadeDeParticipantes(){
        int total = 0;
        
        for (HospedeGrupo vinculo : hospedesDoGrupo)
            total += 1 + vinculo.getQuantidadeDeAcompanhantes();
        
        return total;
    }
    
    public void favoritarHospedagem(Hospedagem hospedagem) throws GrupoException{
        if (aHospedagemEstaFavoritada(hospedagem))
            throw new GrupoException("A hospedagem já foi favoritada");
        
        FavoritaGrupo favoritaGrupo = new FavoritaGrupo(this, hospedagem);
        favoritasDoGrupo.add(favoritaGrupo);
    }
    
    public void removerHospedagemFavorita(Hospedagem hospedagem) throws GrupoException{
        boolean removido = favoritasDoGrupo.removeIf( fg -> fg.getHospedagem().equals(hospedagem));
        
        if (!removido)
            throw new GrupoException("A hospedagem não está favoritada");
    }
    
    public boolean aHospedagemEstaFavoritada(Hospedagem hospedagem){
        for (FavoritaGrupo favoritaGrupo : favoritasDoGrupo)
            if (favoritaGrupo.getHospedagem().equals(hospedagem))
                return true;
        
        return false;
    }
    
    public boolean usuarioJaVotouNaHospedagem(Hospedagem hospedagem, Usuario usuario) throws GrupoException{
        FavoritaGrupo favorita = getFavoritaGrupo(hospedagem);
        if (favorita == null)
            throw new GrupoException("A hospedagem não esta favoritada");
        
        HospedeGrupo vinculo = getVinculoDoUsuario(usuario);
        if (vinculo == null)
            throw new GrupoException("O usuário não pertence ao grupo");
        
        return favorita.foiVotado(vinculo);
    }
    
    public void votarNaHospedagem(Hospedagem hospedagem, Usuario usuario) throws GrupoException{
        FavoritaGrupo favorita = getFavoritaGrupo(hospedagem);
        if (favorita == null)
            throw new GrupoException("A hospedagem não esta favoritada");
        
        HospedeGrupo vinculo = getVinculoDoUsuario(usuario);
        if (vinculo == null)
            throw new GrupoException("O usuário não pertence ao grupo");
        
        favorita.adicionaVotacao(vinculo);
    }
    
    public void retirarVotoNaHospedagem(Hospedagem hospedagem, Usuario usuario) throws GrupoException, DominioException{
        FavoritaGrupo favorita = getFavoritaGrupo(hospedagem);
        if (favorita == null)
            throw new GrupoException("A hospedagem não esta favoritada");
        
        HospedeGrupo vinculo = getVinculoDoUsuario(usuario);
        if (vinculo == null)
            throw new GrupoException("O usuário não pertence ao grupo");
        
        favorita.removerVoto(vinculo);
    }

    @Override
    public String toString() {
        return nome;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == null || this.getClass() != obj.getClass())
            return false;
        
        Grupo that = (Grupo) obj;
        
        return this.id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
