
package br.com.organon.controller;

import br.com.organon.model.Tarefa;
import br.com.organon.model.TarefaDAO;
import br.com.organon.view.MainFX;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ObjetoTarefaController implements Initializable {
    private int id;
    @FXML
    Label lblNome;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void adc(Tarefa tar){
        id = tar.getId();
        lblNome.setText(tar.getNome());
        
    }
    public void rtnTar(MouseEvent e) throws IOException{
        TarefaDAO tarDAO = new TarefaDAO();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/organon/view/TelaBoard.fxml"));
        loader.setController(MainFX.loader.getController());

        TelaBoardController controller = loader.getController();
        Tarefa tar = tarDAO.buscar(id);
        controller.exibirDadosTarefa(tar);
        controller.tarefa.setId(id);
        
    }


}
    
