package br.com.organon.classes;

import java.util.ArrayList;

public class Mensagem {
    private int id;
    private String titulo;
    private String conteudo;
    private ArrayList<String> destEmail;
    
    public Mensagem(ArrayList<String> destEmail){
        for(String email : destEmail ){
            this.destEmail.add(email);
        }
    }
    public Mensagem(){
        this.id = 0;
        this.titulo="";
        this.conteudo="";
        this.destEmail = new ArrayList();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public ArrayList<String> getDestEmail() {
        return destEmail;
    }

    public void setDestEmail(ArrayList<String> destEmail) {
        this.destEmail = destEmail;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
    
    
        
    public boolean enviar(){
        return true;
    }
}
