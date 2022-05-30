    package br.com.organon.telas;    

    import javafx.application.Application;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.stage.Stage;
import javafx.stage.StageStyle;
    
    
    public class MainFX extends Application {
    
        @Override
        public void start(Stage stage) throws Exception {            
            //Criação do palco e cena (Esse parte inicia a tela inicial)
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Login.fxml"));
            
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Organon");
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.show();
        }
    
        public static void main(String[] args) {
            launch(args);
        }
    
    }
