package com.example.bb.web;
import com.example.bb.domain.ConfirmationToken;
import com.example.bb.domain.User;
import com.example.bb.repository.ConfirmationTokenRepository;
import com.example.bb.repository.UserRepository;
import com.example.bb.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    EmailSenderService emailSenderService;


    @GetMapping("/test")
    public String test() {
        return "testView";
    }


//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
//
//    @GetMapping("/forgotPassword")
//    public String forgotPassword(Model model, User user) {
//        model.addAttribute("user", user);
//        return "forgotPassword";
//    }
//
//    // Receive the address and send an email
//    @PostMapping("/forgotPassword")
//    public String forgotUserPassword(Model model, User user) {
//        User existingUser = userRepository.findByEmail(user.getEmail());
//        if (existingUser != null) {
//            // Create token
//            ConfirmationToken confirmationToken = new ConfirmationToken(existingUser);
//
//            // Save it
//            confirmationTokenRepository.save(confirmationToken);
//
//            // Create the email
//            SimpleMailMessage mailMessage = new SimpleMailMessage();
//            mailMessage.setTo(existingUser.getEmail());
//            mailMessage.setSubject("Complete Password Reset!");
//            mailMessage.setFrom("test-email@gmail.com");
//            mailMessage.setText("lol hi");
//
//            // Send the email
//            emailSenderService.sendEmail(mailMessage);
//
//            return "login";
//        } else {
//            return "error";
//        }
//
//    }
}
