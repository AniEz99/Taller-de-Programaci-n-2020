package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dtos.ArtistaDto;
import dtos.EspectadorDto;
import dtos.ImagenDto;
import excepciones.UsuarioYaExisteException;
import logica.Fabrica;
import logica.IControladorDeUsuarios;

class AltaUsuarioTest {

	private static IControladorDeUsuarios ctrl;
	private static int originalSize;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ctrl = Fabrica.getControladorDeUsuarios();
		originalSize = ctrl.obtenerNicknamesUsuarios().size();

		ArtistaDto art1 = new ArtistaDto("rada", "Ruben", "Rada", "rada@adinet.com.uy", new Date(43, 6, 17), null, null,
				null, "Password01", null);
		ctrl.registrarArtista(art1, new ImagenDto());
		EspectadorDto esp1 = new EspectadorDto("cra", "Rodrigo", "Conde", "correo@", new Date(1, 1, 1), "Password01", null);
		ctrl.registrarEspectador(esp1, new ImagenDto());
	}

	@Test
	void agregarArtistaRepetido() throws Exception {
		ArtistaDto art2 = new ArtistaDto("rada", "Julieta", "Rada", "julieta@gmail.com", new Date(90, 4, 25), null, null,
				null, "Password01", null);
		try {
			ctrl.registrarArtista(art2, new ImagenDto());
			fail("No se lanz� ex");
		} catch (UsuarioYaExisteException ex) {
			// si se llega hasta aqui entonces se lanz� la excepci�n corecta y el test pasa
		}
	}

	@Test
	void agregarEspectadorRepetido() throws Exception {
		EspectadorDto esp2 = new EspectadorDto("cra", "Juan", "Perez", "mi-mail", new Date(2, 2, 2), "Password01", null);
		try {
			ctrl.registrarEspectador(esp2, new ImagenDto());
			fail("No se lanz� ex");
		} catch (UsuarioYaExisteException ex) {
			// si se llega hasta aqui entonces se lanz� la excepci�n corecta y el test pasa
			assertTrue(true);
		}
	}

	@Test
	void usuariosAgregadosCorrectamente() throws UsuarioYaExisteException {
		assertTrue(ctrl.obtenerNicknamesUsuarios().size() == originalSize + 2);
	}

}
