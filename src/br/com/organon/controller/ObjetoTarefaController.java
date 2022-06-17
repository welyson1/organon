
package br.com.organon.controller;

import br.com.organon.model.EmpregadoDAO;
import br.com.organon.model.ProjetoDAO;
import br.com.organon.model.Tarefa;
import br.com.organon.model.TarefaDAO;
import br.com.organon.view.MainFX;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class ObjetoTarefaController implements Initializable {
    private int id;
    EmpregadoDAO empDAO = new EmpregadoDAO();
    ProjetoDAO pDAO = new ProjetoDAO();
    @FXML
    Label lblNome;
    @FXML
    private BorderPane painelObjeto;
    @FXML
    private Label lblNomeResponsavel;
    @FXML
    private Label lblDataTermino;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void adc(Tarefa tar){
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        id = tar.getId();
        lblNome.setText(tar.getNome());
        lblNomeResponsavel.setText(empDAO.buscar(tar.getResponsavel()).getNome());
        lblDataTermino.setText(tar.getDataFim().format(formatador));        
    }
    @FXML
    public void rtnTar(MouseEvent e) throws IOException{
        TarefaDAO tarDAO = new TarefaDAO();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/organon/view/TelaBoard.fxml"));
        loader.setController(LoginController.loader.getController());
        TelaBoardController controller = loader.getController();
        
        Tarefa tar = tarDAO.buscar(id);
        //Exibi dados da tarefa ao clicar nela
        controller.exibirDadosTarefa(tar);
        controller.tarefa.setId(id);
        //Exibi dado do projeto ao clicar na tarefa
        controller.exibirDadosProjeto(pDAO.buscar(tar.getProjeto()));
    }


}
    
