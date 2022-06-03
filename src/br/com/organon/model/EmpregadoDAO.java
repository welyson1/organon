
package br.com.organon.model;

import br.com.organon.model.Conexao;
import br.com.organon.model.Desenvolvedor;
import br.com.organon.model.Empregado;
import br.com.organon.model.Gestor;
import java.sql.*;
import java.util.ArrayList;
public class EmpregadoDAO {
        private  Connection conn = null;
        private  PreparedStatement pst = null;
        private  ResultSet rst = null;
        
        
    //Cria um empregado no banco, recebe objeto de Gestor ou Desenvolvedor
    public  <T extends Empregado> T criar(T emp){

        String sql = "INSERT INTO Empregado(email,senha,nome,tipo) values(?,?,?,?)";
        String sql2 = "Select * from Empregado where id ="
                    + " (Select max(id) from Empregado )" ;
        try{
            conn = Conexao.conexao();
            pst = conn.prepareStatement(sql);
            pst.setString(1, emp.getEmail());
            pst.setString(2, emp.getSenha());
            pst.setString(3, emp.getNome());
            pst.setInt(4, emp.getTipo());
            pst.execute();
            
            pst = conn.prepareStatement(sql2);
            rst = pst.executeQuery();
            if(rst.next()){
                emp.setId(rst.getInt("id"));
            
            }
            return emp;
        }catch(Exception e){
            System.out.println(e);
        }finally{
            try{
                if(pst!=null){
                    pst.close();
                }
                if(conn!=null){
                    conn.close();
                }
            }catch(Exception e){
                System.out.println("Erro EMPREGADO " + e);
            }
        }
        return null;
    }    
    /*Busca passando id do empregado, retorna gestor ou desenvolvedor  
    */
    public <T extends Empregado> T buscar(int id){
        //Tipo = 0  - Desenvolvedor
        //Tipo = 1 -  Gestor
        try{
            String sql = "Select * from Empregado where id = ?";
            conn = Conexao.conexao();
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            rst = pst.executeQuery();
            if(rst.next()){
               
               if(rst.getInt("tipo") == 1 ){
                   
                   Gestor ges = new Gestor();
                   
                   ges.setId(rst.getInt("id"));
                   ges.setNome(rst.getString("nome"));
                   ges.setEmail(rst.getString("email"));
                   ges.setSenha(rst.getString("senha"));
                   ges.setTipo(rst.getInt("tipo"));
                   
                   ProjetoDAO p = new ProjetoDAO();
                   ges.setListaProj(p.buscarTodos());
                   
                   return (T) ges;
               }
               if(rst.getInt("tipo") == 0 ){
                   
                   Desenvolvedor dev = new Desenvolvedor();
                   dev.setId(rst.getInt("id"));
                   dev.setNome(rst.getString("nome"));
                   dev.setEmail(rst.getString("email"));
                   dev.setSenha(rst.getString("senha"));
                   dev.setTipo(rst.getInt("tipo"));
                   
                   TarefaDAO tar = new TarefaDAO();
                   dev.setNum_TarToDo(tar.buscar_Dev_Sessao(dev.getId(),1).size());
                   dev.setNumTarDone(tar.buscar_Dev_Sessao(dev.getId(),3).size());
                
                   return (T) dev;
                   
               }
            }
        }catch(Exception e ){
            System.out.println(e);
        }finally{
            try{
                if(pst!=null){
                    pst.close();
                }
                if(conn!=null){
                    conn.close();
                }
            }catch(Exception e){
                System.out.println("Erro Empregado " + e);
            }
        }
        return null;
    }
    
    //Recebe Gestor ou Desenvolvedor e altera informações do empregado no banco
    public <T extends Empregado> void alterar(T emp){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        String sql = "UPDATE Empregado SET nome = ?, email = ?, senha = ?,"
                   + " tipo = ? where id = ?";
        try{
           conn = Conexao.conexao();
           pst = conn.prepareStatement(sql);
           //nome
           pst.setString(1, emp.getNome());
           pst.setString(2, emp.getEmail());
           pst.setString(3,emp.getSenha());
           pst.setInt(4,emp.getTipo());
           pst.setInt(5,emp.getId());

           pst.execute();
           
        }
        catch(Exception e){
            System.out.println("Erro EmpregadoDAO" + e);
        }
        finally{
            try{
                if(pst!=null){
                    pst.close();
                }
                if(conn!=null){
                    conn.close();
                }
                
            }catch(Exception e){
                System.out.println(e);
            }
            
        }
            
    }
    //Exclui empregado do banco, recebe id dele
    public <T extends Empregado> T excluir(int id){
        //Tipo = 0  - Desenvolvedor
        //Tipo = 1 -  Gestor
        try{
            String sql = "DELETE FROM Empregado  WHERE id = ?";
            conn = Conexao.conexao();
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
        }catch(Exception e ){
            System.out.println(e);
        }finally{
            try{
                if(pst!=null){
                    pst.close();
                }
                if(conn!=null){
                    conn.close();
                }
            }catch(Exception e){
                System.out.println("Erro Empregado " + e);
            }
        }
        return null;
    }
    public <T extends Empregado> int Login(String email, String senha){
        try{
            String sql = "Select * from Empregado where email = ? and senha = ?";
            conn = Conexao.conexao();
            pst = conn.prepareStatement(sql);
            pst.setString(1,email);
            pst.setString(2,senha);
            rst = pst.executeQuery();
            if(rst.next()){
                return rst.getInt("tipo");       
            }
        }catch(Exception e ){
            System.out.println(e);
        }finally{
            try{
                if(pst!=null){
                    pst.close();
                }
                if(conn!=null){
                    conn.close();
                }
            }catch(Exception e){
                System.out.println("Erro Empregado " + e);
            }
        }
        return 2;
    }
    public <T extends Empregado> ArrayList<Gestor> buscarTodos(){
        try{
            String sql = "Select * from Empregado";
            ArrayList<Gestor> lista = new ArrayList();
            conn = Conexao.conexao();
            pst = conn.prepareStatement(sql);
            rst = pst.executeQuery();
            if(rst.next()){
              Gestor emp = new Gestor();
              emp.setId(rst.getInt("id"));
              emp.setNome(rst.getString("nome"));
              lista.add(emp);
            }
            return lista;
        }catch(Exception e ){
            System.out.println(e);
        }finally{
            try{
                if(pst!=null){
                    pst.close();
                }
                if(conn!=null){
                    conn.close();
                }
            }catch(Exception e){
                System.out.println("Erro Empregado " + e);
            }
        }
        return null;
    }
    
    
}
