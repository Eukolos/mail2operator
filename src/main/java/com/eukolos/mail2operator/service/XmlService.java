package com.eukolos.mail2operator.service;

import com.eukolos.mail2operator.model.InvoiceElements;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Service
@Slf4j
public class XmlService {

    public InvoiceElements getInvoiceElements(MultipartFile xml) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(xml.getInputStream());

            return InvoiceElements.builder()
                    .invoiceDate(document.getElementsByTagName("NUMBER").item(0).getTextContent())
                    .invoiceNumber(document.getElementsByTagName("NUMBER").item(0).getTextContent())
                    .invoiceSender(document.getElementsByTagName("SENDER_DEF").item(0).getTextContent())
                    .totalPrice(document.getElementsByTagName("TOTAL_NET").item(0).getTextContent())
                    .currency(document.getElementsByTagName("PRCURR_GLOBAL_CODE").item(0).getTextContent())
                    .build();

        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
