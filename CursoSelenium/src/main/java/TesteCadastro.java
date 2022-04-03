import static core.DriverFactory.getDriver;
import static core.DriverFactory.killDriver;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TesteCadastro {

	private CampoTreinamentoPage page;
	
	@Before
	public void inicializa() {
		System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver\\0.30.0\\geckodriver.exe");

		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/site/componentes.html");
		page = new CampoTreinamentoPage();
	}
	
	@After
	public void finaliza() {
		killDriver();
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
		
		assertEquals("Cadastrado!", page.obterResultadoCadastro());
		assertEquals("Kleber", page.obterNomeCadastro());
		assertEquals("Alves", page.obterSobreNomeCadastro());
		assertEquals("Masculino", page.obterSexoCadastro());
		assertEquals("Pizza", page.obterComidaCadastro());
		assertEquals("superior", page.obterEscolaridadeCadastro());
		assertEquals("Natacao", page.obterEsporteCadastro());
		
		}
}
