package com.bpgracey.resilient;

public abstract class ReceiptLine {
	public abstract Value getTotal();
	public abstract Value getSalesTax();
	public abstract Value getImportTax();
}
