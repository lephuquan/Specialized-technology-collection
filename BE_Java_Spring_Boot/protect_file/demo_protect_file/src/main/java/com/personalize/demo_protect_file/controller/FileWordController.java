package com.personalize.demo_protect_file.controller;


import com.aspose.words.DocSaveOptions;
import com.aspose.words.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileWordController {
    private static final String SAVE_DIRECTORY = "sources/data/";

    @PostMapping("/api/process-file")
    public ResponseEntity<String> processFile(@RequestParam("file") MultipartFile file,
                                              @RequestParam("password") String password) {

        try {
            Document doc = new Document("D:\\Personalized\\IE-Eng\\SP_Part1_Ref.docx");
            DocSaveOptions docSaveOptions = new DocSaveOptions();
            docSaveOptions.setPassword("123");

            doc.save("D:\\Personalized\\IE-Eng\\SP_Part1_Ref_pw.doc", docSaveOptions);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}