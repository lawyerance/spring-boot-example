package pers.lyks.spring.example.handler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.servlet.i18n.SessionLocaleResolver

import javax.servlet.http.HttpSession
import java.util.Locale

/**
 * @author lawyerance
 * @version 1.0 2018-08-03
 */
@Component
class I18nMessageSource @Autowired
constructor(@param:Qualifier("customMessageSource") private val messageSource: MessageSource, private val httpSession: HttpSession) {

    val locale: Locale
        get() = if (null == httpSession.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME)) {
            LocaleContextHolder.getLocale()
        } else httpSession.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME) as Locale

    fun getMessage(code: Int, locale: Locale): String? {
        return getMessage(code, null, null, locale)
    }

//    fun getMessage(code: Int, args: Array<Any>?, locale: Locale): String? {
//        return getMessage(code, args, null, locale)
//    }


    @JvmOverloads
    fun getMessage(code: Int, args: Array<Any>?, defaultValue: String? = null, locale: Locale): String? {
        return resolveMessage(merge(code), args, defaultValue, locale)
    }

    private fun merge(code: Int): String {
        return "code.$code"
    }

    private fun resolveMessage(code: String, args: Array<Any>?, defaultValue: String?, locale: Locale): String? {
        var args = args
        if (null == args) {
            args = arrayOf()
        }
        return if (null == defaultValue) {
            messageSource.getMessage(code, args, locale)
        } else messageSource.getMessage(code, args, defaultValue, locale)
    }
}
