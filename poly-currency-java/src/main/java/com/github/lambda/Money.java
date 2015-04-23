package com.github.lambda;

public class Money {
	public Money(int amount, String currency) {
		this.amount = amount;
		this.currency = currency;
	}

	public static Dollar dollar(int amount) {
		return new Dollar(amount, "USD");
	}
	
	public static Franc franc(int amount) {
		return new Franc (amount, "CHF");
	}

	protected int amount;
	protected String currency;

	public String currency() {
		return currency;
	}
	
	public Money times(int multiplier) {
		return new Money(amount * multiplier, currency);
	};
	
	@Override
	public boolean equals(Object object) {
		Money that = (Money) object;
	
		return this.amount == that.amount
				&& this.getClass().equals(that.getClass());
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return amount + " " + currency; 
	}
}
