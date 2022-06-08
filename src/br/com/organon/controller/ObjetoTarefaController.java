package br.com.organon.controller;

import br.com.organon.model.Tarefa;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

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
    public void rtnTar(MouseEvent e){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("TelaBoard.fxml"));
        TelaBoardController tarBoard = loader.getController();   
        Tarefa tar = new Tarefa();
        tar.setNome(lblNome.getText());
        tarBoard.editarPreencher(tar);
    }
}
    
