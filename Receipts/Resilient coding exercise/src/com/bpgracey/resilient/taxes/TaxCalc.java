package com.bpgracey.resilient.taxes;

import com.bpgracey.resilient.Product;
import com.bpgracey.resilient.Value;

/**
 * Parent for TaxCalcNow, and any future tax data classes
 * @author Ban
 *
 */
public abstract class TaxCalc implements ITaxRates {
	public static TaxCalc getInstance() {
		return TaxCalcNow.getTaxCalc();
	}
	
	public static ITaxRates getRates() {
		return TaxCalcNow.getTaxCalc();
	}
	
	public Value calcSalesTax(Product product, Value price) {
		return product.isTaxable() ? price.percent(getSalesTaxRate()).roundUp(5) : Value.ZERO_VALUE;
	}
	
	public Value calcImportTax(Product product, Value price) {
		return product.isImported() ? price.percent(getImportTaxRate()).roundUp(5) : Value.ZERO_VALUE;
	}
}
