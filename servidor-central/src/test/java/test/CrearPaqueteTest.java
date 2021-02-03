package test;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dtos.PaqueteDto;
import dtos.CategoriaDto;
import dtos.ImagenDto;
import excepciones.PaqueteYaExisteException;
import logica.Fabrica;
import logica.IControladorDePaquetes;
import logica.ManejadorDePaquetes;

class CrearPaqueteTest {

	private static IControladorDePaquetes ctrl;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ctrl = Fabrica.getControladorDePaquetes();
		PaqueteDto paq = new PaqueteDto("Paqueton", "Un paquete de prueba", new Date(), new Date(), new Date(), 50, new LinkedList<String>(), new LinkedList<CategoriaDto>(), null, null);
		ctrl.crearPaquete(paq, new ImagenDto(), null);
	}

	@Test
	void crearPaquete() throws Exception {
		PaqueteDto pack = new PaqueteDto("Paquetin", "Un paquete por tiempo limitado", new Date(), new Date(), new Date(), 50, new LinkedList<String>(), new LinkedList<CategoriaDto>(), new LinkedList<String>(), null);
		ctrl.crearPaquete(pack, new ImagenDto(), null);
		
		assertEquals(ctrl.obtenerPaquetes().stream().filter(paq -> paq.getNombre().equals("Paquetin")).collect(Collectors.toList()).size(), 1);
		assertTrue(ManejadorDePaquetes.getInstance().getPaquete("Paquetin").getDescuento() == 50 && ManejadorDePaquetes.getInstance().getPaquete("Paquetin").getEspectaculos().size() == 0);
	}

	@Test
	void crearPaqueteRepetido() throws Exception {
		PaqueteDto paq = new PaqueteDto("Paqueton", "Otro paquete", new Date(0, 0, 0), new Date(), new Date(), (float) 18.03, new LinkedList<String>(), new LinkedList<CategoriaDto>(), new LinkedList<String>(), null);
		try {
			ctrl.crearPaquete(paq, new ImagenDto(), null);
			fail("No se lanzo ex");
		} catch (PaqueteYaExisteException e) {
			assertTrue(true);
		}
	}

}
