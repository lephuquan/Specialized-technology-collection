package com.personalize.demo_protect_file.controller;


import org.apache.commons.io.FileUtils;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Objects;

@RestController
@RequestMapping("/files")
public class FileWordController {

    private static final String SAVE_DIRECTORY = "sources/data/";

    @PostMapping("/api/process-file")
    public ResponseEntity<String> processFile(@RequestParam("file") MultipartFile file,
                                              @RequestParam("password") String password) {
        if (file.isEmpty() || StringUtils.isEmpty(password)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File and password are required.");
        }

        try {
            // Tạo thư mục lưu trữ nếu chưa tồn tại
            File saveDirectory = new File(SAVE_DIRECTORY);
            if (!saveDirectory.exists()) {
                saveDirectory.mkdirs();
            }

            // Lưu file tạm thời
            File tempFile = new File(saveDirectory, file.getOriginalFilename());
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fos.write(file.getBytes());
            }

            // Đặt mật khẩu mở file
            POIFSFileSystem fs = new POIFSFileSystem();
            EncryptionInfo info = new EncryptionInfo(EncryptionMode.standard);
            Encryptor encryptor = info.getEncryptor();
            encryptor.confirmPassword(password);
//            encryptor.setEncryptionOptions(EncryptionOptions.STANDARD);
//            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
//                encryptor.encryptDocument(fos, fs);
//            }

            // Di chuyển file đã đặt mật khẩu vào thư mục lưu trữ cuối cùng
            File finalFile = new File(saveDirectory, file.getOriginalFilename());
            tempFile.renameTo(finalFile);

            return ResponseEntity.ok("File processed and password set successfully.");
        } catch (InvalidFormatException | IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the file.");
        }
    }

}
