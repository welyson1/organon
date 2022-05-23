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
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TelaBoardController implements Initializable  {    
            
    @FXML
    private Button criarProjeto;

    @FXML
    private Button criarTarefa;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
    } 
    
    //Chama a tela de criar projeto
    @FXML
    void abrirCriadorProjeto(ActionEvent event) {
        
    }
    
    //Chama a tela de criar tarefa
    @FXML
    void abrirCriadorTarefa(ActionEvent event) throws IOException {        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TelaCriacaoTarefa.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        
        Stage stage = new Stage();
        stage.setTitle("Criador de tarefas");
        stage.setScene(new Scene(root1));  
        stage.show();
    }

}

