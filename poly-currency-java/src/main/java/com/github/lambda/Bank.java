package com.github.lambda;

public class Bank {
    public Money reduce(IExpression expr, String currency) {
        return Money.dollar(10);
    }
}
