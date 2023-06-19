package com.example.sendingmail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class ServiceImpl implements MailService{
    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private JavaMailSender mailSender;
    @Override
    public String sendMail(MultipartFile[] file, String[] cc, String to, String subject, String text) {
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            if(cc != null){
                helper.setCc(cc);
            }
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            if(file != null){
                for(MultipartFile f : file){
                    helper.addAttachment(f.getOriginalFilename(), f);
                }
            }
            mailSender.send(message);
            return "Mail sent successfully";

        } catch (MessagingException e) {
            throw new RuntimeException("Mail sending failed: " + e.getMessage());
        }
    }
}
