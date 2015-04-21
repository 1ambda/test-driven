package com.github.lambda;

public class Franc {
	private int amount;

	public Franc(int amount) {
		this.amount = amount;
	};
	
	public Dollar times(int multiplier) {
		return new Dollar(amount * multiplier);
	};

	@Override
	public boolean equals(Object object) {
		Franc that = (Franc) object;
		return this.amount == that.amount;
	}

}
