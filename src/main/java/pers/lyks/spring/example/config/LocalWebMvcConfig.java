package pers.lyks.spring.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pers.lyks.spring.example.converter.I18nResultMessageConverter;
import pers.lyks.spring.example.handler.I18nMessageSource;

import java.util.List;

/**
 * Local web mvc configuration.
 *
 * @author lawyerance
 * @version 1.0 2018-08-03
 */
@Configuration
public class LocalWebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    private I18nMessageSource i18nMessageSource;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0, new I18nResultMessageConverter(i18nMessageSource,mappingJackson2HttpMessageConverter.getObjectMapper()));
    }
}
