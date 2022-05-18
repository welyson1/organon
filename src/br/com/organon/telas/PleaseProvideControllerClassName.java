package br.com.organon.telas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class PleaseProvideControllerClassName implements Initializable  {
    
    @FXML
    private javafx.scene.control.TextField descricao;

    @FXML
    private javafx.scene.control.TextField nome;
    
    @FXML
    void criarTarefa(javafx.event.ActionEvent event) {
        System.out.print("Oi");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }      

}

