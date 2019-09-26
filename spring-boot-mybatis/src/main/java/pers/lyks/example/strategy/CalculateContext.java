package pers.lyks.example.strategy;

import pers.lyks.example.holder.SpringContextHolder;

import java.util.Map;

public class CalculateContext {
    private final Map<String, Class<? extends CalculateStrategy>> handlerMap;

    public CalculateContext(Map<String, Class<? extends CalculateStrategy>> handlerMap) {
        this.handlerMap = handlerMap;
    }

    @SuppressWarnings("unchecked")
    public <F extends Number, S extends Number> CalculateStrategy<F, S> getInstance(String type) {
        Class clazz = handlerMap.get(type);
        if (null == clazz) {
            throw new IllegalArgumentException("not found handler for type: " + type);
        }
        return (CalculateStrategy) SpringContextHolder.getBean(clazz);
    }
}
