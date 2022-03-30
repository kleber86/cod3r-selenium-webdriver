import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteFramesEJanelas {

	@Test
	public void devePreencherFrames() {
		System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver\\0.30.0\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1000, 800));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/site/componentes.html");
	
		driver.switchTo().frame("frame1");
		driver.findElement(By.id("frameButton")).click();
		
		Alert alert = driver.switchTo().alert();
		String msg = alert.getText();
		assertEquals("Frame OK!", msg);
		alert.accept();
		
		driver.switchTo().defaultContent();
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);
		
		driver.close();
	}
	
	@Test
	public void devePreencherJanelas() {
		System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver\\0.30.0\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1000, 800));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/site/componentes.html");

		driver.findElement(By.id("buttonPopUpEasy")).click();
		driver.switchTo().window("Popup");
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		driver.close();
		driver.switchTo().window("");
		driver.findElement(By.tagName("textarea")).sendKeys("e agora?");
	}
}
