/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsending;
import java.io.IOException;
import java.util.Date;
import java.util.Properties; 
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;   
/**
 *
 * @author sharmeen
 */
public class email1 {
    
    public email1(String id)
    {
    
         String host = "smtp.gmail.com";        
         String port = "587";        
         String mailFrom = "jnkhan.8686@gmail.com";        
         String password = "786.junaid";         

        // message info        
        
         String mailTo = id;      
         String subject = "Your Nationality document is generated";        
         String message = "You have requested DDGUM for Nationality doc. So download from attachments";
         
          String[] attachFiles = new String[1];        
          attachFiles[0] = id+".pdf";       
          
          try {            
              sendEmailWithAttachments(host, port, mailFrom, password, mailTo,subject, message, attachFiles);            
              System.out.println("Email sent.");
              
              
              
          } catch (Exception ex) 
          {            
              System.out.println("Could not send email.");            
              ex.printStackTrace();        
          }    
    }
    
    public static void sendEmailWithAttachments(String host, String port,final String userName, final String password, String toAddress,String subject, String message, String[] attachFiles) throws AddressException, MessagingException 
    {        // sets SMTP server properties        
        Properties properties = new Properties();        
        properties.put("mail.smtp.host", host);        
        properties.put("mail.smtp.port", port);       
        properties.put("mail.smtp.auth", "true");        
        properties.put("mail.smtp.starttls.enable", "true");        
        properties.put("mail.user", userName);        
        properties.put("mail.password", password);         
// creates a new session with an authenticator        

     Authenticator auth = new Authenticator() 
     {            
         public PasswordAuthentication getPasswordAuthentication() 
         {                
             return new PasswordAuthentication(userName, password);
          }    
     };        
     Session session = Session.getInstance(properties, auth);         
// creates a new e-mail message        

     Message msg = new MimeMessage(session);         
     msg.setFrom(new InternetAddress(userName));        
     InternetAddress[] toAddresses = { new InternetAddress(toAddress) };        
     msg.setRecipients(Message.RecipientType.TO, toAddresses);        
     msg.setSubject(subject);       
     msg.setSentDate(new Date());         
// creates message part        

    MimeBodyPart messageBodyPart = new MimeBodyPart();        
    messageBodyPart.setContent(message, "text/html");         

 // creates multi-part        
 
   Multipart multipart = new MimeMultipart();        
   multipart.addBodyPart(messageBodyPart);         

  // adds attachments        
  
   if (attachFiles != null && attachFiles.length > 0) 
    {            
        for (String filePath : attachFiles) 
        {                
            MimeBodyPart attachPart = new MimeBodyPart();                 
            try {                    
                attachPart.attachFile(filePath);                
            } catch (IOException ex) 
            {                    
                ex.printStackTrace();                
            }                 
            multipart.addBodyPart(attachPart);            
        }        
    }         

    // sets the multi-part as e-mail's content        
    
     msg.setContent(multipart);         

    // sends the e-mail        
    
     Transport.send(msg);     
    }     
    
     /**     * Test sending e-mail with attachments     */    
    
    /*public static void main(String[] args) {        

         // SMTP info        
         
         String host = "smtp.gmail.com";        
         String port = "587";        
         String mailFrom = "jnkhan.8686@gmail.com";        
         String password = "786.junaid";         

        // message info        
        
         String mailTo = id;      
         String subject = "You are requist to DDGUM for doc so your doc is generated";        
         String message = "I have some attachments for you";         

        // attachments        
        
          String[] attachFiles = new String[1];        
          attachFiles[0] = "Test.txt";       
          
          try {            
              sendEmailWithAttachments(host, port, mailFrom, password, mailTo,subject, message, attachFiles);            
              System.out.println("Email sent.");        
          } catch (Exception ex) 
          {            
              System.out.println("Could not send email.");            
              ex.printStackTrace();        
          }    
    }*/
}
   
    
