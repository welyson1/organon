package br.com.organon.controller;

import br.com.organon.model.Tarefa;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ObjetoTarefaController implements Initializable {

    @FXML
    Label lblNome;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void adc(Tarefa tar){
        lblNome.setText(tar.getNome());
        
    }
}
