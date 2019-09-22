package pers.lyks.spring.example.strategy;

import com.google.common.collect.Maps;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CalculateHandlerProcessor implements BeanFactoryPostProcessor {
    private final static String CALCULATE_HANDLER_PACKAGE = "pers.lyks.spring.example.strategy";

    @Override
    @SuppressWarnings("unchecked")
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Map<String, Class<? extends CalculateStrategy>> map = Maps.newHashMap();
        ClassScanner.scan(CALCULATE_HANDLER_PACKAGE, CalculateHandlerType.class).forEach(clazz -> {
            String type = clazz.getAnnotation(CalculateHandlerType.class).value();
            map.put(type, (Class<? extends CalculateStrategy>) clazz);
        });
        CalculateContext context = new CalculateContext(map);
        beanFactory.registerSingleton(CalculateContext.class.getName(), context);
    }
}
