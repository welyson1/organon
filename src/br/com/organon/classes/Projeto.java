package br.com.organon.classes;

import br.com.organon.DAO.ProjetoDAO;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Projeto {
    private int id;
    private String nome;
    private String linguagem;
    private String descricao;
    private String mdlProcess;
    private String repositorio;
    private ArrayList<String> devs;

    public Projeto() {
        this.nome = "" ;
        this.linguagem = "";
        this.descricao = "";
        this.mdlProcess = "";
        this.devs = new ArrayList();
        this.repositorio = "";
    }
    
    public Projeto(Projeto p) {
        this.nome = p.getNome() ;
        this.linguagem = p.getLinguagem();
        this.descricao = p.getDescricao();
        this.mdlProcess = p.getMdlProcess();
        this.devs = p.getDevs();
        this.repositorio = p.getRepositorio();
    }
    
    

    
    
    
    public Projeto criarProj(){
        Projeto novo = new Projeto();
        return novo;
    
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLinguagem() {
        return linguagem;
    }

    public void setLinguagem(String linguagem) {
        this.linguagem = linguagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMdlProcess() {
        return mdlProcess;
    }

    public void setMdlProcess(String mdlProcess) {
        this.mdlProcess = mdlProcess;
    }

    public String getRepositorio() {
        return repositorio;
    }

    public void setRepositorio(String repositorio) {
        this.repositorio = repositorio;
    }

    public ArrayList<String> getDevs() {
        return devs;
    }

    public void setDevs(ArrayList<String> devs) {
        for(String dev : devs){
            this.devs.add(dev);
        }
    }
    
    public Projeto excluirProj(Projeto Proj){
        return Proj;
    }
    
    public Projeto altProj(Projeto Proj){
        return Proj;
    }
    public Projeto buscarProj(Projeto Proj){
        return Proj;
    }

    @Override
    public String toString() {
        return this.id + "\n" + this.nome + "\n" + this.linguagem+ "\n" +this.descricao+"\n"
        + this.devs + "\n"+this.repositorio + "\n" + this.mdlProcess;
    }
    
}
