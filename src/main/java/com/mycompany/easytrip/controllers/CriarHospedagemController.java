package com.mycompany.easytrip.controllers;

import com.mycompany.easytrip.controllers.servicos.HospedagemServico;
import com.mycompany.easytrip.controllers.servicos.excecoes.HospedagemServicoException;
import com.mycompany.easytrip.dominio.entidades.Endereco;
import com.mycompany.easytrip.dominio.entidades.Hospedagem;
import com.mycompany.easytrip.dominio.enums.EstadoBrasil;
import com.mycompany.easytrip.dominio.enums.TipoDeHospedagem;
import com.mycompany.easytrip.dominio.excecoes.DominioException;
import com.mycompany.easytrip.repositorio.MinhaConexao;
import com.mycompany.easytrip.telas.hospedagem.TelaCriarHospedagem;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalTime;
import javax.swing.JOptionPane;

public class CriarHospedagemController {
    private final TelaCriarHospedagem tela;
    
    public CriarHospedagemController(TelaCriarHospedagem tela){
        this.tela = tela;
    }
    
    public void criarHospedagem(){
        String titulo = tela.tituloField.getText();
        String descricao = tela.descricaoTextArea.getText();
        TipoDeHospedagem tipo = (TipoDeHospedagem) tela.tipoHospedagemComboBox.getSelectedItem();
        int capacidadeMaxima = Integer.parseInt(tela.capacidadeMaximaField.getText());
        
        Number auxPreco = (Number)tela.precoDiariaField.getValue();
        BigDecimal precoDiaria = new BigDecimal(auxPreco.doubleValue());
        
        LocalTime checkIn = LocalTime.parse(tela.checkInField.getText());
        LocalTime checkOut = LocalTime.parse(tela.checkOutField.getText());
        
        String cep = tela.cepField.getText();
        String numero = tela.numeroField.getText();
        String rua = tela.ruaField.getText();
        String bairro = tela.bairroField.getText();
        String complemento = tela.complementoField.getText();
        String cidade = tela.cidadeField.getText();
        EstadoBrasil estado = (EstadoBrasil) tela.estadoComboBox.getSelectedItem();
        
        try (EntityManager entityManager = MinhaConexao.getEntityManager()){
            Endereco endereco = new Endereco(cep, numero, rua, bairro, complemento, cidade, estado);
            Hospedagem hospedagem = new Hospedagem(tela.getUsuarioId(), titulo, descricao, tipo, capacidadeMaxima, precoDiaria, checkIn, checkOut, endereco);
            
            HospedagemServico servico = new HospedagemServico(entityManager);
            servico.criar(hospedagem);
            
            JOptionPane.showMessageDialog(tela, "Hospedagem cadastrada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            tela.mudarParaTelaHospedagensCadastradas();
        }
        catch(DominioException | HospedagemServicoException e){
            JOptionPane.showMessageDialog(tela, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(tela, "Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
