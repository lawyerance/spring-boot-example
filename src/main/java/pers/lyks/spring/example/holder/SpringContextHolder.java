package pers.lyks.spring.example.holder;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextHolder implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        assertNotNullApplication();
        return (T) context.getBean(name);
    }

    public static <T> T getBean(Class<T> t) {
        assertNotNullApplication();
        return context.getBean(t);
    }

    public static <T> T getBean(String name, Class<T> t) {
        assertNotNullApplication();
        return context.getBean(name, t);
    }

    private static void assertNotNullApplication() {
        if (null == context) {
            throw new NullPointerException("application context had not been initialized or initializing error, please check...");
        }
    }
}
