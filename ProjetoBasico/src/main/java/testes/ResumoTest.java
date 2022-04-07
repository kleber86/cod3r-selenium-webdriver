package testes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import core.BaseTest;
import pages.MenuPage;
import pages.ResumoPage;

public class ResumoTest extends BaseTest {

	private MenuPage menuPage = new MenuPage();
	private ResumoPage resumoPage = new ResumoPage();
	
	@Test
	public void testRemoverMovimentacao() {
		menuPage.acessarTelaResumo();
		
		resumoPage.excluirMovimentacao();
		assertEquals("Movimentação removida com sucesso!", resumoPage.obterMensagemSucesso());
	}
}
