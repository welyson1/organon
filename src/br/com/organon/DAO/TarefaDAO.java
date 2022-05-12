package br.com.organon.DAO;
import java.text.*;

import br.com.organon.classes.Conexao;
import br.com.organon.classes.Projeto;
import br.com.organon.classes.Tarefa;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class TarefaDAO {
       
        
        public void criar_Tarefa(Tarefa tarefa){
            
            Connection conn = null;
            PreparedStatement pst = null;
            ResultSet rst = null;

            String sql = "Insert into Tarefa (nome, importancia, descricao,"
                        +                         "dataIn, dataFin,responsavelId"
                        +                         ",sessaoId,projetoId)"
                        + " values  (?,?,?,?,?,?,?,?)";
                Date d= new Date();

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
                pst.setDate(4, ConverterDataParaSql(tarefa.getDataIni()));
                //dataFin(string
                pst.setDate(5,ConverterDataParaSql(tarefa.getDataFim()));
                //responsavelId(int
                pst.setInt(6, tarefa.getResponsavel());
                //sessaoId (int: 1,2,3,4)
                pst.setInt(7, tarefa.getSessao());
               // pst.setInt(7, tarefa.getSessao().getTipo());
                //projetoId ()
                pst.setInt(8, tarefa.getProjeto());

                pst.execute();
                

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
           // return tarefa;
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
                p.setDataIni(ConverterDataParaDate(rst.getDate("dataIn")));
                p.setDataFim(ConverterDataParaDate(rst.getDate("dataFin")));
                p.setImportancia(rst.getInt("importancia"));
                // Sessão e projeto tem que implementar a busca no ProjetoDAO e SessaoDAO
                p.setSessao(rst.getInt("sessao"));
                p.setProjeto(rst.getInt("projeto"));
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
        String sql = "UPDATE Projeto SET nome= ?, importancia = ?, descricao = ?,"
                        +                         "dataIn = ?, dataFin = ?,responsavelId = ?,"
                        +                         ",sessaoId = ?,projetoId = ?"
                        + "WHERE id = ?";
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
                pst.setDate(4, ConverterDataParaSql(tarefa.getDataIni()));
                //dataFin(string
                pst.setDate(5, ConverterDataParaSql(tarefa.getDataIni()));
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
