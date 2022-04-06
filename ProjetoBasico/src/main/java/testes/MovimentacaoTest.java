package testes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import core.BaseTest;
import pages.MenuPage;
import pages.MovimentacaoPage;

public class MovimentacaoTest extends BaseTest{

	private MenuPage menuPage = new MenuPage();
	private MovimentacaoPage mp = new MovimentacaoPage();
	
	@Test
	public void testInserirMovimenta��o() {
		menuPage.acessarTelaInserirMovimentacao();
		
		mp.setDataMovimentacao("06/04/2022");
		mp.setDataPagamento("06/04/2022");
		mp.setDescricao("Movimenta��o Teste");
		mp.setInteressado("Interessado Qualquer");
		mp.setValor("123456");
		mp.setConta("Conta de Teste Alterada");
		mp.setStatusPago();
		mp.salvar();
		
		assertEquals("Movimenta��o adicionada com sucesso!", mp.obterMensagemSucesso());
	}
}
