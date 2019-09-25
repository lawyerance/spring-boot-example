package pers.lyks.example.strategy;

import org.springframework.stereotype.Component;

@Component
@CalculateHandlerType("add")
public class AddOperation implements CalculateStrategy<Double, Double> {
    @Override
    public Number calculate(Double first, Double second) {
        return first + second;
    }
}
