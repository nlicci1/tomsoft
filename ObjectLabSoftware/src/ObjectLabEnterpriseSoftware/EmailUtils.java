/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjectLabEnterpriseSoftware;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Miguel
 */
public class EmailUtils {
    
    private static String fName;
    private static String lName;
    private static String Error;
    private static String FileName;
    private static String Ident;
    private static String dateSubmitted;
    private static String TuId;
    private static String message;
    private static String email;

    public EmailUtils(String fname, String lname, String file, String date){
        fName = fname;
        lName = lname;
        FileName = file;
        dateSubmitted = date;
    
    }

    public EmailUtils(String fname, String lname, String Err, String file, String ID) {
        fName = fname;
        lName = lname;
        Error = Err;
        FileName = file;
        Ident = ID;
    }
    
    //Accepts TUID and the file which the email is about
    public EmailUtils(String tuId){
        
        //TODO: get data from db such as email address
        TuId = tuId;
        //Get first name, last name, email
    }
    
    //Sends an email with the header and message thats passed in to the supplied email
    public void SendWithHeaderAndBody(String messageText, String header, String Email){
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
            Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Sends an email about a file submitted
    public void SendAboutFile(String messageText, String fileName){
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
                    + FileName + "\n\n" + messageText);
            
            Transport.send(message);
            System.out.println("Email Sent");
        } catch (MessagingException ex) {
            Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Send() {
        System.out.println(Ident);
        /* Will need to remove the use of DB querying in this class.
           Left this in here to preserve current working state
           commented this out as well becuase it does not exist:
            ResultSet Res = PendingJobsView.dba.searchPendingWithID(Ident);
            - Nick
        */
        SQLMethods dbconn = new SQLMethods();
        ResultSet Res = dbconn.searchPendingWithID(Ident);
        
        String Email ="";
        try {
            while(Res.next()){
                Email = Res.getString("email");
            }
            /* Close our DB connection ASAP -Nick */
            dbconn.closeDBConnection();
        } catch (SQLException ex) {
            Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("The Email is:" +Email);
        final String username = "TowsonuObjectLab@gmail.com";
        final String password = "oblabsoftware";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

            Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(username));
            //Students Email Should go here
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(Email));
            message.setSubject("Towson University Object Lab: No_Reply");
            message.setText("Dear " + fName +" "+lName + " , \n\nAfter analyzing your file submission, "
                    + FileName + ", we have found an error in your file: \n"
                    + Error + "\nPlease fix this error and resubmit."
                    + "\n\nThank you,\nObject Lab Staff");
        
            Transport.send(message);
            System.out.println("Email Sent");
        } catch (MessagingException ex) {
            Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
