package com.bpgracey.resilient.taxes;

public interface ITaxRates {
	public double getSalesTaxRate();
	public double getImportTaxRate();
	public String getTaxFreePatternString();
	public String getImportPatternString();
}
