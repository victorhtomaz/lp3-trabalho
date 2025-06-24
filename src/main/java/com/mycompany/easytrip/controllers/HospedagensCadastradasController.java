package com.mycompany.easytrip.controllers;

import com.mycompany.easytrip.controllers.servicos.HospedagemServico;
import com.mycompany.easytrip.controllers.servicos.excecoes.HospedagemServicoException;
import com.mycompany.easytrip.dominio.entidades.Hospedagem;
import com.mycompany.easytrip.repositorio.MinhaConexao;
import com.mycompany.easytrip.telas.hospedagem.TelaHospedagensCadastradas;
import jakarta.persistence.EntityManager;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class HospedagensCadastradasController {
    private final TelaHospedagensCadastradas tela;
    
    public HospedagensCadastradasController(TelaHospedagensCadastradas tela){
        this.tela = tela;
    }
    
    public void carregarHospedagensUsuario(int usuarioId){
        try(EntityManager entityManager = MinhaConexao.getEntityManager()){
            HospedagemServico servico = new HospedagemServico(entityManager);
            
            List<Hospedagem> hospedagens = servico.listarHospedagensDoUsuario(usuarioId);
            
            preencherTabela(hospedagens);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(tela, "Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void deletarHospedagemSelecionada(int usuarioId){
        int hospedagemSelecionadaId = tela.receberHospedagemSelecionada();
        if (hospedagemSelecionadaId == 0)
        {
            JOptionPane.showMessageDialog(tela, "É necessário selecionar uma hospedagem", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int escolha = JOptionPane.showConfirmDialog(tela, "Deseja deletar a hospedagem?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (escolha != 0)
            return;
        
        try(EntityManager entityManager = MinhaConexao.getEntityManager()){
            HospedagemServico servico = new HospedagemServico(entityManager);
            servico.deletar(hospedagemSelecionadaId);
            
            carregarHospedagensUsuario(usuarioId);
        }
        catch(HospedagemServicoException e){
            JOptionPane.showMessageDialog(tela, e.getMessage(), "Erro",JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(tela, "Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void mudarParaTelaReservas(){
        int hospedagemSelecionadaId = tela.receberHospedagemSelecionada();
        if (hospedagemSelecionadaId == 0)
        {
            JOptionPane.showMessageDialog(tela, "É necessário selecionar uma hospedagem", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        tela.reservasDaHospedagemButton1.mudarParaTelaReservas(hospedagemSelecionadaId);
    }
    
    public void mudarParaTelaGerenciamento(){
        int hospedagemSelecionadaId = tela.receberHospedagemSelecionada();
        if (hospedagemSelecionadaId == 0)
        {
            JOptionPane.showMessageDialog(tela, "É necessário selecionar uma hospedagem", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        tela.gerenciarHospedagemButton1.mudarParaTelaGerenciamentoDeHospedagem(hospedagemSelecionadaId);
    }
    
    private void preencherTabela(List<Hospedagem> hospedagens){
        DefaultTableModel model = (DefaultTableModel) tela.hospedagemTable.getModel();
        model.setRowCount(0);
        TableColumnModel columnModel = tela.hospedagemTable.getColumnModel();
        columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setMaxWidth(0);
        columnModel.getColumn(0).setWidth(0);

        for(Hospedagem hospedagem : hospedagens){
            model.addRow(new Object[]{hospedagem.getId(), hospedagem.getTitulo(), hospedagem.getTipo(), hospedagem.getNotaAvaliacao()});
        }
    }
    
}
