package br.com.organon.telas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

/**
 * FXML Controller class
 *
 * @author Welyson
 */
public class TelaTarefaController implements Initializable {
    @FXML
    private ChoiceBox<String> comboBoxResponsavel;
    
    //Criar um array de string para carregar os valores do seletor de responsaveis
    private String[] responsavel = {"Welyson","Carlos"};  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Carrega o array de string com os valores dos responsaveis
        comboBoxResponsavel.getItems().addAll(responsavel);
        
        //Attiva o retorno do responsavel
        comboBoxResponsavel.setOnAction(this::getResponsavelInterface);
    }
    
    //Função para criação da tarefa
    @FXML
    void criarTarefa(javafx.event.ActionEvent event) {
        System.out.print("Criando tarefa");
    }

    //Função de retorno da seleção do responsavel pelo usuario
    public void getResponsavelInterface(javafx.event.ActionEvent event){
        //Mostra o returno no console (String)
        System.out.print(comboBoxResponsavel.getValue()); 
    }    
    
}
