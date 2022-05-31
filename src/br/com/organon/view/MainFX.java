    package br.com.organon.view;    

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Organon");
            stage.setScene(scene);
            stage.show();
        }
    
        public static void main(String[] args) {
            launch(args);
        }
    
    }
