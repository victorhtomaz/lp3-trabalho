package com.mycompany.easytrip.dominio.entidades;

import com.mycompany.easytrip.dominio.excecoes.DominioException;
import com.mycompany.easytrip.dominio.objetosDeValor.Cpf;
import com.mycompany.easytrip.dominio.objetosDeValor.Email;
import com.mycompany.easytrip.dominio.objetosDeValor.Senha;
import java.time.LocalDate;

public class Usuario{
    private int id;
    private String nome;
    private Email email;
    private Senha senha;
    private Cpf cpf;
    private LocalDate dataNascimento;

    public Usuario(String nome, String endereçoEmail, String senha, String cpf, LocalDate dataNascimento) throws DominioException{
        this.nome = nome;
        this.email = new Email(endereçoEmail);
        this.senha = new Senha(senha);
        this.cpf = new Cpf(cpf);
        this.dataNascimento = dataNascimento;
    }
    public Usuario(String nome, Email email, Senha senha, Cpf cpf, LocalDate dataNascimento){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
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
    
    @Override
    public String toString() {
        return "Nome: " + nome + ", Email: " + email + ", Data de Nascimento: " + dataNascimento;
    }
}
