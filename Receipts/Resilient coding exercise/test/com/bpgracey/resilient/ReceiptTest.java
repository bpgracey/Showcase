package com.bpgracey.resilient;

import static org.junit.Assert.*;

import org.junit.Test;

import com.bpgracey.resilient.exceptions.ProductNameException;
import com.bpgracey.resilient.exceptions.ValueException;

public class ReceiptTest {

	@Test
	public void testReceipt() {
		Receipt r = new Receipt("test receipt");
		assertTrue("Initial total", r.getTotal().value == 0);
		assertTrue("Initial taxes", r.getTotalTaxes().value == 0);
		try {
			r.addLine("some text");
		} catch (ProductNameException | ValueException e) {
			fail("text line not added");
		}
		assertTrue("Line1 total", r.getTotal().value == 0);
		assertTrue("Line1 taxes", r.getTotalTaxes().value == 0);
		try {
			r.addLine("1 imported doodah at 10.00");
		} catch (ProductNameException | ValueException e) {
			fail("product line not added");
		}
		assertTrue("Line2 total", r.getTotal().value == 1150);
		assertTrue("Line2 taxes", r.getTotalTaxes().value == 150);
		assertTrue("Line count", r.getLines().size() == 2);
	}

	@Test
	public void testCreate() {
		String text = "Output Two:\n"
				+"1 imported box of chocolates at 10.00\n"
				+"\n"
				+"1 imported bottle of perfume at 47.50";
		String expected = "Output Two:\n\n"
				+"1\timported box of chocolates:\t10.50\n"
				+"\n"
				+"1\timported bottle of perfume:\t54.65\n"
				+"Sales Taxes:\t7.65\n"
				+"Total:\t65.15\n\n";
		Receipt r = null;
		try {
			r = Receipt.create(text);
		} catch (ProductNameException | ValueException e) {
			fail("Receipt not created!");
		}
		assertEquals(expected, r.toString());
	}

}
