package com.example.demo.service.Impl;
import com.example.demo.service.EmailService;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Override
    public void sendEmail(String to, String subject, String body) {
        // JavaMailSender integration later
        System.out.println("Email sent to " + to);
    }
}
