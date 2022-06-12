package br.com.organon.controller;

import br.com.organon.model.Desenvolvedor;
import br.com.organon.model.EmpregadoDAO;
import br.com.organon.model.Gestor;
import br.com.organon.model.Mensagem;
import br.com.organon.model.MensagemDAO;
import br.com.organon.model.Projeto;
import br.com.organon.model.ProjetoDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TelaMensagemController implements Initializable {
    
    @FXML
    private TextField txtAssunto;
    
    @FXML
    private TextArea txtConteudo;
    
    @FXML
    private Button btnEnviar;
    
    @FXML
    private ComboBox<String> cbDest;
    
    EmpregadoDAO empDAO = new EmpregadoDAO();    
    ProjetoDAO pDAO = new ProjetoDAO();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cbDest.getItems().addAll(getNomeProjeto());
        // TODO
    }   
    
    public void btnEnviar(ActionEvent e) throws IOException{
        enviar();
        txtAssunto.setText("");
        txtConteudo.setText("");
        cbDest.setValue("");       
    }
    
    //Cria mensagem com dados e envia 
    public void enviar(){
        try{
            int confirmacao;
            Mensagem mensagem = new Mensagem();
            Gestor ges = new Gestor();
            //Adicionando email e senha do gestor
            ges.setEmail(LoginController.email);
            ges.setSenha(LoginController.senha);

            //Montando mensagem
            mensagem.setTitulo(txtAssunto.getText());   
            mensagem.setConteudo(txtConteudo.getText());       
            //Verificando se algum projeto foi selecionado
            if(getEmails()!=null){
                mensagem.setDestEmail(getEmails());
                //Verificando erro no envio
                if(MensagemDAO.enviar(ges,mensagem) == 1){
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setHeaderText("Sua mensagem foi enviada!");
                    alert.setContentText("Todos os desenvolvedores presentes no projeto receberam sua mensagem.");
                    alert.showAndWait(); 
                }else{
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setHeaderText("Ocorreu um erro ao enviar a mensagem!");
                    alert.setContentText("Sua mensagem não foi enviada.");
                    alert.showAndWait();
                }     
            }
         
        }catch(Exception e){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Ocorreu um erro ao enviar a mensagem!");
            alert.setContentText("Por favor preencha todos os campos corretamente.");
            alert.showAndWait();
        }
        
    }
    //ArrayList para alimentar o combobox com nome de projetos
    public ArrayList<String> getNomeProjeto(){
        ArrayList<Projeto> projetos = pDAO.buscarTodos();
        ArrayList<String> sLista = new ArrayList();
        try{
            
        }catch(Exception e){
            e.printStackTrace();
        }
        for(Projeto p: projetos){
            sLista.add(p.getNome());
        }
        return sLista;
    }
    //Busca id do projeto selecionado no combobox
    public int buscarNomeProjeto(String projNome){
        ArrayList<Projeto> listP = pDAO.buscarTodos();
        try{
            for(Projeto p: listP){
                if(p.getNome().equals(projNome)){
                    return p.getId();
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }        
        return 0;
    }
    //Busca o email de todos os desenvolvedores de um Projeto    
    public ArrayList<String> getEmails(){
        Projeto p = pDAO.buscar(buscarNomeProjeto(cbDest.getValue()));
        ArrayList<Desenvolvedor> responsaveis = empDAO.buscarTodos();

        ArrayList<String> sLista = new ArrayList();
        try{
            for(Desenvolvedor dev: responsaveis){
                for(String s : p.getDevs()){
                    if(!(s.equals(""))){
                        if(dev.getId() == Integer.valueOf(s)){
                            sLista.add(dev.getEmail());
                            break;
                        }
                    }
                }
            }    
            
            return sLista;
        }catch(Exception e){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Ocorreu um erro ao enviar a mensagem!");
            alert.setContentText("Escolha o projeto em que os desenvolvedores se encontram.");
            alert.showAndWait();       
        }
        return null;
    } 
}
