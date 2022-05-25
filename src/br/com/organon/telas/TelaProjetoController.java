/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.organon.telas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author 404
 */
public class TelaProjetoController implements Initializable {

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
    private ChoiceBox<?> choiseBoxEscolherProjeto;
    @FXML
    private Button btnEditarProjeto;
    @FXML
    private Button bntExcluirProjeto;
    @FXML
    private Button btnSalvarProjeto;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    //Ao clicar no botão EDITAR na interface essa função é chamada
    @FXML
    private void funcaoEditarProjeto(ActionEvent event) {
    }
    
    //Ao clicar no botão EXCLUIR na interface essa função é chamada
    @FXML
    private void funcaoExcluirProjeto(ActionEvent event) {
    }
    
    //Ao clicar no botão SALVAR na interface essa função é chamada
    @FXML
    private void funcaoSalvarProjeto(ActionEvent event) {
    }
    
}
