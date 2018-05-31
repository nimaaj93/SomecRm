package com.farafarin.mata.common.service.impl;

import com.farafarin.mata.common.model.UserOrderModel;
import com.farafarin.mata.common.service.FileUploadService;
import com.farafarin.mata.common.exception.FileUploadException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Asus on 5/19/2018.
 */
@Component
public class FileUploadServiceImpl implements FileUploadService {

    private static String UPLOADED_FOLDER = "D://temp//";

    @Override
    public void saveFile(MultipartFile file, String parent) {
        try {
            Path path = Paths.get(UPLOADED_FOLDER + parent + "//" + file.getOriginalFilename());
            byte[] bytes = file.getBytes();
            Files.createDirectories(path.getParent());
            Files.write(path, bytes);
        } catch (IOException ex) {
            throw new FileUploadException(ex);
        }
    }

    @Override
    public File getFile(Long orderId, String fileName) {
        String path = UPLOADED_FOLDER + orderId.toString() + "//" + fileName;
        return new File(path);
    }
}
