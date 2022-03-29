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
		
		//driver.close();
	}
}
