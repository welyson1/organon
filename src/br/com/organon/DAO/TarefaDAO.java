package br.com.organon.DAO;
import br.com.organon.classes.Conexao;
import java.sql.*;
import java.util.Date;
public class TarefaDAO {
       
        
        public void criar_Tarefa(){
            
            Connection conn = null;
            PreparedStatement pst = null;
            ResultSet rst = null;

            conn = Conexao.conexao();
            try{                
                String sql = "Insert into Tarefa (nome, importancia, descricao,"
                        +                         "dataIn, dataFin,responsavelId"
                        +                         ",sessaoId,projetoId)"
                        + " values  (?,?,?,?,?,?,?,?)";
                Date d= new Date();
                pst = conn.prepareStatement(sql);
                //nome
                pst.setString(1, "Motomami");
                //importância(int:1,2,3,4)
                 pst.setInt(2, 1);
                //descrição(string)
                pst.setString(3, "lalaland");
                //dataIn(string
                pst.setDate(4, java.sql.Date.valueOf("2022-12-03"));
                //dataFin(string
                pst.setDate(5, java.sql.Date.valueOf("2022-12-03"));
                //responsavelId(int
                pst.setInt(6, 0 );
                //sessaoId (int: 1,2,3,4)
                pst.setInt(7, 1);
                //projetoId ()
                pst.setInt(8, 1);

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
            
        }
}
