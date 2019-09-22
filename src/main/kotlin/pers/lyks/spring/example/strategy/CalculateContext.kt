package pers.lyks.spring.example.strategy

import pers.lyks.spring.example.holder.SpringContextHolder

class CalculateContext(private val handlerMap: Map<String, Class<out CalculateStrategy<*, *>>>) {

    fun <F : Number, S : Number> getInstance(type: String): CalculateStrategy<F, S> {
        val clazz = handlerMap[type] ?: throw IllegalArgumentException("not found handler for type: $type")
        return SpringContextHolder.getBean(clazz) as CalculateStrategy<F, S>;
    }
}
