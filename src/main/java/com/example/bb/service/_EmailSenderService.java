//package com.example.bb.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//
//
//// not in use
//@Service("emailSenderService")
//public class _EmailSenderService {
//
//    private JavaMailSender javaMailSender;
//
//    @Autowired
//    public _EmailSenderService(JavaMailSender javaMailSender) {
//        this.javaMailSender = javaMailSender;
//    }
//
//    @Async
//    public void sendEmail(SimpleMailMessage email) {
//        javaMailSender.send(email);
//    }
//}
