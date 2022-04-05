package pages;

import core.BasePage;

public class MenuPage extends BasePage{

	public void acessarTelaInserirConta() {
		clicarLink("Contas");
		clicarLink("Adicionar");
	}
}
