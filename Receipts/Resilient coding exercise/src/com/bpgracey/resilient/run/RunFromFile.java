/**
 * 
 */
package com.bpgracey.resilient.run;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.bpgracey.resilient.Receipt;
import com.bpgracey.resilient.exceptions.ProductNameException;
import com.bpgracey.resilient.exceptions.ValueException;

/**
 * Batch mode: process a text file
 * Text file should be in the form:
 * Receipt: <title>\n
 * <line 1>\n
 * <line 2>\n
 * ...
 * Receipt: <title>\n
 * ...
 * 
 * @author Ban
 *
 */
public class RunFromFile {
	final static Charset ENCODING = StandardCharsets.UTF_8;
	final static Pattern DELIMITER = Pattern.compile("(\\A|\\n+)Receipt:\\s*", Pattern.CASE_INSENSITIVE);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length == 0 || args[0].isEmpty()) System.exit(1);
		for (String file: args) {
			Path path = Paths.get(file);
			try (Scanner scanner = new Scanner(path, ENCODING.name())) {
				scanner.useDelimiter(DELIMITER);
				while (scanner.hasNext()) {
					Receipt receipt = Receipt.create(scanner.next());
					System.out.println(receipt);
				}
			} catch (IOException e) {
				System.err.printf("*** File '%s' not found or opened\n ", file);
			} catch (ProductNameException | ValueException e) {
				System.err.println("*** Error in receipt creation!");
			}
		}
	}

}
