package br.com.organon.model;
import java.sql.*;

public class Conexao {
        
        /*private static final String driver = "com.mysql.cj.jdbc.Driver";
        private static final String url = "jdbc:db.vaurghltkbqbntucywaz.supabase.co:5432";
        private static final String user = "postgres";
        private static final String pass = "u3xCXdhD5C";
        //Senha ofvBQ2Nc9kWS1td4*/
        private static final String url = "jdbc:postgresql://db.vaurghltkbqbntucywaz.supabase.co:5432/postgres?user=postgres&password=ofvBQ2Nc9kWS1td4";
    
        public static Connection conexao(){
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url);
                // fazer operações no banco de dados
            } catch (SQLException e) {
                // tratar exceção
            }
            return conn;
        }
           /* Connection  conn = null;
            try{
                Class.forName(driver);
                conn = DriverManager.getConnection(url,user,pass);
                }catch(Exception e){
                System.out.println("Erro Conexao" + e);
             }
           return conn;
        }*/
         public static void main (String args[]) throws SQLException{
             Connection conexaoTst = conexao();
             if(conexaoTst!=null){
                 System.out.println("Conexão estabelecida");
                 conexaoTst.close();
             }else{
                 System.out.println("Conexão falha");
             }
         }
}
