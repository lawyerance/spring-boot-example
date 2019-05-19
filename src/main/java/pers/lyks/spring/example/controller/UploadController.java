package pers.lyks.spring.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pers.lyks.spring.example.base.BaseController;
import pers.lyks.spring.example.bean.CommonResponse;

import java.io.File;
import java.io.IOException;

/**
 * @author lawyerance
 * @version 1.0 2019-05-18
 */
@RestController
@RequestMapping(value = "/upload")
public class UploadController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    private static final String DEFAULT_UPLOAD_PATH = System.getProperty("user.dir");

    @RequestMapping(value = "/single", method = RequestMethod.POST)
    public CommonResponse<Boolean> upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return success(false);
        }
        String fileName = file.getOriginalFilename();
        File dest = new File(DEFAULT_UPLOAD_PATH, fileName);
        try {
            file.transferTo(dest);
            LOGGER.info("上传成功");
            return success(true);
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
        }
        return success(false);

    }
}
