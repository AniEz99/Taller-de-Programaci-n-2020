package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.List;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dominio.Artista;
import dominio.Funcion;
import dtos.ArtistaDto;
import dtos.CategoriaDto;
import dtos.EspectadorDto;
import dtos.FuncionDto;
import dtos.ImagenDto;
import dtos.PaqueteDto;
import dtos.PlataformaDto;
import dtos.RegistroDto;
import dtos.UsuarioDto;
import excepciones.EspectaculoNoAceptadoException;
import excepciones.EspectaculoNoExisteException;
import excepciones.EspectadorNoExisteException;
import excepciones.FaltanRegistroException;
import excepciones.FuncionNoExisteException;
import excepciones.PlataformaNoExisteException;
import excepciones.RegistroNoExisteException;
import excepciones.YaExisteRegistroException;
import logica.Fabrica;
import logica.IControladorDeEspectaculos;
import logica.IControladorDePlataformas;
import logica.IControladorDeUsuarios;
import logica.ManejadorDePlataformas;
import logica.ManejadorDeUsuarios;
import dominio.Funcion;

class RegistroFuncionTest {

	private static IControladorDeEspectaculos ICE;
	private static IControladorDeUsuarios ICU;
	private static IControladorDePlataformas ICP;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ICE = Fabrica.getControladorEspectaculos();
		ICU = Fabrica.getControladorDeUsuarios();
		ICP = Fabrica.getControladorPlataformas();
		ArtistaDto art = new ArtistaDto("juan", "Juan", "El Artista", "juan@mail.com.uy", new Date(13, 8, 12), null, null,
				null, "Password01", null);
		ICU.registrarArtista(art, new ImagenDto());
		EspectadorDto esp = new EspectadorDto("tito", "Tito", "El Espectador", "eltito@mail.com", new Date(30, 4, 24), "Password01", null);
		ICU.registrarEspectador(esp, new ImagenDto());
		ICP.altaPlataforma(new PlataformaDto("Yotube", "Podes mirar videitos", "youtube.com"));
		ICE.confirmarAltaEspectaculo("Yotube", "juan", "El espectaculo", "Tremendo espectaculo", 0, 4, 10, 1000,
				"https://www.youtube.com/watch?v=dQw4w9WgXcQ", 100, new Date(30, 4, 23), new ImagenDto(), new LinkedList<CategoriaDto>());
		
		ICE.aceptarEspectaculo("Yotube", "El espectaculo");


