package suites;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import core.DriverFactory;
import pages.LoginPage;
import testes.ContaTest;
import testes.MovimentacaoTest;
import testes.RemoverMovimentacaoContaTest;
import testes.ResumoTest;
import testes.SaldoTest;

@RunWith(Suite.class)
@SuiteClasses({
	ContaTest.class,
	MovimentacaoTest.class,
	RemoverMovimentacaoContaTest.class,
	SaldoTest.class,
	ResumoTest.class
})
public class SuiteGeral {

	private static LoginPage page = new LoginPage();
	
	@BeforeClass
	public static void inicializa() {
		page.acessarTelaInicial();
		
		page.setEmail("contato2@kleber.com.br");
		page.setSenha("123456");
		page.entrar();
	}
	
	@AfterClass
	public static void finalizar() {
		DriverFactory.killDriver();
	}
}
