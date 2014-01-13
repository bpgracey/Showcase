/**
 * 
 */
package com.bpgracey.resilient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bpgracey.resilient.exceptions.ProductLineException;
import com.bpgracey.resilient.exceptions.ProductNameException;
import com.bpgracey.resilient.exceptions.ValueException;
import com.bpgracey.resilient.taxes.TaxCalc;

/**
 * @author Ban
 *
 */
public class ProductLine extends ReceiptLine {
	private static final Pattern PRODUCT_LINE_PATTERN = Pattern.compile("(\\d+)\\s+(.+?)\\s+at\\s+(\\d+\\.\\d\\d)");
	
	protected Product product;
	protected Integer quantity;
	protected Value price;
	protected Value importTax;
	protected Value salesTax;
	protected Value total;
	
	public ProductLine(String line) throws ProductNameException, ValueException, ProductLineException {
		if (line == null || line.isEmpty()) throw new ProductLineException("Empty line");
		Matcher m = PRODUCT_LINE_PATTERN.matcher(line);
		if (m.find()) {
			this.quantity = Integer.valueOf(m.group(1));
			this.product = new Product(m.group(2));
			this.price = new Value(m.group(3));
		} else throw new ProductLineException();
	}
	
	public ProductLine(int quantity, String product, String price) throws ProductNameException, ValueException {
		this.product = new Product(product);
		this.quantity = quantity;
		this.price = new Value(price);
	}

	public Product getProduct() {
		return product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Value getPrice() {
		return price;
	}
	
	public Value getTotal() {
		if (total == null) 
			total = price.add(getSalesTax()).add(getImportTax()).multiply(quantity);
		return total;
	}

	public Value getImportTax() {
		if (importTax == null)
			importTax = TaxCalc.getInstance().calcImportTax(product, price);
		return importTax;
	}

	public Value getSalesTax() {
		if (salesTax == null)
			salesTax = TaxCalc.getInstance().calcSalesTax(product, price);
		return salesTax;
	}

	@Override
	public String toString() {
		return "" + getQuantity() + "\t" + getProduct() + ":\t" + getTotal();
	}
}
