package tests;
import static core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import core.DSL;

public class TesteFramesEJanelas {

	private DSL dsl;
	
	@Before
	public void inicializa() {
		System.setProperty("webgetDriver().gecko.driver", "C:\\drivers\\geckodriver\\0.30.0\\geckogetDriver().exe");
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/site/componentes.html");
		dsl = new DSL();
	}
	
	@After
	public void finaliza() {
		getDriver().close();
	}
	
	@Test
	public void devePreencherFrames() {
		getDriver().switchTo().frame("frame1");
		getDriver().findElement(By.id("frameButton")).click();
		
		Alert alert = getDriver().switchTo().alert();
		String msg = alert.getText();
		assertEquals("Frame OK!", msg);
		alert.accept();
		
		getDriver().switchTo().defaultContent();
		
		getDriver().findElement(By.id("elementosForm:nome")).sendKeys(msg);
	}
	
	@Test
	public void devePreencherJanelas() {
		getDriver().findElement(By.id("buttonPopUpEasy")).click();
		getDriver().switchTo().window(((String)getDriver().getWindowHandles().toArray()[1]));
		getDriver().findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		getDriver().close();
		getDriver().switchTo().window(((String)getDriver().getWindowHandles().toArray()[0]));
		getDriver().findElement(By.tagName("textarea")).sendKeys("e agora?");
	}
	
	@Test
	public void deveInteragirComJanelaSemTitulo() {
		getDriver().findElement(By.id("buttonPopUpEasy")).click();
		System.out.println(getDriver().getWindowHandle());
		System.out.println(getDriver().getWindowHandles());
		getDriver().switchTo().window(((String)getDriver().getWindowHandles().toArray()[1]));
		getDriver().findElement(By.tagName("textarea")).sendKeys("Deu certo?");

		getDriver().switchTo().window(((String)getDriver().getWindowHandles().toArray()[0]));
		getDriver().findElement(By.tagName("textarea")).sendKeys("E agora?");
	}
	
	@Test
	public void deveInteragirComFrameEscondido() {
		WebElement frame = getDriver().findElement(By.id("frame2"));
		dsl.execuJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
		dsl.entrarFrame("frame2");
		dsl.clicarBotao("frameButton");
		
		String msg = dsl.alertaObterTextoAceito();
		assertEquals("Frame OK!", msg);
	}
}
