package testes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import core.BaseTest;
import pages.HomePage;

public class SaldoTest extends BaseTest {

	HomePage homePage = new HomePage();
	
	@Test
	public void testSaldoConta() {
		assertEquals("13015103.00", homePage.obterSaldoConta("Conta de Teste Alterada"));
	}
}
