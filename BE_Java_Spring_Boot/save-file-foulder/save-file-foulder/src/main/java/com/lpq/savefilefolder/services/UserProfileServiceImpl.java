package com.lpq.savefilefolder.services;

import com.lpq.savefilefolder.entities.RelatedFile;
import com.lpq.savefilefolder.entities.UserProfile;
import com.lpq.savefilefolder.payload.UserProfileRequest;
import com.lpq.savefilefolder.repositories.RelatedFileRepository;
import com.lpq.savefilefolder.repositories.UserProfileRepository;
import com.lpq.savefilefolder.utils.DateConverter;
import com.lpq.savefilefolder.utils.FileUploadUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;

import org.modelmapper.ModelMapper;


@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements IUserProfileService {

    private final UserProfileRepository userProfileRepository;

    private final FileUploadUtils fileUploadUtil;

    private final RelatedFileRepository relatedFileRepository;

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public String createUserProfile(UserProfileRequest userProfileRequest) {
        ModelMapper mapper = new ModelMapper();
        try {
            UserProfile userProfile = mapper.map(userProfileRequest, UserProfile.class);
            Set<RelatedFile> relatedFiles = new HashSet<>();
            writeRelatedFile(relatedFiles, userProfileRequest, userProfile);
            userProfile.setRelatedFiles(relatedFiles);
            userProfileRepository.save(userProfile);
            return "Create user success";
        }
        catch (Exception e){
            return "Create fail";
        }


    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public String updateUserProfile(UserProfileRequest userProfileRequest, Long id) {

        try{
            UserProfile model = userProfileRepository.findById(id).orElseThrow();

            Set<RelatedFile> currentRelatedFiles = model.getRelatedFiles(); // get file from db

            List<String> fileUrls = new ArrayList<>(); // contain url file from db
            for (RelatedFile relatedFile : currentRelatedFiles){
                fileUrls.add(relatedFile.getFileUrl());
            }
            relatedFileRepository.deleteByUserProfile(model); //delete file object of User

            Set<RelatedFile> newRelatedFiles = new HashSet<>();
            writeRelatedFile(newRelatedFiles, userProfileRequest, model);  // process save file to folder

            List<String> newFileUrls = new ArrayList<>();
            for (RelatedFile relatedFile : newRelatedFiles){ // save new file's url
                newFileUrls.add(relatedFile.getFileUrl()); // !luu tat ca url file moi vao list
            }
            fileUrls.removeAll(newFileUrls);//ex: newFileUrls(a, b) and fileUrls(a, c) -> fileUrls.removeAll(newFileUrls) -> fileUrls(c) - sau khi update file nay can bo di
            FileUploadUtils storeFileUtil = new FileUploadUtils();
            String rootPath = "src/main/resources/";
            for (String fileUrl : fileUrls) { // !remove all roi ko con phan tu de lay ra dung ko
                storeFileUtil.deleteFileNotUsing(rootPath + fileUrl); // tim file trong thu muc de xoa
            }

            model.setRelatedFiles(newRelatedFiles);
            return "Update successfully";
        }catch (Exception e){
            return "Update fail";
        }

    }

    private void writeRelatedFile(Set<RelatedFile> relatedFileSet, UserProfileRequest request, UserProfile model){
        String outPutPath = "src/main/resources/data/";
        if (Objects.nonNull(request.getRelatedFiles())) {
            for (MultipartFile multiPartFile : request.getRelatedFiles()){
                if(!multiPartFile.isEmpty()){
                    RelatedFile relatedFile = new RelatedFile();
                    relatedFile.setFileName(multiPartFile.getOriginalFilename());
                    relatedFile.setFileUrl(fileUploadUtil.storeFile(outPutPath + DateConverter.convertLocalDateTimeToString(LocalDateTime.now(), "yyyy-MM-dd") + "/",
                            multiPartFile)); // Store the file and get the URL

                    relatedFileSet.add(relatedFile);
                    relatedFile.setUserProfile(model);
                }
            }
        }
    }
}
