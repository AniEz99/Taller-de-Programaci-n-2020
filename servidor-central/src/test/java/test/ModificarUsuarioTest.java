package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dtos.ArtistaDto;
import dtos.EspectadorDto;
import dtos.ImagenDto;
import excepciones.UsuarioNoExisteException;
import logica.Fabrica;
import logica.IControladorDeUsuarios;

class ModificarUsuarioTest {
	
	private static IControladorDeUsuarios ctrl;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ctrl = Fabrica.getControladorDeUsuarios();
		ArtistaDto art1 = new ArtistaDto("jsb", "yojan sebastian", "baj", "---", new Date(-200, 3, 27), null, null, null, "Password01", null);
		ctrl.registrarArtista(art1, new ImagenDto());
		EspectadorDto esp1 = new EspectadorDto("pedrito", "Pedro", "Gomez", "", new Date(), "Password01", null);
		ctrl.registrarEspectador(esp1, new ImagenDto());
	}

	@Test
	void modificarUsuarios() throws UsuarioNoExisteException {
		ArtistaDto art1 = new ArtistaDto("jsb", "Johann Sebastian", "Bach", "---", new Date(-200, 3, 27), null, null, null, "Password01", null);
		try {
			ctrl.editarArtista(art1, new ImagenDto());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EspectadorDto esp1 = new EspectadorDto("pedrito", "Pedro", "Gomez", "pedrito@yopmail.com", new Date(), "Password01", null);
		try {
			ctrl.editarEspectador(esp1, new ImagenDto());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void modificarUsuariosNoExistentes() {
		ArtistaDto art1 = new ArtistaDto("jasdfasdf", "-", ".", "---", new Date(), null, null, null, "Password01", null);
		try {
			ctrl.editarArtista(art1,  new ImagenDto());
			fail("No lanz� excepcion.");
		} catch (Exception e) {
			EspectadorDto esp1 = new EspectadorDto("x", "#/@!", "#//(&", "///", new Date(), "Password01", null);
			try {
				ctrl.editarEspectador(esp1,  new ImagenDto());
				fail("No lanz� excepcion.");
			} catch (Exception e2) {
				assertTrue(true);
			}
		}
		
	}

}
