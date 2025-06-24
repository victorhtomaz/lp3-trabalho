package com.mycompany.easytrip.controllers;

import com.mycompany.easytrip.controllers.servicos.ReservaServico;
import com.mycompany.easytrip.dominio.entidades.Reserva;
import com.mycompany.easytrip.repositorio.MinhaConexao;
import com.mycompany.easytrip.telas.reservas.TelaMinhasReservas;
import jakarta.persistence.EntityManager;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class MinhasReservasController {
    private final TelaMinhasReservas tela;
    
    public MinhasReservasController(TelaMinhasReservas tela){
        this.tela = tela;
    }
    
    public void carregarReservas(int usuarioId){
        try(EntityManager entityManager = MinhaConexao.getEntityManager()){
            ReservaServico servico = new ReservaServico(entityManager);
            
            List<Reserva> reservas = servico.listarReservasDoUsuario(usuarioId);
            
            preencherTabela(reservas);
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
        
        for (Reserva reserva : reservas)
            model.addRow(new Object[]{reserva.getId(), reserva.getTituloHospedagem(), reserva.getStatus().name(), reserva.getDataEntradaFormatada()});
    }
    
    public void mudarParaTelaDetalhesReserva(){
        int reservaSelecionadaId = tela.receberReservaSelecionada();
        if (reservaSelecionadaId == 0){
            JOptionPane.showMessageDialog(tela, "É necessário selecionar uma reserva", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
            
        tela.detalhesReservaButton1.abrirTelaDetalhesReserva(reservaSelecionadaId);
    }
}
