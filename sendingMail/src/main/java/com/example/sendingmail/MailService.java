package com.example.sendingmail;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface MailService {
    String sendMail(MultipartFile[] file, String[] cc, String to, String subject, String text);
}
