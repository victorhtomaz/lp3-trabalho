package com.mycompany.easytrip.controllers;

import com.mycompany.easytrip.controllers.servicos.UsuarioServico;
import com.mycompany.easytrip.dominio.entidades.Hospedagem;
import com.mycompany.easytrip.repositorio.MinhaConexao;
import com.mycompany.easytrip.telas.componentes.HospedagemVisualizacaoPanel;
import com.mycompany.easytrip.telas.hospedagem.TelaFavoritasHospede;
import com.mycompany.easytrip.telas.hospedagem.TelaVisualizarHospedagens;
import jakarta.persistence.EntityManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VisualizarFavoritasHospedeController {
    private final TelaFavoritasHospede tela;
    private final List<HospedagemVisualizacaoPanel> hospedagensPanels = new ArrayList<>();
    private static final int QUANTIDADE_COLUNAS = 2;
    
    public VisualizarFavoritasHospedeController(TelaFavoritasHospede tela){
        this.tela = tela;
    }
    
    public void carregarHospedagens(int usuarioId){
        int paginaAtual = tela.getPaginaAtual();
        int quantidadePorPagina = TelaVisualizarHospedagens.QUANTIDADE_POR_PAGINA;
        
        try (EntityManager entityManager = MinhaConexao.getEntityManager()){
            UsuarioServico servico = new UsuarioServico(entityManager);
            
            List<Hospedagem> hospedagens = servico.listarHospedagensFavoritasDoUsuario(usuarioId, paginaAtual, quantidadePorPagina);
            int tamanhoLista = hospedagens.size();
            
            if (tamanhoLista < 1 && paginaAtual == 0){
                removerElementos();
                JOptionPane.showMessageDialog(tela, "Não possui hospedagens favoritadas", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
            else if (tamanhoLista < 1)
                tela.decrementarPagina();
            else{
                removerElementos();
                adicionarElemento(hospedagens);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(tela, "Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void adicionarElemento(List<Hospedagem> hospedagens){
        JPanel painelVisualizacao = tela.visualizaPanel;
        GridBagLayout layout = (GridBagLayout) painelVisualizacao.getLayout();
        GridBagConstraints constraints = layout.getConstraints(painelVisualizacao);
        
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.insets.set(15, 20, 0, 10);
        
        int contador = 0;
        int x = 0;
        int y = 0;
        
        for (Hospedagem hospedagem : hospedagens){
            contador++;
            if (contador % QUANTIDADE_COLUNAS != 0){
                x = 0;
                y++;
            }
            else
                x++;
            
            constraints.gridx = x;
            constraints.gridy = y;
            
            HospedagemVisualizacaoPanel panel = new HospedagemVisualizacaoPanel(hospedagem);
            painelVisualizacao.add(panel, constraints);
            hospedagensPanels.add(panel);
        }
    }
    
    private void removerElementos(){
        JPanel painelVisualizacao = tela.visualizaPanel;
        for (HospedagemVisualizacaoPanel panel : hospedagensPanels){
            painelVisualizacao.remove(panel);
        }
        hospedagensPanels.clear();
    }
    
    public void paginaSeguinte(int usuarioId){
        int quantidadePorPagina = TelaVisualizarHospedagens.QUANTIDADE_POR_PAGINA;
        if (hospedagensPanels.size() < quantidadePorPagina)
            return;
            
        tela.incrementarPagina();
        tela.ligarBotaoAnterior();
        
        carregarHospedagens(usuarioId);
        
        tela.atualizarPagina();
    }
    
    public void paginaAnterior(int usuarioId){
        tela.decrementarPagina();
         
        int paginaAtual = tela.getPaginaAtual();
        if (paginaAtual == 0)
            tela.desligarBotaoAnterior();
        
        removerElementos();
        carregarHospedagens(usuarioId);
        tela.atualizarPagina();
    }
}
