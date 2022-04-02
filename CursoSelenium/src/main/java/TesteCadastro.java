import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastro {

	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;
	
	@Before
	public void inicializa() {
		System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver\\0.30.0\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(900, 600));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/site/componentes.html");
		dsl = new DSL(driver);
		page = new CampoTreinamentoPage(driver);
	}
	
	@After
	public void finaliza() {
		driver.close();
	}
	

	@Test
	public void devePreencherOFormulario() {
		page.setNome("Kleber");
		page.setSobreNome("Alves");
		
		page.setSexoMasculino();
		page.setPizza();
		
		page.setEscolaridade("Superior");
		page.setEsportes("Natacao");
		
		page.cadastrar();
		
		assertTrue(page.obterResultadoCadastro().startsWith("Cadastrado!"));
		assertTrue(page.obterNomeCadastro().endsWith("Kleber"));
		assertEquals("Sobrenome: Alves", page.obterSobreNomeCadastro());
		assertEquals("Sexo: Masculino", page.obterSexoCadastro());
		assertEquals("Comida: Pizza", page.obterComidaCadastro());
		assertEquals("Escolaridade: superior", page.obterEscolaridadeCadastro());
		assertEquals("Esportes: Natacao", page.obterEsporteCadastro());
		
		
		assertEquals("Alves", driver.findElement(By.cssSelector("#descSobrenome span")).getText());
		assertEquals("Masculino", driver.findElement(By.cssSelector("#descSexo span")).getText());
		assertEquals("Pizza", driver.findElement(By.cssSelector("#descComida span")).getText());
		assertEquals("superior", driver.findElement(By.cssSelector("#descEscolaridade span")).getText());
		assertEquals("Natacao", driver.findElement(By.cssSelector("#descEsportes span")).getText());
	}
}
