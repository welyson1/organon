package br.com.organon.classes;

import java.util.ArrayList;

public class Mensagem {
    private int id;
    private String origEmail;
    private ArrayList<String> destEmail;
    
    public Mensagem(String origEmail, ArrayList<String> destEmail){
        this.origEmail = origEmail;
        for(String email : destEmail ){
            this.destEmail.add(email);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigEmail() {
        return origEmail;
    }

    public void setOrigEmail(String origEmail) {
        this.origEmail = origEmail;
    }

    public ArrayList<String> getDestEmail() {
        return destEmail;
    }

    public void setDestEmail(ArrayList<String> destEmail) {
        this.destEmail = destEmail;
    }
    
    
        
    public boolean enviar(){
        return true;
    }
}
