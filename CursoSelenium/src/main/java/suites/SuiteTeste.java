package suites;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tests.TesteCadastro;
import tests.TesteCampoTreinamento;
import tests.TesteRegrasCadastro;

@RunWith(Suite.class)
@SuiteClasses({
	TesteCadastro.class,
	TesteRegrasCadastro.class,
	TesteCampoTreinamento.class
})
public class SuiteTeste {

}
