package com.example.bb.web;

import com.example.bb.domain.User;
import com.example.bb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @Autowired
    UserRepository userRepository;

    private void sendmail(String address, String username, String password)
            throws AddressException, MessagingException, IOException {
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
        msg.setFrom(new InternetAddress(getenv("EMAIL_ADDRESS"), false));


        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address));
        msg.setSubject("BB service - Account info");
        msg.setContent("BB email", "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        String msgText = String.format("your account was updated, " +
                "check with the following credentials: " +
                "<br/>username: %s <br/>password: %s", username, password);
        messageBodyPart.setContent(msgText, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
//        MimeBodyPart attachPart = new MimeBodyPart();
//        attachPart.attachFile("path-to-file");
//        multipart.addBodyPart(attachPart);
        msg.setContent(multipart);
        Transport.send(msg);
    }

    @PostMapping("/send-email")
    public String sendEmail(@RequestBody User user) throws MessagingException, IOException {
        sendmail(user.getEmail(), user.getUsername(), user.getPassword());
        return "Email sent successfully";
    }
}