package testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static utils.DataUtils.obterDataFormatada;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import core.BaseTest;
import pages.MenuPage;
import pages.MovimentacaoPage;
import utils.DataUtils;

public class MovimentacaoTest extends BaseTest{

	private MenuPage menuPage = new MenuPage();
	private MovimentacaoPage mp = new MovimentacaoPage();
	
	@Test
	public void test1InserirMovimenta��o() {
		menuPage.acessarTelaInserirMovimentacao();
		
		mp.setDataMovimentacao(obterDataFormatada(new Date()));
		mp.setDataPagamento(obterDataFormatada(new Date()));
		mp.setDescricao("Movimenta��o Teste");
		mp.setInteressado("Interessado Qualquer");
		mp.setValor("123456");
		mp.setConta("Conta para movimentacoes");
		mp.setStatusPago();
		mp.salvar();
		
		assertEquals("Movimenta��o adicionada com sucesso!", mp.obterMensagemSucesso());
	}
	
	@Test
	public void test2ValidaCamposObrigatorios() {
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
	
	@Test
	public void test3InserirMovimentacaoFutura() {
		menuPage.acessarTelaInserirMovimentacao();
		
		Date dataFutura = DataUtils.obterDataComDiferencaDias(5);
		
		mp.setDataMovimentacao(obterDataFormatada(dataFutura));
		mp.setDataPagamento("06/12/2022");
		mp.setDescricao("Movimenta��o Teste");
		mp.setInteressado("Interessado Qualquer");
		mp.setValor("123456");
		mp.setConta("Conta para movimentacoes");
		mp.setStatusPago();
		mp.salvar();
		
		List<String> erros = mp.obterErros();
		assertTrue(erros.contains("Data da Movimenta��o deve ser menor ou igual � data atual"));
		assertEquals(1, erros.size());
	}
}
