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

        String messageText =
                "Murat Bey Merhaba,\n\n" +
                "EK’teki " + elements.getInvoiceDate() +" tarihindeki ve " + elements.getTotalPrice() +" " + elements.getCurrency() +" tutarındaki " + elements.getInvoiceNumber() +"NO’lu fatura hangi firma için düzenlenmiştir ve onay rica ederim.\n\n" +
                "İyi Çalışmalar;\n\n" +
                "Muhammet Emin AKSOY\n" +
                "Muhasebe Personeli\n\n" +
                "t: +90 212 671 21 02\n" +
                "f: +90 212 671 21 83\n\n" +
                "m.aksoy@rantech.com.tr\n" +
                "www.rantech.com.tr\n\n" +
                "Main Office | Merkez Ofis\n" +
                "AYZ Yedek Parça Pazarlama Ltd. Şti.\n" +
                "15 Temmuz Mah. Bahar Cad. No:73 A Blok Daire.57\n" +
                "Ark Residence Güneşli – Bağcılar / İSTANBUL\n" +
                "Tel. 0212 671 21 02  Fax. 0212 671 21 83\n";

        try {

            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(sender);
            mimeMessageHelper.addCc("n.kaya@rantech.com.tr");
            mimeMessageHelper.addCc("e.barut@rantech.com.tr");
            mimeMessageHelper.setText(messageText);
            mimeMessageHelper.setSubject(elements.getInvoiceSender() + " FATURA ONAY HK.");

            mimeMessageHelper.addAttachment(
                    elements.getInvoiceNumber()+".pdf", pdf);

            // Sending the mail
            javaMailSender.send(mimeMessage);
        }

        // Catch block to handle MessagingException
        catch (MessagingException e) {

            // Display message when exception occurred
        }
    }


}
