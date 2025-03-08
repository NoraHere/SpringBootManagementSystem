package com.practicen.controller;

import com.aliyuncs.exceptions.ClientException;
import com.practicen.pojo.Result;
import com.practicen.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/upload")
    public Result upload(@RequestParam("image") MultipartFile file) throws IOException, ClientException {
        log.info("upload image: {}",file);
        String url = aliOSSUtils.upload(file);
        return Result.success(url);
    }

//    @PostMapping("/upload")
//    public Result upload(String username, Integer age, @RequestParam("image") MultipartFile file) throws IOException {
//        log.info("upload image: {}, {}, {}", username, age, file);
//        String originalFilename = file.getOriginalFilename();
//
//        // unique file name
//        String extname = originalFilename.substring(originalFilename.lastIndexOf(".")); //extension name
//        String newFilename = UUID.randomUUID().toString() + extname;
//
//        // save to local
//        file.transferTo(new File("C:\\Users\\mojbw\\images\\"+newFilename));
//        return Result.success();
//    }

//    public Result upload(String username, Integer age, MultipartFile image) { //Directly comply image with form name(image)
//    }

}
