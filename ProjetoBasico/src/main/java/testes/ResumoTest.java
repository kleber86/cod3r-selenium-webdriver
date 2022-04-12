package testes;

import static core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import core.BaseTest;
import pages.MenuPage;
import pages.ResumoPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ResumoTest extends BaseTest {

	private MenuPage menuPage = new MenuPage();
	private ResumoPage resumoPage = new ResumoPage();
	
	@Test
	public void test1RemoverMovimentacao() {
		menuPage.acessarTelaResumo();
		
		resumoPage.excluirMovimentacao();
		assertEquals("Movimentação removida com sucesso!", resumoPage.obterMensagemSucesso());
	}
	
	@Test
	public void test2ResumoMensal() {
		menuPage.acessarTelaResumo();
		
		assertEquals("Seu Barriga - Extrato", getDriver().getTitle());
	}
}
