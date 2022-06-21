package br.com.organon.controller;
import br.com.organon.model.Desenvolvedor;
import br.com.organon.model.EmpregadoDAO;
import br.com.organon.model.Projeto;
import br.com.organon.model.ProjetoDAO;
import br.com.organon.model.SessaoDAO;
import br.com.organon.model.Tarefa;
import br.com.organon.model.TarefaDAO;
import br.com.organon.view.MainFX;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaBoardController implements Initializable  {

    EmpregadoDAO empDAO = new EmpregadoDAO();    
    TarefaDAO tarDAO = new TarefaDAO();
    SessaoDAO sessaoDAO = new SessaoDAO();
    ProjetoDAO pDAO = new ProjetoDAO();
    //Objeto criado para ediÃ§Ã£o/exclusÃ£o (guarda id)
    Tarefa tarefa = new Tarefa();
    //VariÃ¡vel que guarda a Ãºltima sessÃ£o selecionada
    int ultimaSessao = 1;
    @FXML
    private Button btnProjeto;
    @FXML
    private Button btnMensagem;

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
    @FXML
    private Button btnExcluirTarefa;
    @FXML
    private Button btnEditarTarefa;
    @FXML
    private Button btnSalvarTarefa;
    @FXML
    private ComboBox<String> cbFiltroImportancia;
    @FXML
    private ComboBox<String> cbFiltroResponsavel;
    @FXML
    private ComboBox<String> cbFiltroProjeto;
    @FXML
    private Label labelSessao;
    @FXML
    private Label infoNome;
    @FXML
    private Label infoLinguagem;
    @FXML
    private Label infoDescricao;
    @FXML
    private Label infoMdlProcess;
    @FXML
    private Label infoRepositorio;
    @FXML
    private Button btnSair;
    @FXML
    private Button btnSessaoFazer;
    @FXML
    private Button btnSessaoFazendo;
    @FXML
    private Button btnSessaoFeito;
    @FXML
    private Button btnSessaoArquivado;
    @FXML
    private Button btnLimparFiltro;
    @FXML
    private Button btnAplicarFIltro;
    @FXML
    private Label lblNomeEmpregado;
    @FXML
    private Label lblOpcoes;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Populando comboboxs lado direito
        cbResponsavel.getItems().addAll(getNomeResponsavel());
        cbSessao.getItems().addAll(getNomeSessao());
        cbProjeto.getItems().addAll(getNomeProjeto());
        cbImportancia.getItems().addAll(getNomeImportancia());
        //Populando combobox lado esquerdo
        cbFiltroImportancia.getItems().addAll(getNomeImportancia());
        cbFiltroResponsavel.getItems().addAll(getNomeResponsavel());
        cbFiltroProjeto.getItems().addAll(getNomeProjeto());
        //OnAction
        cbFiltroProjeto.setOnAction(this::DadosProjeto);        
        //Carrega tarefas da sessÃ£o fazer
        carregaTarefas(tarDAO.buscar_Sessao(1));       
        //Define o visual caso seja dev ou gestor
        visual();
    } 
    //Ao clicar no botÃ£o salvar a tarefa Ã© criada no banco
    @FXML
    public void criar(ActionEvent e){
        try{
            Tarefa tar = new Tarefa();
            tar.setNome(txtNomeTarefa.getText());
            tar.setDescricao(txtareaDescricao.getText());
            //Definindo importancia
            tar.setImportancia(getImportancia(cbImportancia.getValue()));  

            tar.setDataIni(dtDataIni.getValue());
            tar.setDataFim(dtDataFim.getValue());
            tar.setResponsavel(buscarNomeResponsavel(cbResponsavel.getValue()));
            tar.setProjeto(buscarNomeProjeto(cbProjeto.getValue()));
            //Definindo Sessao
            tar.setSessao(getSessao(cbSessao.getValue()));

            //Criando tarefa
            tarDAO.criar_Tarefa(tar);
            //Adicionando tarefa a sessao
            sessaoDAO.adcTar(tar.getSessao(), tar);
            //Add responsavel ao projeto no banco
            Projeto p = pDAO.buscar(tar.getProjeto());
            String dev = Integer.toString(tar.getResponsavel());
            p.addDev(dev);
            pDAO.alterar(p);
            //Atualiza Ãºltima sessÃ£o selecionada
            carregaTarefas(tarFiltro(tarDAO.buscar_Sessao(ultimaSessao)));
            //Avisa criaÃ§Ã£o da tarefa
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("Tarefa criada com sucesso!");
            alert.setTitle("Criar Tarefa.");
            alert.showAndWait();
            
            limparPainel();

        }catch(Exception error){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Ocorreu um erro ao criar a tarefa!");
            alert.setContentText("Por favor preencha todos os campos corretamente.");
            alert.showAndWait();
            
        }

    }
    @FXML
    public void editar(ActionEvent e){
        try{
            Tarefa tar = tarDAO.buscar(tarefa.getId());
            tar.setNome(txtNomeTarefa.getText());
            tar.setDescricao(txtareaDescricao.getText());
            //Definindo importancia
            tar.setImportancia(getImportancia(cbImportancia.getValue()));  

            tar.setDataIni(dtDataIni.getValue());
            tar.setDataFim(dtDataFim.getValue());
            tar.setResponsavel(buscarNomeResponsavel(cbResponsavel.getValue()));
            tar.setProjeto(buscarNomeProjeto(cbProjeto.getValue()));
            //Modificando sessÃ£o da tarefa
            if(tar.getSessao() != getSessao(cbSessao.getValue())){
                sessaoDAO.excTar(tar.getSessao(), tar);
                sessaoDAO.adcTar(getSessao(cbSessao.getValue()), tar);
            }
            tarDAO.alterar(tar);
            //Atualiza Ãºltima sessÃ£o selecionada
            carregaTarefas(tarFiltro(tarDAO.buscar_Sessao(ultimaSessao)));            
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("Tarefa editada com sucesso!");
            alert.setTitle("EdiÃ§Ã£o.");
            alert.showAndWait();
            
            limparPainel();
        }catch(Exception error){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Ocorreu um erro ao editar a tarefa!");
            alert.setContentText("Por favor, selecione uma tarefa antes de editar.");
            alert.showAndWait();            
        }        
    }
    @FXML
    public void excluir(ActionEvent e){
        try{
            if(!(txtNomeTarefa.getText().equals(""))){
               
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setHeaderText("Deseja realmente excluir a Tarefa?");
                alert.setTitle("Excluir Tarefa.");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() &&  result.get() == ButtonType.OK){
                    Tarefa tar = tarDAO.buscar(tarefa.getId());
                    sessaoDAO.excTar(tar.getSessao(), tar);
                    tarDAO.deleta(tarefa);
                }
                //Atualiza Ãºltima sessÃ£o selecionada
                carregaTarefas(tarFiltro(tarDAO.buscar_Sessao(ultimaSessao)));
                limparPainel();                
            }else{
                    //Usado para acusar erro caso nenhuma tarefa esteja selecionada
                    Tarefa tar = tarDAO.buscar(tarefa.getId());
                    sessaoDAO.excTar(tar.getSessao(), tar);
                    tarDAO.deleta(tarefa);
            }
        }catch(Exception error){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Ocorreu um erro ao excluir a tarefa!");
            alert.setContentText("Por favor, selecione uma tarefa para realizar a exclusÃ£o!.");
            alert.showAndWait();            
        } 
    }
    //FunÃ§Ã£o que Ã© chamada ao clicar no botÃ£o CRIAR PROJETO na interface
    @FXML
    void abrirCriadorProjeto(ActionEvent event) throws IOException {
        //Cria e chama a interface de criaÃ§Ã£o de projeto
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/organon/view/TelaProjeto.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();

        Stage stage = new Stage();
        stage.setTitle("Criador de Projeto");
        stage.setScene(new Scene(root1));  
        stage.show();  
    }
    @FXML
    //Chama a tela de criar tarefa
    void abrirTelaMensagem(ActionEvent event) throws IOException {        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/organon/view/TelaMensagem.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
    }
    //MÃ©todos responsÃ¡veis pela mudanÃ§a na exibiÃ§Ã£o das tarefas
    @FXML
    public void btnSessaoFazer(ActionEvent e){
        ArrayList<Tarefa> tars = tarDAO.buscar_Sessao(1); 
        carregaTarefas(tarFiltro(tars));
        ultimaSessao = 1;
        //limparFiltros();
        //troca o nome do label da sessÃ£o
        labelSessao.setText("Fazer");

    }
    @FXML 
    public void btnSessaoFazendo(ActionEvent e){        
        carregaTarefas(tarFiltro(tarDAO.buscar_Sessao(2)));
        ultimaSessao = 2;
       // limparFiltros();
        //troca o nome do label da sessÃ£o
        labelSessao.setText("Fazendo");
    }
    @FXML 
    public void btnSessaoFeito(ActionEvent e){
        carregaTarefas(tarFiltro(tarDAO.buscar_Sessao(3)));
        ultimaSessao = 3;
       // limparFiltros();
        
        //troca o nome do label da sessÃ£o
        labelSessao.setText("Feito");
    }
    @FXML 
    public void btnSessaoArquivado(ActionEvent e){
        carregaTarefas(tarFiltro(tarDAO.buscar_Sessao(4)));
        ultimaSessao = 4;
       // limparFiltros(); 
        
        //troca o nome do label da sessÃ£o
        labelSessao.setText("Arquivado");
    }
    //BotÃµes complementares
    @FXML
    private void btnSair(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/br/com/organon/view/Login.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();  
    }
    //Aplica filtro(s) selecionados
    @FXML
    private void btnAplicarFiltro(ActionEvent event) {
        carregaTarefas(tarFiltro(tarDAO.buscar_Sessao(ultimaSessao)));
    }
    //Limpa os campos da tarefa
    @FXML
    private void btnAddTarefa(ActionEvent event) {
        limparPainel();
    }
    //Filtragem e carregamento das tarefas 
    //Filtragem
    public ArrayList<Tarefa> tarFiltro(ArrayList<Tarefa> tars){
        ArrayList<Tarefa> tarsFiltradas = new ArrayList();
        //Verificando filtro projeto 
        if(cbFiltroProjeto.getValue()!=null){
                for(Iterator<Tarefa> tarefa = tars.iterator(); tarefa.hasNext();){
                    if(tarefa.next().getProjeto()!= buscarNomeProjeto(cbFiltroProjeto.getValue())){
                        tarefa.remove();
                    }
                }  
            
        }        
        if(cbFiltroImportancia.getValue()!= null && cbFiltroResponsavel.getValue()!=null){

                for(int i = 0; i<tars.size();i++){
                    if(tars.get(i).getImportancia()==getImportancia(cbFiltroImportancia.getValue()) &&
                       tars.get(i).getResponsavel()==buscarNomeResponsavel(cbFiltroResponsavel.getValue())){
                        tarsFiltradas.add(tars.get(i));
                    }
                }                 
                return tarsFiltradas;            
        }else if(cbFiltroImportancia.getValue()!= null){
            
                for(int i = 0; i<tars.size();i++){
                    if(tars.get(i).getImportancia()==getImportancia(cbFiltroImportancia.getValue())){
                        tarsFiltradas.add(tars.get(i));
                    }
                }
                return tarsFiltradas;              
        }else if(cbFiltroResponsavel.getValue()!=null){
            
            for(int i = 0; i<tars.size();i++){
                if(tars.get(i).getResponsavel()==buscarNomeResponsavel(cbFiltroResponsavel.getValue())){
                    tarsFiltradas.add(tars.get(i));
                }
            }
            return tarsFiltradas;            
        }
        return tars;        
    }
    
    //Carregamento das tarefas 
    private void carregaTarefas(ArrayList<Tarefa> tars) {
       //Array de tarefas que irÃ£o em cada ObjetoTarefa
       Node [] nodes = new Node[tars.size()];
       //Limpa o painel antes de popular com tarefas 
       painelTarefa.getChildren().clear();
        for (int i = 0; i < nodes.length; i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/organon/view/ObjetoTarefa.fxml"));
                nodes[i] = loader.load();
                //Modificando cada node utilizando mÃ©todo do ObjetoTarefaController
                ObjetoTarefaController tarControl = loader.getController();
                tarControl.adc(tars.get(i));
                painelTarefa.getChildren().add(nodes[i]);
            }catch(Exception e){
            }
        } 
    }
   //MÃ©todos de busca usado nos combobox
   //Busca o id do responsÃ¡vel  selecionado no combobox
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
    //MÃ©todos para alimentacao dos comobox no initialize
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
            sLista.add("Arquivada");
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
            sLista.add("Alta");
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return sLista;
    }
    //Retornar valor da sessao em int de acordo com combobox
    public int getSessao(String sessao){
        switch (sessao) {
            case "Fazer":
                return 1;
            case "Fazendo":
                return 2;
            case "Feita":
                return 3;
            default:
                return 4;
        }
        
    }
    //Retorna valor da importancia em int de acordo com combobox
    public int getImportancia(String importancia){
        switch (importancia) {
            case "Baixa":
                return 1;
            case "Media":
                return 2;
            default:
                return 3;
        }        

        
    }
    //MÃ©todos de exibiÃ§Ã£o dos dados
    //Exibe dados das tarefas - utilizado pelo ObjetoTarefaController 
    public void exibirDadosTarefa(Tarefa tar){
       
        txtNomeTarefa.setText(tar.getNome());
        cbResponsavel.setValue(empDAO.buscar(tar.getResponsavel()).getNome());
        //Setando sessao combobox
        if(tar.getSessao()== 1){
            cbSessao.setValue("Fazer");
        }else if(tar.getSessao() == 2){
            cbSessao.setValue("Fazendo");
        }else if(tar.getSessao()== 3){
            cbSessao.setValue("Feita");
        }else{
            cbSessao.setValue("Arquivada");       
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
    //Exibe dados do Projeto selecionado 
    public void exibirDadosProjeto(Projeto p){
        
        infoNome.setText(p.getNome());
    
        infoLinguagem.setText(p.getLinguagem());
    
        infoDescricao.setText(p.getDescricao());
    
        infoMdlProcess.setText(p.getMdlProcess());
    
        infoRepositorio.setText(p.getRepositorio());
        
    }
    //Utilizado para onAction do Projeto
    public void DadosProjeto(ActionEvent e){
        Projeto p = pDAO.buscar(buscarNomeProjeto(cbFiltroProjeto.getValue()));
        if(p!=null){    
          exibirDadosProjeto(p);
        }

    }
    //Atualiza filtro Projeto - utilizado por TelaProjetoController
    public void atualizarProjeto(){
        cbFiltroProjeto.getItems().clear();
        cbFiltroProjeto.getItems().addAll(getNomeProjeto());
        
        cbProjeto.getItems().clear();
        cbProjeto.getItems().addAll(getNomeProjeto());
    }
    //MÃ©todos de limpeza
    //Limpa campos das tarefas
    public void limparPainel(){
        txtNomeTarefa.setText("");
        cbResponsavel.setValue("");
        //Setando sessao combobox){
        cbSessao.setValue("");

        //Setando combobox importancia
        cbImportancia.setValue("");

        cbProjeto.setValue("");
        
        dtDataIni.setValue(null);
        dtDataFim.setValue(null);
        txtareaDescricao.setText("");        
    }
    //Limpa os filtros selecionados
    @FXML
    public void btnLimparFiltros(ActionEvent e){
        cbFiltroImportancia.setValue(null);
        cbFiltroResponsavel.setValue(null);
        cbFiltroProjeto.setValue(null);
        
    }

    //Define o visual caso seja desenvolvedor
    public void visual(){
        lblNomeEmpregado.setText( LoginController.dev.getNome());
       
        if(LoginController.dev.getTipo() == 0){
            btnExcluirTarefa.setDisable(true);   
            btnSalvarTarefa.setDisable(true);
            btnExcluirTarefa.setVisible(false);   
            btnSalvarTarefa.setVisible(false);

            btnMensagem.setDisable(true);
            btnProjeto.setDisable(true);
            btnMensagem.setVisible(false);
            btnProjeto.setVisible(false);
            lblOpcoes.setVisible(false);
            
            txtNomeTarefa.setDisable(true);
            txtareaDescricao.setDisable(true);
            cbProjeto.setDisable(true);
            cbResponsavel.setDisable(true);
            cbImportancia.setDisable(true);
            dtDataIni.setDisable(true);
            dtDataFim.setDisable(true);         
        }
 
    }

    
}

