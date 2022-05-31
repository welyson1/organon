package br.com.organon.model;
import java.util.ArrayList;

public class Gestor extends Empregado {
    
    private ArrayList<Projeto> listaProj;

    public ArrayList<Projeto> getListaProj() {
        return listaProj;
    }

    public void setListaProj(ArrayList<Projeto> listaProj) {
        this.listaProj = listaProj;
    }
    
    
    
    public boolean altTarNome(){
        return true;        
    }
    public boolean altTarResp(){
        return true;        
    }
    public boolean altTarImpor(){
        return true;        
    }
    public boolean altProjNome(){
        return true;        
    }
    //Sobreescrita se necessário
    public void altSessao(){
          
    }
    public void filtrarTar(){
              
        
    }

    @Override
    public String toString() {
        return getId() + getNome()+getEmail()+getSenha()+getTipo()+getListaProj();
    }
    
}
