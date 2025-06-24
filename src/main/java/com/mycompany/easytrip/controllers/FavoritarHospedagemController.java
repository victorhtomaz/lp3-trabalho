package com.mycompany.easytrip.controllers;

import com.mycompany.easytrip.controllers.servicos.UsuarioServico;
import com.mycompany.easytrip.controllers.servicos.excecoes.UsuarioServicoException;
import com.mycompany.easytrip.repositorio.MinhaConexao;
import jakarta.persistence.EntityManager;
import javax.swing.JOptionPane;

public class FavoritarHospedagemController {
    
    public void favoritarHospedagem(int usuarioId, int hospedagemId){
        String[] opcoes = {"Individual", "Grupo"};
        int escolha = JOptionPane.showOptionDialog(null, "", "Favoritar", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
        
        if (escolha == 0)
            favoritarUsuario(usuarioId, hospedagemId);
        else if (escolha == 1)
            favoritarGrupo();
        else
            return;
    }
    
    private void favoritarUsuario(int usuarioId, int hospedagemId){
        try(EntityManager entityManager = MinhaConexao.getEntityManager()){
            UsuarioServico servico = new UsuarioServico(entityManager);
            
            boolean estaFavoritada = servico.usuarioFavoritouHospedagem(usuarioId, hospedagemId);
            if (estaFavoritada){
                int resposta = JOptionPane.showConfirmDialog(null, "A hospedagem já está favoritada, deseja remover?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION)
                    servico.desfavoritarHospedagem(usuarioId, hospedagemId);
            }
            else{
                servico.favoritarHospedagem(usuarioId, hospedagemId);
                JOptionPane.showMessageDialog(null, "Ok!");
            }
        }
        catch(UsuarioServicoException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void favoritarGrupo(){
        
    }
}
