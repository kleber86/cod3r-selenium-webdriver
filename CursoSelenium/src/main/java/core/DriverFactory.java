package core;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
	
private static WebDriver driver;
	
	private DriverFactory() {}
	
	public static WebDriver getDriver(){
		System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver\\0.30.0\\geckodriver.exe");
		if(driver == null) {
			driver = new FirefoxDriver();
			driver.manage().window().setSize(new Dimension(1200, 765));			
		}
		return driver;
	}

	public static void killDriver(){
		if(driver != null) {
			driver.close();
			driver = null;
		}
	}
}
