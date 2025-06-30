package com.mycompany.easytrip.controllers.servicos;

import com.mycompany.easytrip.controllers.servicos.excecoes.HospedagemServicoException;
import com.mycompany.easytrip.dominio.entidades.Disponibilidade;
import com.mycompany.easytrip.dominio.entidades.Hospedagem;
import com.mycompany.easytrip.dominio.entidades.Imagem;
import com.mycompany.easytrip.dominio.enums.EstadoBrasil;
import com.mycompany.easytrip.dominio.enums.StatusDisponibilidade;
import com.mycompany.easytrip.dominio.excecoes.DominioException;
import com.mycompany.easytrip.repositorio.HospedagemRepositorio;
import com.mycompany.easytrip.repositorio.Transacao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import javax.imageio.ImageIO;

public class HospedagemServico {
    private final Transacao transacao;
    private final HospedagemRepositorio hospedagemRepositorio;
    
    private static final String CAMINHO_PASTA_IMAGENS = "imagemHospedagens/";
    
    public HospedagemServico(EntityManager entityManager){
        this.transacao = new Transacao(entityManager);
        this.hospedagemRepositorio = new HospedagemRepositorio(entityManager);
    }
    
    public void criar(Hospedagem hospedagem) throws HospedagemServicoException{
        try{
            transacao.iniciar();
            hospedagemRepositorio.criarHospedagem(hospedagem);
            transacao.confirmar();
        }
        catch(PersistenceException e){
            throw new HospedagemServicoException("Erro ao persistir os dados");
        }
    }
    
    public List<Hospedagem> listarHospedagensDoUsuario(int usuarioId){
        return hospedagemRepositorio.getHospedagensDoUsuario(usuarioId);
    }
    
    public Hospedagem getHospedagem(int hospedagemId) throws HospedagemServicoException{
        try{
            return hospedagemRepositorio.getHospedagem(hospedagemId);
        }
        catch(EntityNotFoundException e){
            throw new HospedagemServicoException("A hospedagem não foi encontrada");
        }
    }
    
    public List<Hospedagem> listarHospedagens(int pagina, int quantidadePorPagina, String cidade, EstadoBrasil estado){
        return hospedagemRepositorio.getHospedagens(pagina, quantidadePorPagina, cidade, estado);
    }
    
    public List<Hospedagem> listarHospedagensFavoritasDoUsuario(int usuarioId, int pagina, int quantidadePorPagina){
        return null;
    }
    
    public void deletar(int hospedagemId) throws HospedagemServicoException{
       try{
            transacao.iniciar();
            Hospedagem hospedagem = hospedagemRepositorio.getHospedagem(hospedagemId);
            if (hospedagem.existeReservaConfirmada())
                throw new HospedagemServicoException("Não é possivel deletar hospedagem, cancele reservas confirmadas");
            
            hospedagemRepositorio.deletarHospedagem(hospedagem);
            transacao.confirmar();
        }
        catch(EntityNotFoundException e){
            throw new HospedagemServicoException("A entidade não foi encontrada");
        }
    }
    
    public void atualizarInformacoesDaHospedagem(Hospedagem hospedagem, String titulo, 
            String descricao, int capacidadeMaxima, 
            BigDecimal precoDiaria, LocalTime checkIn, 
            LocalTime checkOut) throws HospedagemServicoException{
            
            try{
                hospedagem.setTitulo(titulo);
                hospedagem.setDescricao(descricao);
                hospedagem.setCapacidadeMaxima(capacidadeMaxima);
                hospedagem.setPrecoDiaria(precoDiaria);
                hospedagem.setCheckIn(checkIn);
                hospedagem.setCheckOut(checkOut);
                
                hospedagem.validar();
                
                transacao.iniciar();
                hospedagemRepositorio.atualizarHospedagem(hospedagem);
                transacao.confirmar();
            }
            catch(DominioException e){
                if (transacao.estaAtivo())
                    transacao.reverter();
            
            throw new HospedagemServicoException(e.getMessage());
        }
    }
    
