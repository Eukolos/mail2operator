package com.eukolos.mail2operator.resource;

import com.eukolos.mail2operator.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class FileResource {
    private final MainService service;


    @PostMapping("/upload")
    public ResponseEntity<String> uploadPdfFileToLocal(@RequestParam("pdf") MultipartFile pdf, @RequestParam("xml") MultipartFile xml) {
        service.sendMail(pdf,xml);
        return ResponseEntity.ok().body("Pdf send successfully");
    }
}
