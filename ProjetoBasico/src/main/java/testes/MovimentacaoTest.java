package testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

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
	
	@Test
	public void testValidaCamposObrigatorios() {
		menuPage.acessarTelaInserirMovimentacao();
		
		mp.salvar();
		
		List<String> erros = mp.obterErros();
		//assertTrue(erros.contains("Data da Movimentação é obrigatório"));
		assertTrue(erros.containsAll(Arrays.asList(
				"Data da Movimentação é obrigatório",
				"Data do pagamento é obrigatório",
				"Descrição é obrigatório",
				"Interessado é obrigatório",
				"Valor é obrigatório",
				"Valor deve ser um número")));
		assertEquals(6, erros.size());
	}
}
