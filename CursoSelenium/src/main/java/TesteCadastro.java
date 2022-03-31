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
	public void devePreencherOFormulario() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Kleber");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Alves");
		
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		
		new Select(driver.findElement(By.id("elementosForm:escolaridade")))
		.selectByVisibleText("Superior");
		
		new Select(driver.findElement(By.id("elementosForm:esportes")))
		.selectByVisibleText("Natacao");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
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
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		assertEquals("Nome eh obrigatorio", alert.getText());
		alert.accept();
	}
	
	@Test
	public void deveValidarSobreNomeObrigatorio() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Kleber");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		assertEquals("Sobrenome eh obrigatorio", alert.getText());
		alert.accept();
	}
	
	@Test
	public void deveValidarSexoObrigatorio() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Kleber");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Alves");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		assertEquals("Sexo eh obrigatorio", alert.getText());
		alert.accept();
	}
	
	@Test
	public void deveValidarComidaVegetariana() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Kleber");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Alves");
		driver.findElement(By.id("elementosForm:sexo")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
		alert.accept();
	}
	
	@Test
	public void deveValidarEsportes() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Kleber");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Alves");
		driver.findElement(By.id("elementosForm:sexo")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		
		Select combo = new Select(driver.findElement(By.id("elementosForm:esportes")));
		combo.selectByVisibleText("Karate");
		combo.selectByVisibleText("O que eh esporte?");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		assertEquals("Voce faz esporte ou nao?", alert.getText());
		alert.accept();
	}
}
