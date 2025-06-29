package com.mycompany.easytrip.controllers;

import com.mycompany.easytrip.controllers.servicos.GrupoServico;
import com.mycompany.easytrip.controllers.servicos.excecoes.GrupoServicoException;
import com.mycompany.easytrip.dominio.entidades.Grupo;
import com.mycompany.easytrip.dominio.entidades.HospedeGrupo;
import com.mycompany.easytrip.dominio.entidades.Usuario;
import com.mycompany.easytrip.dominio.enums.FuncaoGrupo;
import com.mycompany.easytrip.dominio.excecoes.GrupoException;
import com.mycompany.easytrip.repositorio.MinhaConexao;
import com.mycompany.easytrip.telas.grupos.TelaGerenciarGrupo;
import jakarta.persistence.EntityManager;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class GerenciarGrupoController {
    private final TelaGerenciarGrupo tela;
    private Grupo grupo;
    
    public GerenciarGrupoController(TelaGerenciarGrupo tela){
        this.tela = tela;
    }
    
    public void carregarGrupo(int grupoId){
        try(EntityManager entityManager = MinhaConexao.getEntityManager()){
            GrupoServico servico = new GrupoServico(entityManager);
            
            grupo = servico.getGrupo(grupoId);
            
            preencherCampos();
        }
        catch(GrupoServicoException e){
            JOptionPane.showMessageDialog(tela, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(tela, "Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    private void preencherCampos() throws GrupoException{
        tela.nomeGrupoField.setText(grupo.getNome());
        tela.totalPessoasField.setText(String.valueOf(grupo.getQuantidadeDeParticipantes()));
        
        preencherTabela(grupo.getListaHospedesDoGrupo());
    }
    
    private void preencherTabela(List<HospedeGrupo> hospedesGrupos) throws GrupoException{
        DefaultTableModel model = (DefaultTableModel) tela.membroTable.getModel();
        model.setRowCount(0);
        TableColumnModel columnModel = tela.membroTable.getColumnModel();
        columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setMaxWidth(0);
        columnModel.getColumn(0).setWidth(0);

        for(HospedeGrupo vinculo : hospedesGrupos){
            Usuario usuario = vinculo.getUsuario();
            if (usuario.getId() == tela.getUsuarioLogadoId()){
                if (grupo.OUsuarioEUmMembro(usuario)){
                    tela.alterarFuncaoButton.setEnabled(false);
                    tela.novoMembroButton.setEnabled(false);
                    tela.removerUsuarioButton.setEnabled(false);
                }
            }
            model.addRow(new Object[]{usuario.getId(), usuario.getNome(), vinculo.getFuncao().name(), vinculo.getQuantidadeDeAcompanhantes()});
        }
    }
    
    public void removerUsuario(int grupoId){
        int usuarioSelecionadoId = tela.receberParticipanteSelecionado();
        if (usuarioSelecionadoId == 0)
        {
            JOptionPane.showMessageDialog(tela, "É necessário selecionar um usuario do grupo", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try(EntityManager entityManager = MinhaConexao.getEntityManager()){
            GrupoServico servico = new GrupoServico(entityManager);
            
            servico.removerUsuarioDoGrupo(grupoId, tela.getUsuarioLogadoId(), usuarioSelecionadoId);
            
            JOptionPane.showMessageDialog(tela, "Usuario removido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
            carregarGrupo(grupoId);
        }
        catch(GrupoServicoException e){
            JOptionPane.showMessageDialog(tela, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(tela, "Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    public void alterarFuncao(int grupoId){
        int usuarioSelecionadoId = tela.receberParticipanteSelecionado();
        if (usuarioSelecionadoId == 0)
        {
            JOptionPane.showMessageDialog(tela, "É necessário selecionar um usuario do grupo", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        FuncaoGrupo funcaoEscolhida = null;
        FuncaoGrupo[] opcoes = FuncaoGrupo.values();
        JComboBox<FuncaoGrupo> escolherFuncao = new JComboBox<>(opcoes);
        int funcaoEscolha = JOptionPane.showConfirmDialog(tela, escolherFuncao, "Funções", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (funcaoEscolha != JOptionPane.OK_OPTION)
            return;
        else
            funcaoEscolhida = (FuncaoGrupo) escolherFuncao.getSelectedItem();
        
        try(EntityManager entityManager = MinhaConexao.getEntityManager()){
            GrupoServico servico = new GrupoServico(entityManager);
            
            servico.alterarFuncaoDoUsuario(grupoId, tela.getUsuarioLogadoId(), usuarioSelecionadoId, funcaoEscolhida);
            
            carregarGrupo(grupoId);
        }
        catch(GrupoServicoException e){
            JOptionPane.showMessageDialog(tela, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(tela, "Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    public void adicionarNovoMembro(int grupoId){
        String emailSelecionado = JOptionPane.showInputDialog(tela, "Email: ", "Convidar usuário", JOptionPane.PLAIN_MESSAGE);
        if (emailSelecionado == null)
            return;
        
        if (emailSelecionado.isBlank()){
            JOptionPane.showMessageDialog(tela, "É necessário inserir um email", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try(EntityManager entityManager = MinhaConexao.getEntityManager()){
            GrupoServico servico = new GrupoServico(entityManager);
            
            servico.adicionarUsuario(grupoId, emailSelecionado);
            
            JOptionPane.showMessageDialog(tela, "Usuario convidado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
            carregarGrupo(grupoId);
        }
        catch(GrupoServicoException e){
            JOptionPane.showMessageDialog(tela, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(tela, "Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    public void alterarQuantidadeDeAcompanhantes(int grupoId){
        String qAcompanhantesString = JOptionPane.showInputDialog(tela, "Quantidade acompanhantes: ", "Inserir", JOptionPane.PLAIN_MESSAGE);
        if (qAcompanhantesString == null)
            return;
        
        if (qAcompanhantesString.isBlank())
            return;
        
        for (char caracter : qAcompanhantesString.toCharArray())
            if (!Character.isDigit(caracter)){
                JOptionPane.showMessageDialog(tela, "Insira apenas números", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
        
        try(EntityManager entityManager = MinhaConexao.getEntityManager()){
            GrupoServico servico = new GrupoServico(entityManager);
            
            int qAcompanhantes = Integer.parseInt(qAcompanhantesString);
            
            servico.alterarQuantidadeDeAcompanhantesUsuario(grupoId, tela.getUsuarioLogadoId(), qAcompanhantes);
            
            carregarGrupo(grupoId);
        }
        catch(GrupoServicoException e){
            JOptionPane.showMessageDialog(tela, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(tela, "Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
        } 
    }
}
