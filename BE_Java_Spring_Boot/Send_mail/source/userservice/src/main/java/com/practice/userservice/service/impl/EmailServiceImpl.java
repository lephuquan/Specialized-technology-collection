package com.practice.userservice.service.impl;

import com.practice.userservice.service.EmaiService;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.BodyPart;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.util.Map;

import static com.practice.userservice.utils.EmailUtils.getEmailMessage;
import static com.practice.userservice.utils.EmailUtils.getVerificationUrl;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmaiService {

    public static final String NEW_USER_ACCOUNT_VERIFICATION = "New User Account verification";
    public static final String UTF_8_ENCODING = "UTF-8";
    public static final String EMAIL_TEMPLATE = "emailtemplate";
    public static final String TEXT_HTML_ENCODING = "text/html";

    @Value("${spring.mail.verify.host}")
    private String host;

    @Value("${spring.mail.username}")
    private String fromEmail;

    private final JavaMailSender emailSender;

    private final TemplateEngine templateEngine;

    @Override
    @Async
    public void sendSimpleMailMessage(String name, String to, String token) { // Chỉ gửi link
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setText(getEmailMessage(name, host, token));
            emailSender.send(message);

        } catch (Exception exception){
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }

    }

    @Override
    @Async
    public void sendMineMessageWithAttachments(String name, String to, String token) {// Gửi ảnh và file(word)

        try {
            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
            helper.setPriority(1); // Ưu tiên chạy đầu đối với đa luồng
            helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setText(getEmailMessage(name, host, token));
            //Add attachments
            // Nguyên mẫu ko sử dụng được: FileSystemResource fort = new FileSystemResource(new File(System.getProperty("user.home") + "/Downloads/images/fort.jpg"));
            FileSystemResource culture = new FileSystemResource(new File("E:/Main/Specialized_Technology_Collection/BE_Java_Spring_Boot/Send_mail/source_files/culture.jpg"));
            FileSystemResource phone_image = new FileSystemResource(new File("E:/Main/Specialized_Technology_Collection/BE_Java_Spring_Boot/Send_mail/source_files/phone_image.jpg"));
            FileSystemResource words = new FileSystemResource(new File("E:/Main/Specialized_Technology_Collection/BE_Java_Spring_Boot/Send_mail/source_files/Words.docx"));
            helper.addAttachment(culture.getFilename(), culture);
            helper.addAttachment(phone_image.getFilename(), phone_image);
            helper.addAttachment(words.getFilename(), words);

            emailSender.send(message);

        } catch (Exception exception){
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }

    }

    @Override
    @Async
    public void sendMineMessageWithEmbeddedFiles(String name, String to, String token) { // nhúng vào lội dung mai: ảnh và file word không xác định -> không nhúng được trong mail nhưng đc ở outlook
        try {
            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
            helper.setPriority(1); // Ưu tiên chạy đầu đối với đa luồng
            helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setText(getEmailMessage(name, host, token));

            //Add attachments
            FileSystemResource culture = new FileSystemResource(new File("E:/Main/Specialized_Technology_Collection/BE_Java_Spring_Boot/Send_mail/source_files/culture.jpg"));
            FileSystemResource phone_image = new FileSystemResource(new File("E:/Main/Specialized_Technology_Collection/BE_Java_Spring_Boot/Send_mail/source_files/phone_image.jpg"));
            FileSystemResource words = new FileSystemResource(new File("E:/Main/Specialized_Technology_Collection/BE_Java_Spring_Boot/Send_mail/source_files/Words.docx"));

            helper.addInline(getContentId(culture.getFilename()), culture);
            helper.addInline(getContentId(phone_image.getFilename()), phone_image);
            helper.addInline(getContentId(words.getFilename()), words);

            emailSender.send(message);

        } catch (Exception exception){
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    @Async
    public void sendHtmlEmail(String name, String to, String token) {
        try {
            Context context = new Context(); // Context của thư viện thymeleaf
//            context.setVariable("name", name);
//            context.setVariable("url", getVerificationUrl(host, token));
            context.setVariables(Map.of("name", name, "url", getVerificationUrl(host, token))); // Rút gọn
            String text = templateEngine.process(EMAIL_TEMPLATE, context);
            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
            helper.setPriority(1); // Ưu tiên chạy đầu đối với đa luồng
            helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setText(text,true);

            //Add attachments
            FileSystemResource culture = new FileSystemResource(new File("E:/Main/Specialized_Technology_Collection/BE_Java_Spring_Boot/Send_mail/source_files/culture.jpg"));
            FileSystemResource phone_image = new FileSystemResource(new File("E:/Main/Specialized_Technology_Collection/BE_Java_Spring_Boot/Send_mail/source_files/phone_image.jpg"));
            FileSystemResource words = new FileSystemResource(new File("E:/Main/Specialized_Technology_Collection/BE_Java_Spring_Boot/Send_mail/source_files/Words.docx"));

            helper.addInline(getContentId(culture.getFilename()), culture);
            helper.addInline(getContentId(phone_image.getFilename()), phone_image);
            helper.addInline(getContentId(words.getFilename()), words);

            emailSender.send(message);

        } catch (Exception exception){
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    @Async
    public void sendHtmlEmailWithEmbeddedFiles(String name, String to, String token) {
        try {
            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
            helper.setPriority(1);
            helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            helper.setFrom(fromEmail);
            helper.setTo(to);
//            helper.setText(text, true);
            Context context = new Context();
            context.setVariables(Map.of("name", name, "url", getVerificationUrl(host, token)));
            String text = templateEngine.process(EMAIL_TEMPLATE, context);

            // Add HTML email body
            MimeMultipart mimeMultipart = new MimeMultipart("related");
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(text, TEXT_HTML_ENCODING);
            mimeMultipart.addBodyPart(messageBodyPart);

            //Add images to the email body
            BodyPart imageBodyPart = new MimeBodyPart();
            DataSource dataSource = new FileDataSource("D:/Personalized/Specialized-technology-collection/BE_Java_Spring_Boot/Send_mail/source_files/phone_image.jpg");
            imageBodyPart.setDataHandler(new DataHandler(dataSource));
            imageBodyPart.setHeader("Content-ID", "image");
            mimeMultipart.addBodyPart(imageBodyPart);
            message.setContent(mimeMultipart); // Bản gốc của tác giả thiếu dòng này

            emailSender.send(message);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }

    }


    private MimeMessage getMimeMessage() {
        return emailSender.createMimeMessage();
    }

    private String getContentId(String fileName){
        return "<" + fileName + ">";
    }
}
