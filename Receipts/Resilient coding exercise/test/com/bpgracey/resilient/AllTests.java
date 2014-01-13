package com.bpgracey.resilient;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.bpgracey.resilient.taxes.*;

@RunWith(Suite.class)
@SuiteClasses({ ProductLineTest.class, ProductTest.class, ValueTest.class, TaxCalcTest.class, TextLineTest.class, ReceiptTest.class })
public class AllTests {

}
