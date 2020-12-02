package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));
		SweBank.deposit("Alice", new Money(1000000, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
		testAccount.addTimedPayment("1",1,1,new Money(1000,SEK),SweBank,"Alice");
		assertTrue(testAccount.timedPaymentExists("1"));
		testAccount.removeTimedPayment("1");
		assertFalse(testAccount.timedPaymentExists("1"));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		assertEquals(Integer.valueOf(10000000), testAccount.getBalance().getAmount());
		testAccount.addTimedPayment("1", 2, 2, new Money(1000, SEK), SweBank, "Alice");
		for (int i = 0; i < 15; i++) {
			testAccount.tick();
		}
		assertEquals(Integer.valueOf(9995000), testAccount.getBalance().getAmount());
	}

	@Test
	public void testAddWithdraw() {
		testAccount.withdraw(new Money(80,SEK));
		assertEquals(Integer.valueOf(9999920), testAccount.getBalance().getAmount());
	}
	
	@Test
	public void testGetBalance() {
		assertEquals(Integer.valueOf(10000000), testAccount.getBalance().getAmount());
	}
}
