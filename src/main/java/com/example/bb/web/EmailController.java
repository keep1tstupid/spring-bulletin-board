package com.example.bb.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import static java.lang.System.getenv;

@RestController
@RequestMapping("/api")
public class EmailController {
    private void sendmail(String address) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(getenv("EMAIL_ADDRESS"), getenv("EMAIL_PASSWORD"));
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("buul.booard@gmail.com", false));


        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address));
        msg.setSubject("Test BB email");
        msg.setContent("BB email", "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("test email content", "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
//        MimeBodyPart attachPart = new MimeBodyPart();
//        attachPart.attachFile("path-to-file");
//        multipart.addBodyPart(attachPart);
        msg.setContent(multipart);
        Transport.send(msg);
    }

    @RequestMapping(value = "/send-email")
    public String sendEmail(String address) throws MessagingException, IOException {
        sendmail(address);
        return "Email sent successfully";
    }
}