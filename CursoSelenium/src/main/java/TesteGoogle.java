import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class TesteGoogle {

	@Test
	public void testFirefox() {
		System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver\\0.30.0\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(800, 600));
		driver.get("https:www.google.com.br");
		assertEquals("Google", driver.getTitle());
		driver.close();
	}
	
	@Test
	public void testChrome() {
		System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver\\99.0\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https:www.google.com.br");
		assertEquals("Google", driver.getTitle());
		driver.close();
	}
}
