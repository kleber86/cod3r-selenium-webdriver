import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCampoTreinamento {

	@Test
	public void testeTextField() {
		System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver\\0.30.0\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(900, 600));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/site/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Texto Um");
		assertEquals("Texto Um", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));;
		
		driver.close();
	}
	
	@Test
	public void deveInteragirComTextArea() {
		System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver\\0.30.0\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(900, 600));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/site/componentes.html");
		
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Test");
		assertEquals("Test", driver.findElement(By.id("elementosForm:sobrenome")).getAttribute("value"));
	
		driver.close();
	}
	
	@Test
	public void deveInteragirComRadioButton() {
		System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver\\0.30.0\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(900, 600));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/site/componentes.html");
		
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
		
		driver.close();
	}
	
	@Test
	public void deveInteragirComCheckbox() {
		System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver\\0.30.0\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(900, 600));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/site/componentes.html");
		
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected());
		
		driver.close();
	}
	
	@Test
	public void deveInteragirComCombo() {
		System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver\\0.30.0\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(900, 600));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/site/componentes.html");
		
		WebElement elemento = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(elemento);
		//combo.selectByIndex(1);
		//combo.selectByValue("superior");
		combo.selectByVisibleText("2o grau completo");
		
		assertEquals("2o grau completo", combo.getFirstSelectedOption().getText());
		driver.close();
	}
	
	@Test
	public void deveVerificarValoresCombo() {
		System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver\\0.30.0\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(900, 600));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/site/componentes.html");
		
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
		driver.close();
	}
}
