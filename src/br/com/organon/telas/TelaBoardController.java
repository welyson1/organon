package br.com.organon.telas;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaBoardController implements Initializable  {    
            
    @FXML
    private Button criarProjeto;

    @FXML
    private Button criarTarefa;
    
    @FXML
    private VBox vObjetoTarefaBacklog;

    @FXML
    private VBox vObjetoTarefaFazendo;

    @FXML
    private VBox vObjetoTarefaFazer;

    @FXML
    private VBox vObjetoTarefaFeito;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Populando a sessão BACKLOG com os objetos de tarefa
        Node [] nodes = new Node[10];
        for (int i = 0; i < 10; i++) {
            try {
                nodes[i] = FXMLLoader.load(getClass().getResource("ObjetoTarefa.fxml"));
                vObjetoTarefaBacklog.getChildren().add(nodes[i]);
            }catch(Exception e){
            }
        }        
    } 
    
    //Função que é chamada ao clicar no botão CRIAR PROJETO na interface
    @FXML
    void abrirCriadorProjeto(ActionEvent event) throws IOException {
        //Cria e chama a interface de criação de projeto
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TelaProjeto.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();

        Stage stage = new Stage();
        stage.setTitle("Criador de Projeto");
        stage.setScene(new Scene(root1));  
        stage.show();  
    }
    
    //Chama a tela de criar tarefa
    @FXML
    void abrirCriadorTarefa(ActionEvent event) throws IOException {        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TelaTarefa.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        
        Stage stage = new Stage();
        stage.setTitle("Criador de tarefas");
        stage.setScene(new Scene(root1));  
        stage.show();
    }

}

