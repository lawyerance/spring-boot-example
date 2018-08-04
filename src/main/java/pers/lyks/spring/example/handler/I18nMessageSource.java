package pers.lyks.spring.example.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * @author lawyerance
 * @version 1.0 2018-08-03
 */
@Component
public class I18nMessageSource {

    @Autowired
    @Qualifier("customMessageSource")
    private MessageSource messageSource;

    @Autowired
    private HttpSession httpSession;

    public String getMessage(int code) {
        return getMessage(code, getLocale());
    }

    public String getMessage(int code, Object[] args) {
        return getMessage(code, args, null, getLocale());
    }

    public String getMessage(int code, Locale locale) {
        return getMessage(code, null, locale);
    }

    public String getMessage(int code, Object[] args, Locale locale) {
        return getMessage(code, args, null, locale);
    }

    public String getMessage(int code, Object[] args, String defaultValue) {
        return getMessage(code, args, defaultValue, getLocale());
    }


    public String getMessage(int code, Object[] args, String defaultValue, Locale locale) {
        return getMessage(merge(code), args, defaultValue, locale);
    }

    public Locale getLocale() {
        if (null == httpSession.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME)) {
            return LocaleContextHolder.getLocale();
        }
        return (Locale) httpSession.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
    }

    private static String merge(int code) {
        return "code." + code;
    }

    private String getMessage(String code, Object[] args, String defaultValue, Locale locale) {
        if (null == args) {
            args = new Object[0];
        }
        if (null == defaultValue) {
            return messageSource.getMessage(code, args, locale);
        }
        return messageSource.getMessage(code, args, defaultValue, locale);
    }
}
