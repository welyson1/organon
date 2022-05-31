package br.com.organon.model;
public class Desenvolvedor extends Empregado{
    
    private int num_TarToDo;
    private int numTarDone;

    public int getNum_TarToDo() {
        return num_TarToDo;
    }

    public void setNum_TarToDo(int num_TarToDo) {
        this.num_TarToDo = num_TarToDo;
    }



    public int getNumTarDone() {
        return numTarDone;
    }

    public void setNumTarDone(int numTarDone) {
        this.numTarDone = numTarDone;
    }
    
    

//Sobreescrita se necessário
    public void altSessao(){
        
    }
    public void filtrarTar(){
        
    }    
    
    
    public String toString() {
         return getId() + getNome()+getEmail()+getSenha()+getTipo()+getNum_TarToDo()+getNumTarDone();
    }
}
