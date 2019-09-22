package pers.lyks.spring.example.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

import java.io.File
import java.io.IOException

/**
 * @author lawyerance
 * @version 1.0 2019-05-18
 */
@RestController
@RequestMapping(value = ["/upload"])
class UploadController {
    private val logger: Logger = LoggerFactory.getLogger(UploadController::class.java)

    @RequestMapping(value = ["/single"], method = [RequestMethod.POST])
    fun upload(@RequestParam("file") file: MultipartFile): ResponseEntity<Boolean> {
        if (file.isEmpty) {
            return ResponseEntity(false, HttpStatus.OK)
        }
        val fileName = file.originalFilename
        val dest = File(DEFAULT_UPLOAD_PATH, fileName!!)
        try {
            file.transferTo(dest)
            logger.info("上传成功")
            return ResponseEntity(true, HttpStatus.OK)
        } catch (e: IOException) {
            logger.error(e.toString(), e)
        }

        return ResponseEntity(false, HttpStatus.OK)

    }

    companion object {

        private val DEFAULT_UPLOAD_PATH = System.getProperty("user.dir")
    }
}
