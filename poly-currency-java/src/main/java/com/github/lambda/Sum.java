package com.github.lambda;

public class Sum implements IExpression {
    private Money augend;
    private Money addend;

    public Money augend() {
        return augend;
    }

    public Money addend() {
        return addend;
    }


    public Sum(Money augend, Money addend) {
        this.augend = augend;
        this.addend = addend;
    }

    public Money reduce(String currency) {
        if (!augend.currency().equals(addend.currency())
                && augend.currency().equals(currency)) {
            return null;
        }

        int amount = augend.amount() + augend.amount();

        return new Money(amount, currency);
    }

}
