package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import excepciones.EspectaculoNoExisteException;
import excepciones.PlataformaNoExisteException;
import logica.Fabrica;
import logica.IControladorDeEspectaculos;
import logica.IControladorDePlataformas;
import logica.IControladorDeUsuarios;

public class ConsultaEspectaculoTest {

	private static IControladorDeEspectaculos ctrle;
	private static IControladorDeUsuarios ctrlu;
	private static IControladorDePlataformas ctrlp;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ctrle = Fabrica.getControladorEspectaculos();
		ctrlu = Fabrica.getControladorDeUsuarios();
		ctrlp = Fabrica.getControladorPlataformas();
		ArtistaDto art1 = new ArtistaDto("AniEz", "Anibal", "Presa", "anibalpresa@gmail.com", new Date(43, 6, 17), null,
				null, null, "Password01", null);
		ctrlu.registrarArtista(art1, new ImagenDto());
		PlataformaDto plt1 = new PlataformaDto("Twitch", "Streamings", "www.twitch.tv");
		ctrlp.altaPlataforma(plt1);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String fech = "11-09-2020";
		Date fecha = sdf.parse(fech);
		ctrle.confirmarAltaEspectaculo(plt1.getNombre(), art1.getNickname(), "Prueba", "Esto es una prueba.", 1, 30, 10,
				1000, "www.com", 50, fecha, new ImagenDto(), new LinkedList<CategoriaDto>());
	}

	@Test
	void obtenerEspectaculosPlataformaNoExistente() throws PlataformaNoExisteException {
		try {
			ctrle.obtenerEspectaculosPorPlataforma("Facebook");
			fail("No se lanz� ex");
		} catch (PlataformaNoExisteException ex) {
			// si se llega hasta aqui entonces se lanz� la excepci�n corecta y el test pasa
		}
	}

	@Test
	void obtenerDatosEspectaculosPlataformaNoExistente()
			throws EspectaculoNoExisteException, PlataformaNoExisteException {
		try {
			ctrle.obtenerDatosEspectaculo("Facebook", "Prueba");
			fail("No se lanz� ex");
		} catch (PlataformaNoExisteException ex) {
			// si se llega hasta aqui entonces se lanz� la excepci�n corecta y el test pasa
		}
	}

	@Test
	void obtenerDatosEspectaculoNoExistente() throws EspectaculoNoExisteException, PlataformaNoExisteException {
		try {
			ctrle.obtenerDatosEspectaculo("Twitch", "NoExiste");
			fail("No se lanz� ex");
		} catch (EspectaculoNoExisteException ex) {
			// si se llega hasta aqui entonces se lanz� la excepci�n corecta y el test pasa
		}
	}

	@Test
	void obtenerEspectaculosPlataforma() throws PlataformaNoExisteException, ParseException {
		LinkedList<EspectaculoDto> res = ctrle.obtenerEspectaculosPorPlataforma("Twitch");
		EspectaculoDto unico = res.get(0);
		assertTrue(unico.getNombre().contentEquals("Prueba"));
		assertTrue(unico.getDescripcion().contentEquals("Esto es una prueba."));
		assertTrue(unico.getDuracionHoras() == 1);
		assertTrue(unico.getDuracionMinutos() == 30);
		assertTrue(unico.getMinEspectadores() == 10);
		assertTrue(unico.getMaxEspectadores() == 1000);
		assertTrue(unico.getURL().contentEquals("www.com"));
		assertTrue(unico.getCosto() == 50);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String fech = "11-09-2020";
		Date fecha = sdf.parse(fech);
		assertTrue(unico.getFechaRegistro().equals(fecha));
	}

	@Test
	void obtenerDatosEspectaculos() throws PlataformaNoExisteException, EspectaculoNoExisteException, ParseException {
		EspectaculoDto unico = ctrle.obtenerDatosEspectaculo("Twitch", "Prueba");
		assertTrue(unico.getNombre().contentEquals("Prueba"));
		assertTrue(unico.getDescripcion().contentEquals("Esto es una prueba."));
		assertTrue(unico.getDuracionHoras() == 1);
		assertTrue(unico.getDuracionMinutos() == 30);
		assertTrue(unico.getMinEspectadores() == 10);
		assertTrue(unico.getMaxEspectadores() == 1000);
		assertTrue(unico.getURL().contentEquals("www.com"));
		assertTrue(unico.getCosto() == 50);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String fech = "11-09-2020";
		Date fecha = sdf.parse(fech);
		assertTrue(unico.getFechaRegistro().equals(fecha));
	}
}
