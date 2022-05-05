package br.com.organon.DAO;
import br.com.organon.classes.Conexao;
import br.com.organon.classes.Projeto;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
        
public class ProjetoDAO {
    
    public Projeto criar(Projeto p){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        String sql = "Insert into Projeto (nome,linguagem,descricao,"
                 +   "devs,repositorioGit,modeloProcesso) values (?,?,?,?,?,?)";
        String sql2 = "Select * from Projeto where id = "
                   + "(Select max(id) from Projeto) ";
        try{
           conn = Conexao.conexao();
           pst = conn.prepareStatement(sql);
           pst.setString(1, p.getNome());
           pst.setString(2, p.getLinguagem() );
           pst.setString(3, p.getDescricao());
           pst.setString(4, String.join(",", p.getDevs()));
           pst.setString(5, p.getRepositorio());
           pst.setString(6, p.getMdlProcess());
           pst.execute();    
           
           pst = conn.prepareStatement(sql2);
           rst = pst.executeQuery();
           if(rst.next()){
               p.setId(rst.getInt("id"));
           }
        
        }
        catch(Exception e){
            System.out.println("Erro ProjetoDAO" + e);
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
        return p;
    }
    
    public Projeto buscar(int id){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        String sql = "Select * from Projeto where"
                   + " id = ?";
        Projeto p = new Projeto();
        try{
           conn = Conexao.conexao();
           pst = conn.prepareStatement(sql);
           pst.setInt(1,id );
           rst = pst.executeQuery();
           if(rst.next()){
                p.setId(rst.getInt("id"));
                p.setNome(rst.getString("nome"));
                p.setLinguagem(rst.getString("linguagem"));
                p.setDescricao(rst.getString("descricao"));
                String[] list = rst.getString("devs").split(",");
                ArrayList<String> devs = new ArrayList<String>(Arrays.asList(list));
                p.setDevs(devs);
                p.setRepositorio(rst.getString("repositorioGit"));
                p.setMdlProcess(rst.getString("modeloProcesso"));
           }else{
               return null;
           }
           
           
        }
        catch(Exception e){
            System.out.println("Erro ProjetoDAO" + e);
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
        
        return p;
    }
    public void alterar(Projeto p){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        String sql = "UPDATE Projeto SET nome = ? WHERE id = ?";
        try{
           conn = Conexao.conexao();
           pst = conn.prepareStatement(sql);
           pst.setString(1, p.getNome());
           pst.setInt(2,p.getId());
           pst.execute();
           
        }
        catch(Exception e){
            System.out.println("Erro ProjetoDAO" + e);
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
    
    public Projeto deleta(Projeto p){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        String sql = "DELETE FROM Projeto  WHERE id = ?";
        try{
           if(buscar(p.getId()) != null ){
                conn = Conexao.conexao();
                pst = conn.prepareStatement(sql);
                pst.setInt(1,p.getId());
                pst.execute();
           }else{
               return null;
           }
        }
        catch(Exception e){
            System.out.println("Erro ProjetoDAO" + e);
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
        return p;
    }

}
