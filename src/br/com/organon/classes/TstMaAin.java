package br.com.organon.classes;
import br.com.organon.DAO.* ;
import java.util.ArrayList;
import java.util.Arrays;

public class TstMaAin {
    public static void main (String args[]){
        //SessaoDAO sec = new SessaoDAO();
        Tarefa tar = new Tarefa();
        TarefaDAO tarefaDao = new TarefaDAO();
        tarefaDao.buscar(12);
        System.out.println("Tarefa 12: " +  tarefaDao.buscar(12).getDataIni());
        //tar.setId(10);
        //sec.adcTar(1, tar);
        //System.out.println("Lista de tarefas antes de excluir:" + sec.buscar(1));
        
       // sec.excTar(1,tar);
      
        
       // System.out.println("Lista de tarefas:" + sec.buscar(1));
        
        
        
    }
    
}
