package com.lpq.savefilefolder.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProfileRequest {

    private String staffName;
    private MultipartFile[] relatedFiles;
}
