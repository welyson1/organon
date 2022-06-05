package br.com.organon.model;
import java.text.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class TarefaDAO {
       
        
        public Tarefa criar_Tarefa(Tarefa tarefa){
            
            Connection conn = null;
            PreparedStatement pst = null;
            ResultSet rst = null;

            String sql = "Insert into Tarefa (nome, importancia, descricao,"
                        +                         "dataIn, dataFin,responsavelId"
                        +                         ",sessaoId,projetoId)"
                        + " values  (?,?,?,?,?,?,?,?)";
            String sql2 = "Select * from Tarefa where id = "
                   + "(Select max(id) from Tarefa) ";

            try{                
                conn = Conexao.conexao();
                pst = conn.prepareStatement(sql);
                //nome
                pst.setString(1, tarefa.getNome());
                //importância(int:1,2,3,4)
                 pst.setInt(2, tarefa.getImportancia());
                //descrição(string)
                pst.setString(3, tarefa.getDescricao());
                //dataIn(string
                pst.setDate(4, java.sql.Date.valueOf(tarefa.getDataIni()));
                //dataFin(string
                pst.setDate(5,java.sql.Date.valueOf(tarefa.getDataFim()));
                //responsavelId(int
                pst.setInt(6, tarefa.getResponsavel());
                //sessaoId (int: 1,2,3,4)
                pst.setInt(7, tarefa.getSessao());
               // pst.setInt(7, tarefa.getSessao().getTipo());
                //projetoId ()
                pst.setInt(8, tarefa.getProjeto());

                pst.execute();
                
              pst = conn.prepareStatement(sql2);
              rst = pst.executeQuery();
              if(rst.next()){
                  tarefa.setId(rst.getInt("id"));
              }
                

            }catch(Exception e){
                System.out.println("Erro DAO" + e);
            }finally{
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
           return tarefa;
        }
    
     public Tarefa buscar(int id){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        String sql = "Select * from Tarefa where"
                   + " id = ?";
        Tarefa p = new Tarefa();
        try{
           conn = Conexao.conexao();
           pst = conn.prepareStatement(sql);
           pst.setInt(1,id );
           rst = pst.executeQuery();
           if(rst.next()){
                p.setId(rst.getInt("id"));
                p.setNome(rst.getString("nome"));
                p.setDescricao(rst.getString("descricao"));
                p.setDataIni(rst.getDate("dataIn").toLocalDate());
                p.setDataFim(rst.getDate("dataFin").toLocalDate());
                p.setImportancia(rst.getInt("importancia"));
                p.setResponsavel(rst.getInt("responsavelId"));
                // Sessão e projeto tem que implementar a busca no ProjetoDAO e SessaoDAO
                p.setSessao(rst.getInt("sessaoId"));
                p.setProjeto(rst.getInt("projetoId"));
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
    public void alterar(Tarefa tarefa){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        String sql = "UPDATE Tarefa SET nome = ?, importancia = ?, descricao = ?,"
                   + "dataIn = ?, dataFin = ?,responsavelId = ?," + "sessaoId = ?,projetoId = ?"
                   + " WHERE id = ?";
        
        try{
           conn = Conexao.conexao();
           pst = conn.prepareStatement(sql);
           //nome
           pst.setString(1, tarefa.getNome());
           //importância(int:1,2,3,4)
           pst.setInt(2, tarefa.getImportancia());
           //descrição(string)
           pst.setString(3, tarefa.getDescricao());
           //dataIn(string
           pst.setDate(4, java.sql.Date.valueOf(tarefa.getDataIni()));
           //dataFin(string
           pst.setDate(5, java.sql.Date.valueOf(tarefa.getDataIni()));
           //responsavelId(int
           pst.setInt(6, tarefa.getResponsavel());
           //sessaoId (int: 1,2,3,4)
           pst.setInt(7, tarefa.getSessao());
           //pst.setInt(7, tarefa.getSessao().getTipo());
           //projetoId ()
           pst.setInt(8, tarefa.getProjeto());
           pst.setInt(9, tarefa.getId());
           pst.execute();
           
        }
        catch(Exception e){
            System.out.println("Errooo TarefaDAO" + e);
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
    
    public Tarefa deleta(Tarefa tarefa){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        String sql = "DELETE FROM Tarefa  WHERE id = ?";
        try{
           if(buscar(tarefa.getId()) != null ){
                conn = Conexao.conexao();
                pst = conn.prepareStatement(sql);
                pst.setInt(1,tarefa.getId());
                pst.execute();
           }else{
               return null;
           }
        }
        catch(Exception e){
            System.out.println("Erro TarefaDAO" + e);
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
        return tarefa;
    }
    //Retorna todas as tarefas de um desenvolvedor em determinada sessão
    // int id = id do dev e sessao = 1(fazer),2(fazendo),3(feita),4(concluida)
    public ArrayList<Tarefa> buscar_Dev_Sessao(int id, int sessao){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        String sql = "Select * from Tarefa where responsavelId = ? "
                   + "and sessaoId =? ";
        ArrayList<Tarefa> lista = new ArrayList<Tarefa>();
        try{
           conn = Conexao.conexao();
           pst = conn.prepareStatement(sql);
           pst.setInt(1, id);
           pst.setInt(2,sessao);
           rst = pst.executeQuery();
           while(rst.next()){
                Tarefa p = new Tarefa();
                p.setId(rst.getInt("id"));
                p.setNome(rst.getString("nome"));
                p.setDescricao(rst.getString("descricao"));
                p.setDataIni(rst.getDate("dataIn").toLocalDate());
                p.setDataFim(rst.getDate("dataFin").toLocalDate());
                p.setImportancia(rst.getInt("importancia"));
                // Sessão e projeto tem que implementar a busca no ProjetoDAO e SessaoDAO
                p.setSessao(rst.getInt("sessaoId"));
                p.setProjeto(rst.getInt("projetoId"));
                lista.add(p);
           }
           return lista;
            
        }
        catch(Exception e){
            System.out.println("Erro TarefaDAO" + e);
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
    
    public ArrayList<Tarefa> buscar_Sessao(int sessao){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        String sql = "Select * from Tarefa where  sessaoId =? ";
        ArrayList<Tarefa> lista = new ArrayList<Tarefa>();
        try{
           conn = Conexao.conexao();
           pst = conn.prepareStatement(sql);
           pst.setInt(1,sessao);
           rst = pst.executeQuery();
           while(rst.next()){
                Tarefa tar = new Tarefa();
                tar.setId(rst.getInt("id"));
                tar.setNome(rst.getString("nome"));
                tar.setDescricao(rst.getString("descricao"));
                tar.setDataIni(rst.getDate("dataIn").toLocalDate());
                tar.setDataFim(rst.getDate("dataFin").toLocalDate());
                tar.setImportancia(rst.getInt("importancia"));
                
                tar.setSessao(rst.getInt("sessaoId"));
                tar.setProjeto(rst.getInt("projetoId"));
                lista.add(tar);
           }
           return lista;
            
        }
        catch(Exception e){
            System.out.println("Erro TarefaDAO" + e);
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
    private java.sql.Date ConverterDataParaSql(Date dataOriginal){
        java.sql.Date sqlDate = new java.sql.Date(dataOriginal.getTime());
        return sqlDate;
    }
    
    private Date ConverterDataParaDate(java.sql.Date dataOriginal){
        Date dataConvertida = new Date (dataOriginal.getTime());
        return dataConvertida;
    }
    
}