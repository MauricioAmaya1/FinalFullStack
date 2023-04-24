package com.proyecto.proyecto.service;

public interface EmailSenderService {

    public void sendEmail(String toEmail, String subj, String body);
}
