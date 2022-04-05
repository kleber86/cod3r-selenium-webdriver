package tests;
import static core.DriverFactory.getDriver;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import core.DSL;

public class TestePrime {

	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializa() {
		System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver\\0.30.0\\geckodriver.exe");
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
		dsl = new DSL();
	}
	
	@After
	public void finaliza() {
		driver.close();
	}
	
	@Test
	public void deveInteragirComRadioPrime() {
		dsl.clicarRadio(By.xpath("//input[@id='j_idt312:console:0']/../..//span"));
		assertTrue(dsl.isRadioMarcado("j_idt312:console:0"));
		
		dsl.clicarRadio(By.xpath("//label[.='Option2']/..//span"));
		assertTrue(dsl.isRadioMarcado("j_idt312:console:1"));
	}
	
	@Test
	public void deveSelecionarCombro() throws InterruptedException {
		driver.get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
		
		dsl.clicarRadio(By.xpath("//*[@id='j_idt311:option_label']/../div/span"));
		dsl.clicarRadio(By.xpath("//*[@id='j_idt311:option_1']"));
	}
	
}
