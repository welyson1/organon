package br.com.organon.model;
import br.com.organon.model.Conexao;
import br.com.organon.model.Projeto;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
        
public class ProjetoDAO {
    //Cria um projeto no banco e retorna projeto criado
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
           //Usado para criar o projeto antes de adicionar tarefas
           if(p.getDevs()!= null){
               pst.setString(4, String.join(",", p.getDevs()));
           }else{
               pst.setString(4,"");
           }
           pst.setString(5, p.getRepositorio());
           pst.setString(6, p.getMdlProcess());
           pst.execute();    
           
           pst = conn.prepareStatement(sql2);
           rst = pst.executeQuery();
           if(rst.next()){
               p.setId(rst.getInt("id"));
           }
           return p;
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
        return null;
    }
    
    //Busca projeto no banco usando id e retorna ele como objeto de Projeto
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
    //Busca todos os projetos do banco e retorna um ArrayList de Projeto
    public ArrayList<Projeto> buscarTodos(){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        String sql = "Select * from Projeto";
        ArrayList<Projeto> lista = new ArrayList();

        try{
           conn = Conexao.conexao();
           pst = conn.prepareStatement(sql);
           rst = pst.executeQuery();

           while(rst.next()){          
                Projeto p = new Projeto();
                
                p.setId(rst.getInt("id"));
                p.setNome(rst.getString("nome"));
                p.setLinguagem(rst.getString("linguagem"));
                p.setDescricao(rst.getString("descricao"));
                String[] list = rst.getString("devs").split(",");
                ArrayList<String> devs = new ArrayList<String>(Arrays.asList(list));
                p.setDevs(devs);
                p.setRepositorio(rst.getString("repositorioGit"));
                p.setMdlProcess(rst.getString("modeloProcesso"));
                
                lista.add(p);
           }
           return lista;  
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
        return null;
        
    }
    //Altera o projeto no banco usando um projeto como argumento
    public void alterar(Projeto p){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        String sql = "UPDATE Projeto SET nome = ?, linguagem = ?, descricao = ?,"
                + "devs = ?, repositorioGit = ?, modeloProcesso = ? WHERE id = ?";
        try{
           conn = Conexao.conexao();
           pst = conn.prepareStatement(sql);
           pst.setString(1, p.getNome());
           pst.setString(2, p.getLinguagem());
           pst.setString(3, p.getDescricao());
           if(p.getDevs()!= null){
               pst.setString(4, String.join(",", p.getDevs()));
           }else{
               pst.setString(4,"");
           }         
           pst.setString(5, p.getRepositorio());
           pst.setString(6, p.getMdlProcess());
           pst.setInt(7,p.getId());
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
    //Recebe um projeto e recebe o mesmo para uso se necessário
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
