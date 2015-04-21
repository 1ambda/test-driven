package com.github.lambda;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class FrancTest {
	
	// equals dup
	// times dup
	// obj dup

	@Test
	public void testMultiplication() {
		Franc five = new Franc(5);
		
		assertEquals(new Franc(5), five);
		assertEquals(new Franc(10), five.times(2));
	}

}
