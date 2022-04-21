package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

//private static WebDriver driver;
	private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>() {
		@Override
		protected synchronized WebDriver initialValue() {
			return initDriver();
		}
	};

	private DriverFactory() {
	}

	public static WebDriver getDriver() {
		return threadDriver.get();
	}

	public static WebDriver initDriver() {
		WebDriver driver = null;
		switch (Propriedades.browsers) {
		case FIREFOX:
			System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver\\0.30.0\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case CHROME:
			System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver\\99.0\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		}

		driver.manage().window().maximize();
		return driver;
	}

	public static void killDriver() {
		WebDriver driver = getDriver();
		if (driver != null) {
			driver.close();
			driver = null;
		}
		
		if(threadDriver != null) {
			threadDriver.remove();
		}
	}
}
