import br.com.organon.DAO.* ;
import br.com.organon.classes.Projeto;
import java.util.ArrayList;
import java.util.Arrays;

public class TstMaAin {
    public static void main (String args[]){
        ProjetoDAO proj = new ProjetoDAO();
        
        Projeto p = proj.buscar(2);
        p = proj.deleta(p);
        //Mudan�a no nome
        //p.setNome("Rosalia");
        //proj.alterar(p);
        System.out.println(p);
        /*     
        p.setNome("Terceiro");
        p.setLinguagem("Python");
        p.setDescricao("Teste");
 
        ArrayList<String> list = new ArrayList();
        list.add("patrick");
        list.add("adeline");
       
        p.setDevs(list);
        p.setRepositorio("Link_Github");
        p.setMdlProcess("PU");
        p.setId(proj.criar(p).getId()); 
        
        */
        
        
    }
    
}
