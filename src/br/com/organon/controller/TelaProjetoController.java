package br.com.organon.controller;

import br.com.organon.model.ProjetoDAO;
import br.com.organon.model.Projeto;
import br.com.organon.model.Tarefa;
import br.com.organon.view.MainFX;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 * Controla a tela de criaÃ§Ã£o de projeto
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
        exibirProjeto();
    }
    


    /**
     * Ao clicar no botÃ£o SALVAR na interface essa funÃ§Ã£o Ã© chamada
     * @param event 
     */
    @FXML
    private void funcaoSalvarProjeto(ActionEvent event) {
        try{
            Projeto p = new Projeto();
            
            p.setNome(txtNomeProjeto.getText());
            p.setRepositorio(txtLinkRepositorio.getText());
            p.setLinguagem(txtLinguagens.getText());
            p.setMdlProcess(txtModProcesso.getText());
            p.setDescricao(txtDescricao.getText());
            p.setDevs(null);
            //Definir ao menos o nome
            if(p.getNome().equals("")){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText("Ocorreu um erro ao criar  o projeto!");
                alert.setContentText("Por favor, informe o nome do projeto.");
                alert.showAndWait();
                
            }else{
                pDAO.criar(p);
         
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText("Projeto criado com sucesso!");
                alert.setTitle("Criar Projeto");
                alert.showAndWait();
                limparProjeto();
                atualizar();
                
            }

            
        }catch(Exception e){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Ocorreu um erro ao criar  o projeto!");
            alert.setContentText("Por favor, preencha as informaÃ§Ãµes corretamente.");
            alert.showAndWait();   
        }
    }
    /**
     * Ao clicar no botÃ£o EDITAR na interface essa funÃ§Ã£o Ã© chamada
     * @param event 
     */
    @FXML
    private void funcaoEditarProjeto(ActionEvent event) {      
        try{
           Projeto p = buscarNome(choiseBoxEscolherProjeto.getValue());
           p.setNome(txtNomeProjeto.getText());
           p.setRepositorio(txtLinkRepositorio.getText());
           p.setLinguagem(txtLinguagens.getText());
           p.setMdlProcess(txtModProcesso.getText());
           p.setDescricao(txtDescricao.getText());
           pDAO.alterar(p);
           Alert alert = new Alert(AlertType.INFORMATION);
           alert.setHeaderText("Projeto editado com sucesso!");
           alert.setTitle("Editar Projeto");
           alert.showAndWait();
           
           limparProjeto();
           atualizar();

        }catch(Exception e){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Ocorreu um erro ao editar o projeto!");
            alert.setContentText("Por favor, selecione um projeto para realizar a ediÃ§Ã£o!");
            alert.showAndWait();   
            
        }
        
    }    
    
    /**
     * Ao clicar no botÃ£o EXCLUIR na interface essa funÃ§Ã£o Ã© chamada
     * @param event 
     */
    @FXML
    private void funcaoExcluirProjeto(ActionEvent event) {
        try{
            Projeto p = new Projeto();
            p.setId(buscarNome(choiseBoxEscolherProjeto.getValue()).getId());
            pDAO.deleta(p);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("Projeto excluido com sucesso!");
            alert.setTitle("Excluir Projeto");
            alert.showAndWait(); 
            limparProjeto();
            atualizar();
           
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Ocorreu um erro ao excluir o projeto!");
            alert.setContentText("Por favor, selecione um projeto para realizar a exclusÃ£o!.");
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
    public void exibirProjeto(){
        Projeto p = buscarNome(choiseBoxEscolherProjeto.getValue());
        if(p!=null){
            txtNomeProjeto.setText(p.getNome());
            txtLinkRepositorio.setText(p.getRepositorio());
            txtLinguagens.setText(p.getLinguagem());
            txtModProcesso.setText(p.getMdlProcess());
            txtDescricao.setText(p.getDescricao());   
        }
        
        
    }
    public void limparProjeto(){
        
        txtNomeProjeto.setText("");
        txtLinkRepositorio.setText("");
        txtLinguagens.setText("");
        txtModProcesso.setText("");
        txtDescricao.setText("");
        
    }
    public void atualizar(){
        choiseBoxEscolherProjeto.getItems().clear();
        choiseBoxEscolherProjeto.getItems().addAll(getNomeProjetos());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/organon/view/TelaBoard.fxml"));
        loader.setController(MainFX.loader.getController());
        TelaBoardController controller = loader.getController(); 
        //Exibi dados da tarefa ao clicar nela
        controller.atualizarProjeto();
        

    }
    
   
    
}
