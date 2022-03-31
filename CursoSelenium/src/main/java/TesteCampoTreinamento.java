import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCampoTreinamento {
	private WebDriver driver;
	
	@Before
	public void inicializa() {
		System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver\\0.30.0\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(900, 600));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/site/componentes.html");
	}
	
	@After
	public void finaliza() {
		driver.close();
	}
	
	@Test
	public void testeTextField() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Texto Um");
		assertEquals("Texto Um", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));;
	}
	
	@Test
	public void deveInteragirComTextArea() {
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Test");
		assertEquals("Test", driver.findElement(By.id("elementosForm:sobrenome")).getAttribute("value"));
	}
	
	@Test
	public void deveInteragirComRadioButton() {
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
	}
	
	@Test
	public void deveInteragirComCheckbox() {
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected());
	}
	
	@Test
	public void deveInteragirComCombo() {
		WebElement elemento = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(elemento);
		//combo.selectByIndex(1);
		//combo.selectByValue("superior");
		combo.selectByVisibleText("2o grau completo");
		
		assertEquals("2o grau completo", combo.getFirstSelectedOption().getText());
	}
	
	@Test
	public void deveVerificarValoresCombo() {
		WebElement elemento = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(elemento);
		List<WebElement> options = combo.getOptions();
		
		assertEquals(8, options.size());
		
		boolean encontrou = false;
		for(WebElement option: options) {
			if(option.getText().equals("Mestrado")) {
				encontrou = true;
				break;
			}
		}
		
		assertTrue(encontrou);
	}
	
	@Test
	public void deveVerificarValoresComboMultiplo() {
		WebElement elemento = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(elemento);
		
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Futebol");
		combo.selectByVisibleText("Karate");
		
		List<WebElement> todosOsValores = combo.getAllSelectedOptions();
		assertEquals(3, todosOsValores.size());
	}
	
	@Test
	public void deveInteragirComBotoes() {
		WebElement botao = driver.findElement(By.id("buttonSimple"));
		botao.click();
		assertEquals("Obrigado!", botao.getAttribute("value"));
	}
	
	@Test
	public void deveInteragirComLinks() {
		driver.findElement(By.linkText("Voltar")).click();
		assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());
	}
	
	@Test
	public void deveBuscarTextosNaPagina() {
		assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());
		assertEquals("Cuidado onde clica, muitas armadilhas...", driver.findElement(By.className("facilAchar")).getText());
	}
}
