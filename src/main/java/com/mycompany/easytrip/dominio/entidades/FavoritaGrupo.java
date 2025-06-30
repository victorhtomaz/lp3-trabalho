package com.mycompany.easytrip.dominio.entidades;

import com.mycompany.easytrip.dominio.excecoes.FavoritaGrupoException;
import com.mycompany.easytrip.dominio.excecoes.GrupoException;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class FavoritaGrupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "Grupo_Id")
    private Grupo grupo;
    
    @ManyToOne
    @JoinColumn(name = "Hospedagem_Id")
    private Hospedagem hospedagem;
    
    @OneToMany(mappedBy = "favoritaGrupo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VotacaoFavoritas> votacoes = new ArrayList<>();
    
    protected FavoritaGrupo(){}
    
    public FavoritaGrupo(Grupo grupo, Hospedagem hospedagem){
        this.grupo = grupo;
        this.hospedagem = hospedagem;
    }
    
    public Grupo getGrupo(){
        return grupo;
    }
    
    public Hospedagem getHospedagem(){
        return hospedagem;
    }
    
    public void adicionaVotacao(HospedeGrupo hospedeGrupo){
        VotacaoFavoritas votacao = new VotacaoFavoritas(this, hospedeGrupo);
        votacoes.add(votacao);
    }
    
    public boolean foiVotado(HospedeGrupo hospedeGrupo){
        for (VotacaoFavoritas votacao : votacoes)
            if (votacao.getHospedeGrupo().equals(hospedeGrupo))
                return true;
        
        return false;
    }
    
    public void removerVoto(HospedeGrupo hospedeGrupo) throws FavoritaGrupoException{
        boolean foiRemovido = votacoes.removeIf(vh -> vh.getHospedeGrupo().equals(hospedeGrupo));
        
        if (!foiRemovido)
            throw new FavoritaGrupoException("A hospedagem não está favoritada");
    }
}
