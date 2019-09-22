package pers.lyks.spring.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author lawyerance
 * @version 1.0 2019-05-18
 */
@RestController
@RequestMapping(value = "/upload")
@Slf4j
public class UploadController {

    private static final String DEFAULT_UPLOAD_PATH = System.getProperty("user.dir");

    @RequestMapping(value = "/single", method = RequestMethod.POST)
    public ResponseEntity<Boolean> upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
        String fileName = file.getOriginalFilename();
        File dest = new File(DEFAULT_UPLOAD_PATH, fileName);
        try {
            file.transferTo(dest);
            logger.info("上传成功");
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (IOException e) {
            logger.error(e.toString(), e);
        }
        return new ResponseEntity<>(false, HttpStatus.OK);

    }
}
