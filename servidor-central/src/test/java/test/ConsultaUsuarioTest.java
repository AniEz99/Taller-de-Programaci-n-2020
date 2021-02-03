package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dtos.ArtistaDto;
import dtos.EspectadorDto;
import dtos.ImagenDto;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioYaExisteException;
import logica.Fabrica;
import logica.IControladorDeUsuarios;

class ConsultaUsuarioTest {

	private static IControladorDeUsuarios ctrl;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ctrl = Fabrica.getControladorDeUsuarios();
		ArtistaDto art = new ArtistaDto("peke", "Peke�o", "77", "no tiene xq es gansta", new Date(95, 10, 7), null, null,
				null, "Password01", null);
		ctrl.registrarArtista(art, new ImagenDto());
		EspectadorDto esp = new EspectadorDto("condee", "Rodrigo", "Conde", "ro@fing.com", new Date(98, 8, 13), "Password01", null);
		ctrl.registrarEspectador(esp, new ImagenDto());
	}

	@Test
	void recuperarArtista() throws UsuarioYaExisteException, UsuarioNoExisteException {
		ArtistaDto art2 = (ArtistaDto) ctrl.obtenerUsuario("peke");

		assertTrue(art2.getNombre() == "Peke�o");
		assertTrue(art2.getApellido() == "77");
		assertTrue(art2.getCorreo() == "no tiene xq es gansta");
		assertEquals(art2.getFechaNacimiento(), new Date(95, 10, 7));
	}

	@Test
	void recuperarEspectador() throws UsuarioYaExisteException, UsuarioNoExisteException {
		EspectadorDto esp2 = (EspectadorDto) ctrl.obtenerUsuario("condee");
		assertTrue(esp2.getNombre() == "Rodrigo");
		assertTrue(esp2.getApellido() == "Conde");
		assertTrue(esp2.getCorreo() == "ro@fing.com");
		assertEquals(esp2.getFechaNacimiento(), new Date(98, 8, 13));
	}
}
