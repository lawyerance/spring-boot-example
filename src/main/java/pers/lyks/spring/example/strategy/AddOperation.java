package pers.lyks.spring.example.strategy;

import org.springframework.stereotype.Component;

@Component
@CalculateHandlerType("add")
public class AddOperation implements CalculateStrategy {
    @Override
    public Number calculate(Number first, Number second) {
        return first.doubleValue() + second.doubleValue() ;
    }
}
