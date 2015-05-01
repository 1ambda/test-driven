package com.github.lambda;

public class Money implements IExpression {
	public Money(int amount, String currency) {
		this.amount = amount;
		this.currency = currency;
	}

	public static Money dollar(int amount) {
		return new Money(amount, "USD");
	}

	public static Money franc(int amount) {
		return new Money (amount, "CHF");
	}

	protected int amount;
	protected String currency;

	public int amount() {
		return amount;
	}

	public Money reduce(Bank bank, String to) {
		int rate = bank.rate(this.currency, to);
		return new Money(amount / rate, to);
	}

	public String currency() {
		return currency;
	}

	// TODO
	public Money times(int multiplier) {
		return new Money(amount * multiplier, currency);
	};

    public IExpression plus (Money that) {
        return new Sum(this, that);
    }
	
	@Override
	public boolean equals(Object object) {
		Money that = (Money) object;

		return this.currency.equals(that.currency)
				&& this.amount == that.amount;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return amount + " " + currency; 
	}
}
