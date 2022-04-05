package core;

import static core.DriverFactory.killDriver;

import org.junit.After;

public class BaseTest extends BasePage{

	
	@After
	public void finaliza() {
		if(Propriedades.FECHAR_BROWSER) {
			killDriver();
		}
	}
}