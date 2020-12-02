package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() {
		assertEquals("SEK",SEK.getName());
		assertEquals("DKK",DKK.getName());
		assertEquals("EUR",EUR.getName());
	}

	@Test
	public void testGetRate() {
		assertEquals(0.15,SEK.getRate(),0);
		assertEquals(0.20,DKK.getRate(),0);
		assertEquals(1.5,EUR.getRate(),0);
	}
	
	@Test
	public void testSetRate() {
		double sekNewRate = 0.20;
		double dkkNewRate = 0.15;
		double eurNewRate = 1.25;
		//after set rate
		SEK.setRate(sekNewRate);
		assertEquals(sekNewRate,SEK.getRate(),0);

		DKK.setRate(dkkNewRate);
		assertEquals(dkkNewRate,DKK.getRate(),0);

		EUR.setRate(eurNewRate);
		assertEquals(eurNewRate,EUR.getRate(),0);
	}
	
	@Test
	public void testGlobalValue() {
		assertEquals(Integer.valueOf(60),DKK.universalValue(3));
		assertEquals(Integer.valueOf(150),SEK.universalValue(10));
		assertEquals(Integer.valueOf(1050),EUR.universalValue(7));
	}
	
	@Test
	public void testValueInThisCurrency() {
		assertEquals(Integer.valueOf(20),SEK.valueInThisCurrency(15,DKK));
		assertEquals(Integer.valueOf(5625),DKK.valueInThisCurrency(750,EUR));
	}
}
