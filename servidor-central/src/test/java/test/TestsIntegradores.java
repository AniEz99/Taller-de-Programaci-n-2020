package test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import logica.CargadorDeDatosDePrueba;

class TestsIntegradores {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		new CargadorDeDatosDePrueba();
	}

	@Test
	void test() {
		
	}

}
