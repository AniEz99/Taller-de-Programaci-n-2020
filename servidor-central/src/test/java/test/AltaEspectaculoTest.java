package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dtos.ArtistaDto;
import dtos.CategoriaDto;
import dtos.EspectaculoDto;
import dtos.ImagenDto;
import dtos.PlataformaDto;
import excepciones.CategoriaNoExisteException;
import excepciones.EspectaculoNoExisteException;
import excepciones.EspectaculoYaExisteException;
import excepciones.MinEspMayorAMaxEspException;
import excepciones.PlataformaNoExisteException;
import excepciones.UsuarioNoExisteException;
import excepciones.YaExisteCategoriaException;
import logica.Fabrica;
import logica.IControladorDeEspectaculos;
import logica.IControladorDePlataformas;
import logica.IControladorDeUsuarios;

public class AltaEspectaculoTest {

	private static IControladorDeEspectaculos ctrle;
	private static IControladorDeUsuarios ctrlu;
	private static IControladorDePlataformas ctrlp;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ctrle = Fabrica.getControladorEspectaculos();
		ctrlu = Fabrica.getControladorDeUsuarios();
		ctrlp = Fabrica.getControladorPlataformas();
		ArtistaDto art1 = new ArtistaDto("Ani", "Anibal", "Presa", "ap@gmail.com", new Date(43, 6, 17), null, null, null, "Password01", null);
		ctrlu.registrarArtista(art1, new ImagenDto());
		PlataformaDto pl1 = new PlataformaDto("YouTube", "Videos", "www.youtube.com");
		ctrlp.altaPlataforma(pl1);
		LinkedList<EspectaculoDto> lista =  ctrle.obtenerEspectaculosAceptadosPorPlataforma(pl1.getNombre());
		ctrle.confirmarAltaEspectaculo(pl1.getNombre(), art1.getNickname(), "Prueba2", "Esto es una prueba.", 1, 30, 10,
				1000, "www.com", 50, new Date(), new ImagenDto(), new LinkedList<CategoriaDto>());
	}

	@Test
	void agregarEspectaculoRepetido() throws Exception {
		try {
			ctrle.confirmarAltaEspectaculo("YouTube", "Ani", "Prueba2", "Esto es una prueba.", 1, 30, 10, 1000,
					"www.com", 50, new Date(), null, new LinkedList<CategoriaDto>());
			fail("No se lanz� ex");
		} catch (EspectaculoYaExisteException | CategoriaNoExisteException | YaExisteCategoriaException ex) {
			// si se llega hasta aqui entonces se lanz� la excepci�n corecta y el test pasa
		}
	}

	@Test
	void agregarEspectaculoEnPlataformaQueNoExiste() throws Exception {
		try {
			ctrle.confirmarAltaEspectaculo("Facebook", "Ani", "Prueba2", "Esto es una prueba.", 1, 30, 10, 1000,
					"www.com", 50, new Date(), null, new LinkedList<CategoriaDto>());
			fail("No se lanz� ex");
		} catch (PlataformaNoExisteException | CategoriaNoExisteException | YaExisteCategoriaException ex) {
			// si se llega hasta aqui entonces se lanz� la excepci�n corecta y el test pasa
		}
	}

	@Test
	void agregarEspectaculoArtistaOrganizadorNoExiste() throws Exception {
		try {
			ctrle.confirmarAltaEspectaculo("YouTube", "Eze", "Prueba2", "Esto es una prueba.", 1, 30, 10, 1000,
					"www.com", 50, new Date(), null, new LinkedList<CategoriaDto>());
			fail("No se lanz� ex");
		} catch (UsuarioNoExisteException | CategoriaNoExisteException | YaExisteCategoriaException ex) {
			// si se llega hasta aqui entonces se lanz� la excepci�n corecta y el test pasa
		}
	}

	@Test
	void minEspMayorMaxEsp() throws Exception {
		try {
			ctrle.confirmarAltaEspectaculo("YouTube", "Ani", "Prueba3", "Esto es una prueba.", 1, 30, 100, 10,
					"www.com", 50, new Date(), null, new LinkedList<CategoriaDto>());
			fail("No se lanz� ex");
		} catch (MinEspMayorAMaxEspException | CategoriaNoExisteException | YaExisteCategoriaException ex) {
			// si se llega hasta aqui entonces se lanz� la excepci�n corecta y el test pasa
		}
	}

	@Test
	void espectaculoAgregadoCorrectamente()
			throws EspectaculoYaExisteException, PlataformaNoExisteException, EspectaculoNoExisteException {
		assertTrue(ctrle.obtenerDatosEspectaculo("YouTube", "Prueba2").getNombre().equals("Prueba2"));
	}
}
