package com.mycompany.easytrip.controllers;

import com.mycompany.easytrip.controllers.servicos.HospedagemServico;
import com.mycompany.easytrip.controllers.servicos.excecoes.HospedagemServicoException;
import com.mycompany.easytrip.dominio.entidades.Disponibilidade;
import com.mycompany.easytrip.dominio.entidades.Endereco;
import com.mycompany.easytrip.dominio.entidades.Hospedagem;
import com.mycompany.easytrip.dominio.entidades.Imagem;
import com.mycompany.easytrip.repositorio.MinhaConexao;
import com.mycompany.easytrip.telas.componentes.EscolhedorDeImagem;
import com.mycompany.easytrip.telas.componentes.EscolherDataPanel;
import com.mycompany.easytrip.telas.hospedagem.TelaDeGerenciamentoHospedagem;
import jakarta.persistence.EntityManager;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class GerenciarHospedagemController {
    private final TelaDeGerenciamentoHospedagem tela;
    private Hospedagem hospedagem;
    private Map<Integer, Imagem> indexImagem;

    
    public GerenciarHospedagemController(TelaDeGerenciamentoHospedagem tela){
        this.tela = tela;
    }
    
    public void carregarHospedagem(int hospedagemId){
        try(EntityManager entityManager = MinhaConexao.getEntityManager()){
            HospedagemServico servico = new HospedagemServico(entityManager);
            
            this.hospedagem = servico.getHospedagem(hospedagemId);
            preencherTodosCampos();
        }
        catch(HospedagemServicoException e){
            JOptionPane.showMessageDialog(tela, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(tela, "Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    public void atualizarInformacoes(){
        String titulo = tela.tituloField.getText();
        String descricao = tela.descricaoTextArea.getText();
        int capacidadeMaxima = Integer.parseInt(tela.capacidadeMaximaField1.getText());
        
        Number auxPreco = (Number)tela.precoDiariaField1.getValue();
        BigDecimal precoDiaria = new BigDecimal(auxPreco.doubleValue());
        
        LocalTime checkIn = LocalTime.parse(tela.checkInField1.getText());
        LocalTime checkOut = LocalTime.parse(tela.checkOutField1.getText());
        
        try(EntityManager entityManager = MinhaConexao.getEntityManager()){
            HospedagemServico servico = new HospedagemServico(entityManager);
            
            servico.atualizarInformacoesDaHospedagem(hospedagem, titulo, descricao, capacidadeMaxima, precoDiaria, checkIn, checkOut);
            
            carregarHospedagem(hospedagem.getId());
            
            JOptionPane.showMessageDialog(null, "Informações da hospedagem foram atualizadas", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(HospedagemServicoException e){
            JOptionPane.showMessageDialog(tela, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(tela, "Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    public void adicionarImagem(){
        EscolhedorDeImagem escolherImagem = new EscolhedorDeImagem();
        int escolha = escolherImagem.showOpenDialog(tela);
        if (escolha != JFileChooser.APPROVE_OPTION)
            return;
        
        try(EntityManager entityManager = MinhaConexao.getEntityManager()){
            HospedagemServico servico = new HospedagemServico(entityManager);
            
            File imagemEscolhida = escolherImagem.getSelectedFile();
            servico.adicionarImagemNaHospedagem(hospedagem, imagemEscolhida);
            
            carregarHospedagem(hospedagem.getId());
        }
        catch(HospedagemServicoException e){
            JOptionPane.showMessageDialog(tela, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(tela, "Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    public void removerImagem(){
        Imagem imagem = receberImagemEscolhida();
        int escolha = JOptionPane.showConfirmDialog(tela, "Deseja deletar a imagem?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (escolha != 0)
            return;
        
        try(EntityManager entityManager = MinhaConexao.getEntityManager()){
            HospedagemServico servico = new HospedagemServico(entityManager);
            
            servico.deletarImagemNaHospedagem(hospedagem, imagem);
            
            carregarHospedagem(hospedagem.getId());
        }
        catch(HospedagemServicoException e){
            JOptionPane.showMessageDialog(tela, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(tela, "Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    public void adicionarDisponibilidade(){
        EscolherDataPanel escolherData = new EscolherDataPanel();
        int resposta = JOptionPane.showConfirmDialog(null, escolherData, "teste", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
       
        if (resposta != JOptionPane.OK_OPTION)
            return;
        
        LocalDate dateEscolhida = escolherData.getDataEscolhida();
        
        try(EntityManager entityManager = MinhaConexao.getEntityManager()){
            HospedagemServico servico = new HospedagemServico(entityManager);
            
            servico.adicionarDisponibilidade(hospedagem, dateEscolhida);
            
            carregarHospedagem(hospedagem.getId());
        }
        catch(HospedagemServicoException e){
            JOptionPane.showMessageDialog(tela, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(tela, "Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
        } 
    }    
    
    public void removerDisponibilidade(){
        Disponibilidade disponibilidade = receberDisponibilidadeEscolhida();
        if (disponibilidade == null)
        {
            JOptionPane.showMessageDialog(tela, "É necessário selecionar uma disponibilidade", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try(EntityManager entityManager = MinhaConexao.getEntityManager()){
            HospedagemServico servico = new HospedagemServico(entityManager);
            
            servico.removerDisponibilidade(hospedagem, disponibilidade);
            
            carregarHospedagem(hospedagem.getId());
        }
        catch(HospedagemServicoException e){
            JOptionPane.showMessageDialog(tela, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(tela, "Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    private void preencherTodosCampos(){
        Endereco endereco = hospedagem.getEndereco();
        
        tela.tituloField.setText(hospedagem.getTitulo());
        tela.descricaoTextArea.setText(hospedagem.getDescricao());
        tela.tipoHospedagemComboBox.setSelectedItem(hospedagem.getTipo());
        tela.capacidadeMaximaField1.setText(String.valueOf(hospedagem.getCapacidadeMaxima()));
        BigDecimal precoDiaria = hospedagem.getPrecoDiaria().getValor();
        tela.precoDiariaField1.setValue(precoDiaria);
        tela.checkInField1.setText(hospedagem.getCheckIn().toString());
        tela.checkOutField1.setText(hospedagem.getCheckOut().toString());
        
        tela.cepField.setText(endereco.getCep());
        tela.numeroField.setText(endereco.getNumero());
        tela.ruaField.setText(endereco.getRua());
        tela.bairroField.setText(endereco.getBairro());
        tela.complementoField.setText(endereco.getComplemento());
        tela.cidadeField.setText(endereco.getCidade());
        tela.estadoComboBox.setSelectedItem(endereco.getEstado());
        
        preencherImagensComboBox();
        preencherDisponibilidadeTable();
    }
    
    private void preencherImagensComboBox(){
        List<Imagem> imagens = hospedagem.getImagens();
        
        Integer i = 0;
        indexImagem = new HashMap<>();
        for (Imagem img : imagens)
            indexImagem.put(i++, img);
        
        tela.escolherImagemComboBox.setModel(new DefaultComboBoxModel<>(indexImagem.keySet().toArray(Integer[]::new)));
        if (!imagens.isEmpty()){
            tela.escolherImagemComboBox.setEnabled(true);
            mudarImagemDaTela();
        }
    }
    
    public void preencherDisponibilidadeTable(){
        List<Disponibilidade> disponibilidades = hospedagem.getDisponibilidades();
        int mesEscolhido = tela.getMesSelecionado();
        int anoEscolhido = tela.getAnoSelecionado();
        
        DefaultTableModel model = (DefaultTableModel) tela.disponibilidadesTable.getModel();
        model.setRowCount(0);

        for(Disponibilidade disponibilidade : disponibilidades){
            LocalDate disponibilidadeData = disponibilidade.getData();
            if (mesEscolhido == disponibilidadeData.getMonthValue() && anoEscolhido == disponibilidadeData.getYear())
                model.addRow(new Object[]{disponibilidade, disponibilidade.getStatus().name()});
        }
    }
    
    private Imagem receberImagemEscolhida(){
        int chave = (Integer) tela.escolherImagemComboBox.getSelectedItem();
        return indexImagem.get(chave);
    }  
    
    private Disponibilidade receberDisponibilidadeEscolhida(){
        int linha = tela.disponibilidadesTable.getSelectedRow();
        Disponibilidade disponibilidade = null;

        if(linha != -1)
            disponibilidade = (Disponibilidade)tela.disponibilidadesTable.getValueAt(linha, 0);
        
        return disponibilidade;
    }
    
    public void mudarImagemDaTela(){
        Imagem imagem = receberImagemEscolhida();
        tela.imagemPanel1.setImagem(imagem.getUrl());
        tela.repaint();
    }
}
