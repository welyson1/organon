package br.com.organon.view;

import br.com.organon.view.*;

import br.com.organon.model.EmpregadoDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class LoginController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML 
    private Button btnSair;
    
    @FXML
    private Label txtLoginMessage;
    
    @FXML
    private TextField txtUser;
    
    @FXML 
    private PasswordField txtSenha;
    
    
    public void btnLoginOnAction(ActionEvent e) throws IOException{
           
        if(txtUser.getText().isBlank() == false && txtSenha.getText().isBlank() == false){
            
            if(validateLogin()==1){
                root = FXMLLoader.load(getClass().getResource("/br/com/organon/view/TelaBoard.fxml"));
                stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();  
            }
        }else{
            
            txtLoginMessage.setText("Por favor insira o email e a senha.");
        }
        
    }
    
    public void btnSairOnAction(ActionEvent e){
        stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }
    
    public int validateLogin(){
        try{
            EmpregadoDAO empDAO = new EmpregadoDAO();
            String email =  txtUser.getText();
            String senha = txtSenha.getText();
            int validacao = empDAO.Login(email, senha);
            
            if(validacao == 2){
                txtLoginMessage.setText("Credenciais inválidas, por favor tente novamente!");
                return 0;
                
            }else{
                txtLoginMessage.setText("Bem-Vindo!");
                return 1;
                
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
