package com.mycompany.easytrip.dominio.entidades;

import com.mycompany.easytrip.dominio.enums.EstadoBrasil;
import com.mycompany.easytrip.dominio.excecoes.DominioException;
import com.mycompany.easytrip.dominio.excecoes.EnderecoException;
import com.mycompany.easytrip.dominio.interfaces.Validacao;
import com.mycompany.easytrip.dominio.objetosDeValor.Cep;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Endereco")
public class Endereco implements Validacao{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    
    @Embedded
    private Cep cep;
    
    @Column(name = "Numero")
    private String numero;
    
    @Column(name = "Rua")
    private String rua;
    
    @Column(name = "Bairro")
    private String bairro;
    
    @Column(name = "Complemento")
    private String complemento;
    
    @Column(name = "Cidade")
    private String cidade;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "Estado")
    private EstadoBrasil estado;
 
    protected Endereco(){}
    
    public Endereco(String cep, String numero, String rua, String bairro, String complemento, String cidade, EstadoBrasil estado) throws DominioException{
        this.cep = new Cep(cep);
        
        StringBuilder aux = new StringBuilder();
        for (char c : numero.toCharArray()){
            if (Character.isDigit(c))
                aux.append(c);
        }
        this.numero = aux.toString();
        this.rua = rua;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cidade = cidade;
        this.estado = estado;
        
        validar();
    }

    @Override
    public void validar() throws DominioException {
        if (numero.isBlank())
            throw new EnderecoException("O número não pode ser vazio");
        
        if(rua.isBlank())
            throw new EnderecoException("A rua não pode ser vazia");
        
        if (bairro.isBlank())
            throw new EnderecoException("O bairro não pode ser vazio");
        
        if (complemento.isBlank())
            throw new EnderecoException("O complemento não pode ser vazio");
        
        if (cidade.isBlank())
            throw new EnderecoException("A cidade não pode ser vazia");
    }

    public int getId(){
        return id;
    }
    
    public String getCep(){
        return cep.getValor();
    }
    
    public String getRua(){
        return rua;
    }
    
    public String getNumero(){
        return numero;
    }
    
    public String getComplemento(){
        return complemento;
    }
    
    public String getBairro(){
        return bairro;
    }
    
    public String getCidade(){
        return cidade;
    }
    
    public EstadoBrasil getEstado(){
        return estado;
    }
    
    @Override
    public String toString() {
        return rua + ", n°" + numero + ", " + complemento + " - " + bairro + ", " + cidade + ". " +  "Cep: " + cep.toString() + ". " + estado.name();
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == null || this.getClass() != obj.getClass())
            return false;
        
        Endereco that = (Endereco) obj;
        
        return this.id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
