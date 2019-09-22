package pers.lyks.spring.example.strategy

import org.springframework.stereotype.Component

@Component
@CalculateHandlerType("add")
class AddOperation : CalculateStrategy<Double, Double> {
    override fun calculate(first: Double?, second: Double?): Number {
        return first!! + second!!
    }
}
