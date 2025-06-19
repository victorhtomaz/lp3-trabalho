package com.mycompany.easytrip.controllers;

import com.mycompany.easytrip.controllers.servicos.HospedagemServico;
import com.mycompany.easytrip.dominio.entidades.Hospedagem;
import com.mycompany.easytrip.dominio.enums.EstadoBrasil;
import com.mycompany.easytrip.repositorio.MinhaConexao;
import com.mycompany.easytrip.telas.componentes.HospedagemVisualizacaoPanel;
import com.mycompany.easytrip.telas.hospedagem.TelaVisualizarHospedagens;
import jakarta.persistence.EntityManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VisualizarHospedagensController {
    
    private static final int QUANTIDADE_COLUNAS = 2;
    private final TelaVisualizarHospedagens tela;
    private final List<HospedagemVisualizacaoPanel> hospedagensPanels = new ArrayList<>();
    private String cidade = null;
    private EstadoBrasil estado = null;
    
    public VisualizarHospedagensController(TelaVisualizarHospedagens tela){
        this.tela = tela;
    }
    
    public void carregarHospedagens(){
        int paginaAtual = tela.getPaginaAtual();
        int quantidadePorPagina = TelaVisualizarHospedagens.QUANTIDADE_POR_PAGINA;
        
        
        try (EntityManager entityManager = MinhaConexao.getEntityManager()){
            HospedagemServico servico = new HospedagemServico(entityManager);
            
            List<Hospedagem> hospedagens = servico.listarHospedagens(paginaAtual, quantidadePorPagina, cidade, estado);
            int tamanhoLista = hospedagens.size();
            
            if (tamanhoLista < 1 && paginaAtual == 0){
                removerElementos();
                JOptionPane.showMessageDialog(tela, "Não foi encontrado nenhuma hospedagem", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
            else if (tamanhoLista < 1)
                tela.decrementarPagina();
            else{
                removerElementos();
                adicionarElemento(hospedagens);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void adicionarFiltro(){
        String cidadeFiltro = JOptionPane.showInputDialog(tela, "Cidade: ", "Filtrar", JOptionPane.PLAIN_MESSAGE);
        if (cidadeFiltro.isBlank())
            this.cidade = null;
        else
            this.cidade = cidadeFiltro;
        
        EstadoBrasil[] opcoes = EstadoBrasil.values();
        JComboBox<EstadoBrasil> escolherEstado = new JComboBox<>(opcoes);
        int estadoFiltro = JOptionPane.showConfirmDialog(tela, escolherEstado, "Estados", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (estadoFiltro != JOptionPane.OK_OPTION)
            this.estado = null;
        else
            this.estado = (EstadoBrasil) escolherEstado.getSelectedItem();
        
        tela.setPaginaAtualParaZero();
        tela.desligarBotaoAnterior();
        carregarHospedagens();
    }
    
    public void paginaSeguinte(){
        int quantidadePorPagina = TelaVisualizarHospedagens.QUANTIDADE_POR_PAGINA;
        if (hospedagensPanels.size() < quantidadePorPagina)
            return;
            
        tela.incrementarPagina();
        tela.ligarBotaoAnterior();
        
        carregarHospedagens();
        
        tela.atualizarPagina();
    }
    
    public void paginaAnterior(){
        tela.decrementarPagina();
         
        int paginaAtual = tela.getPaginaAtual();
        if (paginaAtual == 0)
            tela.desligarBotaoAnterior();
        
        removerElementos();
        carregarHospedagens();
        tela.atualizarPagina();
    }
    
    private void adicionarElemento(List<Hospedagem> hospedagens){
        JPanel painelVisualizacao = tela.panelVisualizar;
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
        JPanel painelVisualizacao = tela.panelVisualizar;
        for (HospedagemVisualizacaoPanel panel : hospedagensPanels){
            painelVisualizacao.remove(panel);
        }
        hospedagensPanels.clear();
    }
}
