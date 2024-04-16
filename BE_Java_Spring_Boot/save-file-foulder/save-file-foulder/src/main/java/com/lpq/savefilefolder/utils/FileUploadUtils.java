package com.lpq.savefilefolder.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Component
public class FileUploadUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUploadUtils.class);

    public String storeFile(String outputPath, MultipartFile multipartFile) {
        logger.info("writeFile method with outPut path = {}/{}", outputPath, multipartFile.getOriginalFilename());

        try {
            Path path = Paths.get(outputPath).toAbsolutePath().normalize();
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            Path targetLocation = path.resolve(fileName);
            int dataIndex = targetLocation.toString().indexOf("data");
            String absolutePath = targetLocation.toString().substring(dataIndex);

            InputStream inputStream = multipartFile.getInputStream();
            Files.copy(inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
            inputStream.close();

            return absolutePath;
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file. Please try again.", ex);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public void deleteFileNotUsing(String fileUrl) {
        Path path = Paths.get(fileUrl).toAbsolutePath().normalize();
        try {
            Files.delete(path);
        } catch (IOException e) {
            logger.warn("Cannot delete file!");
        }
    }

}
