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
	
	@Test
	public void testValidaCamposObrigatorios() {
		menuPage.acessarTelaInserirMovimentacao();
		
		mp.salvar();
		
		List<String> erros = mp.obterErros();
		//assertTrue(erros.contains("Data da Movimenta��o � obrigat�rio"));
		assertTrue(erros.containsAll(Arrays.asList(
				"Data da Movimenta��o � obrigat�rio",
				"Data do pagamento � obrigat�rio",
				"Descri��o � obrigat�rio",
				"Interessado � obrigat�rio",
				"Valor � obrigat�rio",
				"Valor deve ser um n�mero")));
		assertEquals(6, erros.size());
	}
}
