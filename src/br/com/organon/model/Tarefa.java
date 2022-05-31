
package br.com.organon.model;

import java.lang.Object;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Tarefa {
    private String nome;
    private String descricao;
    private int responsavel;
    private Date  dataIni;
    private Date dataFim;
    private int importancia;
    private int id;
    private int sessao;
    private int projeto;
    
    
    public Tarefa(){
        this.nome = "a";
        this.descricao = "b";
        this.responsavel = 0;
        this.dataIni = new GregorianCalendar(2022, Calendar.MAY, 11).getTime();
        this.dataFim = new GregorianCalendar(2022, Calendar.MAY, 15).getTime();
        this.importancia = 0;
        this.id = 0;
        this.sessao = 1;
        this.projeto = 3;
        
    }
    
    
    public Tarefa(String nome, String descricao, int resp, Date dataIni,
                  Date dataFim, int impor, int id, int sessao, int projeto ){
        this.nome = nome;
        this.descricao = descricao;
        this.responsavel = resp;
        this.dataIni = dataIni;
        this.dataFim = dataFim;
        this.importancia = impor;
        this.id = id;
        this.sessao = sessao;
        this.projeto = projeto;
       
        
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

    public int getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(int responsavel) {
        this.responsavel = responsavel;
    }

    public Date getDataIni() {
        return dataIni;
    }

    public void setDataIni(Date dataIni) {
        this.dataIni = dataIni;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
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
    
    public int getProjeto() {
        return projeto;
    }

    public void setSessao(int sessao) {
        this.sessao = sessao;
    }
    
    public void setProjeto(int projeto) {
        this.projeto = projeto;
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
