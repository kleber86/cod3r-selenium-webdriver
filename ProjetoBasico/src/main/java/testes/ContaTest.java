package testes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import core.BaseTest;
import pages.ContasPage;
import pages.MenuPage;

public class ContaTest extends BaseTest{

	MenuPage menuPage = new MenuPage();
	ContasPage contasPage = new ContasPage();
	
	@Test
	public void testInserirConta() {
		menuPage.acessarTelaInserirConta();
		
		contasPage.setNome("Conta de Teste");
		contasPage.salvar();
		
		assertEquals("Conta adicionada com sucesso!", contasPage.obterMensagemSucesso());
	}
	
	@Test
	public void testAlterarConta() {
		menuPage.acessarTelaListarConta();
		
		contasPage.clicarAlteraConta("Conta de Teste");
		contasPage.setNome("Conta de Teste Alterada");
		contasPage.salvar();
		
		assertEquals("Conta alterada com sucesso!", contasPage.obterMensagemSucesso());
	}
	
	@Test
	public void testInserirContaMesmoNome() {
		menuPage.acessarTelaInserirConta();
		
		contasPage.setNome("Conta de Teste Alterada");
		contasPage.salvar();
		
		assertEquals("Já existe uma conta com esse nome!", contasPage.obterMensagemErro());
	}
	
	@Test
	public void testExcluirContaComMovimentacao() {
		menuPage.acessarTelaListarConta();
		
		contasPage.clicarExcluirConta("Conta de Teste Alterada");
		
		assertEquals("Conta em uso na movimentações", contasPage.obterMensagemErro());
	}
}
