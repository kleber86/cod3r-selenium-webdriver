import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestePrime {

	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializa() {
		System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver\\0.30.0\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(900, 600));
		driver.get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
		dsl = new DSL(driver);
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
	
}
