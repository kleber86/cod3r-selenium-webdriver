package testes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import core.BaseTest;
import pages.ContasPage;
import pages.MenuPage;

public class RemoverMovimentacaoContaTest extends BaseTest {
	private MenuPage menuPage = new MenuPage();
	private ContasPage contasPage = new ContasPage();
	
	
	@Test
	public void testExcluirContaComMovimentacao() {
		menuPage.acessarTelaListarConta();
		
		contasPage.clicarExcluirConta("Conta de Teste Alterada");
		
		assertEquals("Conta em uso na movimenta��es", contasPage.obterMensagemErro());
	}
}
