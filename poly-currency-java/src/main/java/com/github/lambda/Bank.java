package com.github.lambda;

public class Bank {
    public Money reduce(IExpression expr, String to) {
        return expr.reduce(to);
    }
}
