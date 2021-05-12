package com.example.bb.web;

import com.example.bb.domain.User;
import com.example.bb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import static java.lang.System.getenv;

@RestController
@RequestMapping("/api")
public class EmailController {
    @Autowired
    UserRepository userRepository;

    private String makeMessageContent(String eventType, User user) {
        String msg = "";

        switch(eventType) {
            case "newUser":
                System.out.println("Type is " + eventType);
                msg = String.format("hey, %s, account for you is created! go and check " +
                        "with following credentials: "  +
                        "<br/>username: %s <br/>password: %s", user.getUsername(),
                        user.getUsername(), user.getPassword());
                break;
            case "updUser":
                msg = String.format("hey, %s, your account was updated, " +
                "go and check it", user.getUsername());
                break;
            case "delUser":
                msg = "your account was deleted, he-he";
                break;
            default:
                msg = "sorry, something went wrong";
        }

        return msg;
    };

    private void sendmail(String address, String msgText)
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
        messageBodyPart.setContent(msgText, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
//        MimeBodyPart attachPart = new MimeBodyPart();
//        attachPart.attachFile("path-to-file");
//        multipart.addBodyPart(attachPart);
        msg.setContent(multipart);
        Transport.send(msg);
    }

    @PostMapping("/send-email/{eventType}")
    public String sendEmail(@PathVariable("eventType") String eventType, @RequestBody User user)
            throws MessagingException, IOException {
//        System.out.println("event type " + eventType);
//        System.out.println("user " + user.getUsername());
        String msgText = makeMessageContent(eventType, user);
        sendmail(user.getEmail(), msgText);
        return "Email sent successfully";
    }
}