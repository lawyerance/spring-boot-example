package pers.lyks.spring.example.converter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import pers.lyks.spring.example.bean.CommonResponse;
import pers.lyks.spring.example.handler.I18nMessageSource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author lawyerance
 * @version 1.0 2018-08-03
 */
public class I18nResultMessageConverter extends AbstractHttpMessageConverter<CommonResponse> {
    private final static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    private I18nMessageSource i18nMessageSource;

    public I18nResultMessageConverter(I18nMessageSource i18nMessageSource) {
        super(StandardCharsets.UTF_8, MediaType.APPLICATION_JSON);
        this.i18nMessageSource = i18nMessageSource;
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return CommonResponse.class.isAssignableFrom(clazz);
    }

    @Override
    protected CommonResponse readInternal(Class<? extends CommonResponse> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(CommonResponse commonResponse, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        if (null == commonResponse.getMessage()) {
            String message = i18nMessageSource.getMessage(commonResponse.getCode(), commonResponse.getParams());
            commonResponse.setMessage(message);
        }
        outputMessage.getBody().write(mapper.writeValueAsBytes(commonResponse));
        outputMessage.getBody().flush();
    }
}
