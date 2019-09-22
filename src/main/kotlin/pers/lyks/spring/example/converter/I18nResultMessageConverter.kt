package pers.lyks.spring.example.converter

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.*
import org.springframework.http.converter.AbstractHttpMessageConverter
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.http.converter.HttpMessageNotWritableException
import pers.lyks.spring.example.handler.I18nMessageSource

import java.io.IOException
import java.nio.charset.StandardCharsets

/**
 * @author lawyerance
 * @version 1.0 2018-08-03
 */
class I18nResultMessageConverter(private val i18nMessageSource: I18nMessageSource, private val objectMapper: ObjectMapper) : AbstractHttpMessageConverter<ResponseEntity<*>>(StandardCharsets.UTF_8, MediaType.APPLICATION_JSON) {

    override fun supports(clazz: Class<*>): Boolean {
        return ResponseEntity<*>::class.java.isAssignableFrom(clazz)
    }

    @Throws(IOException::class, HttpMessageNotReadableException::class)
    override fun readInternal(clazz: Class<out ResponseEntity<*>>, inputMessage: HttpInputMessage): ResponseEntity<*>? {
        return null
    }

    @Throws(IOException::class, HttpMessageNotWritableException::class)
    protected override fun writeInternal(entity: ResponseEntity<*>?, outputMessage: HttpOutputMessage) {
        val res = if (null == entity) ResponseEntity(HttpEntity.EMPTY, HttpStatus.ACCEPTED) else ResponseEntity(entity.body, entity.statusCode)
        outputMessage.body.write(objectMapper.writeValueAsBytes(res))
        outputMessage.body.flush()
    }
}
