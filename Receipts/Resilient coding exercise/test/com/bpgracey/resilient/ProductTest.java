/**
 * 
 */
package com.bpgracey.resilient;

import static org.junit.Assert.*;

import org.junit.Test;

import com.bpgracey.resilient.exceptions.ProductNameException;

/**
 * @author Ban
 *
 */
public class ProductTest {
	private static String TESTTAX = "A Test Product";
	private static String TESTIMPORT = "An Imported product";
	private static String TESTNOTAX = "Some domestic chocolates";
	private static String TESTIMPORTNOTAX = "Some imported pills";
	
	/**
	 * Test method for {@link com.bpgracey.resilient.Product}.
	 */
	@Test
	public void test() {
		Product p = null;
		try {
			p = new Product(null);
			fail("Null: Exception not generated!");
		} catch (ProductNameException ex) {
			// pass
		}
		try {
			p = new Product("");
			fail("Empty: Exception not generated!");
		} catch (ProductNameException ex) {
			// pass
		}
		try {
			p = new Product(TESTTAX);
		} catch (ProductNameException ex) {
			fail("Valid: Exception thrown!");
		}
		assertTrue("Should be taxable!", p.isTaxable());
		assertFalse("Should not be an import!", p.isImported());
		assertTrue("Name", TESTTAX.equalsIgnoreCase(p.toString()));
		try {
			p = new Product(TESTIMPORT);
			assertTrue("Imported taxable: not taxable", p.isTaxable());
			assertTrue("Imported taxable: not imported", p.isImported());
			p = new Product(TESTNOTAX);
			assertFalse("Domestic taxfree: taxable", p.isTaxable());
			assertFalse("Domestic taxfree: imported", p.isImported());
			p = new Product(TESTIMPORTNOTAX);
			assertFalse("Imported taxfree: taxable", p.isTaxable());
			assertTrue("Imported taxfree: domestic", p.isImported());
		} catch (ProductNameException ex) {
			fail("Extended tests: name failed!");
		}
	}
	
}
