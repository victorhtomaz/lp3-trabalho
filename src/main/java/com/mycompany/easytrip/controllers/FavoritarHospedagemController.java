package com.mycompany.easytrip.controllers;

import com.mycompany.easytrip.controllers.servicos.GrupoServico;
import com.mycompany.easytrip.controllers.servicos.UsuarioServico;
import com.mycompany.easytrip.controllers.servicos.excecoes.GrupoServicoException;
import com.mycompany.easytrip.controllers.servicos.excecoes.UsuarioServicoException;
import com.mycompany.easytrip.dominio.entidades.Grupo;
import com.mycompany.easytrip.repositorio.MinhaConexao;
import jakarta.persistence.EntityManager;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class FavoritarHospedagemController {
    
    public void favoritarHospedagem(int usuarioId, int hospedagemId){
        String[] opcoes = {"Individual", "Grupo"};
        int escolha = JOptionPane.showOptionDialog(null, "", "Favoritar", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
        
        if (escolha == 0)
            favoritarUsuario(usuarioId, hospedagemId);
        else if (escolha == 1)
            favoritarGrupo(usuarioId, hospedagemId);
        else
            return;
    }
    
    private void favoritarUsuario(int usuarioId, int hospedagemId){
        try(EntityManager entityManager = MinhaConexao.getEntityManager()){
            UsuarioServico servico = new UsuarioServico(entityManager);
            
            boolean estaFavoritada = servico.usuarioFavoritouHospedagem(usuarioId, hospedagemId);
            if (estaFavoritada){
                int resposta = JOptionPane.showConfirmDialog(null, "A hospedagem já está favoritada, deseja remover?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION){
                    servico.desfavoritarHospedagem(usuarioId, hospedagemId);
                    JOptionPane.showMessageDialog(null, "Removido!");
                }
            }
            else{
                servico.favoritarHospedagem(usuarioId, hospedagemId);
                JOptionPane.showMessageDialog(null, "Favoritado!");
            }
        }
        catch(UsuarioServicoException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void favoritarGrupo(int usuarioId, int hospedagemId){
        try(EntityManager entityManager = MinhaConexao.getEntityManager()){
            GrupoServico servico = new GrupoServico(entityManager);
            
            List<Grupo> grupos = servico.listarGruposDoUsuario(usuarioId);
            Grupo grupoEscolhido = escolhaDeGrupo(grupos);
            if (grupoEscolhido == null)
                return;
            
            int grupoId = grupoEscolhido.getId();
            
            boolean estaFavoritada = servico.grupoFavoritouAHospedagem(grupoId, hospedagemId);
            if (estaFavoritada){
                int resposta = JOptionPane.showConfirmDialog(null, "A hospedagem já está favoritada, deseja remover?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION){
                    servico.desfavoritarHospedagem(grupoId, hospedagemId);
                    JOptionPane.showMessageDialog(null, "Removido!");
                }
            }
            else{
                servico.favoritarHospedagem(grupoId, hospedagemId);
                JOptionPane.showMessageDialog(null, "Favoritado!");
            }
        }
        catch(GrupoServicoException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private Grupo escolhaDeGrupo(List<Grupo> grupos){
        JComboBox<Grupo> escolherGrupo = new JComboBox<>(grupos.toArray(Grupo[]::new));
        int grupoEscolha = JOptionPane.showConfirmDialog(null, escolherGrupo, "Grupos: ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (grupoEscolha == JOptionPane.OK_OPTION)
            return (Grupo) escolherGrupo.getSelectedItem();
        
        return null;
    }
}
