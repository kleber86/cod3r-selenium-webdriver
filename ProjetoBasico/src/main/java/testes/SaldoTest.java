package testes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import core.BaseTest;
import pages.HomePage;
import pages.MenuPage;

public class SaldoTest extends BaseTest {

	HomePage homePage = new HomePage();
	MenuPage menuPage = new MenuPage();
	
	@Test
	public void testSaldoConta() {
		menuPage.acessarTelaPrincipal();
		assertEquals("534.00", homePage.obterSaldoConta("Conta para saldo"));
	}
}
