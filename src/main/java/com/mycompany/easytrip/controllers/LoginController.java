package com.mycompany.easytrip.controllers;

import com.mycompany.easytrip.controllers.servicos.UsuarioServico;
import com.mycompany.easytrip.dominio.entidades.Usuario;
import com.mycompany.easytrip.dominio.excecoes.DominioException;
import com.mycompany.easytrip.dominio.objetosDeValor.Cpf;
import com.mycompany.easytrip.dominio.objetosDeValor.Email;
import com.mycompany.easytrip.dominio.objetosDeValor.Senha;
import com.mycompany.easytrip.repositorio.MinhaConexao;
import com.mycompany.easytrip.repositorio.UsuarioRepositorio;
import com.mycompany.easytrip.controllers.servicos.excecoes.UsuarioServicoException;
import com.mycompany.easytrip.telas.TelaContaDoUsuario;
import com.mycompany.easytrip.telas.TelaDeLogin;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JOptionPane;
public class LoginController {
    
    private final TelaDeLogin tela;
    
    public LoginController(TelaDeLogin tela){
        this.tela = tela;
    }
    
    public void logarUsuario(){
        String enderecoEmail = tela.emailTextField1.getText();
        String valorSenha = new String(tela.senhaField1.getPassword());
        
        try (EntityManager entityManager = MinhaConexao.getEntityManager()){
            Email email = new Email(enderecoEmail);
            Senha senha = new Senha(valorSenha);
        
            UsuarioServico servico = new UsuarioServico(entityManager);
        
            int usuarioId = servico.checarLogin(email, senha);
            
            if (usuarioId == 0)
                JOptionPane.showMessageDialog(null, "O email e/ou senha estão incorretos", "Aviso", JOptionPane.WARNING_MESSAGE);
            else
                tela.atualizarTelaPrincipal(usuarioId);
        }
        catch(DominioException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
   
    public void registrarUsuario(){
        //Le dados dos campos
        String nome = tela.nomeField.getText();
        String cpfValor = tela.cpfField.getText();
        String enderecoEmail = tela.emailField2.getText();
        String senhaValor = new String(tela.senhaField2.getPassword());
        LocalDate dataNascimento = tela.dataNascimentoDateChosser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        try(EntityManager entityManager = MinhaConexao.getEntityManager()){
            //valida as entradas
            Email email = new Email(enderecoEmail);
            Senha senha = new Senha(senhaValor);
            Cpf cpf = new Cpf(cpfValor);
            
            Usuario usuario = new Usuario(nome, email, senha, cpf, dataNascimento);
   
            //verifica no banco se o usuario ja existe e cria o usuario
            UsuarioServico servico = new UsuarioServico(entityManager);
            
            servico.registrarUsuario(usuario);
            
            JOptionPane.showMessageDialog(tela, "Registrado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            tela.voltarParaLogin();
        }
        catch(DominioException | UsuarioServicoException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
        }
   }
    
    public static void obterDadosUsuario(TelaContaDoUsuario tela, EntityManager entityManager){
        UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio(MinhaConexao.getEntityManager());
        Usuario usuario = usuarioRepositorio.getUsuario(tela.getUsuarioId());
        
        tela.nomeField.setText(usuario.getNome());
        tela.emailField.setText(usuario.getEnderecoEmail());
        
        LocalDate dataNascimento = usuario.getDataNascimento();
        tela.dataNascimentoChooser.setDate(Date.from(dataNascimento.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        tela.cpfField.setText(usuario.getValorCpf());
        
        MinhaConexao.finalizar(entityManager);
    }
}
