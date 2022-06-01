/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.organon.controller;

import br.com.organon.model.ProjetoDAO;
import br.com.organon.model.Projeto;
import java.net.URL;
import java.util.ArrayList;
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
    
    //Criar um array de string para carregar os valores do seletor de projetos    
    private String[] projetos = {"Projeto a","Projeto b"}; 
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Carrega o array de string com os valores dos responsaveis
        choiseBoxEscolherProjeto.getItems().addAll(getNomeProjetos());
        
        //Ativa o retorno do responsavel
        choiseBoxEscolherProjeto.setOnAction(this::getProjetolInterface);
        
        
    } 
    
    //Função de retorno da seleção do responsavel pelo usuario
    public void getProjetolInterface(javafx.event.ActionEvent event){
        //Mostra o returno no console (String)
        Projeto p = buscarNome(choiseBoxEscolherProjeto.getValue());
        txtNomeProjeto.setText(p.getNome());
        txtLinkRepositorio.setText(p.getRepositorio());
        txtLinguagens.setText(p.getLinguagem());
        txtModProcesso.setText(p.getMdlProcess());
        txtDescricao.setText(p.getDescricao());
           
        System.out.print(choiseBoxEscolherProjeto.getValue()); 
    }
    
    //Ao clicar no botão EDITAR na interface essa função é chamada
    @FXML
    private void funcaoEditarProjeto(ActionEvent event) {
        
        editar();
    }
    
    //Ao clicar no botão EXCLUIR na interface essa função é chamada
    @FXML
    private void funcaoExcluirProjeto(ActionEvent event) {
        excluir();
    }
    
    //Ao clicar no botão SALVAR na interface essa função é chamada
    @FXML
    private void funcaoSalvarProjeto(ActionEvent event) {
        System.out.print("Entrou");
        try{
            Projeto p = new Projeto();

            ProjetoDAO pDAO = new ProjetoDAO();
            
            ArrayList<String> array = new ArrayList();

            p.setNome(txtNomeProjeto.getText());
            p.setRepositorio(txtLinkRepositorio.getText());
            p.setLinguagem(txtLinguagens.getText());
            p.setMdlProcess(txtModProcesso.getText());
            p.setDescricao(txtDescricao.getText());
            p.setDevs(array);

            pDAO.criar(p);
        }catch(Exception e){
            System.out.print("Foi não");
        }
    }
    
  public Projeto buscarNome(String projNome){
      ArrayList<Projeto> listP = pDAO.buscarTodos();
      for(Projeto p: listP){
        if(p.getNome().equals(projNome)){
            return p;
        }
              
      }
      return null;
    }    
  public void editar(){  
        Projeto p = buscarNome(choiseBoxEscolherProjeto.getValue());
        p.setNome(txtNomeProjeto.getText());
        p.setRepositorio(txtLinkRepositorio.getText());
        p.setLinguagem(txtLinguagens.getText());
        p.setMdlProcess(txtModProcesso.getText());
        p.setDescricao(txtDescricao.getText());
        pDAO.alterar(p);
    }
  public void excluir(){
      Projeto p = buscarNome(choiseBoxEscolherProjeto.getValue());
      pDAO.deleta(p);
    }    
  //Metood apos criar tarefa 
/*  public void criarTar(){
      
      Tarefa tar = new Tarefa();
      Projeto p = buscarNome(choiseBoxEscolherProjeto.getValue());
      ArrayList<String> devList = new ArrayList();
      String dev = new String();
      
      //tar.get
      //Add responsavel ao projeto no banco
      dev= Integer.toString(tar.getResponsavel());
      devList.add(dev);
      p.setDevs(devList);
      
    }
*/  
    public ArrayList<String> getNomeProjetos(){
        ArrayList<Projeto> laland = pDAO.buscarTodos();
        ArrayList<String> sLista = new ArrayList();
        for(Projeto p: laland){
            sLista.add(p.getNome());
        }
        return sLista;
    }
    
    
}