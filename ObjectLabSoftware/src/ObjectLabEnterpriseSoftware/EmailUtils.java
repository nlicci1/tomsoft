/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjectLabEnterpriseSoftware;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtils 
{
    private Session smtpSession = null;
    private Properties emailSessionProperties = null;
    private Message emailContent = null;
    private String username = null;
    private String password = null;
    private String emailAdrTo = null;
    private String emailMessageContent = null;
    
    private Message setEmailContents()
    {
        Message message = new MimeMessage(smtpSession);
        try 
        {
            message.setFrom(new InternetAddress(username));
            //Students Email Should go here
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAdrTo));
            message.setSubject("Towson University Object Lab: No_Reply");
            message.setText(emailMessageContent);
        } 
        catch (MessagingException ex) 
        {
            Logger.getLogger(EmailUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return message;
    }
    
    public EmailUtils(String emailAdrSend, String accountName, String pw, String emailTextMsg)
    {
        username = accountName;
        password = pw;
        emailAdrTo = emailAdrSend;
        emailMessageContent = emailTextMsg;
        
        emailSessionProperties = new Properties();
        emailSessionProperties.put("mail.smtp.starttls.enable", "true");
        emailSessionProperties.put("mail.smtp.auth", "true");
        emailSessionProperties.put("mail.smtp.host", "smtp.gmail.com");
        emailSessionProperties.put("mail.smtp.port", "587");

        smtpSession = Session.getInstance
        (
                emailSessionProperties,
                new javax.mail.Authenticator() 
                {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() 
                    {
                        return new PasswordAuthentication(username, password);
                    }
                }
        );
        
        /* This MUST be the last thing initalized since there are so many vars to init prior to setting the contents of the email */
        emailContent = setEmailContents();
    
    }
    
    public boolean send() 
    {
        try
        {
            Transport.send(emailContent);
        }
        catch (SendFailedException send_fail_to_recipient)
        {
            Logger.getLogger(EmailUtils.class.getName()).log(Level.SEVERE, null, send_fail_to_recipient);
            return false;
        }
        catch (MessagingException ex) 
        {
            Logger.getLogger(EmailUtils.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    /*
    //Sends an email with the header and message thats passed in to the supplied email
    public void sendWithHeaderAndBody(String messageText, String header, String Email)
    {
        //TODO: send message based on string and saved data
        final String username = "TowsonuObjectLab@gmail.com";
        final String password = "oblabsoftware";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
     
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }});
        
            Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(username));
            //Students Email Should go here
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            
            message.setSubject("Towson University Object Lab: " + header);
            
            message.setText(messageText);
            
            Transport.send(message);
            System.out.println("Email Sent");
        } catch (MessagingException ex) {
            Logger.getLogger(EmailUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Sends an email about a file submitted
    public void sendAboutFile(String messageText, String fileName)
    {
        //TODO: send message based on string and saved data
        final String username = "TowsonuObjectLab@gmail.com";
        final String password = "oblabsoftware";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
     
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }});
        
            Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(username));
            //Students Email Should go here
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            
            message.setSubject("Towson University Object Lab: No_Reply");
            
            message.setText("Dear " + fName +" "+lName + " , \n\nAfter analyzing your file submission, "
                    + fileName + "\n\n" + messageText);
            
            Transport.send(message);
            System.out.println("Email Sent");
        } catch (MessagingException ex) {
            Logger.getLogger(EmailUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    */
}
