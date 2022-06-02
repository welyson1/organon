package br.com.organon.model;


import br.com.organon.model.Gestor;
import br.com.organon.model.Mensagem;
import java.util.ArrayList;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

public class MensagemDAO {
    
    
    public static void enviar(Gestor gestor,Mensagem mensagem){
        
        try{
            //Definição do email e senha da conta 
            String accountEmail = gestor.getEmail();
            String accountSenha = gestor.getSenha();
            
            Properties prop = new Properties();
            prop.put("mail.smtp.auth", "true"); //Ativa autenticação
            prop.put("mail.smtp.starttls.enable", "true");//Ativa criptografia
            prop.put("mail.smtp.host", "smtp.office365.com");//Indica servidor smtp
            prop.put("mail.smtp.port", "587");//Indica porta

            //Autenticação usando sobrescrita
            Session sec = Session.getInstance(prop, new Authenticator(){
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(accountEmail,accountSenha);
                }

            });

            Message mens = prepararMensagem(sec, mensagem, accountEmail);
            Transport.send(mens);   
            }catch(Exception e){
                System.out.println(e);
            }
    }
    
    //Atribui os campos básicos da mensagem
    public static Message prepararMensagem(Session sec, Mensagem mens, String accountEmail){
        try{
            Message mensagem = new MimeMessage(sec);
            mensagem.setFrom(new InternetAddress(accountEmail));
            mensagem.setRecipients(Message.RecipientType.TO, prepararDestino(mens.getDestEmail()));
            mensagem.setSubject(mens.getTitulo());
            mensagem.setText(mens.getConteudo());
            
            return mensagem;
            
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
        
    }
    public static InternetAddress[] prepararDestino(ArrayList<String> destino){
        try{
            InternetAddress[] desList = new InternetAddress[destino.size()];
            for(int i = 0;i<destino.size();i++){
               desList[i] = new InternetAddress(destino.get(i));
               
            }
            return desList;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    
}
