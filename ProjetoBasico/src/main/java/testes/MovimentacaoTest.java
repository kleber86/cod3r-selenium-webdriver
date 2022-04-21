package testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static utils.DataUtils.obterDataFormatada;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import core.BaseTest;
import pages.MenuPage;
import pages.MovimentacaoPage;
import utils.DataUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MovimentacaoTest extends BaseTest{

	private MenuPage menuPage = new MenuPage();
	private MovimentacaoPage mp = new MovimentacaoPage();
	
	@Test
	public void test1InserirMovimentação() {
		menuPage.acessarTelaInserirMovimentacao();
		
		mp.setDataMovimentacao(obterDataFormatada(new Date()));
		mp.setDataPagamento(obterDataFormatada(new Date()));
		mp.setDescricao("Movimentação Teste");
		mp.setInteressado("Interessado Qualquer");
		mp.setValor("123456");
		mp.setConta("Conta para movimentacoes");
		mp.setStatusPago();
		mp.salvar();
		
		assertEquals("Movimentação adicionada com sucesso!", mp.obterMensagemSucesso());
	}
	
	@Test
	public void test2ValidaCamposObrigatorios() {
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
	
	@Test
	public void test3InserirMovimentacaoFutura() {
		menuPage.acessarTelaInserirMovimentacao();
		
		Date dataFutura = DataUtils.obterDataComDiferencaDias(5);
		
		mp.setDataMovimentacao(obterDataFormatada(dataFutura));
		mp.setDataPagamento("06/12/2022");
		mp.setDescricao("Movimentação Teste");
		mp.setInteressado("Interessado Qualquer");
		mp.setValor("123456");
		mp.setConta("Conta para movimentacoes");
		mp.setStatusPago();
		mp.salvar();
		
		List<String> erros = mp.obterErros();
		assertTrue(erros.contains("Data da Movimentação deve ser menor ou igual à data atual"));
		assertEquals(1, erros.size());
	}
}
