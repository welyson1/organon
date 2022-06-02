package br.com.organon.view;
import br.com.organon.model.Tarefa;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaBoardController implements Initializable  {    
            
    @FXML
    private Button criarProjeto;

    @FXML
    private Button criarTarefa;

    @FXML
    private VBox painelTarefa;
    @FXML
    private SplitPane splitPane;
    @FXML
    private TextField txtNomeTarefa;
    @FXML
    private TextField txtNomeResponsavel;
    @FXML
    private ComboBox<?> cbSessao;
    @FXML
    private ComboBox<?> cbImportancia;
    @FXML
    private ComboBox<?> cbProjeto;
    @FXML
    private DatePicker dtDataIni;
    @FXML
    private DatePicker dtDataFim;
    @FXML
    private TextArea txtareaDescricao;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregaTarefas();
    } 
    
    //Função que é chamada ao clicar no botão CRIAR PROJETO na interface
    @FXML
    void abrirCriadorProjeto(ActionEvent event) throws IOException {
        //Cria e chama a interface de criação de projeto
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TelaProjeto.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();

        Stage stage = new Stage();
        stage.setTitle("Criador de Projeto");
        stage.setScene(new Scene(root1));  
        stage.show();  
    }
    
    //Chama a tela de criar tarefa
    @FXML
    void abrirCriadorTarefa(ActionEvent event) throws IOException {        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TelaTarefa.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        
        Stage stage = new Stage();
        stage.setTitle("Criador de tarefas");
        stage.setScene(new Scene(root1));  
        stage.show();
    }
    
    /**
     * Esse metodo carrega tarefas vazias no vBox  
     */
    private void carregaTarefas() {
       Node [] nodes = new Node[20];
        for (int i = 0; i < nodes.length; i++) {
            try {
                System.out.println("foi"+i);
                nodes[i] = FXMLLoader.load(getClass().getResource("ObjetoTarefa.fxml"));
                painelTarefa.getChildren().add(nodes[i]);
            }catch(Exception e){
            }
        } 
    }  
      public void criarTar(){
      
      Tarefa tar = new Tarefa();
      tar.setNome("");
      tar.setDescricao("");
      if(CheckBox.value()=="pequena"){
      
          tar.setImportancia(1);  
          
      }else if(CheckBox.value()=="media"){
          tar.setImportancia(2);
      }else{
           tar.setImportancia(3);
      }
      tar.setDataIni(null);
      tar.setDataFim(null);
      tar.setResponsavel(Integer.valueOf(s));
      tar.setProjeto(Integer.valueOf(s));
      tar.setSessao(Integer.valueOf(s));
      
      
      tarDAO.criar_Tarefa(tar);
      //Add responsavel ao projeto no banco
      Projeto p = buscarNome();
      ArrayList<String> devList = new ArrayList();
      String dev = new String();
      dev= Integer.toString(tar.getResponsavel());
      devList.add(dev);
      p.setDevs(devList);
      
      
      

    }
    public void editarTar(){
        Tarefa tar = tarDAO.buscar(id);
        tar.setNome("");
        if(CheckBox.value()=="pequena"){

            tar.setImportancia(1);  

        }else if(CheckBox.value()=="media"){
            tar.setImportancia(2);
        }else{
             tar.setImportancia(3);
        }
        tar.setDescricao("");
        tar.setDataIni(null);
        tar.setDataFim(null);
        tar.setResponsavel(Integer.valueOf(s));
        tar.setProjeto(Integer.valueOf(s));
        tar.setSessao(Integer.valueOf(s));
        tarDAO.alterar(tar);
    }
    public void excluirTar(){
        Tarefa tar = //;

        tarDAO.deleta(tar);
    }
     /// Sessao 
    SessaoDAO sessaoDAO = new SessaoDAO();
    public void adcTar(){
        Tarefa tar = new Tarefa();
        sessaoDAO.adcTar(1, tar);

    }
    public void rmvTar(){
        Tarefa tar = new Tarefa();

    }
}

