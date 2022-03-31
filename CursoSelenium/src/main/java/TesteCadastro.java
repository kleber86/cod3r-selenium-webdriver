import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastro {

	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializa() {
		System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver\\0.30.0\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(900, 600));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/site/componentes.html");
		dsl = new DSL(driver);
	}
	
	@After
	public void finaliza() {
		driver.close();
	}
	

	@Test
	public void devePreencherOFormulario() {
		dsl.escreve("elementosForm:nome", "Kleber");
		dsl.escreve("elementosForm:sobrenome", "Alves");
		
		dsl.clicarRadio("elementosForm:sexo:0");
		
		dsl.clicarRadio("elementosForm:comidaFavorita:2");
		
		dsl.selecionarCombo("elementosForm:escolaridade", "Superior");
		dsl.selecionarCombo("elementosForm:esportes", "Superior");
		
		dsl.clicarBotao("elementosForm:cadastrar");
		
		assertEquals("Cadastrado!", driver.findElement(By.cssSelector("#resultado span")).getText());
		assertEquals("Kleber", driver.findElement(By.cssSelector("#descNome span")).getText());
		assertEquals("Alves", driver.findElement(By.cssSelector("#descSobrenome span")).getText());
		assertEquals("Masculino", driver.findElement(By.cssSelector("#descSexo span")).getText());
		assertEquals("Pizza", driver.findElement(By.cssSelector("#descComida span")).getText());
		assertEquals("superior", driver.findElement(By.cssSelector("#descEscolaridade span")).getText());
		assertEquals("Natacao", driver.findElement(By.cssSelector("#descEsportes span")).getText());
	}
	
	@Test
	public void deveValidarNomeObrigatorio() {
		dsl.clicarBotao("elementosForm:cadastrar");
		Alert alert = driver.switchTo().alert();
		assertEquals("Nome eh obrigatorio", alert.getText());
		alert.accept();
	}
	
	@Test
	public void deveValidarSobreNomeObrigatorio() {
		dsl.escreve("elementosForm:nome", "Kleber");
		dsl.clicarBotao("elementosForm:cadastrar");
		Alert alert = driver.switchTo().alert();
		assertEquals("Sobrenome eh obrigatorio", alert.getText());
		alert.accept();
	}
	
	@Test
	public void deveValidarSexoObrigatorio() {
		dsl.escreve("elementosForm:nome", "Kleber");
		dsl.escreve("elementosForm:sobrenome", "Alves");
		dsl.clicarBotao("elementosForm:cadastrar");
		Alert alert = driver.switchTo().alert();
		assertEquals("Sexo eh obrigatorio", alert.getText());
		alert.accept();
	}
	
	@Test
	public void deveValidarComidaVegetariana() {
		dsl.escreve("elementosForm:nome", "Kleber");
		dsl.escreve("elementosForm:sobrenome", "Alves");
		dsl.clicarBotao("elementosForm:cadastrar");
		dsl.clicarBotao("elementosForm:comidaFavorita:3");
		dsl.clicarBotao("elementosForm:comidaFavorita:0");
		
		dsl.clicarBotao("elementosForm:cadastrar");
		Alert alert = driver.switchTo().alert();
		assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
		alert.accept();
	}
	
	@Test
	public void deveValidarEsportes() {
		dsl.escreve("elementosForm:nome", "Kleber");
		dsl.escreve("elementosForm:sobrenome", "Alves");
		dsl.clicarBotao("elementosForm:cadastrar");
		dsl.clicarBotao("elementosForm:comidaFavorita:0");
		
		Select combo = new Select(driver.findElement(By.id("elementosForm:esportes")));
		combo.selectByVisibleText("Karate");
		combo.selectByVisibleText("O que eh esporte?");
		
		dsl.clicarBotao("elementosForm:cadastrar");
		Alert alert = driver.switchTo().alert();
		assertEquals("Voce faz esporte ou nao?", alert.getText());
		alert.accept();
	}
}
