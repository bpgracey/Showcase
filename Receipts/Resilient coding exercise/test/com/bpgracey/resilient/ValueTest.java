package com.bpgracey.resilient;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.bpgracey.resilient.exceptions.ValueException;

public class ValueTest {
	private Value testV1;
	private Value testV2;
	
	@Before
	public void setUp() {
		testV1 = new Value(110);
		testV2 = new Value(14);
	}

	@Test
	public void testValue() {
		Value v = null;
		try {
			v = new Value("1.23");
		} catch (ValueException e) {
			fail("Exception thrown!");
		}
		assertEquals("Value error", 123, (long)v.value);
		try {
			v = new Value("wib1.23le");
			fail("Exception not thrown!");
		} catch (ValueException ex) {
			// pass
		}
	}

	@Test
	public void testAdd() {
		Value v = testV1.add(testV2);
		assertTrue("Add", v.value == 124);
	}
	
	@Test
	public void testRoundUp() {
		Value v = testV2.roundUp(5);
		assertTrue("Rounded", v.value == 15);
	}
	
	@Test
	public void testPercent() {
		Value v = testV1.percent(10.0);
		assertTrue("% was "+v.toString(), v.value == 11);
	}
	
	@Test
	public void testMultiply() {
		Value v = testV1.multiply(2);
		assertTrue("result was "+v.toString(), v.value == 220);
	}

	@Test
	public void testToString() {
		assertTrue("Output1", "1.10".equalsIgnoreCase(testV1.toString()));
		assertTrue("Output2", "0.14".equalsIgnoreCase(testV2.toString()));
	}

}
