import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteAlert {

	@Test
	public void deveInteragirComAlertSimples() {
		System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver\\0.30.0\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1000, 800));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/site/componentes.html");
	
		driver.findElement(By.id("alert")).click();
		Alert alert = driver.switchTo().alert();
		String texto = alert.getText();
		assertEquals("Alert Simples", texto);
		alert.accept();
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
		
		driver.close();
	}
	
	@Test
	public void deveInteragirComAlertConfirm() {
		System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver\\0.30.0\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1000, 800));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/site/componentes.html");
	
		driver.findElement(By.id("confirm")).click();
		Alert alerta = driver.switchTo().alert();
		assertEquals("Confirm Simples", alerta.getText());
		alerta.accept();
		assertEquals("Confirmado", alerta.getText());
		alerta.accept();
		
		driver.findElement(By.id("confirm")).click();
		alerta = driver.switchTo().alert();
		assertEquals("Confirm Simples", alerta.getText());
		alerta.dismiss();
		assertEquals("Negado", alerta.getText());
		alerta.dismiss();
		
		driver.close();
	}
	
	@Test
	public void deveInteragirComAlertPrompt() {
		System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver\\0.30.0\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1000, 800));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/site/componentes.html");
	
		driver.findElement(By.id("prompt")).click();
		Alert alerta = driver.switchTo().alert();
		assertEquals("Digite um numero", alerta.getText());
		alerta.sendKeys("12");
		alerta.accept();
		assertEquals("Era 12?", alerta.getText());
		alerta.accept();
		assertEquals(":D", alerta.getText());
		alerta.accept();
		
		driver.close();
	}
}
