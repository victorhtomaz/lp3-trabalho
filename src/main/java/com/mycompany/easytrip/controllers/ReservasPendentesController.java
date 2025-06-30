package com.mycompany.easytrip.controllers;

import com.mycompany.easytrip.controllers.servicos.ReservaServico;
import com.mycompany.easytrip.controllers.servicos.excecoes.ReservaServicoException;
import com.mycompany.easytrip.dominio.entidades.Hospedagem;
import com.mycompany.easytrip.dominio.entidades.Reserva;
import com.mycompany.easytrip.repositorio.MinhaConexao;
import com.mycompany.easytrip.telas.reservas.TelaReservasPendentes;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class ReservasPendentesController {
    private final TelaReservasPendentes tela;
    
    public ReservasPendentesController(TelaReservasPendentes tela){
        this.tela = tela;
    }
    
    public void carregarReservasPendentes(int usuarioId){
        try(EntityManager entityManager = MinhaConexao.getEntityManager()){
            ReservaServico servico = new ReservaServico(entityManager);
            
            List<Reserva> reservas = servico.listarReservasPendentes(usuarioId);
            
            preencherTabela(reservas);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(tela, "Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void confirmarReservaPendente(){
        int reservaSelecionadaId = tela.receberReservaSelecionada();
        if (reservaSelecionadaId == 0){
            JOptionPane.showMessageDialog(tela, "É necessário selecionar uma reserva", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int escolha = JOptionPane.showConfirmDialog(tela, "Deseja confirmar a reserva?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (escolha != 0)
            return;
        
        try(EntityManager entityManager = MinhaConexao.getEntityManager()){
            ReservaServico servico = new ReservaServico(entityManager);
            
            servico.confirmarReserva(reservaSelecionadaId);
            
            JOptionPane.showMessageDialog(tela, "Reserva confirmada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            carregarReservasPendentes(tela.getUsuarioLogadoId());
        }
        catch(ReservaServicoException e){
            JOptionPane.showMessageDialog(tela, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(tela, "Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void preencherTabela(List<Reserva> reservas){
        DefaultTableModel model = (DefaultTableModel) tela.reservaTable.getModel();
        model.setRowCount(0);
        TableColumnModel columnModel = tela.reservaTable.getColumnModel();
        columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setMaxWidth(0);
        columnModel.getColumn(0).setWidth(0);
        
        for (Reserva reserva : reservas){
            Hospedagem hospedagem = reserva.getHospedagem();
            BigDecimal quantidadeDias = BigDecimal.valueOf(reserva.getQuantidadeDeDias());
            BigDecimal valorTotal = reserva.getPrecoDiaria().getValor().multiply(quantidadeDias);
            model.addRow(new Object[]{reserva.getId(), hospedagem.getTitulo(), hospedagem.getCapacidadeMaxima(), reserva.getQuantidadeDeHospedes(), reserva.getDataEntradaFormatada(), valorTotal});
        }
    }
    
    public void mudarParaTelaDetalhesReserva(){
        int reservaSelecionadaId = tela.receberReservaSelecionada();
        if (reservaSelecionadaId == 0){
            JOptionPane.showMessageDialog(tela, "É necessário selecionar uma reserva", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
            
        tela.detalhesReservaButton.abrirTelaDetalhesReserva(reservaSelecionadaId);
    }
}
