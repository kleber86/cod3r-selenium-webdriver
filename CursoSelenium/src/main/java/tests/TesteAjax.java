package tests;
import static core.DriverFactory.getDriver;
import static core.DriverFactory.killDriver;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.DSL;
import page.CampoTreinamentoPage;

public class TesteAjax {

	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;
	
	@Before
	public void inicializa() {
		System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver\\0.30.0\\geckodriver.exe");
		getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml?jfwid=9cec7");
		dsl = new DSL();
	}
	
	@After
	public void finaliza() {
		killDriver();
	}
	
	@Test
	public void testAjax() {
		dsl.escreve("j_idt311:name", "Kleber");
		dsl.clicarBotao("j_idt311:j_idt315");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.textToBe(By.id("j_idt311:display"), "Kleber"));
		assertEquals("Kleber", dsl.obterTexto("j_idt311:display"));
	}
}
