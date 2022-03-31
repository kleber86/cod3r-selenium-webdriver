import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteFramesEJanelas {

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
	public void devePreencherFrames() {
		driver.switchTo().frame("frame1");
		driver.findElement(By.id("frameButton")).click();
		
		Alert alert = driver.switchTo().alert();
		String msg = alert.getText();
		assertEquals("Frame OK!", msg);
		alert.accept();
		
		driver.switchTo().defaultContent();
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);
	}
	
	@Test
	public void devePreencherJanelas() {
		driver.findElement(By.id("buttonPopUpEasy")).click();
		driver.switchTo().window(((String)driver.getWindowHandles().toArray()[1]));
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		driver.close();
		driver.switchTo().window(((String)driver.getWindowHandles().toArray()[0]));
		driver.findElement(By.tagName("textarea")).sendKeys("e agora?");
	}
	
	@Test
	public void deveInteragirComJanelaSemTitulo() {
		driver.findElement(By.id("buttonPopUpEasy")).click();
		System.out.println(driver.getWindowHandle());
		System.out.println(driver.getWindowHandles());
		driver.switchTo().window(((String)driver.getWindowHandles().toArray()[1]));
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");

		driver.switchTo().window(((String)driver.getWindowHandles().toArray()[0]));
		driver.findElement(By.tagName("textarea")).sendKeys("E agora?");
	}
}