		for (int i = 0; i < 10; i++) {
			Funcion testF = new Funcion(new FuncionDto("func " + i, new Date(2021, 1, 1), new Date(2022, 1, 1), new LinkedList<UsuarioDto>()), new LinkedList<Artista>());
			ManejadorDePlataformas.getInstance().getPlataforma("Yotube").getEspectaculo("El espectaculo").addFuncion(testF);
		}
		Funcion noVig = new Funcion(new FuncionDto("No vig", new Date(11, 1, 1), new Date(12, 1, 1), new LinkedList<UsuarioDto>()), new LinkedList<Artista>());
		ManejadorDePlataformas.getInstance().getPlataforma("Yotube").getEspectaculo("El espectaculo").addFuncion(noVig);


	}

	@Test
	void registroRealizarRepetido() {
 		try {
			ICE.altaRegistroComun("tito",  "Yotube", "El espectaculo", "func 0");
		} catch (PlataformaNoExisteException e1) {
			fail(e1.getMessage());
		} catch (EspectaculoNoExisteException e1) {
			fail(e1.getMessage());
		} catch (FuncionNoExisteException e1) {
			fail(e1.getMessage());
		} catch (EspectadorNoExisteException e1) {
			fail(e1.getMessage());
		} catch (YaExisteRegistroException e1) {
			fail(e1.getMessage());
		} catch (EspectaculoNoAceptadoException e) {
			fail(e.getMessage());
		}
	
		try {
			ICE.altaRegistroComun("tito",   "Yotube", "El espectaculo", "func 0");
			fail("No se lanza YaExisteRegistroException");
		} catch (YaExisteRegistroException ex) {
			// BIEN
		} catch (PlataformaNoExisteException e) {
			fail("No se lanza YaExisteRegistroException");
		} catch (EspectaculoNoExisteException e) {
			fail("No se lanza YaExisteRegistroException");
		} catch (FuncionNoExisteException e) {
			fail("No se lanza YaExisteRegistroException");
		} catch (EspectadorNoExisteException e) {
			fail("No se lanza YaExisteRegistroException");
		} catch (EspectaculoNoAceptadoException e) {
			fail(e.getMessage());
		}
	}
	
	
	@Test
	void registroRealizarTresRegistrosYCanjear() {
		LinkedList<PlataformaDto> plats = ICE.obtenerPlataformas();
		try {
			ICE.altaRegistroComun("tito",  "Yotube", "El espectaculo", "func 1");
		} catch (PlataformaNoExisteException | EspectaculoNoExisteException | FuncionNoExisteException
				| EspectadorNoExisteException | YaExisteRegistroException | EspectaculoNoAceptadoException e) {
			fail("Tira exception");
		}
		
		try {
			ICE.altaRegistroComun("tito",  "Yotube", "El espectaculo", "func 2");
			ICE.obtenerEspectadoresNoRegistradosEnFuncion("func 2").forEach(e -> assertFalse(e.getNickname().equals("tito"))); //tito ya esta registrado
			assertTrue(ManejadorDeUsuarios.getInstance().getEspectador("tito").obtenerRegistros().size() > 0);
		} catch (PlataformaNoExisteException | EspectaculoNoExisteException | FuncionNoExisteException
				| EspectadorNoExisteException | YaExisteRegistroException | EspectaculoNoAceptadoException e) {
			fail("Tira exception");
		}
		
		LinkedList<RegistroDto> regList = null;
		try {
			regList = ICE.obtenerRegistrosEspectador("tito");
		} catch (EspectadorNoExisteException e1) {
			fail(e1.getMessage());
		}
		
		LinkedList<String> registros = new LinkedList<String>();
		for (int x = 0; x < 3; x++) {
			registros.add(regList.get(x).nombreFuncion());
			assert registros.get(x) != null;
		}
		try {
			ICE.altaRegistroConCanje("tito",   "Yotube", "El espectaculo", "func 3", registros);
		} catch (PlataformaNoExisteException | EspectaculoNoExisteException | FuncionNoExisteException
				| EspectadorNoExisteException | YaExisteRegistroException e) {
			fail("Tira exception");
		} catch (RegistroNoExisteException e) {
			fail(e.getMessage());
		} catch (FaltanRegistroException e) {
			fail(e.getMessage());
		}
		
		// Ya no tendrian que haber mas registros disponibles
		try {
			regList = ICE.obtenerRegistrosEspectador("tito");
		} catch (EspectadorNoExisteException e1) {
			fail(e1.getMessage());
		}
		assert regList.isEmpty();
		
	}
	
	@Test
	void registroEspectadorNoExiste() {
		try {
			ICE.altaRegistroComun("-",  "Yotube", "El espectaculo", "func 1");
		} catch (PlataformaNoExisteException e) {
			e.printStackTrace();
		} catch (EspectaculoNoExisteException e) {
			e.printStackTrace();
		} catch (FuncionNoExisteException e) {
			e.printStackTrace();
		} catch (EspectadorNoExisteException e) {
			//esta es la correcta!
		} catch (YaExisteRegistroException e) {
			e.printStackTrace();
		} catch (EspectaculoNoAceptadoException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void registroConCanjeNoExisteCanje() {
		LinkedList<String> registros = new LinkedList<String>();
		for (int x = 0; x < 3; x++) {
			registros.add("-");
		}
		try {
			ICE.altaRegistroConCanje("tito",   "Yotube", "El espectaculo", "func 3", registros );
		} catch (PlataformaNoExisteException | EspectaculoNoExisteException | FuncionNoExisteException
				| EspectadorNoExisteException | YaExisteRegistroException | FaltanRegistroException e) {
			fail("Tira exception incorrecta");
		} catch (RegistroNoExisteException e) {
			assertTrue(true);
		} 		

	}
	
	@Test
	void obtenerFuncionesVigentes() {
//		LinkedList<FuncionDto> funcs = null;
//		try {
//			funcs = ICE.obtenerFuncionesVigentesDeEspectaculo("Yotube", "El espectaculo");
//			boolean noTieneFunc = true;
//			for (FuncionDto func : funcs) {
//				if (func.getNombre() == "No vig")
//					fail("Tira una funcion no vigente");
//			}
//		} catch (PlataformaNoExisteException | EspectaculoNoExisteException e) {
//			fail(e.getMessage());
//		}
	}
	
	@Test
	void obtenerPaqueteUsuario() {
		try {
			LinkedList<PaqueteDto> listPaq = ICE.obtenerPaquetesEspectadorEspectaculo("tito");
		} catch (EspectadorNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
