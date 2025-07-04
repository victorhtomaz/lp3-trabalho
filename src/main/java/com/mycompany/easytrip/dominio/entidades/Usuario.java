package com.mycompany.easytrip.dominio.entidades;

import com.mycompany.easytrip.dominio.excecoes.DominioException;
import com.mycompany.easytrip.dominio.excecoes.UsuarioException;
import com.mycompany.easytrip.dominio.interfaces.Validacao;
import com.mycompany.easytrip.dominio.objetosDeValor.Cpf;
import com.mycompany.easytrip.dominio.objetosDeValor.Email;
import com.mycompany.easytrip.dominio.objetosDeValor.Senha;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Entity
@Table(name = "Usuario")
public class Usuario implements Validacao{
    private static final int NOME_TAMANHO_MINIMO = 2;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    
    @Column(name = "Nome")
    private String nome;
    
    @Embedded
    private Email email;
    
    @Embedded
    private Senha senha;
    
    @Embedded
    private Cpf cpf;
    
    @Column(name = "DataNascimento")
    private LocalDate dataNascimento;
    
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "Usuario_Id")
    private final List<Hospedagem> hospedagens = new ArrayList<>();
    
    @OneToMany(mappedBy = "usuario")
    private final List<HospedeGrupo> gruposDoHospede = new ArrayList<>();
    
    @ManyToMany
    @JoinTable(
            name = "FavoritaHospede",
            joinColumns = @JoinColumn(name = "Usuario_Id"),
            inverseJoinColumns = @JoinColumn(name = "Hospedagem_Id")
    )
    private final List<Hospedagem> hospedagensFavoritas = new ArrayList<>();
    
    private static final DateTimeFormatter DATA_NASCIMENTO_FORMATADOR = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.forLanguageTag("pt-BR"));
    
    //Para Hibernate
    protected Usuario(){  }

    public Usuario(String nome, String endereçoEmail, String senha, String cpf, LocalDate dataNascimento) throws DominioException{
        this.nome = nome;
        this.email = new Email(endereçoEmail);
        this.senha = new Senha(senha);
        this.cpf = new Cpf(cpf);
        this.dataNascimento = dataNascimento;
        
        validar();
    }
    public Usuario(String nome, Email email, Senha senha, Cpf cpf, LocalDate dataNascimento) throws DominioException{
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        
        validar();
    }
    
    @Override
    public void validar() throws DominioException {
        if (nome.isBlank() || nome.length() < NOME_TAMANHO_MINIMO)
            throw new UsuarioException("O nome precisa conter no minimo: " + NOME_TAMANHO_MINIMO + " caracteres");
        
        if(dataNascimento.isAfter(LocalDate.now()) || dataNascimento.isEqual(LocalDate.now()))
            throw new UsuarioException("A data de nascimento é inválida");
    }

    public int getId(){
        return id;
    }
    
    public String getNome(){
        return nome;
    }
    
    public String getEnderecoEmail(){
        return email.getEndereco();
    }
    
    public String getValorSenha(){
        return senha.getValor();
    }
    
    public LocalDate getDataNascimento(){
        return dataNascimento;
    }
    
    public String getValorCpf(){
        return cpf.getValor();
    }
    
    public List<Hospedagem> getHospedagens(){
        return hospedagens;
    }
    
    public List<Hospedagem> getHospedagensFavoritas(){
        return hospedagensFavoritas;
    }
    
    public boolean aHospedagemEstaFavoritada(int hospedagemId){
        for (Hospedagem hospedagem : hospedagensFavoritas)
            if (hospedagem.getId() == hospedagemId)
                return true;
        
        return false;
    }
    
    public void adicionarHospedagemFavorita(Hospedagem hospedagem) throws UsuarioException{
        if (hospedagensFavoritas.contains(hospedagem))
            throw new UsuarioException("A hospedagem já está na lista");
        
        hospedagensFavoritas.add(hospedagem);
    }
    
    public void removerHospedagemFavorita(Hospedagem hospedagem){
        hospedagensFavoritas.remove(hospedagem);
    }
    
    public void adicionarHospedagem(Hospedagem hospedagem){
        hospedagens.add(hospedagem);
    }
    
    @Override
    public String toString() {
        return "Nome: " + nome + ", Email: " + email + ", Data de Nascimento: " + dataNascimento.format(DATA_NASCIMENTO_FORMATADOR);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || this.getClass() != obj.getClass())
            return false;
        
        Usuario that = (Usuario) obj;
        
        return this.id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
