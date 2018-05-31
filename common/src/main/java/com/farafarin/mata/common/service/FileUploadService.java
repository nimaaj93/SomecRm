package com.farafarin.mata.common.service;

import com.farafarin.mata.common.model.UserOrderModel;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created by Asus on 5/19/2018.
 */

public interface FileUploadService {
    void saveFile(MultipartFile file, String parent);
    File getFile(Long orderId, String fileName);
}
