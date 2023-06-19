package com.example.sendingmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MailController {
    private final MailService mailService;
    @Autowired
    public MailController(MailService mailService){
        this.mailService = mailService;
    }
    @PostMapping("/sendMail")
    public String sendMail(@RequestParam(value = "file",required = false) MultipartFile[] file, @RequestParam("cc") String[] cc, @RequestParam("to") String to, @RequestParam("subject") String subject, @RequestParam("text") String text){
        return mailService.sendMail(file, cc, to, subject, text);
    }
}
