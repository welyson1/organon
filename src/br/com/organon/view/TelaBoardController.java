package br.com.organon.view;
import br.com.organon.model.EmpregadoDAO;
import br.com.organon.model.Gestor;
import br.com.organon.model.Projeto;
import br.com.organon.model.ProjetoDAO;
import br.com.organon.model.SessaoDAO;
import br.com.organon.model.Tarefa;
import br.com.organon.model.TarefaDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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

    EmpregadoDAO empDAO = new EmpregadoDAO();    
    TarefaDAO tarDAO = new TarefaDAO();
    SessaoDAO sessaoDAO = new SessaoDAO();
    ProjetoDAO pDAO = new ProjetoDAO();
    
    ArrayList<Tarefa> tarList = new ArrayList();
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
    private ComboBox<String> cbResponsavel;
    @FXML
    private ComboBox<String> cbSessao;
    @FXML
    private ComboBox<String> cbImportancia;
    @FXML
    private ComboBox<String> cbProjeto;
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
      tar.setNome(txtNomeTarefa.getText());
      tar.setDescricao(txtareaDescricao.getText());
      //Definindo importancia
      if(cbImportancia.getValue().equals("Baixa")){
      
          tar.setImportancia(1);  
     
      }else if(cbImportancia.getValue().equals("Média")){
          tar.setImportancia(2);
      }else{
           tar.setImportancia(3);
      }
      tar.setDataIni(new Date());
      tar.setDataFim(new Date());
      tar.setResponsavel(buscarNomeResponsavel(cbResponsavel.getValue()));
      tar.setProjeto(buscarNomeProjeto(cbProjeto.getValue()));
      //Definindo Sessao
      if(cbSessao.getValue().equals("Backlog")){
          tar.setSessao(1);
      }else if(cbSessao.getValue().equals("A fazer")){
          tar.setSessao(2);
      }else if(cbSessao.getValue().equals("Fazendo")){
          tar.setSessao(3);
      }else{
          tar.setSessao(4);        
      }
      
      tarDAO.criar_Tarefa(tar);
      //Adicionado tarefa aqui para controle
      tarList.add(tar);
      //Add responsavel ao projeto no banco
      Projeto p = pDAO.buscar(tar.getProjeto());
      ArrayList<String> devList = new ArrayList();
      String dev = new String();
      dev= Integer.toString(tar.getResponsavel());
      devList.add(dev);
      p.setDevs(devList);
      
      

    }
    public void editarTar(){
      Tarefa tar = new Tarefa();
      tar.setNome(txtNomeTarefa.getText());
      tar.setDescricao(txtareaDescricao.getText());
      //Definindo importancia
      if(cbImportancia.getValue().equals("Baixa")){
      
          tar.setImportancia(1);  
     
      }else if(cbImportancia.getValue().equals("Média")){
          tar.setImportancia(2);
      }else{
           tar.setImportancia(3);
      }
      tar.setDataIni(new Date());
      tar.setDataFim(new Date());
      tar.setResponsavel(buscarNomeResponsavel(cbResponsavel.getValue()));
      tar.setProjeto(buscarNomeProjeto(cbProjeto.getValue()));
      //Definindo Sessao
      if(cbSessao.getValue().equals("Backlog")){
          tar.setSessao(1);
      }else if(cbSessao.getValue().equals("A fazer")){
          tar.setSessao(2);
      }else if(cbSessao.getValue().equals("Fazendo")){
          tar.setSessao(3);
      }else{
          tar.setSessao(4);        
      }
      tarDAO.alterar(tar);
    }
    public void excluirTar(){
        Tarefa tar = tarDAO.buscar(0);//;

        tarDAO.deleta(tar);
    }
     /// Sessao 
    public void adcTar(){
        Tarefa tar = tarDAO.buscar(0);
        sessaoDAO.adcTar(Integer.valueOf(cbSessao.getValue()), tar);

    }
    public void bosta (){
        Tarefa tar = new Tarefa();
        sessaoDAO.excTar(Integer.valueOf(cbSessao.getValue()), tar);
    }
    
    public int buscarNomeResponsavel(String nome){
        ArrayList<Gestor> emp = empDAO.buscarTodos();
        for(Gestor gestor : emp){
            if(gestor.getNome().equals(nome)){
                return gestor.getId();
            }
        }
        return 0;
    }
    public int buscarNomeProjeto(String projNome){
        ArrayList<Projeto> listP = pDAO.buscarTodos();
        for(Projeto p: listP){
            if(p.getNome().equals(projNome)){
                return p.getId();
            }
        }
        return 0;
    } 
}

