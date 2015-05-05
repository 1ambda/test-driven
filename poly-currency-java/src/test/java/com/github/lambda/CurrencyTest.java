package com.github.lambda;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CurrencyTest {

    private IExpression fiveBucks;
    private IExpression tenFrancs;
    private Bank bank;

    @Before
    public void setUpClass() {
        fiveBucks = Money.dollar(5);
        tenFrancs = Money.franc(10);
        bank = new Bank();
        bank.addRate("CHF", "USD", 2);
    }
    
    @Test
    public void testSumTimes () {
        IExpression sum = new Sum(fiveBucks, tenFrancs).times(2);
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(20), result);
    }

    @Test
    public void testSumPlus () {
        IExpression sum = new Sum(fiveBucks, tenFrancs).plus(fiveBucks);
        Money result = bank.reduce(sum, "USD");

        assertEquals(Money.dollar(15), result);
    }

    @Test
    public void testMixedAddition () {
        Money result = bank.reduce(
                fiveBucks.plus(tenFrancs), "USD");
        assertEquals(Money.dollar(10), result);
    }

    @Test
    public void testIdentityRate () {
        assertEquals(1, new Bank().rate("USD", "USD"));
    }

    @Test
    public void testArrayEquals () {
        assertArrayEquals(
                new Object[] {"USD", "CHF" },
                new Object[] {"USD", "CHF"});
    }

    @Test
    public void testReduceMoneyDifferentCurrency() {
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);

        Money reduced = Money.franc(2).reduce(bank, "USD");
        assertEquals(Money.dollar(1), reduced);
    }

    @Test
    public void testReduceMoney() {
        Money one = Money.dollar(1);
        Bank bank = new Bank();

        Money reduced = one.reduce(bank, "USD");
        assertEquals(Money.dollar(1), reduced);
    }

    @Test
    public void testSimpleAddtion() {
        IExpression sum = fiveBucks.plus(fiveBucks);
        Money reduced = sum.reduce(bank, "USD");
        assertEquals(Money.dollar(10), reduced);
    }

    @Test
    public void testPlusReturnSum() {
        IExpression expr = fiveBucks.plus(fiveBucks);
        Sum sum = (Sum) expr;

        assertEquals(fiveBucks, sum.augend());
        assertEquals(fiveBucks, sum.addend());
    }

    @Test
    public void testMultiplication() {
        IExpression tenBucks = fiveBucks.times(2);
        assertEquals(Money.dollar(5), fiveBucks);
        assertEquals(Money.dollar(10), tenBucks);

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
