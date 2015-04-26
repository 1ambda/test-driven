package com.github.lambda;

public class Money {
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

	public String currency() {
		return currency;
	}
	
	public Money times(int multiplier) {
		return new Money(amount * multiplier, currency);
	};

    public Money plus(Money that) {
        return null;
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
