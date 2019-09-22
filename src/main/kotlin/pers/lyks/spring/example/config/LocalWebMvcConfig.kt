package pers.lyks.spring.example.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import pers.lyks.spring.example.converter.I18nResultMessageConverter
import pers.lyks.spring.example.handler.I18nMessageSource

/**
 * Local web mvc configuration.
 *
 * @author lawyerance
 * @version 1.0 2018-08-03
 */
@Configuration
open class LocalWebMvcConfig : WebMvcConfigurer {
    @Autowired
    private val mappingJackson2HttpMessageConverter: MappingJackson2HttpMessageConverter? = null

    @Autowired
    private val i18nMessageSource: I18nMessageSource? = null

    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        i18nMessageSource?.let { I18nResultMessageConverter(it, mappingJackson2HttpMessageConverter!!.objectMapper) }?.let { converters.add(0, it) }
    }
}
