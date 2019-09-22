package pers.lyks.spring.example.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.support.ResourceBundleMessageSource

/**
 *
 * @author lawyerance
 * @version 1.0 2019-09-22
 */
@Configuration
open class CustomMessageConfig {
    private val logger: Logger = LoggerFactory.getLogger(CustomMessageConfig::class.java)
    private val BASE_NAME = "i18n/messages";

    @Primary
    @Bean("customMessageSource")
    open fun messageSource(): MessageSource {
        val messageSource: ResourceBundleMessageSource = ResourceBundleMessageSource();
        logger.info("load i18n message base name: {}", BASE_NAME);
        messageSource.setBasename(BASE_NAME);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheMillis(-1);
        return messageSource;
    }
}