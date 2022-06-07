package br.com.organon.controller;
import br.com.organon.model.Desenvolvedor;
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
import java.util.ResourceBundle;
import java.sql.Date;
import java.time.LocalDate;
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
        cbResponsavel.getItems().addAll(getNomeResponsavel());
        cbSessao.getItems().addAll(getNomeSessao());
        cbProjeto.getItems().addAll(getNomeProjeto());
        cbImportancia.getItems().addAll(getNomeImportancia());
        carregaTarefas();
    } 
    //Ao clicar no botão salvar a tarefa é criada no banco
    @FXML
    public void criar(ActionEvent e){
        criarTar();
    }
    @FXML
    public void editar(ActionEvent e){
        Tarefa tar = tarDAO.buscar(1);
        txtNomeTarefa.setText(tar.getNome());
        cbResponsavel.setValue(empDAO.buscar(tar.getResponsavel()).getNome());
        //Setando sessao combobox
        if(tar.getSessao()== 1){
            cbSessao.setValue("Fazer");
        }else if(tar.getSessao() == 2){
            cbSessao.setValue("Fazendo");
        }else if(tar.getSessao()== 3){
            cbSessao.setValue("Feta");
        }else{
            cbSessao.setValue("Concluida");       
        }
        //Setando combobox importancia
        if(tar.getImportancia()==1){
            cbImportancia.setValue("Baixa");
      
        }else if(tar.getImportancia()==2){
            
            cbImportancia.setValue("Media");
        }else{
            cbImportancia.setValue("Alta");
        }
        cbProjeto.setValue(pDAO.buscar(tar.getProjeto()).getNome());
        dtDataIni.setValue(tar.getDataIni());
        dtDataFim.setValue(tar.getDataFim());
        txtareaDescricao.setText(tar.getDescricao());
        
    }
    public void excluir(ActionEvent e){
        excluirTar();
        
    }
    //Função que é chamada ao clicar no botão CRIAR PROJETO na interface
    @FXML
    void abrirCriadorProjeto(ActionEvent event) throws IOException {
        //Cria e chama a interface de criação de projeto
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/organon/view/TelaProjeto.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();

        Stage stage = new Stage();
        stage.setTitle("Criador de Projeto");
        stage.setScene(new Scene(root1));  
        stage.show();  
    }
    
    //Chama a tela de criar tarefa
    @FXML
    void abrirCriadorTarefa(ActionEvent event) throws IOException {        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/organon/view/TelaTarefa.fxml"));
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
       //Array de tarefas que irão em cada ObjetoTarefa
       ArrayList<Tarefa> tars = tarDAO.buscar_Sessao(2);
       Node [] nodes = new Node[tars.size()];
        for (int i = 0; i < nodes.length; i++) {
            try {
                System.out.println("foi"+i);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/organon/view/ObjetoTarefa.fxml"));
                nodes[i] = loader.load();
                //Modificando cada node utilizando método do ObjetoTarefaController
                ObjetoTarefaController tarControl = loader.getController();
                tarControl.adc(tars.get(i));
                
                painelTarefa.getChildren().add(nodes[i]);
            }catch(Exception e){
            }
        } 
    }  
      public void criarTar(){
      try{
               
        Tarefa tar = new Tarefa();
        tar.setNome(txtNomeTarefa.getText());
        tar.setDescricao(txtareaDescricao.getText());
        //Definindo importancia
        if(cbImportancia.getValue().equals("Baixa")){

            tar.setImportancia(1);  

        }else if(cbImportancia.getValue().equals("Media")){
            tar.setImportancia(2);
        }else{
             tar.setImportancia(3);
        }
        tar.setDataIni(dtDataIni.getValue());
        tar.setDataFim(dtDataFim.getValue());
        tar.setResponsavel(buscarNomeResponsavel(cbResponsavel.getValue()));
        tar.setProjeto(buscarNomeProjeto(cbProjeto.getValue()));
        //Definindo Sessao
        if(cbSessao.getValue().equals("Fazer")){
            tar.setSessao(1);
        }else if(cbSessao.getValue().equals("Fazendo")){
            tar.setSessao(2);
        }else if(cbSessao.getValue().equals("Concluida")){
            tar.setSessao(3);
        }else{
            tar.setSessao(4);        
        }

        tarDAO.criar_Tarefa(tar);
        //Adicionado tarefa aqui para controle
        tarList.add(tar);
        //Add responsavel ao projeto no banco
        Projeto p = pDAO.buscar(tar.getProjeto());
        String dev = Integer.toString(tar.getResponsavel());
        p.addDev(dev);
        pDAO.alterar(p);
      }catch(Exception e){
            e.printStackTrace();
      } 
      
      

    }
    public void editarTar(){
            
      try{
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
          tar.setDataIni(dtDataIni.getValue());
          tar.setDataFim(dtDataFim.getValue());
          tar.setResponsavel(buscarNomeResponsavel(cbResponsavel.getValue()));
          tar.setProjeto(buscarNomeProjeto(cbProjeto.getValue()));
          //Definindo Sessao
          if(cbSessao.getValue().equals("Fazer")){
              tar.setSessao(1);
          }else if(cbSessao.getValue().equals("Fazendo")){
              tar.setSessao(2);
          }else if(cbSessao.getValue().equals("Feita")){
              tar.setSessao(3);
          }else{
              tar.setSessao(4);        
          }
          tarDAO.alterar(tar);
            
      }catch(Exception e){
            e.printStackTrace();
      }
      
    }
    public void excluirTar(){
        try{
            Tarefa tar = tarDAO.buscar(2);//;

            tarDAO.deleta(tar);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
     /// Sessao 
    public void adcTar(){
        try{
            Tarefa tar = tarDAO.buscar(0);
            sessaoDAO.adcTar(Integer.valueOf(cbSessao.getValue()), tar);

        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public void bosta (){
        Tarefa tar = new Tarefa();
        sessaoDAO.excTar(Integer.valueOf(cbSessao.getValue()), tar);
    }
   //Busca o id do responsável  selecionado no combobox
    public int buscarNomeResponsavel(String nome){
        ArrayList<Desenvolvedor> emp = empDAO.buscarTodos();
        try{
            for(Desenvolvedor dev : emp){
                if(dev.getNome().equals(nome)){
                    return dev.getId();
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }   
        
        return 0;
    }
   //Busca o id do Projeto  selecionado no combobox
  
    public int buscarNomeProjeto(String projNome){
        ArrayList<Projeto> listP = pDAO.buscarTodos();
        try{
            for(Projeto p: listP){
                if(p.getNome().equals(projNome)){
                    return p.getId();
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return 0;
    } 
    //Cria lista com nome de Projetos para alimentar o combobox
    public ArrayList<String> getNomeProjeto(){
        ArrayList<Projeto> projetos = pDAO.buscarTodos();
        ArrayList<String> sLista = new ArrayList();
        try{
            
        }catch(Exception e){
            e.printStackTrace();
        }
        for(Projeto p: projetos){
            sLista.add(p.getNome());
        }
        return sLista;
    } 
    
    //Cria lista com nome de Desenvolvedores para alimentar o combobox
    public ArrayList<String> getNomeResponsavel(){
        ArrayList<Desenvolvedor> responsaveis = empDAO.buscarTodos();
        ArrayList<String> sLista = new ArrayList();
        try{
            for(Desenvolvedor dev: responsaveis){
                sLista.add(dev.getNome());
            }
            return sLista;            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    //Cria lista com nome das Sessoes para alimentar o combobox
    public ArrayList<String> getNomeSessao(){
        ArrayList<String> sLista = new ArrayList();   
        try{
            sLista.add("Fazer");
            sLista.add("Fazendo");
            sLista.add("Feita");
            sLista.add("Concluida");
        }catch(Exception e){
            e.printStackTrace();
        }
        return sLista;
        
        
    }
    
    //Cria lista com os tipos de Importancia para alimentar o combobox
    public ArrayList<String> getNomeImportancia(){
        ArrayList<String> sLista = new ArrayList();
        try{

            sLista.add("Baixa");
            sLista.add("Media");
            sLista.add("Grande");
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return sLista;
    }
}

