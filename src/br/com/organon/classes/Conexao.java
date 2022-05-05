package br.com.organon.classes;
import java.sql.*;

public class Conexao {
        
        private static final String driver = "com.mysql.cj.jdbc.Driver";
        private static final String url = "jdbc:mysql://sql3.freesqldatabase.com:3306/sql3488733";
        private static final String user = "sql3488733";
        private static final String pass = "NJc9ZsEBdK";
    
        public static Connection conexao(){
            Connection  conn = null;
            try{
                Class.forName(driver);
                conn = DriverManager.getConnection(url,user,pass);
                }catch(Exception e){
                System.out.println("Erro Conexao" + e);
             }
           return conn;
        }
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
