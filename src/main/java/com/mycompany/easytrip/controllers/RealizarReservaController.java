package com.mycompany.easytrip.controllers;

import com.mycompany.easytrip.controllers.servicos.GrupoServico;
import com.mycompany.easytrip.controllers.servicos.HospedagemServico;
import com.mycompany.easytrip.controllers.servicos.ReservaServico;
import com.mycompany.easytrip.controllers.servicos.excecoes.GrupoServicoException;
import com.mycompany.easytrip.controllers.servicos.excecoes.HospedagemServicoException;
import com.mycompany.easytrip.controllers.servicos.excecoes.ReservaServicoException;
import com.mycompany.easytrip.dominio.entidades.Disponibilidade;
import com.mycompany.easytrip.dominio.entidades.Grupo;
import com.mycompany.easytrip.dominio.entidades.Hospedagem;
import com.mycompany.easytrip.repositorio.MinhaConexao;
import com.mycompany.easytrip.telas.TelaPrincipal;
import com.mycompany.easytrip.telas.componentes.ConfirmarReservaPanel;
import com.mycompany.easytrip.telas.reservas.TelaRealizarReserva;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class RealizarReservaController {
    private final TelaRealizarReserva tela;
    private LocalDate dataEntradaSalva;
    private LocalDate dataSaidaSalva;
    private final DateTimeFormatter formatador = Disponibilidade.DATA_FORMATADOR;
     
    public RealizarReservaController(TelaRealizarReserva tela){
        this.tela = tela;
    }
    
    public void carregarHospedagem(int hospedagemId){
        try(EntityManager entityManager = MinhaConexao.getEntityManager()){
            HospedagemServico servico = new HospedagemServico(entityManager);
            
            Hospedagem hospedagem = servico.getHospedagem(hospedagemId);
            List<Disponibilidade> disponibilidades = hospedagem.getDisponibilidades();
            
            if (disponibilidades.isEmpty())
                JOptionPane.showMessageDialog(tela, "Não há datas disponiveis para reservar", "Aviso", JOptionPane.WARNING_MESSAGE);
            
            tela.setListaDisponibilidade(disponibilidades);
        }
        catch(HospedagemServicoException e){
            JOptionPane.showMessageDialog(tela, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(tela, "Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    public void continuarReserva(){
        LocalDate dataEntrada = tela.disponibilidadeParaReservarPanel1.getDataEntrada();
        LocalDate dataSaida = tela.disponibilidadeParaReservarPanel1.getDataSaida();
        
        if (dataEntrada == null || dataEntrada.isEqual(LocalDate.MIN) || dataSaida == null || dataSaida.isEqual(LocalDate.MIN)){
            JOptionPane.showMessageDialog(tela, "É necessário escolher as datas para continuar", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try(EntityManager entityManager = MinhaConexao.getEntityManager()){
            HospedagemServico hospedagemServico = new HospedagemServico(entityManager);
            GrupoServico grupoServico = new GrupoServico(entityManager);
            
            String[] opcoes = {"Individual", "Grupo"};
            int escolha = JOptionPane.showOptionDialog(null, "A reserva é", "Reserva", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
            if(escolha == 1)
                selecionarGrupo(grupoServico);
            
            hospedagemServico.verificarDisponibilidades(tela.getHospedagemId(), dataEntrada, dataSaida);
            Hospedagem hospedagem = hospedagemServico.getHospedagem(tela.getHospedagemId());
            BigDecimal precoDiaria = hospedagem.getPrecoDiaria().getValor();

            this.dataEntradaSalva = dataEntrada;
            this.dataSaidaSalva = dataSaida;
            int diferencaDias = (int) (dataSaidaSalva.toEpochDay() - dataEntrada.toEpochDay());
            BigDecimal precoTotal = BigDecimal.valueOf(diferencaDias).multiply(precoDiaria);
            
            ConfirmarReservaPanel confirmarReservaPanel = tela.confirmarReservaPanel1;
            confirmarReservaPanel.dataEntradaField.setText(dataEntradaSalva.format(formatador));
            confirmarReservaPanel.dataSaidaField.setText(dataSaidaSalva.format(formatador));
            confirmarReservaPanel.precoDiariaField.setValue((Number)precoDiaria);
            confirmarReservaPanel.quantidadeDiasField.setText(String.valueOf(diferencaDias));
            confirmarReservaPanel.precoTotalField.setText(precoTotal.toString());
            tela.proximoCardLayout();
        }
        catch(HospedagemServicoException e){
            JOptionPane.showMessageDialog(tela, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(tela, "Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    private void selecionarGrupo(GrupoServico grupoServico) throws GrupoServicoException{
        int usuarioLogadoId = tela.getUsuarioLogadoId();
        List<Grupo> grupos = grupoServico.listarGruposDoUsuario(usuarioLogadoId);
        
        JComboBox<Grupo> escolherGrupo = new JComboBox<>(grupos.toArray(Grupo[]::new));
        int grupoEscolha = JOptionPane.showConfirmDialog(tela, escolherGrupo, "Grupos: ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (grupoEscolha != JOptionPane.OK_OPTION)
            return;
        
        Grupo grupoEscolhido = (Grupo) escolherGrupo.getSelectedItem();
        
        boolean eResponsavel = grupoServico.checaSeUsuarioEResponsavel(grupoEscolhido.getId(), usuarioLogadoId);
        if (!eResponsavel){
            JOptionPane.showMessageDialog(tela, "É necessário ser um responsavel para reservar para o grupo", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int quantidadeHospedesGrupo = grupoEscolhido.getQuantidadeDeParticipantes();
        
        tela.confirmarReservaPanel1.quantidadeHospedeField.setText(String.valueOf(quantidadeHospedesGrupo));
    }
    
    public void finalizarReserva(){
        LocalDate dataEntrada = dataEntradaSalva;
        LocalDate dataSaida = dataSaidaSalva;
        Number auxPreco = (Number)tela.confirmarReservaPanel1.precoDiariaField.getValue();
        BigDecimal precoDiaria = new BigDecimal(auxPreco.doubleValue());
        int quantidadeDeHospedes = Integer.parseInt(tela.confirmarReservaPanel1.quantidadeHospedeField.getText());
        
        if(quantidadeDeHospedes == 0){
            JOptionPane.showMessageDialog(tela, "É necessário inserir a quantidade de hospedes", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try(EntityManager entityManager = MinhaConexao.getEntityManager()){
            ReservaServico servico = new ReservaServico(entityManager);
            
            servico.criarReserva(tela.getUsuarioLogadoId(), tela.getHospedagemId(), dataEntrada, dataSaida, quantidadeDeHospedes, precoDiaria);
            
            JOptionPane.showMessageDialog(tela, "Reserva realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
            mudarParaTelaMinhasReservas();
        } 
        catch(ReservaServicoException e){
            JOptionPane.showMessageDialog(tela, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } 
        catch(Exception e){
            JOptionPane.showMessageDialog(tela, "Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    private void mudarParaTelaMinhasReservas(){
        TelaPrincipal telaPrincipal = (TelaPrincipal)SwingUtilities.getWindowAncestor(tela);
        telaPrincipal.mudarParaTelaMinhasReservas();
    }
}
