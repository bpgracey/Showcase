package com.bpgracey.resilient;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TextLineTest {
	private static final String TEST_LINE = "some text";
	private TextLine testLine = null;

	@Before
	public void setUp() throws Exception {
		testLine = new TextLine(TEST_LINE);
	}

	@Test
	public void testGetTotal() {
		assertTrue(testLine.getTotal().value == 0);
	}

	@Test
	public void testGetSalesTax() {
		assertTrue(testLine.getSalesTax().value == 0);
	}

	@Test
	public void testGetImportTax() {
		assertTrue(testLine.getImportTax().value == 0);
	}

	@Test
	public void testToString() {
		assertEquals(TEST_LINE, testLine.toString());
	}

}
