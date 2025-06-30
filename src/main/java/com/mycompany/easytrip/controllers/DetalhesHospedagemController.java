package com.mycompany.easytrip.controllers;

import com.mycompany.easytrip.controllers.servicos.HospedagemServico;
import com.mycompany.easytrip.controllers.servicos.excecoes.HospedagemServicoException;
import com.mycompany.easytrip.dominio.entidades.Hospedagem;
import com.mycompany.easytrip.dominio.entidades.Imagem;
import com.mycompany.easytrip.repositorio.MinhaConexao;
import com.mycompany.easytrip.telas.hospedagem.TelaDetalhesHospedagem;
import jakarta.persistence.EntityManager;
import java.util.List;
import javax.swing.JOptionPane;

public class DetalhesHospedagemController {
    private final TelaDetalhesHospedagem tela;
    private Hospedagem hospedagem;
    private int indexImagemAtual = 0;
    
    public DetalhesHospedagemController(TelaDetalhesHospedagem tela){
        this.tela = tela;
    }
    
    public void carregarHospedagem(int hospedagemId){
        try(EntityManager entityManager = MinhaConexao.getEntityManager()){
            HospedagemServico servico = new HospedagemServico(entityManager);
            
            this.hospedagem = servico.getHospedagem(hospedagemId);
            preencherCampos();
        }
        catch(HospedagemServicoException e){
            JOptionPane.showMessageDialog(tela, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(tela, "Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    private void preencherCampos(){
        tela.tituloLabel.setText(hospedagem.getTitulo());
        tela.enderecoLabel.setText(hospedagem.getEndereco().toString());
        tela.descricaoTextArea.setText(hospedagem.getDescricao());
        
        tela.tipoLabel.setText("Tipo: " + hospedagem.getTipo().name());
        tela.capacidadeMaximaLabel.setText("Capacidade Máxima: " + String.valueOf(hospedagem.getCapacidadeMaxima()));
        tela.checkInLabel.setText("CheckIn: " + hospedagem.getCheckIn());
        tela.checkOutLabel.setText("CheckOut: " + hospedagem.getCheckOut());
        tela.precoLabel.setText("Preço: " + hospedagem.getPrecoDiaria().toString());
        
        setImagem(hospedagem.getPrimeiraImagem().getUrl());
    }
    
    private void setImagem(String url){
        tela.imagemPanel1.setImagem(url);
        tela.atualizarTela();
    }
    
    public void proximaImagem(){
        List<Imagem> imagens = hospedagem.getImagens();
        int tamanhoDaLista = imagens.size();
        
        indexImagemAtual++;
        int index = indexImagemAtual % tamanhoDaLista;
        setImagem(imagens.get(index).getUrl());
    }
    
    public void anteriorImagem(){
        List<Imagem> imagens = hospedagem.getImagens();
        int tamanhoDaLista = imagens.size();
        
        indexImagemAtual--;
        if (indexImagemAtual <= 0)
            indexImagemAtual = tamanhoDaLista;
        
        int index = indexImagemAtual % tamanhoDaLista;
        setImagem(imagens.get(index).getUrl());
    }
}
