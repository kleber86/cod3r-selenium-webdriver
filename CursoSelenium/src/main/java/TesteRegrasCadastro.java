import static core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import core.DriverFactory;

@RunWith(Parameterized.class)
public class TesteRegrasCadastro {

	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;
	
	@Parameter
	public String nome;
	
	@Parameter(value = 1)
	public String sobreNome;
	
	@Parameter(value = 2)
	public String sexo;
	
	@Parameter(value = 3)
	public List<String> comidas;
	
	@Parameter(value = 4)
	public String[] esportes;
	
	@Parameter(value = 5)
	public String msg;
	
	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/site/componentes.html");
		dsl = new DSL();
		page = new CampoTreinamentoPage();
	}
	
	@After
	public void finaliza() {
		DriverFactory.killDriver();
	}
	
	@Parameters
	public static Collection<Object[]> getCollection(){
		return Arrays.asList(new Object[][] {
			{"", "", "", Arrays.asList(), new String[] {}, "Nome eh obrigatorio"},
			{"Kleber", "", "", Arrays.asList(), new String[] {}, "Sobrenome eh obrigatorio"},
			{"Kleber", "Alves", "", Arrays.asList(), new String[] {}, "Sexo eh obrigatorio"},
			{"Kleber", "Alves", "Masculino", Arrays.asList("Carne", "Vegetariano"), new String[] {}, "Tem certeza que voce eh vegetariano?"},
			{"Kleber", "Alves", "Masculino", Arrays.asList(), new String[] {"Karate", "O que eh esporte?"}, "Voce faz esporte ou nao?"},
		});
	}
	
	@Test
	public void deveValidarRegras() {
		page.setNome(nome);
		page.setSobreNome(sobreNome);
		if(sexo.equals("Masculino")) {
			page.setSexoMasculino();
		} 
		if (sexo.equals("Feminino")) {
			page.setSexoFeminino();	
		}
		
		if (comidas.contains("Carne")) page.setComidaCarne();
		if (comidas.contains("Pizza")) page.setComidaPizza();
		if (comidas.contains("Vegetariano")) page.setComidaVegetariano();
		
		page.setEsportes(esportes);
		page.cadastrar();
		
		System.out.println(msg);
		Alert alert = getDriver().switchTo().alert();
		assertEquals(msg, alert.getText());
		alert.accept();
	}
}
