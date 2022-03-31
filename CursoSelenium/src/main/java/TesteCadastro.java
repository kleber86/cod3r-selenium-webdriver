import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastro {

	@Test
	public void devePreencherOFormulario() {
		System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver\\0.30.0\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1000, 800));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/site/componentes.html");
	
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
		
		driver.close();
		
	}
	
	@Test
	public void deveValidarNomeObrigatorio() {
		System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver\\0.30.0\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1000, 800));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/site/componentes.html");
	
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		assertEquals("Nome eh obrigatorio", alert.getText());
		alert.accept();
		driver.close();
	}
	
	@Test
	public void deveValidarSobreNomeObrigatorio() {
		System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver\\0.30.0\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1000, 800));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/site/componentes.html");
	
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Kleber");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		assertEquals("Sobrenome eh obrigatorio", alert.getText());
		alert.accept();
		driver.close();
	}
	
	@Test
	public void deveValidarSexoObrigatorio() {
		System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver\\0.30.0\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1000, 800));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/site/componentes.html");
	
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Kleber");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Alves");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		assertEquals("Sexo eh obrigatorio", alert.getText());
		alert.accept();
		driver.close();
	}
	
	@Test
	public void deveValidarComidaVegetariana() {
		System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver\\0.30.0\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1000, 800));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/site/componentes.html");
	
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Kleber");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Alves");
		driver.findElement(By.id("elementosForm:sexo")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
		alert.accept();
		driver.close();
	}
	
	@Test
	public void deveValidarEsportes() {
		System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver\\0.30.0\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1000, 800));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/site/componentes.html");
	
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
		driver.close();
	}
}
