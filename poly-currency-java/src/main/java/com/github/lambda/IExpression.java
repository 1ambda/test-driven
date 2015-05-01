package com.github.lambda;

public interface IExpression {
    Money reduce(Bank bank, String to);
}
