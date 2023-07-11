package com.eukolos.mail2operator.service;

import com.eukolos.mail2operator.model.InvoiceElements;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;


    public void sendMailWithAttachment(InvoiceElements elements, MultipartFile pdf) {
        // Creating a mime message
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {

            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(sender);
            mimeMessageHelper.setText(elements.toString());
            mimeMessageHelper.setSubject("deneme");

            mimeMessageHelper.addAttachment(
                    Objects.requireNonNull(pdf.getOriginalFilename()), pdf);

            // Sending the mail
            javaMailSender.send(mimeMessage);
        }

        // Catch block to handle MessagingException
        catch (MessagingException e) {

            // Display message when exception occurred
        }
    }


}
