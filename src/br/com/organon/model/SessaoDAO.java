package br.com.organon.model;
import java.sql.*;
import br.com.organon.model.Conexao;
import br.com.organon.model.Tarefa;
import java.util.ArrayList;
import java.util.Arrays;


public class SessaoDAO {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rst = null;
    

    
    public String buscar(int tipo){
        String sql = "SELECT * FROM Sessao WHERE tipo = ?";
        String tarList = new String();
        try{
            conn = Conexao.conexao();
            pst = conn.prepareStatement(sql);
            pst.setInt(1, tipo);
            rst = pst.executeQuery();         
            if(rst.next()){
                tarList = rst.getString("listTarefas");
            }
            return tarList;
        }catch(Exception e){
            System.out.println("Erro SESSAO " + e);
        }finally{
            try{
                if(pst!=null){
                    pst.close();
                }
                if(conn!=null){
                    conn.close();
                }
            }catch(Exception e){
                System.out.println("Erro SESSAO " + e);
            }
        }
        return null;
    }
    //Adiciona tarefa em uma sessão. Obs: int tipo = 1,2,3,4
    public void adcTar(int tipo, Tarefa tar){
        String list = buscar(tipo);
        String sql = "UPDATE Sessao set listTarefas = ? WHERE tipo = ?";
        try{
            conn = Conexao.conexao();
            pst = conn.prepareStatement(sql);
            list = list + "," + Integer.toString(tar.getId());
            pst.setString(1, list);
            pst.setInt(2,tipo);
            //Altera o atributo sessao na tarefa passada como argumento
            tar.setSessao(tipo);
            pst.execute();
        }catch(Exception e){
            System.out.println("Erro SESSAO " + e);
        }finally{
            try{
                if(pst!=null){
                    pst.close();
                }
                if(conn!=null){
                    conn.close();
                }
            }catch(Exception e){
                System.out.println("Erro SESSAO " + e);
            }
        }
        
    }
    //Exclui uma tarefa de uma determina sessão, int tipo = 1,2,3,4
    
    public void excTar(int tipo, Tarefa tar){
        
        String[] list = buscar(tipo).split(",");
        ArrayList<String> tars = new ArrayList<>(Arrays.asList(list));
        tars.remove(Integer.toString(tar.getId()));
        
        String sql = "UPDATE Sessao set listTarefas = ? WHERE tipo = ?";
        try{
            conn = Conexao.conexao();
            pst = conn.prepareStatement(sql);
            pst.setString(1, String.join(",", tars));
            pst.setInt(2,tipo);
            pst.execute();
        }catch(Exception e){
            System.out.println("Erro SESSAO " + e);
        }finally{
            try{
                if(pst!=null){
                    pst.close();
                }
                if(conn!=null){
                    conn.close();
                }
            }catch(Exception e){
                System.out.println("Erro SESSAO " + e);
            }
        }
        
    }
        
 
    
    
    
}
