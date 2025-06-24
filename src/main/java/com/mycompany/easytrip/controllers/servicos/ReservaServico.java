package com.mycompany.easytrip.controllers.servicos;

import com.mycompany.easytrip.controllers.servicos.excecoes.ReservaServicoException;
import com.mycompany.easytrip.dominio.entidades.Disponibilidade;
import com.mycompany.easytrip.dominio.entidades.Hospedagem;
import com.mycompany.easytrip.dominio.entidades.Reserva;
import com.mycompany.easytrip.dominio.entidades.Usuario;
import com.mycompany.easytrip.dominio.enums.StatusReserva;
import com.mycompany.easytrip.dominio.excecoes.DominioException;
import com.mycompany.easytrip.repositorio.HospedagemRepositorio;
import com.mycompany.easytrip.repositorio.ReservaRepositorio;
import com.mycompany.easytrip.repositorio.Transacao;
import com.mycompany.easytrip.repositorio.UsuarioRepositorio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ReservaServico {
    private final Transacao transacao;
    private final ReservaRepositorio reservaRepositorio;
    private final HospedagemRepositorio hospedagemRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    
    public ReservaServico(EntityManager entityManager){
        this.transacao = new Transacao(entityManager);
        this.reservaRepositorio = new ReservaRepositorio(entityManager);
        this.hospedagemRepositorio = new HospedagemRepositorio(entityManager);
        this.usuarioRepositorio = new UsuarioRepositorio(entityManager);
    }
    
    public void criarReserva(int usuarioId, int hospedagemId, LocalDate dataEntrada, LocalDate dataSaida,
            int quantidadeDeHospedes, BigDecimal precoDiaria) throws ReservaServicoException{
        
        try{
            LocalDate dataAtual = LocalDate.now();
            if (dataEntrada.isBefore(dataAtual) || dataEntrada.isEqual(dataAtual))
                throw new ReservaServicoException("Uma reserva não pode ser feita em dias que ja passaram");
            
            Usuario usuario = usuarioRepositorio.getUsuario(usuarioId);
            Hospedagem hospedagem = hospedagemRepositorio.getHospedagem(hospedagemId);
            
            if (usuario.getId() == hospedagem.getUsuarioId())
                throw new ReservaServicoException("O Anfitrião nao pode realizar reserva na própria hospedagem");

            Reserva reserva = new Reserva(usuario, hospedagem ,dataEntrada, dataSaida, quantidadeDeHospedes, precoDiaria);
            
            List<Disponibilidade> disponibilidades = hospedagem.getDisponibilidadesNoPeriodo(dataEntrada, dataSaida);
            for (Disponibilidade disponibilidade : disponibilidades)
                disponibilidade.setStatusParaReservado();
            
            transacao.iniciar();
            reservaRepositorio.criarReserva(reserva);
            hospedagemRepositorio.atualizarHospedagem(hospedagem);
            transacao.confirmar();
        }
        catch(DominioException e){
            throw new ReservaServicoException(e.getMessage());
        }
        catch(PersistenceException e){
            throw new ReservaServicoException("Falha ao persistir dados");
        }
    }
    
    public Reserva getReserva(int reservaId) throws ReservaServicoException{
        try{
            return reservaRepositorio.getReserva(reservaId);
        }
        catch(EntityNotFoundException e){
            throw new ReservaServicoException("A reserva não foi encontrada");
        }
    }
    
    public List<Reserva> listarReservasDoUsuario(int usuarioId){
        return reservaRepositorio.getReservasDoUsuario(usuarioId);
    }
    
    public List<Reserva> listarReservasDaHospedagem(int hospedagemId){
        return reservaRepositorio.getReservasDaHospedagem(hospedagemId);
    }
    
    public List<Reserva> listarReservasPendentes(int usuarioId){
        return reservaRepositorio.getReservasPendentes(usuarioId);
    }
    
    public void avaliarReserva(int reservaId, float nota) throws ReservaServicoException{
        try{
            Reserva reserva = reservaRepositorio.getReserva(reservaId);
            Hospedagem hospedagem = reserva.getHospedagem();
            
            reserva.setAvaliacao(nota);
            
            int quantidadeAvaliacoesHospedagem = reservaRepositorio.getQuantidadeReservasAvaliadasDaHospedagem(hospedagem.getId()) + 1;
            
            float notaMedia = hospedagem.getNotaAvaliacao();
            float novaNotaMedia = (notaMedia + reserva.getAvaliacao().getNota()) / (quantidadeAvaliacoesHospedagem + 1);

            hospedagem.setAvaliacao(novaNotaMedia);
            
            transacao.iniciar();
            reservaRepositorio.atualizarReserva(reserva);
            hospedagemRepositorio.atualizarHospedagem(hospedagem);
            transacao.confirmar();
        }
        catch(DominioException e){
            throw new ReservaServicoException(e.getMessage());
        }
        catch(EntityNotFoundException e){
            throw new ReservaServicoException("A reserva não foi encontrada");
        }
        catch(IllegalArgumentException e){
            throw new ReservaServicoException("Falha ao atualizar reserva");
        }
    }
    
    public void cancelarReserva(int reservaId) throws ReservaServicoException{
        try{
            Reserva reserva = reservaRepositorio.getReserva(reservaId);
            
            StatusReserva status = reserva.getStatus();
            if (status != StatusReserva.PENDENTE && status != StatusReserva.CONFIRMADA)
                throw new ReservaServicoException("Apenas reservas pendentes ou confirmadas podem ser canceladas");
                
            reserva.setStatusParaCancelada();
            
            LocalDate dataEntrada = reserva.getDataEntrada();
            LocalDate dataSaida = reserva.getDataSaida();
            
            Hospedagem hospedagem = reserva.getHospedagem();
            List<Disponibilidade> disponibilidades = hospedagem.getDisponibilidadesNoPeriodo(dataEntrada, dataSaida);
            
            for (Disponibilidade disponibilidade : disponibilidades)
                disponibilidade.setStatusParaDisponivel();
            
            transacao.iniciar();
            reservaRepositorio.atualizarReserva(reserva);
            hospedagemRepositorio.atualizarHospedagem(hospedagem);
            transacao.confirmar();
        }
        catch(EntityNotFoundException e){
            throw new ReservaServicoException("A reserva não foi encontrada");
        }
        catch(IllegalArgumentException e){
            throw new ReservaServicoException("Falha ao atualizar reserva");
        }
    }
    
    public void confirmarReserva(int reservaId) throws ReservaServicoException{
        try{
            Reserva reserva = reservaRepositorio.getReserva(reservaId);
            
            if (reserva.getStatus() != StatusReserva.PENDENTE)
                throw new ReservaServicoException("A reserva não possui o status de pendente para confirma-la");
            
            reserva.setStatusParaConfirmada();
            
            transacao.iniciar();
            reservaRepositorio.atualizarReserva(reserva);
            transacao.confirmar();
        }
        catch(EntityNotFoundException e){
            throw new ReservaServicoException("A reserva não foi encontrada");
        }
        catch(IllegalArgumentException e){
            throw new ReservaServicoException("Falha ao atualizar reserva");
        }
    }
}
