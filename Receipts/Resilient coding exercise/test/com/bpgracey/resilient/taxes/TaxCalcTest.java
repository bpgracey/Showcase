package com.bpgracey.resilient.taxes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.bpgracey.resilient.Product;
import com.bpgracey.resilient.Value;
import com.bpgracey.resilient.exceptions.ProductNameException;
import com.bpgracey.resilient.exceptions.ValueException;
import com.bpgracey.resilient.taxes.TaxCalcNow;

public class TaxCalcTest {
	private Product product = null;
	
	@Before
	public void setUp() {
		try {
			product = new Product("Imported thingys");
		} catch (ProductNameException e) {
			fail("Test setup error!");
		}
	}

	@Test
	public void testCalcSalesTax() {
		Value price = null;
		try {
			price = new Value("1.10");
		} catch (ValueException e) {
			fail("Test setup error!");
		}
		Value salesTax = TaxCalcNow.getTaxCalc().calcSalesTax(product, price);
		assertEquals("0.15", salesTax.toString());
	}

	@Test
	public void testCalcImportTax() {
		Value price = null;
		try {
			price = new Value("1.05");
		} catch (ValueException e) {
			fail("Test setup error!");
		}
		Value importTax = TaxCalcNow.getTaxCalc().calcImportTax(product, price);
		assertEquals("0.05", importTax.toString());
	}

}
