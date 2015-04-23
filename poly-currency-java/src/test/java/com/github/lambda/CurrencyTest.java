package com.github.lambda;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Test;

public class CurrencyTest {

	@Test
	public void testMultiplication() {
		Money five = Money.dollar(5); 
		Money ten = five.times(2);
		assertEquals(Money.dollar(5), five);
		assertEquals(Money.dollar(10), ten);

		Franc fifteenFranc = Money.franc(15); 
		
		assertEquals(Money.franc(30), fifteenFranc.times(2));
	}

	@Test
	public void testEquality() {
		assertTrue(Money.dollar(5).equals(Money.dollar(5)));
		assertFalse(Money.dollar(6).equals(Money.dollar(5)));
		
		assertTrue(Money.franc(5).equals(Money.franc(5)));
		assertFalse(Money.franc(6).equals(Money.franc(5)));
	}
	
	@Test
	public void testCurrency() {
		assertEquals("USD", Money.dollar(5).currency());
		assertEquals("CHF", Money.franc(5).currency());
	}
	
	@Test
	public void testDifferentCurrency() {
		// 실패해야 
		assertTrue(new Money(5, "USD").equals(new Money(5, "CHF")));
	}
}