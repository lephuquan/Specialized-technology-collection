package com.personalize.demo_protect_file.controller;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Objects;

@RestController
@RequestMapping("/files")
public class FilePdfController {

    private final String uploadDirectory = "src/main/resources/data/";

    @PostMapping("/upload-pdf")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("password") String password) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file to upload.");
        }

        try {
            byte[] bytes = file.getBytes();
            File uploadFile = new File(uploadDirectory + file.getOriginalFilename());
            FileUtils.writeByteArrayToFile(uploadFile, bytes);

            // Encrypt file if it's PDF or Excel
            if (Objects.requireNonNull(file.getContentType()).equals("application/pdf") || file.getContentType().equals("application/vnd.ms-excel")) {
                encryptFile(uploadFile, password);
            }

            return ResponseEntity.ok("File uploaded successfully!");
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
        }
    }

    private void encryptFile(File file, String password) throws IOException, GeneralSecurityException {
        if (file.getName().endsWith(".pdf")) {
            PDDocument document = PDDocument.load(file);
            AccessPermission ap = new AccessPermission();
            StandardProtectionPolicy spp = new StandardProtectionPolicy(password, password, ap);
            document.protect(spp);
            document.save(file);
            document.close();
        }
    }
}
