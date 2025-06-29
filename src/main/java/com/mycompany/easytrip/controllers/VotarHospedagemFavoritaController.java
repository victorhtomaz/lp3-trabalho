package com.mycompany.easytrip.controllers;

import com.mycompany.easytrip.controllers.servicos.GrupoServico;
import com.mycompany.easytrip.controllers.servicos.excecoes.GrupoServicoException;
import com.mycompany.easytrip.repositorio.MinhaConexao;
import jakarta.persistence.EntityManager;
import javax.swing.JOptionPane;

public class VotarHospedagemFavoritaController {
    
    public void votarHospedagem(int usuarioId, int grupoId, int hospedagemId){
        try(EntityManager entityManager = MinhaConexao.getEntityManager()){
            GrupoServico servico = new GrupoServico(entityManager);
            
            boolean foiVotado = servico.verificarSeUsuarioJaVotouNaHospedagem(usuarioId, grupoId, hospedagemId);
            if (foiVotado){
                int resposta = JOptionPane.showConfirmDialog(null, "Já votou na hospedagem, deseja remover o voto?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION){
                    servico.retirarVotoDaHospedagem(usuarioId, grupoId, hospedagemId);
                    JOptionPane.showMessageDialog(null, "Voto removido!");
                }
            }
            else{
                servico.votarHospedagem(usuarioId, grupoId, hospedagemId);
                JOptionPane.showMessageDialog(null, "Voto realizado!");
            }
        }
        catch(GrupoServicoException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
