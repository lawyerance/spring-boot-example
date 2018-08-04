package pers.lyks.spring.example.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

/**
 * @author lawyerance
 * @version 1.0 2018-08-03
 */
@Configuration
public class CustomMessageConfig {
    private final static Logger logger = LoggerFactory.getLogger(CustomMessageConfig.class);
    private final static String basename = "i18n/messages";

    @Primary
    @Bean("customMessageSource")
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        logger.info("load i18n message base name: {}", basename);
        messageSource.setBasename(basename);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheMillis(-1);
        return messageSource;
    }
}
