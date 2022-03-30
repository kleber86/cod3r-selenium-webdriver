import static org.junit.Assert.assertEquals;

import org.junit.Test;
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
}
