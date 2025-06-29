package com.mycompany.easytrip.dominio.entidades.chavesCompostas;

import com.mycompany.easytrip.dominio.entidades.Grupo;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VotacaoFavoritasId implements Serializable{
    private Integer hospedeGrupoId;
    private Integer favoritaGrupoId;
    
    public VotacaoFavoritasId(){}
    
    public VotacaoFavoritasId(Integer hospedeGrupoId, Integer favoritaGrupoId){
        this.hospedeGrupoId = hospedeGrupoId;
        this.favoritaGrupoId = favoritaGrupoId;
    }
    
    public Integer getHospedeGrupoId(){
        return hospedeGrupoId;
    }
    
    public Integer getFavoritaGrupoId(){
        return favoritaGrupoId;
    }
    
    public void setHospedeGrupoId(Integer hospedeGrupoId){
        this.hospedeGrupoId = hospedeGrupoId;
    }

    public void setFavoritaGrupoId(Integer favoritaGrupoId){
        this.favoritaGrupoId = favoritaGrupoId;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == null || this.getClass() != obj.getClass())
            return false;
        
        VotacaoFavoritasId that = (VotacaoFavoritasId) obj;
        
        return this.hospedeGrupoId.equals(that.hospedeGrupoId) && this.favoritaGrupoId.equals(that.favoritaGrupoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hospedeGrupoId, favoritaGrupoId);
    }
}
