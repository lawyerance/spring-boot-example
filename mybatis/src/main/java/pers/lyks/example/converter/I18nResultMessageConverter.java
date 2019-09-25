package pers.lyks.example.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import pers.lyks.example.handler.I18nMessageSource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author lawyerance
 * @version 1.0 2018-08-03
 */
public class I18nResultMessageConverter extends AbstractHttpMessageConverter<ResponseEntity> {

    private I18nMessageSource i18nMessageSource;
    private ObjectMapper objectMapper;

    public I18nResultMessageConverter(I18nMessageSource i18nMessageSource, ObjectMapper objectMapper) {
        super(StandardCharsets.UTF_8, MediaType.APPLICATION_JSON);
        this.i18nMessageSource = i18nMessageSource;
        this.objectMapper = objectMapper;
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return ResponseEntity.class.isAssignableFrom(clazz);
    }

    @Override
    protected ResponseEntity readInternal(Class<? extends ResponseEntity> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(ResponseEntity entity, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        ResponseEntity<?> res = null == entity ? new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.ACCEPTED) : new ResponseEntity<>(entity.getBody(), entity.getStatusCode());
        outputMessage.getBody().write(objectMapper.writeValueAsBytes(res));
        outputMessage.getBody().flush();
    }
}
