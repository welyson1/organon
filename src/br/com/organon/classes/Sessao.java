package br.com.organon.classes;
import java.util.ArrayList;

public class Sessao {
    
    private int numTar;
    private int tipo;
    private ArrayList<Tarefa> tarList;
     
    public Sessao() {
        Tarefa a = new Tarefa();
        this.numTar = 0;
        this.tarList.add(a);
        this.tipo = tipo;
        
    }
    public Sessao(Sessao section) {
        this.numTar = section.numTar;
        this.tarList = section.tarList;
        this.tipo = tipo;
    }

    public int getNumTar() {
        return numTar;
    }

    public void setNumTar(int numTar) {
        this.numTar = numTar;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public ArrayList<Tarefa> getTarList() {
        return tarList;
    }

    public void setTarList(ArrayList<Tarefa> tarList) {
        this.tarList = tarList;
    }
    
    
    public void exibir(Sessao section){
        
    }
    public void ocultar(Sessao section){
        
    }

    
}
