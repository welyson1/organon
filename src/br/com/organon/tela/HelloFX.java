    package br.com.organon.tela;
    
    import java.io.IOException;
    import javafx.application.Application;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.stage.Stage;
    
    
    public class HelloFX extends Application {
    
        @Override
        public void start(Stage stage) throws IOException {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("tela.fxml"));
            
            Scene scene = new Scene (root);
            stage.setScene(scene);
            stage.show();
        }
    
        public static void main(String[] args) {
            launch();
        }
    
    }
