package com.github.lambda;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CurrencyTest {

    @Test
    public void testReduceMoney() {
        Money one = Money.dollar(1);
        Bank bank = new Bank();
        Money reduced = bank.reduce(one, "USD");

        assertEquals(Money.dollar(1), reduced);
    }

    @Test
    public void testPlusReturnSum() {
        Money five = Money.dollar(5);
        IExpression expr = five.plus(five);
        Sum sum = (Sum) expr;

        assertEquals(five, sum.augend());
        assertEquals(five, sum.addend());
    }

	@Test
	public void testSimpleAddtion() {
		Money five = Money.dollar(5);
		IExpression sum = five.plus(five);

		Bank bank = new Bank();
		Money reduced = bank.reduce((Sum) sum, "USD");

		assertEquals(Money.dollar(10), reduced);
	}

	@Test
	public void testMultiplication() {
		Money five = Money.dollar(5);
		Money ten = five.times(2);
		assertEquals(Money.dollar(5), five);
		assertEquals(Money.dollar(10), ten);

		Money fifteenFranc = Money.franc(15);
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
		assertFalse(new Money(5, "USD").equals(new Money(5, "CHF")));
	}

}
