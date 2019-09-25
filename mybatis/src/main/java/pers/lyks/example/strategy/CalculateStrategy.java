package pers.lyks.example.strategy;

public interface CalculateStrategy<F extends Number, S extends Number> {
    Number calculate(F first, S second);
}
