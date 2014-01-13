package com.bpgracey.resilient;

public class TextLine extends ReceiptLine {
	private String text;
	
	public TextLine(String text) {
		this.text = text;
	}

	@Override
	public Value getTotal() {
		return Value.ZERO_VALUE;
	}

	@Override
	public Value getSalesTax() {
		return Value.ZERO_VALUE;
	}

	@Override
	public Value getImportTax() {
		return Value.ZERO_VALUE;
	}

	@Override
	public String toString() {
		return text;
	}
}
