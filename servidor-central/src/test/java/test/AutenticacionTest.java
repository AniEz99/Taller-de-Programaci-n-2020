package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dtos.ArtistaDto;
import dtos.ImagenDto;
import dtos.UsuarioDto;
import excepciones.AutenticacionException;
import logica.Fabrica;
import logica.IControladorDeUsuarios;

class AutenticacionTest {
	
	private static IControladorDeUsuarios ctrl;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ctrl = Fabrica.getControladorDeUsuarios();

		ArtistaDto art1 = new ArtistaDto("ete-sech", "Ete", "Sech", "es@adinet.com.uy", new Date(43, 6, 17), null, null,
				null, "12321", null);
		ctrl.registrarArtista(art1, new ImagenDto());
	}

	@Test
	void loginExitoso() throws Exception {
		ArtistaDto art = (ArtistaDto) ctrl.login("ete-sech", "12321");
		assertEquals(art.getApellido(), "Sech");
		UsuarioDto esp = ctrl.login("chino", "ncnl123");
	}
	
	@Test
	void contraseniaIncorrecta() throws Exception {
		try {
			ArtistaDto art = (ArtistaDto) ctrl.login("ete-sech", "abcba");
			fail("No tir� excepcion");			
		} catch (AutenticacionException e) {
			assertTrue(true);
		}
	}

}