    public void adicionarImagemNaHospedagem(Hospedagem hospedagem, File arquivo) throws HospedagemServicoException{
        try{
            BufferedImage dadosImagem = ImageIO.read(arquivo);
            if (dadosImagem == null)
                throw new HospedagemServicoException("Falha ao ler a imagem, garanta que seja um arquivo valido");
            
            String nome = arquivo.getName();
            int indexPonto = nome.lastIndexOf(".");
            if (indexPonto < 0)
                throw new HospedagemServicoException("Falha ao ler a imagem, garanta que seja um arquivo valido");
            
            String extensao = nome.substring(indexPonto + 1);
            String nomeSalvar = CAMINHO_PASTA_IMAGENS + "hospedagem"+ hospedagem.getId() + "-" + UUID.randomUUID() + "." + extensao;
            File arquivoImagem = new File(nomeSalvar);
            
            Imagem imagem = new Imagem(nomeSalvar);
            
            transacao.iniciar();
            hospedagem.adicionarImagem(imagem);
            hospedagemRepositorio.atualizarHospedagem(hospedagem);
            ImageIO.write(dadosImagem, extensao, arquivoImagem);
            transacao.confirmar();
        }
        catch(DominioException e){
            if (transacao.estaAtivo())
                transacao.reverter();
            
            throw new HospedagemServicoException(e.getMessage());
        }
        catch(IOException e){
            if (transacao.estaAtivo())
                transacao.reverter();
            
            throw new HospedagemServicoException("Falha ao escrever a imagem");
        }
    }
    
    public void deletarImagemNaHospedagem(Hospedagem hospedagem, Imagem imagem) throws HospedagemServicoException{
        File arquivo = new File(imagem.getUrl());
            
        if (!arquivo.exists())
            throw new HospedagemServicoException("Falha ao encontrar o arquivo da imagem");
            
        transacao.iniciar();
        hospedagem.removerImagem(imagem);
        hospedagemRepositorio.atualizarHospedagem(hospedagem);
        arquivo.delete();
        transacao.confirmar();
    }
    
    public void adicionarDisponibilidade(Hospedagem hospedagem, LocalDate data) throws HospedagemServicoException{
        try{
            Disponibilidade disponibilidade = new Disponibilidade(data);
            
            transacao.iniciar();
            hospedagem.adicionarDisponibilidade(disponibilidade);
            hospedagemRepositorio.atualizarHospedagem(hospedagem);
            transacao.confirmar();
        }
        catch(DominioException e){
            if (transacao.estaAtivo())
                transacao.reverter();
            
            throw new HospedagemServicoException(e.getMessage());
        }
    }
    
    public void removerDisponibilidade(Hospedagem hospedagem, Disponibilidade disponibilidade) throws HospedagemServicoException{
        if (disponibilidade.getStatus() == StatusDisponibilidade.RESERVADO)
            throw new HospedagemServicoException("Não é possivel remover a disponibilidade, a data possui uma reserva");
        
        transacao.iniciar();
        hospedagem.removerDisponibilidade(disponibilidade);
        hospedagemRepositorio.atualizarHospedagem(hospedagem);
        transacao.confirmar();
    }
    
    public void verificarDisponibilidades(int hospedagemId, LocalDate dataEntrada, LocalDate dataSaida) throws HospedagemServicoException{
        Hospedagem hospedagem = hospedagemRepositorio.getHospedagem(hospedagemId);
        
        List<Disponibilidade> disponibilidades = hospedagem.getDisponibilidades();
        LocalDate dataChecar = dataEntrada;
        
        DateTimeFormatter formato = Disponibilidade.DATA_FORMATADOR;
                
        while(dataChecar.isBefore(dataSaida)){
            boolean encontrouData = false;
            for (Disponibilidade dsp : disponibilidades){
                if(dsp.equals(dataChecar)){
                    encontrouData = true;
                        
                    if (dsp.getStatus() != StatusDisponibilidade.DISPONIVEL)
                        throw new HospedagemServicoException("A data: " + dataChecar.format(formato) +  " não está disponivel");
                                    
                    break;
                }
            }
                
            if (!encontrouData)
                throw new HospedagemServicoException("A data: " + dataChecar.format(formato) +  " não está disponivel");
                
            dataChecar = dataChecar.plusDays(1);
        }
    }
}
