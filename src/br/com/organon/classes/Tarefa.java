
package br.com.organon.classes;

import br.com.organon.DAO.TarefaDAO;

public class Tarefa {
    private String nome;
    private String descricao;
    private String responsavel;
    private String  dataIni;
    private String dataFim;
    private int importancia;
    private int id;
    private int sessao;
    
    
    public Tarefa(){
        this.nome = "";
        this.descricao = "";
        this.responsavel = "";
        this.dataIni = "";
        this.dataFim = "";
        this.importancia = 0;
        this.id = 0;
        this.sessao = 0;
        
    }
    
    
    public Tarefa(String nome, String descricao, String resp,String dataIni,
                  String dataFim, int impor, int id, int sessao ){
        this.nome = nome;
        this.descricao = descricao;
        this.responsavel = resp;
        this.dataIni = dataIni;
        this.dataFim = dataFim;
        this.importancia = impor;
        this.id = id;
        this.sessao = 0;
       
        
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getDataIni() {
        return dataIni;
    }

    public void setDataIni(String dataIni) {
        this.dataIni = dataIni;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public int getImportancia() {
        return importancia;
    }

    public void setImportancia(int importancia) {
        this.importancia = importancia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSessao() {
        return sessao;
    }

    public void setSessao(int sessao) {
        this.sessao = sessao;
    }
    

    public Tarefa criarTar(){
        Tarefa nova = new Tarefa();
        return nova;
    }
    
    public Tarefa excluirTar(Tarefa Tar){
        return Tar;
    }
    
    public Tarefa altTar(Tarefa Tar){
        return Tar;
    }
    public Tarefa buscarTar(Tarefa Tar){
        return Tar;
    }
    
    
}
