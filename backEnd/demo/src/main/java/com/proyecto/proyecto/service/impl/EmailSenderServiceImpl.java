package com.proyecto.proyecto.service.impl;
import com.proyecto.proyecto.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {


    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String toEmail, String subj, String body) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("digitalbooking@hotmail.com");
        mail.setTo(toEmail);
        mail.setSubject(subj);
        mail.setText(body);
        javaMailSender.send(mail);
    }
}
