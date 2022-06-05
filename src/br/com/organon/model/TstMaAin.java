package br.com.organon.model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TstMaAin {
    public static void main (String args[]){
       //Testes Empregado
       
       TarefaDAO tarDAO = new TarefaDAO();
       Tarefa tar = tarDAO.buscar(12);
       /*tar.setNome("Tarefa Teste");
       tar.setDescricao("Tarefa criada hoje");
       tar.setProjeto(3);
       tar.setImportancia(2);
       tar.setResponsavel(1);
       tar.setSessao(1);
       tar.setDataIni(new Date());
       tar.setDataFim(new Date());
       
       tarDAO.criar_Tarefa(tar);
       */
       tarDAO.deleta(tar);
        
        
        
    
    }
    
}
