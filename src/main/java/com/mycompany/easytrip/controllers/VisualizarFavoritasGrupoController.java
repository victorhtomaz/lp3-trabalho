package com.mycompany.easytrip.controllers;

import com.mycompany.easytrip.controllers.servicos.GrupoServico;
import com.mycompany.easytrip.dominio.entidades.Grupo;
import com.mycompany.easytrip.dominio.entidades.Hospedagem;
import com.mycompany.easytrip.repositorio.MinhaConexao;
import com.mycompany.easytrip.telas.componentes.HospedagemFavoritasGrupoVisuPanel;
import com.mycompany.easytrip.telas.grupos.TelaFavoritasGrupo;
import jakarta.persistence.EntityManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VisualizarFavoritasGrupoController {
    private final TelaFavoritasGrupo tela;
    private final List<HospedagemFavoritasGrupoVisuPanel> hospedagensPanels = new ArrayList<>();
    private List<Grupo> grupos = new ArrayList<>();
    private Grupo grupoEscolhido = null;

    public VisualizarFavoritasGrupoController(TelaFavoritasGrupo tela){
        this.tela = tela;
    }
    
    public void carregarHospedagens(int usuarioId){
        int paginaAtual = tela.getPaginaAtual();
        int quantidadePorPagina = TelaFavoritasGrupo.QUANTIDADE_POR_PAGINA;
        
        try(EntityManager entityManager = MinhaConexao.getEntityManager()){
            GrupoServico servico = new GrupoServico(entityManager);
            
            grupos = servico.listarGruposDoUsuario(usuarioId);
            
            if (grupos.isEmpty()){
                JOptionPane.showMessageDialog(tela, "Não faz parte de nenhum grupo", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (grupoEscolhido == null)
                resolverGrupoEscolhido();
            
            tela.favoritasTituloLabel.setText("Favoritas de " + grupoEscolhido.getNome());
            
            List<Hospedagem> hospedagens = new ArrayList<>();
            hospedagens = servico.listarHospedagensFavoritasDoGrupo(grupoEscolhido.getId(),  paginaAtual, quantidadePorPagina);            
            
            int tamanhoLista = hospedagens.size();
            
            if (tamanhoLista < 1 && paginaAtual == 0){
                removerElementos();
                JOptionPane.showMessageDialog(tela, "O grupo não possui hospedagens favoritadas", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
            else if (tamanhoLista < 1)
                tela.decrementarPagina();
            else{
                removerElementos();
                adicionarElementos(hospedagens);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(tela, "Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void alterarGrupo(int usuarioId){
        Grupo grupoAlterar = escolhaDeGrupo(grupos);
        if(grupoAlterar == null)
            return;
            
        grupoEscolhido = grupoAlterar;
        carregarHospedagens(usuarioId);
    }
    
    private void resolverGrupoEscolhido(){
        grupoEscolhido = escolhaDeGrupo(grupos);
            
        if (grupoEscolhido == null)
            grupoEscolhido = grupos.getFirst();
    }
    
    private Grupo escolhaDeGrupo(List<Grupo> grupos){
        JComboBox<Grupo> escolherGrupo = new JComboBox<>(grupos.toArray(Grupo[]::new));
        int grupoEscolha = JOptionPane.showConfirmDialog(tela, escolherGrupo, "Grupos: ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (grupoEscolha == JOptionPane.OK_OPTION)
            return (Grupo) escolherGrupo.getSelectedItem();
        
        return null;
    }
    
    private void adicionarElementos(List<Hospedagem> hospedagens){
        JPanel painelVisualizacao = tela.visualizacaoPanel;
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
            if (contador % TelaFavoritasGrupo.QUANTIDADE_COLUNAS != 0){
                x = 0;
                y++;
            }
            else
                x++;
            
            constraints.gridx = x;
            constraints.gridy = y;
            
            HospedagemFavoritasGrupoVisuPanel panel = new HospedagemFavoritasGrupoVisuPanel(hospedagem, grupoEscolhido.getId());
            painelVisualizacao.add(panel, constraints);
            hospedagensPanels.add(panel);
        }
    }
    
    private void removerElementos(){
        JPanel painelVisualizacao = tela.visualizacaoPanel;
        for (HospedagemFavoritasGrupoVisuPanel panel : hospedagensPanels){
            painelVisualizacao.remove(panel);
        }
        hospedagensPanels.clear();
    }
    
    public void paginaSeguinte(int usuarioId){
        int quantidadePorPagina = TelaFavoritasGrupo.QUANTIDADE_POR_PAGINA;
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
