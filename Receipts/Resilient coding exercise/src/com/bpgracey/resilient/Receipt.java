package com.bpgracey.resilient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bpgracey.resilient.exceptions.ProductLineException;
import com.bpgracey.resilient.exceptions.ProductNameException;
import com.bpgracey.resilient.exceptions.ValueException;

/**
 * Receipt object (mutable)
 * Holds list of receipt lines, which may be either free text or product lines
 * Also totals taxes and payments
 * @author Ban
 *
 */
public class Receipt {
	protected String title;
	protected ArrayList<ReceiptLine> lines = new ArrayList<ReceiptLine>();
	protected Value totalTaxes = Value.ZERO_VALUE;
	protected Value total = Value.ZERO_VALUE;
	
	/**
	 * Create a receipt from a block of text
	 * (first line is assumed to be a 'title' for the receipt)
	 * @param input - lines of text separated by '\n'
	 * @return new receipt object
	 * @throws ProductNameException
	 * @throws ValueException
	 */
	public static Receipt create(String input) throws ProductNameException, ValueException {
		List<String> list = new ArrayList<String>(Arrays.asList(input.split("\n")));
		Receipt receipt = new Receipt(list.remove(0));
		for (String line: list) {
			receipt.addLine(line);
		}
		return receipt;
	}
	
	/**
	 * Constructor
	 * @param title - first line of receipt
	 */
	public Receipt(String title) {
		if (title == null) title = "";
		this.title = title.replaceFirst("^Input\\b", "Output");
	}
	
	public String getTitle() {
		return title;
	}
	
	public ArrayList<ReceiptLine> getLines() {
		return lines;
	}
	
	public Value getTotalTaxes() {
		return totalTaxes;
	}
	
	public Value getTotal() {
		return total;
	}

	public Receipt addLine(String line) throws ProductNameException, ValueException {
		ReceiptLine l = null;
		try {
			l = new ProductLine(line);
		} catch (ProductLineException ex) {
			l = new TextLine(line);
		}
		lines.add(l);
		totalTaxes = totalTaxes.add(l.getSalesTax()).add(l.getImportTax());
		total = total.add(l.getTotal());
		return this;
	}
	
	@Override
	public String toString() {
		StringBuilder buff = new StringBuilder(title + "\n\n");
		for (ReceiptLine line: lines) {
			buff.append(line.toString()).append("\n");
		}
		buff.append("Sales Taxes:\t").append(totalTaxes.toString()).append("\n");
		buff.append("Total:\t").append(total.toString()).append("\n\n");
		return buff.toString();
	}
}
