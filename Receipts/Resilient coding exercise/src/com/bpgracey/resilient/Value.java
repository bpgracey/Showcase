/**
 * 
 */
package com.bpgracey.resilient;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bpgracey.resilient.exceptions.ValueException;

/**
 * Represent a currency value (lightweight)
 * 
 * @author Ban
 *
 */
public class Value {
	public static final Value ZERO_VALUE = new Value(0);
	
	private static final Pattern valueMatch = Pattern.compile("(\\d+)\\.(\\d\\d)");
	private static final DecimalFormat valueFormat = new DecimalFormat("#,##0.00");
	
	static {
		valueFormat.setDecimalSeparatorAlwaysShown(true);
		valueFormat.setRoundingMode(RoundingMode.UP);
	}
	
	protected Integer value;
	
	public Value(String val) throws ValueException {
		if (val == null || val.isEmpty()) throw new ValueException("Missing input!");
		Matcher m = valueMatch.matcher(val);
		if (m.matches()) {
			this.value = Integer.valueOf(m.group(1)) * 100 + Integer.valueOf(m.group(2));
		} else {
			throw new ValueException("Invalid value!");
		}
	}
	
	/**
	 * constructor for tests
	 * @param value - value to inject
	 */
	protected Value(Integer value) {
		this.value = value;
	}
	
	/**
	 * Add two values, return new value
	 * @param val
	 * @return
	 */
	public Value add(Value val) {
		return new Value(this.value + val.value);
	}
	
	/**
	 * Round up value to nearest 'to'
	 * @param to
	 * @return
	 */
	public Value roundUp(Integer to) {
		return (value % to == 0) ? this : new Value(value + to - (value % to));
	}
	
	/**
	 * Return percent of current value
	 * @param percentage
	 * @return
	 */
	public Value percent(Double percentage) {
		double val = value * percentage / 100.0;
		return new Value((int)Math.round(val));
	}

	@Override
	public String toString() {
		return valueFormat.format(value/100.0);
	}

	public Value multiply(int factor) {
		return factor == 1 ? this : new Value(value * factor);
	}
	
}
