package com.bpgracey.resilient.run;

import java.util.Scanner;

import com.bpgracey.resilient.Receipt;
import com.bpgracey.resilient.exceptions.ProductNameException;
import com.bpgracey.resilient.exceptions.ValueException;

public class RunConsole {
	
	public static void printReceipt(Receipt receipt) {
		if (receipt.getLines().size() > 0) {
			System.out.println("Printing...");
			System.out.println(receipt.toString());
			System.out.println("Print completed.");
		} else if (!receipt.getTitle().isEmpty()) {
			System.out.println("Nothing to print for "+receipt.getTitle());
		}
	}

	/**
	 * Simple console runner for receipt demo
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Receipt creator\nType 'help' for help, 'exit' to quit");
		Receipt receipt = new Receipt(null);
		boolean running = true;
		while (running) {
			System.out.printf("Title: %s, total: %s\n>", receipt.getTitle(), receipt.getTotal().toString());
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("exit")) {
				printReceipt(receipt);
				running = false;
			} else if (input.equalsIgnoreCase("help")) {
				System.out.printf("Type 'new' to start a new receipt,\n");
				System.out.println("'<qty> <product> at <price>' to enter a receipt line,");
				System.out.println("'help' to see this text,");
				System.out.println("'print' to print the receipt so far,");
				System.out.println("'exit' to quit,");
				System.out.println("or anything else to add a line of text or space.");
			} else if (input.equalsIgnoreCase("new")) {
				System.out.print("Title?");
				String title = scanner.nextLine();
				if (title.isEmpty()) {
					System.out.print("continuing with current receipt");
				} else {
					receipt = new Receipt(title);
				}
			} else if (input.equalsIgnoreCase("print")) {
				printReceipt(receipt);
			} else {
				try {
					receipt.addLine(input);
				} catch (ProductNameException e) {
					System.out.printf("Invalid product in %s", input);
				} catch (ValueException e) {
					System.out.printf("Invalid price in %s",  input);
				} catch (NumberFormatException e) {
					System.out.printf("Invalid quantity in %s", input);
				}
			}
		}
		scanner.close();
		System.out.print("Goodbye!");
	}

}
