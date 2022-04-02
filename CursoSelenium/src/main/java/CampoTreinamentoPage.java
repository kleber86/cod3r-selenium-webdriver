import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {

	private DSL dsl;
	
	public CampoTreinamentoPage(WebDriver driver) {
		dsl = new DSL(driver);
	}
	
	public void setNome(String nome) {
		dsl.escreve("elementosForm:nome", nome);
	}
	
	public void setSobreNome(String sobreNome) {
		dsl.escreve("elementosForm:sobrenome", sobreNome);
	}
	
	public void setSexoMasculino() {
		dsl.clicarRadio("elementosForm:sexo:0");
	}
	
	public void setSexoFeminino() {
		dsl.clicarRadio("elementosForm:sexo:1");
	}
	
	public void setComidaCarne() {
		dsl.clicarBotao("elementosForm:comidaFavorita:0");
	}
	
	public void setComidaPizza() {
		dsl.clicarBotao("elementosForm:comidaFavorita:2");
	}
	
	public void setComidaVegetariano() {
		dsl.clicarBotao("elementosForm:comidaFavorita:3");
	}
	public void setPizza() {
		dsl.clicarBotao("elementosForm:comidaFavorita:2");
	}
	
	public void setEscolaridade(String nome) {
		dsl.selecionarCombo("elementosForm:escolaridade", nome);
	}
	
	public void setEsportes(String... valores) {
		for(String valor: valores) {
			dsl.selecionarCombo("elementosForm:esportes", valor);	
		}
	}
	
	public void cadastrar() {
		dsl.clicarBotao("elementosForm:cadastrar");
	}
	
	public String obterResultadoCadastro() {
		return dsl.obterTexto("resultado");
	}
	
	public String obterNomeCadastro() {
		return dsl.obterTexto("descNome");
	}
	
	public String obterSobreNomeCadastro() {
		return dsl.obterTexto("descSobreNome");
	}
	
	
	public String obterSexoCadastro() {
		return dsl.obterTexto("descSexo");
	}
	
	public String obterComidaCadastro() {
		return dsl.obterTexto("descComida");
	}
	
	public String obterEscolaridadeCadastro() {
		return dsl.obterTexto("descEscolaridade");
	}
	
	public String obterEsporteCadastro() {
		return dsl.obterTexto("descEsportes");
	}
}
