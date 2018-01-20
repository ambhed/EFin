
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Email2SMS
{
public static void email2SMS(String coin, double perIncreased, int timevalue)
{    
    // Sender's email ID needs to be mentioned
     String from = "bharanisms18@gmail.com";
     String pass ="Testbharanisms18";
    // Recipient's email ID needs to be mentioned.
   String to = "9147277023@txt.att.net";

   String host = "smtp.gmail.com";

   // Get system properties
   Properties properties = System.getProperties();
   // Setup mail server
   properties.put("mail.smtp.starttls.enable", "true");
   properties.put("mail.smtp.host", host);
   properties.put("mail.smtp.user", from);
   properties.put("mail.smtp.password", pass);
   properties.put("mail.smtp.port", "587");
   properties.put("mail.smtp.auth", "true");

   // Get the default Session object.
   Session session = Session.getDefaultInstance(properties);

   try{
      // Create a default MimeMessage object.
      MimeMessage message = new MimeMessage(session);

      // Set From: header field of the header.
      message.setFrom(new InternetAddress(from));

      // Set To: header field of the header.
      message.addRecipient(Message.RecipientType.TO,
                               new InternetAddress(to));

      // Set Subject: header field
      message.setSubject("Alert "+ coin);

      // Now set the actual message
      message.setText(perIncreased+"-"+timevalue);

      // Send message
      Transport transport = session.getTransport("smtp");
      transport.connect(host, from, pass);
      transport.sendMessage(message, message.getAllRecipients());
      transport.close();
      System.out.println("Sent message successfully....");
   }catch (MessagingException mex) {
      mex.printStackTrace();
   }
}
}