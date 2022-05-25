    package br.com.organon.telas;    

    import javafx.application.Application;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.stage.Stage;
    
    
    public class MainFX extends Application {
    
        @Override
        public void start(Stage stage) throws Exception {            
            //Criação do palco e cena (Esse parte inicia a tela inicial)
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("TelaBoard.fxml"));
            
            stage.setTitle("Organon (Gerencie suas tarefas)");
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.show();
        }
    
        public static void main(String[] args) {
            launch(args);
        }
    
    }
