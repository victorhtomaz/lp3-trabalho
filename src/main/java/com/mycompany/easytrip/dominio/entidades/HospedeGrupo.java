package com.mycompany.easytrip.dominio.entidades;

import com.mycompany.easytrip.dominio.enums.FuncaoGrupo;
import com.mycompany.easytrip.dominio.excecoes.DominioException;
import com.mycompany.easytrip.dominio.excecoes.HospedeGrupoException;
import com.mycompany.easytrip.dominio.interfaces.Validacao;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class HospedeGrupo implements Validacao{
    private static final int QUANTIDADE_MAXIMA_ACOMPANHANTES = 10;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "Usuario_Id")
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "Grupo_Id")
    private Grupo grupo;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "Funcao")
    private FuncaoGrupo funcao;
    
    @Column(name = "QuantidadeAcompanhantes")
    private int quantidadeDeAcompanhantes;
    
    protected HospedeGrupo(){}
    
    public HospedeGrupo(Usuario usuario, Grupo grupo, FuncaoGrupo funcao) throws DominioException{
        this.usuario = usuario;
        this.grupo = grupo;
        this.funcao = funcao;
        this.quantidadeDeAcompanhantes = 0;
        
        validar();
    }

    @Override
    public void validar() throws DominioException {
        if (usuario == null)
            throw new HospedeGrupoException("O usuario deve ser valido");
        
        if (grupo == null)
            throw new HospedeGrupoException("O grupo deve ser valido");
        
        if (quantidadeDeAcompanhantes > QUANTIDADE_MAXIMA_ACOMPANHANTES || quantidadeDeAcompanhantes < 0)
            throw new HospedeGrupoException("A quantidade maxima de acompanhantes é de: " + QUANTIDADE_MAXIMA_ACOMPANHANTES);
    }
    
    public int getId(){
        return id;
    }
    
    public Usuario getUsuario(){
        return usuario;
    }
    
    public Grupo getGrupo(){
        return grupo;
    }
    
    public FuncaoGrupo getFuncao(){
        return funcao;
    }
    
    public int getQuantidadeDeAcompanhantes(){
        return quantidadeDeAcompanhantes;
    }
    
    private void setFuncao(FuncaoGrupo funcao){
        this.funcao = funcao;
    }
    
    public void setFuncaoParaResponsavel(){
        setFuncao(FuncaoGrupo.RESPONSAVEL);
    }
    
    public void setFuncaoParaAdministrador(){
        setFuncao(FuncaoGrupo.ADMINISTRADOR);
    }
    
    public void setFuncaoParaMembro(){
        setFuncao(FuncaoGrupo.MEMBRO);
    }
    
    private boolean estaNaFuncao(FuncaoGrupo funcao){
        return this.funcao == funcao;
    }
    
    public boolean eUmResponsavel(){
        return estaNaFuncao(FuncaoGrupo.RESPONSAVEL);
    }
    
    public boolean eUmAdministrador(){
        return estaNaFuncao(FuncaoGrupo.ADMINISTRADOR);
    }
    
    public boolean eUmMembro(){
        return estaNaFuncao(FuncaoGrupo.MEMBRO);
    }
    
    public void setQuantidadeDeAcompanhantes(int quantidadeDeAcompanhantes) throws DominioException{
        this.quantidadeDeAcompanhantes = quantidadeDeAcompanhantes;
        validar();
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == null || this.getClass() != obj.getClass())
            return false;
        
        HospedeGrupo that = (HospedeGrupo) obj;
        
        return this.id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
