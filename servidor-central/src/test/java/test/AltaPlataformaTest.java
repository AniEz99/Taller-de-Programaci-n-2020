package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dtos.PlataformaDto;
import excepciones.CamposIncompletosException;
import excepciones.PlataformaYaExisteException;
import logica.Fabrica;
import logica.IControladorDeEspectaculos;
import logica.IControladorDePlataformas;

class AltaPlataformaTest {
	
	private static IControladorDePlataformas ctrl;
	private static IControladorDeEspectaculos ctrlEs;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ctrl = Fabrica.getControladorPlataformas();
		ctrlEs = Fabrica.getControladorEspectaculos();
		
		PlataformaDto plt = new PlataformaDto("BlueTube", "Videos cristianos para ver con la familia", null);
		ctrl.altaPlataforma(plt);
	}

	@Test
	void altaPlataforma() {
		assertTrue(ctrl.getNombrePlataformas().contains("BlueTube"));
		boolean esta = false;
		for (PlataformaDto plat : ctrlEs.obtenerPlataformas()) {
			if (plat.getNombre() == "BlueTube") {
				esta = true;
			}
		}
		assertTrue(esta);
	}
	
	@Test
	void altaPlataformaRepetida() {
		try {
			PlataformaDto plt2 = new PlataformaDto("BlueTube", null, null);
			ctrl.altaPlataforma(plt2);
			fail("No se lanz� excepcion.");
		} catch (PlataformaYaExisteException e) {
			assertTrue(true);
		} catch (CamposIncompletosException e) {
			fail("excepcion incorrecta");
		}
	}
	
	@Test
	void altaPlataformaSinDatos() {
		try {
			PlataformaDto plt3 = new PlataformaDto("", null, null);
			ctrl.altaPlataforma(plt3);
			fail("No se lanz� excepcion.");
		} catch (PlataformaYaExisteException e) {
			fail("excepcion incorrecta");			
		} catch (CamposIncompletosException e) {
			assertTrue(true);
		}
	}

}
