package com.practice.userservice.service;

public interface EmaiService {

    void sendSimpleMailMessage(String name, String to, String token);
    void sendMineMessageWithAttachments(String name, String to, String token);
    void sendMineMessageWithEmbeddedFiles(String name, String to, String token);
    void sendHtmlEmail(String name, String to, String token);
    void sendHtmlEmailWithEmbeddedFiles(String name, String to, String token);

}
