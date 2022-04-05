package tests;
import static core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import core.BaseTest;
import page.CampoTreinamentoPage;

public class TesteCadastro extends BaseTest{

	private CampoTreinamentoPage page;
	
	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/site/componentes.html");
		page = new CampoTreinamentoPage();
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
