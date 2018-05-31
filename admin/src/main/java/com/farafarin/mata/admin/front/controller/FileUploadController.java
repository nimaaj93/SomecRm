package com.farafarin.mata.admin.front.controller;

import com.farafarin.mata.common.service.FileUploadService;
//import com.farafarin.mata.common.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by Asus on 5/19/2018.
 */

@RestController
@RequestMapping("ns/fileupload")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;
//    @Autowired
//    private MailService mailService;
//
//    @RequestMapping(value = "/test",method = RequestMethod.GET)
//    public String test() {
//        mailService.sendEmail("mzavargadim@gmail.com",
//                "mzavarghadim@yahoo.com",
//                "Testing123",
//                "Testing only \n\n Hello Spring Email Sender");
//        return "ok";
//    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void uploadFile(@RequestParam("file") MultipartFile file,RedirectAttributes redirectAttributes){
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
        }
//        fileUploadService.saveFile(file);
    }
}
