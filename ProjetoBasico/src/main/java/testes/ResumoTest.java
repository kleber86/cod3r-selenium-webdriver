package testes;

import static core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import core.BaseTest;
import core.DriverFactory;
import pages.MenuPage;
import pages.ResumoPage;

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

		resumoPage.selecionarAno("2020");
		resumoPage.buscar();
		
		List<WebElement> elementosEncontrados =
		DriverFactory.getDriver().findElements(By.xpath("//*[@id='tabelaExtrato']/tbody/tr"));

		assertEquals(0, elementosEncontrados.size());
	}
}
