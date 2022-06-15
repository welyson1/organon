
package br.com.organon.controller;

import br.com.organon.model.ProjetoDAO;
import br.com.organon.model.Projeto;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 * Controla a tela de criação de projeto
 * @author Welyson
 */
public class TelaProjetoController implements Initializable {
    ProjetoDAO pDAO = new ProjetoDAO();
    @FXML
    private TextField txtNomeProjeto;
    @FXML
    private TextField txtLinguagens;
    @FXML
    private TextField txtLinkRepositorio;
    @FXML
    private TextField txtModProcesso;
    @FXML
    private TextArea txtDescricao;
    @FXML
    private ChoiceBox<String> choiseBoxEscolherProjeto;
    @FXML
    private Button btnEditarProjeto;
    @FXML
    private Button bntExcluirProjeto;
    @FXML
    private Button btnSalvarProjeto;
          
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Carrega o array de string com os valores dos responsaveis
        choiseBoxEscolherProjeto.getItems().addAll(getNomeProjetos());
        
        //Ativa o retorno do responsavel
        choiseBoxEscolherProjeto.setOnAction(this::setProjetolInterface);    
    } 
    
    /**
     * Carrega o nome de todos os projetos do banco de dados.
     * @param event
     */
    public void setProjetolInterface(javafx.event.ActionEvent event){
        Projeto p = buscarNome(choiseBoxEscolherProjeto.getValue());
        txtNomeProjeto.setText(p.getNome());
        txtLinkRepositorio.setText(p.getRepositorio());
        txtLinguagens.setText(p.getLinguagem());
        txtModProcesso.setText(p.getMdlProcess());
        txtDescricao.setText(p.getDescricao());
           
        System.out.print(choiseBoxEscolherProjeto.getValue()); 
    }
    
    /**
     * Ao clicar no botão EDITAR na interface essa função é chamada
     * @param event 
     */
    @FXML
    private void funcaoEditarProjeto(ActionEvent event) {        
        editar();
        
    }
    
    /**
     * Ao clicar no botão EXCLUIR na interface essa função é chamada
     * @param event 
     */
    @FXML
    private void funcaoExcluirProjeto(ActionEvent event) {
        excluir();
        
    }
    
    /**
     * Ao clicar no botão SALVAR na interface essa função é chamada
     * @param event 
     */
    @FXML
    private void funcaoSalvarProjeto(ActionEvent event) {
        System.out.print("Entrou");
        try{
            Projeto p = new Projeto();
            ProjetoDAO pDAO = new ProjetoDAO();
       
            p.setNome(txtNomeProjeto.getText());
            p.setRepositorio(txtLinkRepositorio.getText());
            p.setLinguagem(txtLinguagens.getText());
            p.setMdlProcess(txtModProcesso.getText());
            p.setDescricao(txtDescricao.getText());
            p.setDevs(null);
            pDAO.criar(p);
       

    
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Ocorreu um erro ao criar o projeto!");
            alert.setContentText("Por favor preencha todos os campos corretamente.");
            alert.showAndWait();
        }
    }
    
    /**
     * Busca todas as tarefas pelo nome
     * @param projNome
     * @return null
     */
    public Projeto buscarNome(String projNome){
        ArrayList<Projeto> listP = pDAO.buscarTodos();
        for(Projeto p: listP){
            if(p.getNome().equals(projNome)){
                return p;
            }
        }
        return null;
    } 
    
    /**
     * Edita a tarefa no banco de dados
     */
    public void editar(){ 
        try{
            Projeto p = buscarNome(choiseBoxEscolherProjeto.getValue());
            p.setNome(txtNomeProjeto.getText());
            p.setRepositorio(txtLinkRepositorio.getText());
            p.setLinguagem(txtLinguagens.getText());
            p.setMdlProcess(txtModProcesso.getText());
            p.setDescricao(txtDescricao.getText());
            pDAO.alterar(p);
            
     
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Ocorreu um erro ao editar o projeto!");
            alert.setContentText("Por favor, selecione um projeto para realizar a edição!.");
            alert.showAndWait();   
        }

    }
    
    /**
     * Exclui a tarefa no banco de dados
     */
    public void excluir(){
        try{
            Projeto p = buscarNome(choiseBoxEscolherProjeto.getValue());
            pDAO.deleta(p);
  
       
            limparProjeto();  
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Ocorreu um erro ao excluir o projeto!");
            alert.setContentText("Por favor, selecione um projeto para realizar a exclusão!.");
            alert.showAndWait();   
            
        }

    }    
  

    /**
     * Carrega a lista de tarefas em uma ArrayList
     * @return 
     */
    public ArrayList<String> getNomeProjetos(){
        ArrayList<Projeto> laland = pDAO.buscarTodos();
        ArrayList<String> sLista = new ArrayList();
        for(Projeto p: laland){
            sLista.add(p.getNome());
        }
        return sLista;
    }  
    public void limparProjeto(){
        
        choiseBoxEscolherProjeto.setValue("");
        txtNomeProjeto.setText("");
    
        txtLinguagens.setText("");
    
        txtLinkRepositorio.setText("");
    
        txtModProcesso.setText("");
   
        txtDescricao.setText("");
            
    }    
    public void atualizarProjeto(){
         choiseBoxEscolherProjeto.getItems().clear();
         choiseBoxEscolherProjeto.getItems().addAll(getNomeProjetos());
          
    }
}
