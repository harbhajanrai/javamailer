/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author harbhajan.rai
 */
import java.util.Properties;    
import java.util.Scanner;
import javax.mail.*;    
import javax.mail.internet.*;

class Mail{
    private String subject;
    private String message;

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }
}

class Mailer{
    public static void send(String from,String password,String to, Mail mail){
        
        //Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        //get Session by authenticating emailID
        Session session;
        session = Session.getDefaultInstance(
                props,new javax.mail.Authenticator(){
                    protected PasswordAuthentication getPasswordAuthentication(){
                        return new PasswordAuthentication(from,password);
                    }
                }
        );
        //compose message    
        try
        {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            
            // set subject to mail
            // String sub = "enter you subject";
            message.setSubject(mail.getSubject());
            
            // set message in mail
            // String msg = "enter body of the mail";
            message.setText(mail.getMessage(), "UTF-8");
            
            //send message
            Transport.send(message);
            System.out.println("Your Mail Sent");
        }catch (MessagingException e)
        {
            throw new RuntimeException(e);
        }    
             
    }  
}  
public class JavaEmail{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        
        //mailid which will be used to send mail
        System.out.print("Email ID for sending mail : ");
        String from = in.nextLine();
        
        //password of the mailid used to send mail
        System.out.print("Password to authenticate the email ID : ");
        String password = in.nextLine();
        
        //emailid to whom email has to be sent
        System.out.print("Email to whom mail has to be sent : ");
        String to = in.nextLine();

        // create object of mail
        Mail mail = new Mail();

        // set subject for the mail
        String subject = in.nextLine();
        mail.setSubject(subject);

        //set Message for the mail
        String message = in.nextLine();
        mail.setMessage(message);
        Mailer.send(from, password, to, mail);
    }   
} 