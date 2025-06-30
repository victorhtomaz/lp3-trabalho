package com.mycompany.easytrip.dominio.entidades;

import com.mycompany.easytrip.dominio.entidades.chavesCompostas.VotacaoFavoritasId;
import jakarta.persistence.*;

@Entity
public class VotacaoFavoritas {
    
    @EmbeddedId
    private VotacaoFavoritasId id = new VotacaoFavoritasId();
    
    @ManyToOne
    @MapsId("hospedeGrupoId")
    @JoinColumn(name = "HospedeGrupo_Id")
    private HospedeGrupo hospedeGrupo;
    
    @ManyToOne
    @MapsId("favoritaGrupoId")
    @JoinColumn(name = "FavoritaGrupo_Id")
    private FavoritaGrupo favoritaGrupo;
    
    public HospedeGrupo getHospedeGrupo(){
        return hospedeGrupo;
    }
    
    protected VotacaoFavoritas(){}
    
    public VotacaoFavoritas(FavoritaGrupo favoritaGrupo,HospedeGrupo hospedeGrupo){
        this.hospedeGrupo = hospedeGrupo;
        this.favoritaGrupo = favoritaGrupo;
    }
}
