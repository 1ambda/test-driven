package com.github.lambda;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Test;

public class DollarTest {

	// equals: Null, diff class
	// hashCode

	@Test
	public void testMultiplication() {
		Dollar five = new Dollar(5);
		Dollar ten = five.times(2);
		assertEquals(new Dollar(5), five);
		assertEquals(new Dollar(10), ten);
		
		Dollar fifteen = five.times(3);
		assertEquals(new Dollar(15), fifteen);
		assertEquals(new Dollar(5), five);
	}

	@Test
	public void testEquality() {
		assertTrue(new Dollar(5).equals(new Dollar(5)));
		assertFalse(new Dollar(6).equals(new Dollar(5)));
	}
}
