package com.github.lambda;

public class Dollar {
	private int amount;
	
	public Dollar(int amount) {
		this.amount = amount;
	};
	
	public Dollar times(int multiplier) {
		return new Dollar(amount * multiplier);
	};

	@Override
	public boolean equals(Object object) {
		Dollar that = (Dollar) object;
		return this.amount == that.amount;
	}
}
