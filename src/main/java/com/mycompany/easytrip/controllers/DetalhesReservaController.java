package com.mycompany.easytrip.controllers;

import com.mycompany.easytrip.controllers.servicos.ReservaServico;
import com.mycompany.easytrip.controllers.servicos.excecoes.ReservaServicoException;
import com.mycompany.easytrip.dominio.entidades.Reserva;
import com.mycompany.easytrip.dominio.enums.StatusReserva;
import com.mycompany.easytrip.dominio.objetosDeValor.Avaliacao;
import com.mycompany.easytrip.repositorio.MinhaConexao;
import com.mycompany.easytrip.telas.reservas.TelaDetalhesReserva;
import jakarta.persistence.EntityManager;
import javax.swing.JOptionPane;

public class DetalhesReservaController {
    private final TelaDetalhesReserva tela;
    private Reserva reserva;
    
    public DetalhesReservaController(TelaDetalhesReserva tela){
        this.tela = tela;
    }
    
    public void carregarReserva(int reservaId){
        try(EntityManager entityManager = MinhaConexao.getEntityManager()){
            ReservaServico servico = new ReservaServico(entityManager);
            
            this.reserva = servico.getReserva(reservaId);
            
            preencherCampos();
        }
        catch(ReservaServicoException e){
            JOptionPane.showMessageDialog(tela, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(tela, "Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void preencherCampos(){
        tela.tituloField.setText(reserva.getTituloHospedagem());
        tela.enderecoField.setText(reserva.getEnderecoHospedagem());
        tela.dataEntradaField.setText(reserva.getDataEntradaFormatada());
        tela.dataSaidaField.setText(reserva.getDataSaidaFormatada());
        tela.quantidadeHospedeField.setText(String.valueOf(reserva.getQuantidadeDeHospedes()));
        tela.quantidadeDiasField.setText(String.valueOf(reserva.getQuantidadeDeDias()));
        tela.precoDiariaField.setValue( (Number) reserva.getPrecoDiaria().getValor());
        
        StatusReserva status = reserva.getStatus();
        tela.statusField.setText(status.name());
        
        int usuarioLogadoId = tela.getUsuarioLogado();
        boolean eHospede = reserva.getUsuario().getId() == usuarioLogadoId;
        
        Avaliacao avaliacao = reserva.getAvaliacao();
        float nota = 0;
        if (avaliacao == null && status == StatusReserva.FINALIZADA && eHospede)
            tela.avaliarButton.setEnabled(true);
        
        if (avaliacao != null)
            nota = avaliacao.getNota();

        tela.avaliacaoField.setText(String.valueOf(nota));
        
        if (status == StatusReserva.PENDENTE || status == StatusReserva.CONFIRMADA)
            tela.cancelarReservaButton.setEnabled(true);
    }
    
    public void avaliarReserva(int reservaId){
        String nota = JOptionPane.showInputDialog(null, "Digite a nota entre 0.00 e 5.00: ");
        if (nota == null)
            return;
        
        try(EntityManager entityManager = MinhaConexao.getEntityManager()){
            ReservaServico servico = new ReservaServico(entityManager);
            
            servico.avaliarReserva(reservaId, Float.parseFloat(nota));
            
            tela.avaliarButton.setEnabled(false);
            carregarReserva(reservaId);
        }
        catch(ReservaServicoException e){
            JOptionPane.showMessageDialog(tela, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(tela, "Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void cancelarReserva(int reservaId){
        int escolha = JOptionPane.showConfirmDialog(tela, "Deseja cancelar a reserva?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (escolha != 0)
            return;
        
        try(EntityManager entityManager = MinhaConexao.getEntityManager()){
            ReservaServico servico = new ReservaServico(entityManager);
            
            servico.cancelarReserva(reservaId);
            
            JOptionPane.showMessageDialog(tela, "A reserva foi cancelada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
            tela.cancelarReservaButton.setEnabled(false);
            carregarReserva(reservaId);
        }
        catch(ReservaServicoException e){
            JOptionPane.showMessageDialog(tela, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(tela, "Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
