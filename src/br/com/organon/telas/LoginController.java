package br.com.organon.telas;

import br.com.organon.classes.Conexao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;


public class LoginController implements Initializable {
    
    @FXML 
    private Button btnSair;
    
    @FXML
    private Label txtLoginMessage;
    
    @FXML
    private TextField txtUser;
    
    @FXML 
    private PasswordField txtSenha;
    
    public void btnLoginOnAction(ActionEvent e){
           
        if(txtUser.getText().isBlank() == false && txtSenha.getText().isBlank() == false){
            
            validateLogin();
            
        }else{
            
            txtLoginMessage.setText("Por favor insira o email e a senha.");
        }
        
    }
    
    public void btnSairOnAction(ActionEvent e){
        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }
    
    public void validateLogin(){
        Conexao connectNow = new Conexao();
        Connection connectDB = connectNow.conexao();
        
        String verifyLogin = "SELECT count(1) FROM Empregado WHERE email = '" + txtUser.getText() + "' AND senha = '" + txtSenha.getText() + "'";
        
        try{
            
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            
            while(queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    txtLoginMessage.setText("Bem-Vindo!");
                }else{
                    txtLoginMessage.setText("Credenciais inválidas, por favor tente novamente!");
                }
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
