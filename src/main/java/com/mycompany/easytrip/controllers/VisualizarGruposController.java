package com.mycompany.easytrip.controllers;

import com.mycompany.easytrip.controllers.servicos.GrupoServico;
import com.mycompany.easytrip.controllers.servicos.excecoes.GrupoServicoException;
import com.mycompany.easytrip.dominio.entidades.Grupo;
import com.mycompany.easytrip.dominio.entidades.HospedeGrupo;
import com.mycompany.easytrip.repositorio.MinhaConexao;
import com.mycompany.easytrip.telas.grupos.TelaVisualizarGrupos;
import jakarta.persistence.EntityManager;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class VisualizarGruposController {
    private final TelaVisualizarGrupos tela;
    
    public VisualizarGruposController(TelaVisualizarGrupos tela){
        this.tela = tela;
    }
    
    public void carregarGrupos(int usuarioId){
        try(EntityManager entityManager = MinhaConexao.getEntityManager()){
            GrupoServico servico = new GrupoServico(entityManager);
            
            List<HospedeGrupo> hospedesGrupos = servico.listarVinculosDoUsuario(usuarioId);
            
            preencherTabela(hospedesGrupos);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(tela, "Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void criarGrupo(int usuarioId){
        String nomeGrupo = JOptionPane.showInputDialog(tela, "Digite um nome para o grupo: ", "Criação de grupos", JOptionPane.PLAIN_MESSAGE);
        if (nomeGrupo == null)
            return;
        
        try(EntityManager entityManager = MinhaConexao.getEntityManager()){
            GrupoServico servico = new GrupoServico(entityManager);
            
            servico.criarGrupo(usuarioId, nomeGrupo);
            
            JOptionPane.showMessageDialog(tela, "Grupo criado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            carregarGrupos(usuarioId);
        }
        catch(GrupoServicoException e){
            JOptionPane.showMessageDialog(tela, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(tela, "Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void sairDoGrupo(int usuarioId){
        int grupoSelecionadoId = tela.receberGrupoSelecionado();
        if (grupoSelecionadoId == 0)
        {
            JOptionPane.showMessageDialog(tela, "É necessário selecionar um grupo", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int escolha = JOptionPane.showConfirmDialog(tela, "Deseja sair do grupo?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (escolha != 0)
            return;
        
        try(EntityManager entityManager = MinhaConexao.getEntityManager()){
            GrupoServico servico = new GrupoServico(entityManager);
            
            servico.removerUsuarioDoGrupo(grupoSelecionadoId, usuarioId);
            
            carregarGrupos(usuarioId);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(tela, "Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void mudarParaTelaGerenciarGrupo(){
        int grupoSelecionadoId = tela.receberGrupoSelecionado();
        if (grupoSelecionadoId == 0)
        {
            JOptionPane.showMessageDialog(tela, "É necessário selecionar um grupo", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        tela.gerenciarGrupoButton1.mudarParaTelaGerenciar(grupoSelecionadoId);
    }
    
    private void preencherTabela(List<HospedeGrupo> hospedesGrupos){
        DefaultTableModel model = (DefaultTableModel) tela.grupoTable.getModel();
        model.setRowCount(0);
        TableColumnModel columnModel = tela.grupoTable.getColumnModel();
        columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setMaxWidth(0);
        columnModel.getColumn(0).setWidth(0);

        for(HospedeGrupo vinculo : hospedesGrupos){
            Grupo grupo = vinculo.getGrupo();
            model.addRow(new Object[]{grupo.getId(), grupo.getNome(), vinculo.getFuncao().name(), vinculo.getQuantidadeDeAcompanhantes()});
        }
    }
}
