package pers.lyks.spring.example.strategy

interface CalculateStrategy<F : Number, S : Number> {
    fun calculate(first: F, second: S): Number
}
