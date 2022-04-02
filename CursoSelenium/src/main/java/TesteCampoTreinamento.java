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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCampoTreinamento {
	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializa() {
		System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver\\0.30.0\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().fullscreen();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/site/componentes.html");
		dsl = new DSL(driver);
	}
	
	@After
	public void finaliza() {
		//driver.close();
	}
	
	@Test
	public void testeTextField() {
		dsl.escreve("elementosForm:nome", "Texto Um");
		assertEquals("Texto Um", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void deveInteragirComTextArea() {
		dsl.escreve("elementosForm:sobrenome", "Test");
		assertEquals("Test", dsl.obterValorCampo("elementosForm:sobrenome"));
	}
	
	@Test
	public void deveInteragirComRadioButton() {
		dsl.clicarRadio("elementosForm:sexo:0");
		assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
	}
	
	@Test
	public void deveInteragirComCheckbox() {
		dsl.clicarRadio("elementosForm:comidaFavorita:2");
		assertTrue(dsl.isRadioMarcado("elementosForm:comidaFavorita:2"));
	}
	
	@Test
	public void deveInteragirComCombo() {
		dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
		assertEquals("2o grau completo", dsl.obterValorCombo("elementosForm:escolaridade"));
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
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		
		WebElement elemento = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(elemento);
		
		List<WebElement> todosOsValores = combo.getAllSelectedOptions();
		assertEquals(3, todosOsValores.size());
	}
	
	@Test
	public void deveInteragirComBotoes() {
		dsl.clicarBotao("buttonSimple");

		WebElement botao = driver.findElement(By.id("buttonSimple"));
		assertEquals("Obrigado!", botao.getAttribute("value"));
	}
	
	@Test
	public void deveInteragirComLinks() {
		dsl.clicarLink("Voltar");
		assertEquals("Voltou!", dsl.obterTexto("resultado"));
	}
	
	@Test
	public void deveBuscarTextosNaPagina() {
		assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
		assertEquals("Cuidado onde clica, muitas armadilhas...", 
				dsl.obterTexto(By.className("facilAchar")));
	}
	
	@Test
	public void testJavaScript() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");
		WebElement element = driver.findElement(By.id("elementosForm:nome"));
		js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 5px red");
	}
	
	@Test
	public void deveClicarBotaoTabela() {
		dsl.clicarBotaoTabela("Escolaridade", "Mestrado", "Radio", "elementosForm:tableUsuarios");
	}
}
