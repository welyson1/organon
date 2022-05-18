    package br.com.organon.telas;    

    import javafx.application.Application;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.stage.Stage;
    
    
    public class HelloFX extends Application {
    
        @Override
        public void start(Stage stage) throws Exception {            
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("tela.fxml"));
            
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.show();
        }
    
        public static void main(String[] args) {
            launch(args);
        }
    
    }
