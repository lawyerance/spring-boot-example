package pers.lyks.spring.example.strategy

import com.google.common.collect.Maps
import org.springframework.beans.BeansException
import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.stereotype.Component

@Component
class CalculateHandlerProcessor : BeanFactoryPostProcessor {

    @Throws(BeansException::class)
    override fun postProcessBeanFactory(beanFactory: ConfigurableListableBeanFactory) {
        val map = Maps.newHashMap<String, Class<out CalculateStrategy<*, *>>>()
        ClassScanner.scan(CALCULATE_HANDLER_PACKAGE, CalculateHandlerType::class.java).forEach { clazz ->
            val type = clazz.getAnnotation(CalculateHandlerType::class.java).value
            map[type] = clazz as Class<out CalculateStrategy<*, *>>
        }
        val context = CalculateContext(map)
        beanFactory.registerSingleton(CalculateContext::class.java.name, context)
    }

    companion object {
        private val CALCULATE_HANDLER_PACKAGE = "pers.lyks.spring.example.strategy"
    }
}
