package com.bpgracey.resilient;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.bpgracey.resilient.exceptions.ProductLineException;
import com.bpgracey.resilient.exceptions.ProductNameException;
import com.bpgracey.resilient.exceptions.ValueException;

public class ProductLineTest {

	private ProductLine testProduct = null;

	@Before
	public void setUp() throws Exception {
		try {
			testProduct  = new ProductLine(2, "imported pills", "5.00");
		} catch (ProductNameException | ValueException e) {
			fail("Shouldn't throw an exception!");
		}
	}

	@Test
	public void testProductLineString() {
		ProductLine p = null;
		try {
			p = new ProductLine("1 music cd at 14.99");
		} catch (ProductNameException | ValueException | ProductLineException e) {
			fail("Shouldn't throw an exception!");
		}
		assertTrue("Quantity is "+p.quantity, p.quantity == 1);
		assertTrue("Price is "+p.price, p.price.value == 1499);
		assertEquals("Name is "+p.product.name, "music cd", p.product.toString());
	}

	@Test
	public void testProductLineIntStringString() {
		ProductLine p = null;
		try {
			p = new ProductLine(1, "music cd", "14.99");
		} catch (ProductNameException | ValueException e) {
			fail("Shouldn't throw an exception!");
		}
		assertTrue("Quantity is "+p.quantity, p.quantity == 1);
		assertTrue("Price is "+p.price, p.price.value == 1499);
		assertEquals("Name is "+p.product.name, "music cd", p.product.toString());
	}

	@Test
	public void testGetProduct() {
		assertEquals("imported pills", testProduct.getProduct().name);
	}

	@Test
	public void testGetQuantity() {
		assertTrue(2 == testProduct.getQuantity());
	}

	@Test
	public void testGetPrice() {
		assertTrue(500 == testProduct.getPrice().value);
	}

	@Test
	public void testGetTotal() {
		assertTrue(1050 == testProduct.getTotal().value);
	}

	@Test
	public void testGetImportTax() {
		assertTrue(25 == testProduct.getImportTax().value);
	}

	@Test
	public void testGetSalesTax() {
		assertTrue(0 == testProduct.getSalesTax().value);
	}

	@Test
	public void testToString() {
		assertEquals("2\timported pills:\t10.50", testProduct.toString());
	}

}
