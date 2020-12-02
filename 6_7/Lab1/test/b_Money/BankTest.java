package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		assertEquals("SweBank",SweBank.getName());
		assertEquals("DanskeBank",DanskeBank.getName());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK,SweBank.getCurrency());
		assertEquals(DKK,DanskeBank.getCurrency());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException {
		try {
			SweBank.openAccount("Ulrika");
			SweBank.openAccount("Bob");
		}catch (AccountExistsException ex){

		}
		Nordea.openAccount("Roman");
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		SweBank.deposit("Ulrika",new Money(350 , SEK));
		assertEquals(Integer.valueOf(350),SweBank.getBalance("Ulrika"));

		SweBank.deposit("Bob",new Money(1500 , SEK));
		assertEquals(Integer.valueOf(1500),SweBank.getBalance("Bob"));
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		testDeposit();
		SweBank.withdraw("Bob",new Money(150 , SEK));
		assertEquals(Integer.valueOf(1350),SweBank.getBalance("Bob"));
		SweBank.withdraw("Ulrika",new Money(350 , SEK));
		assertEquals(Integer.valueOf(0),SweBank.getBalance("Ulrika"));
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		assertEquals(Integer.valueOf(0),Nordea.getBalance("Bob"));
		assertEquals(Integer.valueOf(0),SweBank.getBalance("Ulrika"));
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		SweBank.deposit("Ulrika",new Money(350 , SEK));
		SweBank.transfer("Ulrika",SweBank,"Bob",new Money(350,SEK));
		assertEquals(Integer.valueOf(0),SweBank.getBalance("Ulrika"));
		assertEquals(Integer.valueOf(350),SweBank.getBalance("Bob"));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		SweBank.addTimedPayment("Bob", "1", 5, 5, new Money(1000, SEK), SweBank, "Ulrika");
		for (int i = 0; i < 10; i++) {
			SweBank.tick();
		}
		assertEquals(Integer.valueOf(-1000), SweBank.getBalance("Bob"));
		assertEquals(Integer.valueOf(1000), SweBank.getBalance("Ulrika"));
		SweBank.removeTimedPayment("Bob","test1");
		SweBank.tick();
	}
}
