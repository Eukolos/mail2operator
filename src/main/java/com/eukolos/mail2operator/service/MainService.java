package com.eukolos.mail2operator.service;

import com.eukolos.mail2operator.model.InvoiceElements;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MainService {
    private final XmlService xmlService;
    private final MailService mailService;
    public void sendMail(MultipartFile pdf, MultipartFile xml) {
        InvoiceElements elements = xmlService.getInvoiceElements(xml);
        mailService.sendMailWithAttachment(elements, pdf);
    }
}
