    package br.com.organon.view;    

    import javafx.application.Application;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.stage.Stage;   
    
    public class MainFX extends Application {
        public static FXMLLoader loader;
    
        @Override
        public void start(Stage stage) throws Exception {            
            //Criação do palco e cena (Esse parte inicia a tela inicial)
          // FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaBoard.fxml"));
            loader = new FXMLLoader(getClass().getResource("/br/com/organon/view/TelaBoard.fxml"));
            
            Parent root = loader.load(); 
            Scene scene = new Scene(root);
            stage.setTitle("Organon");
            stage.setScene(scene);
            stage.show();
        }
    
        public static void main(String[] args) {
            launch(args);
        }    
    }
