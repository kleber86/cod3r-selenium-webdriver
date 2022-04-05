package tests;
import static core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TesteAlert {
	
	@Before
	public void inicializa() {
		System.setProperty("webgetDriver().gecko.driver", "C:\\drivers\\geckodriver\\0.30.0\\geckogetDriver().exe");
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/site/componentes.html");
	}
	
	@After
	public void finaliza() {
		core.DriverFactory.killDriver();
	}
	
	@Test
	public void deveInteragirComAlertSimples() {
		getDriver().findElement(By.id("alert")).click();
		Alert alert = getDriver().switchTo().alert();
		String texto = alert.getText();
		assertEquals("Alert Simples", texto);
		alert.accept();
		
		getDriver().findElement(By.id("elementosForm:nome")).sendKeys(texto);
	}
	
	@Test
	public void deveInteragirComAlertConfirm() {
		getDriver().findElement(By.id("confirm")).click();
		Alert alerta = getDriver().switchTo().alert();
		assertEquals("Confirm Simples", alerta.getText());
		alerta.accept();
		assertEquals("Confirmado", alerta.getText());
		alerta.accept();
		
		getDriver().findElement(By.id("confirm")).click();
		alerta = getDriver().switchTo().alert();
		assertEquals("Confirm Simples", alerta.getText());
		alerta.dismiss();
		assertEquals("Negado", alerta.getText());
		alerta.dismiss();
	}
	
	@Test
	public void deveInteragirComAlertPrompt() {
		getDriver().findElement(By.id("prompt")).click();
		Alert alerta = getDriver().switchTo().alert();
		assertEquals("Digite um numero", alerta.getText());
		alerta.sendKeys("12");
		alerta.accept();
		assertEquals("Era 12?", alerta.getText());
		alerta.accept();
		assertEquals(":D", alerta.getText());
		alerta.accept();
	}
}
