package pers.lyks.spring.example.holder

import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

@Component
class SpringContextHolder : ApplicationContextAware {

    @Throws(BeansException::class)
    override fun setApplicationContext(applicationContext: org.springframework.context.ApplicationContext) {
        context = applicationContext
    }

    companion object {
        private var context: ApplicationContext? = null

        fun <T> getBean(name: String): T {
            assertNotNullApplication()
            return context!!.getBean(name) as T
        }

        fun <T> getBean(t: Class<T>): T {
            assertNotNullApplication()
            return context!!.getBean(t)
        }

        fun <T> getBean(name: String, t: Class<T>): T {
            assertNotNullApplication()
            return context!!.getBean(name, t)
        }

        private fun assertNotNullApplication() {
            if (null == context) {
                throw NullPointerException("application context had not been initialized or initializing error, please check...")
            }
        }
    }
}
