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
	public void testInserirMovimentação() {
		menuPage.acessarTelaInserirMovimentacao();
		
		mp.setDataMovimentacao("06/04/2022");
		mp.setDataPagamento("06/04/2022");
		mp.setDescricao("Movimentação Teste");
		mp.setInteressado("Interessado Qualquer");
		mp.setValor("123456");
		mp.setConta("Conta de Teste Alterada");
		mp.setStatusPago();
		mp.salvar();
		
		assertEquals("Movimentação adicionada com sucesso!", mp.obterMensagemSucesso());
	}
}
