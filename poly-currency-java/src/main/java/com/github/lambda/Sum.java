package com.github.lambda;

public class Sum implements IExpression {
    private IExpression augend;
    private IExpression addend;

    public IExpression augend() {
        return augend;
    }

    public IExpression addend() {
        return addend;
    }

    public Sum(IExpression augend, IExpression addend) {
        this.augend = augend;
        this.addend = addend;
    }

    public IExpression plus(IExpression addend) {
        return new Sum(this, addend);
    }

    public IExpression times(int multiplier) {
        return new Sum(augend.times(multiplier), addend.times(multiplier));
    }

    public Money reduce(Bank bank, String currency) {
        int amount = augend.reduce(bank, currency).amount()
                + addend.reduce(bank, currency).amount();
        return new Money(amount, currency);
    }

}
