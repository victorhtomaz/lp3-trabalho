package com.mycompany.easytrip.repositorio;

import com.mycompany.easytrip.dominio.entidades.Usuario;
import com.mycompany.easytrip.dominio.excecoes.DominioException;
import com.mycompany.easytrip.dominio.objetosDeValor.Cpf;
import com.mycompany.easytrip.dominio.objetosDeValor.Email;
import com.mycompany.easytrip.dominio.objetosDeValor.Senha;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioRepositorio {
    public static void criarUsuario(Usuario usuario) throws SQLException, DominioException {
        try(Connection conexao = MinhaConexao.obterConexao()){
            String comando = "INSERT INTO Usuario(Nome, Email, Senha, DataNascimento, CPF)"
                    + "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement stmt = conexao.prepareStatement(comando);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEnderecoEmail());
            stmt.setString(3, usuario.getValorSenha());
            stmt.setDate(4, Date.valueOf(usuario.getDataNascimento()));
            stmt.setString(5, usuario.getValorCpf());

            stmt.executeUpdate();
        }
    }
    
    public static int verificarLogin(Email email, Senha senha) throws SQLException{
        try(Connection conexao = MinhaConexao.obterConexao()){
            String comando = "SELECT Id FROM Usuario WHERE Email = ? AND Senha = ?";

            PreparedStatement stmt = conexao.prepareStatement(comando);
            stmt.setString(1, email.getEndereco());
            stmt.setString(2, senha.getValor());

            ResultSet result = stmt.executeQuery();
            
            return result.next() ? result.getInt("Id") : 0;
        }
    }
    
    public static boolean existeUmUsuarioComEmail(Email email) throws SQLException{
        try(Connection conexao = MinhaConexao.obterConexao()){
            String comando = "SELECT EXISTS (SELECT 1 FROM Usuario WHERE Email = ?)";

            PreparedStatement stmt = conexao.prepareStatement(comando);
            stmt.setString(1, email.getEndereco());
            
            ResultSet result = stmt.executeQuery();
            
            result.next();
            
            return result.getBoolean(1);
        }
    }
    
    public static boolean existeUmUsuarioComCpf(Cpf cpf) throws SQLException{
        try(Connection conexao = MinhaConexao.obterConexao()){
            String comando = "SELECT EXISTS (SELECT 1 FROM Usuario WHERE CPF = ?)";

            PreparedStatement stmt = conexao.prepareStatement(comando);
            stmt.setString(1, cpf.getValor());
            
            ResultSet result = stmt.executeQuery();
            
            result.next();
            
            return result.getBoolean(1);
        }
    }
}
