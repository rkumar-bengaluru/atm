package com.oppurtun.atm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ATMTest {
	ATM instance = new ATM();

	public void deposit01() {
		instance.deposit(0, 20, 8, 0);
	}
	
	public void deposit02() {
		instance.deposit(4, 18, 0, 3);
	}

	@Test
	void testDeposit01() {
		deposit01();
		// one dollar bills
		assertEquals(instance.getSumOfBills()[0], 0);
		// five dollar bills
		assertEquals(instance.getSumOfBills()[1], 20);
		// 10 dollar bills
		assertEquals(instance.getSumOfBills()[2], 8);
		// 20 dollar bills
		assertEquals(instance.getSumOfBills()[3], 0);
	}

	@Test
	void testDeposit02() {
		deposit01();
		deposit02();
		// one dollar bills
		assertEquals(instance.getSumOfBills()[0], 4);
		// five dollar bills
		assertEquals(instance.getSumOfBills()[1], 38);
		// 10 dollar bills
		assertEquals(instance.getSumOfBills()[2], 8);
		// 20 dollar bills
		assertEquals(instance.getSumOfBills()[3], 3);
	}
	
	@Test
	void testWithdraw01() {
		deposit01();
		deposit02();
		instance.withdrawn(75);
		// one dollar bills
		assertEquals(instance.getSumOfBills()[0], 4);
		// five dollar bills
		assertEquals(instance.getSumOfBills()[1], 37);
		// 10 dollar bills
		assertEquals(instance.getSumOfBills()[2], 7);
		// 20 dollar bills
		assertEquals(instance.getSumOfBills()[3], 0);
	}
	
	@Test
	void testWithdraw02() {
		deposit01();
		deposit02();
		instance.withdrawn(75);
		instance.withdrawn(122);
		// one dollar bills
		assertEquals(instance.getSumOfBills()[0], 2);
		// five dollar bills
		assertEquals(instance.getSumOfBills()[1], 27);
		// 10 dollar bills
		assertEquals(instance.getSumOfBills()[2], 0);
		// 20 dollar bills
		assertEquals(instance.getSumOfBills()[3], 0);
	}
	
	@Test
	void testWithdraw03() {
		deposit01();
		deposit02();
		instance.withdrawn(75);
		instance.withdrawn(122);
		assertFalse(instance.withdrawn(63));
	}

	@Test
	void testWithdraw4() {
		ATM instance = new ATM();
		assertFalse(instance.withdrawn(0));
	}

	@Test
	void testWithdraw5() {
		ATM instance = new ATM();
		assertFalse(instance.withdrawn(-25));
	}

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		// TODO
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		// TODO
	}

	@BeforeEach
	void setUp() throws Exception {
		// TODO
	}

	@AfterEach
	void tearDown() throws Exception {
		// TODO
	}

}
