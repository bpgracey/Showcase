Specification:

Basic sales tax is applicable at a rate of 10% on all goods, except books, food, 
and medical products that are exempt. Import duty is an additional sales tax applicable 
on all imported goods at a rate of 5%, with no exemptions.

When I purchase items I receive a receipt which lists the name of all the items
and their price (including tax), finishing with the total cost of the items, 
and the total amounts of sales taxes paid.  The rounding rules for sales tax 
are that for a tax rate of n%, a shelf price of p contains (np/100 rounded up 
to the nearest 0.05) amount of sales tax.

Write an application that prints out the receipt details for these shopping 
baskets...

INPUT:

Input One:
1 book at 12.49
1 music CD at 14.99
1 chocolate bar at 0.85

Input Two:
1 imported box of chocolates at 10.00

1 imported bottle of perfume at 47.50

Input Three:
1 imported bottle of perfume at 27.99
1 bottle of perfume at 18.99
1 packet of headache pills at 9.75
1 box of imported chocolates at 11.25

OUTPUT:

Output One:
1 book : 12.49
1 music CD: 16.49
1 chocolate bar: 0.85
Sales Taxes: 1.50
Total: 29.83

Output Two:
1 imported box of chocolates: 10.50

1 imported bottle of perfume: 54.65
Sales Taxes: 7.65

Total: 65.15

Output Three:
1 imported bottle of perfume: 32.19
1 bottle of perfume: 20.89
1 packet of headache pills: 9.75
1 imported box of chocolates: 11.85
Sales Taxes: 6.70
Total: 74.68

=====================================================================================

Notes:

The code was written & tested in Eclipse under JDK 1.7. Probably won't compile under JDK 1.6. No libraries outside the regular JRE were used (except for JUnit 4 for testing). Source code is under <project>/src, unit/integration tests under <project>/test, and resource files are under the project root. (I didn't want the overhead of managing a pom file for a little project like this, so the directory structure is Java/Eclipse, not Maven.)

Design: I wanted to produce something flexible, capable of improvement & updating. I also didn't want the receipt-maker dependent on a database, even a mocked one. So the system relies on inputs in the tax.properties file to determine both tax rates and the status (imported, taxable) of a product based on its description. And the tax calculator (package com.bpgracey.resilient.taxes) has a skeleton that should make it easy to adapt for different tax codes.
The core classes (in com.bpgracey.resilient) produce their output via toString() - in an effort to make the runner classes simple and elegant. Output is delimited by tabs, on the assumption that the 'printed' receipt would be piped to another program for formatting. Additionally (and only so that the 'input' & 'output' data could mirror the examples provided!), in the receipt 'title', the word 'Input' is always replaced by 'Output'.   

To run: There are two runners, com.bpgracey.resilient.run.RunConsole and com.bpgracey.resilient.run.RunFromFile. RunConsole takes input from a console; RunFromFile takes input from a text file specified in the runtime parameters (the file 'inputs.txt' is supplied as an example). Both output to standard console (with error messages going to standard error).

Testing: In a commercial environment, I would probably use mocks for the unit tests, and included some separate integration tests. For this project, the tests are both unit and integration; the acceptance test would consist of running RunFromFile on inputs.txt and returning output sufficiently similar to the examples provided.  The full test suite can be run via com.bpgracey.resilient.AllTests (with JUnit 4 in the classpath).
