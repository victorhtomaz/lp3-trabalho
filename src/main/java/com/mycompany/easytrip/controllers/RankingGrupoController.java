package com.mycompany.easytrip.controllers;

import com.mycompany.easytrip.controllers.servicos.GrupoServico;
import com.mycompany.easytrip.dominio.entidades.Grupo;
import com.mycompany.easytrip.dominio.entidades.Hospedagem;
import com.mycompany.easytrip.repositorio.MinhaConexao;
import com.mycompany.easytrip.telas.componentes.HospedagemFavoritasGrupoVisuPanel;
import com.mycompany.easytrip.telas.grupos.TelaRankingGrupo;
import jakarta.persistence.EntityManager;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RankingGrupoController {
    private final TelaRankingGrupo tela;
    private final List<HospedagemFavoritasGrupoVisuPanel> hospedagensPanels = new ArrayList<>();
    private final List<JLabel> posicaoLabels = new ArrayList<>();
    private List<Grupo> grupos = new ArrayList<>();
    private Grupo grupoEscolhido = null;
    
    public RankingGrupoController(TelaRankingGrupo tela){
        this.tela = tela;
    }
    
    public void carregarHospedagens(int usuarioId){
        int quantidadeNoRanking = TelaRankingGrupo.QUANTIDADE_HOSPEDAGEM_RANKING;
        try(EntityManager entityManager = MinhaConexao.getEntityManager()){
            GrupoServico servico = new GrupoServico(entityManager);
            
            grupos = servico.listarGruposDoUsuario(usuarioId);
            
            if (grupos.isEmpty()){
                JOptionPane.showMessageDialog(tela, "Não faz parte de nenhum grupo", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (grupoEscolhido == null)
                resolverGrupoEscolhido();
            
            tela.rankingTituloLabel.setText("Ranking de " + grupoEscolhido.getNome());
            
            Map<Hospedagem, Long> hospedagensDoRanking = servico.listarRankingHospedagensFavoritadas(grupoEscolhido.getId(), quantidadeNoRanking);
            
            adicionarElementos(hospedagensDoRanking);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(tela, "Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void adicionarElementos(Map<Hospedagem, Long> hospedagens){
        JPanel painelVisualizacao = tela.visualizacaoPanel;
        GridBagLayout layout = (GridBagLayout) painelVisualizacao.getLayout();
        GridBagConstraints constraints = layout.getConstraints(painelVisualizacao);
        
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.insets.set(15, 20, 0, 10);
        
        int contador = 0;
        
        for (Map.Entry<Hospedagem, Long> entrie : hospedagens.entrySet()){
            contador++;
            
            constraints.gridx = 1;
            constraints.gridy = contador;
            
            constraints.anchor = GridBagConstraints.WEST;
            JLabel posicaoRankingLabel = new JLabel(contador + " - (" + entrie.getValue().toString() + " votos)");
            posicaoRankingLabel.setFont(new Font("JetBrainsMono NF", Font.PLAIN, 24));
            
            painelVisualizacao.add(posicaoRankingLabel, constraints);
            posicaoLabels.add(posicaoRankingLabel);
            
            constraints.anchor = GridBagConstraints.CENTER;
            
            HospedagemFavoritasGrupoVisuPanel panel = new HospedagemFavoritasGrupoVisuPanel(entrie.getKey(), grupoEscolhido.getId());
            painelVisualizacao.add(panel, constraints);
            hospedagensPanels.add(panel);
        }
    }
    
    private void removerElementos(){
        JPanel painelVisualizacao = tela.visualizacaoPanel;
        for (HospedagemFavoritasGrupoVisuPanel panel : hospedagensPanels){
            painelVisualizacao.remove(panel);
        }
        for (JLabel label : posicaoLabels)
            painelVisualizacao.remove(label);
        
        hospedagensPanels.clear();
        posicaoLabels.clear();
    }
    
    public void alterarGrupo(int usuarioId){
        removerElementos();
        
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
}
