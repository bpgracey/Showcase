/**
 * 
 */
package com.bpgracey.resilient;

import java.util.regex.Pattern;

import com.bpgracey.resilient.exceptions.ProductNameException;
import com.bpgracey.resilient.taxes.ITaxRates;
import com.bpgracey.resilient.taxes.TaxCalc;

/**
 * Representation of the product being sold, based on the description
 * 
 * @author Ban
 *
 */
public class Product {
	private static final ITaxRates taxData = TaxCalc.getRates();
	protected static final Pattern importPattern = Pattern.compile(taxData.getImportPatternString(), Pattern.CASE_INSENSITIVE);
	protected static final Pattern taxfreePattern = Pattern.compile(taxData.getTaxFreePatternString(), Pattern.CASE_INSENSITIVE);
	
	protected String name;
	protected Boolean imported;
	protected Boolean taxable;
	
	public Product(String name) throws ProductNameException {
		if (name == null || name.isEmpty()) throw new ProductNameException();
		this.name = name;
	}

	public boolean isImported() {
		if (imported == null) this.imported = importPattern.matcher(name).find();
		return imported;
	}

	public boolean isTaxable() {
		if (taxable == null) this.taxable = !taxfreePattern.matcher(name).find();
		return taxable;
	}

	@Override
	public String toString() {
		return name;
	}

}
