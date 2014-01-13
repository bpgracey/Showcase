package com.bpgracey.resilient.taxes;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Tax data
 * (reads parameters from tax.properties file; has defaults if file is absent or incomplete)
 * 
 * @author Ban
 *
 */
public class TaxCalcNow extends TaxCalc {
	private final static String TAX_FREE_PATTERN = "book|pill|chocolate";
	private final static String IMPORT_PATTERN = "imported";
	private final static Double SALES_TAX = 10.0d;
	private final static Double IMPORT_TAX = 5.0d;
	
	private String taxFreePattern;
	private String importPattern;
	private Double salesTax;
	private Double importTax;
	
	private static TaxCalc taxCalc;
	
	protected static TaxCalc getTaxCalc() {
		if (taxCalc == null) taxCalc = new TaxCalcNow();
		return taxCalc;
	}
	
	private TaxCalcNow() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("tax.properties"));
			salesTax = Double.valueOf(prop.getProperty("salesTaxPercent", SALES_TAX.toString()));
			importTax = Double.valueOf(prop.getProperty("importTaxPercent", IMPORT_TAX.toString()));
			importPattern = "\\b" + prop.getProperty("import", IMPORT_PATTERN) + "\\b";
			StringBuilder taxFree = new StringBuilder();
			taxFree.append(prop.getProperty("books"));
			taxFree.append("|");
			taxFree.append(prop.getProperty("medical"));
			taxFree.append("|");
			taxFree.append(prop.getProperty("food"));
			String pattern = taxFree.toString().replaceAll("\\|\\|", "|").replace("^\\|", "").replace("\\|$", "");
			taxFreePattern = "\\b(" + (pattern.isEmpty() ? TAX_FREE_PATTERN : pattern) + ")s?\\b";
		} catch (IOException ex) {
			salesTax = SALES_TAX;
			importTax = IMPORT_TAX;
			importPattern = "\\b" + IMPORT_PATTERN + "\\b";
			taxFreePattern = "\\b(" + TAX_FREE_PATTERN + ")s?\\b";
		}
	}
	
	@Override
	public double getSalesTaxRate() {
		return salesTax;
	}

	@Override
	public double getImportTaxRate() {
		return importTax;
	}

	@Override
	public String getTaxFreePatternString() {
		return taxFreePattern;
	}

	@Override
	public String getImportPatternString() {
		return importPattern;
	}

}
