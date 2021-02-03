package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dominio.Artista;
import dominio.Plataforma;
import dominio.Espectaculo;
import dominio.Funcion;
import dtos.EspectaculoDto;
import dtos.ImagenDto;
import dtos.PlataformaDto;
import excepciones.EspectaculoNoExisteException;
import excepciones.FuncionYaExisteException;
import excepciones.PlataformaNoExisteException;
import excepciones.PlataformaYaExisteException;
import logica.Fabrica;
import logica.IControladorDeEspectaculos;
import logica.ManejadorDePlataformas;
import logica.ManejadorDeUsuarios;

class AltaFuncionEspectaculoTest {
	
	private static IControladorDeEspectaculos ctrlE;
	
	@BeforeAll
	static void setUpBeforeClass() {
		ctrlE = Fabrica.getControladorEspectaculos();
	}

	@Test
	void testConfirmarAltaFuncionOk() throws Exception {
		Date fecha = new Date();
		Artista art1 = new Artista("Nuevoart1", "Nuevoart1", "art1", "art1@gmail", fecha, "", "", "", "Password01");
		Artista art2 = new Artista("Nuevoart2", "Nuevoart2", "art2", "art2@gmail", fecha, "", "", "", "Password01");
		ManejadorDeUsuarios manU = ManejadorDeUsuarios.getInstance();
		manU.addArtista(art1, new ImagenDto());
		manU.addArtista(art2, new ImagenDto());
		Plataforma nuevaPlat = new Plataforma("PlataformaNueva", "hola", "hola@gm");
		ManejadorDePlataformas manP = ManejadorDePlataformas.getInstance();
		try {
			manP.addPlataforma(nuevaPlat);
		} catch (PlataformaYaExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Not yet implemented");
		}
		Espectaculo espec = new Espectaculo(nuevaPlat, art1, "Espectaculo", "", 1, 30, 1, 10, "plat@", 30, fecha, Espectaculo.Estado.ACEPTADO);
		nuevaPlat.addEspectaculo(espec, new ImagenDto());
		
		LinkedList<String> nombresArtistas = new LinkedList<String>();
		nombresArtistas.add("Nuevoart2");
		
		EspectaculoDto dataEspec = new EspectaculoDto(espec);
		PlataformaDto dataPlat = new PlataformaDto("PlataformaNueva", "hola", "hola@gm");
		
		try {
			ctrlE.confirmarAltaFuncion("funcion", 1, 30, fecha, nombresArtistas, dataEspec, dataPlat, "");
		} catch (FuncionYaExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Not yet implemented");
		} catch (PlataformaNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Not yet implemented");
		} catch (EspectaculoNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Not yet implemented");
		}
		
		Funcion res = espec.getFuncion("funcion");
		assertEquals(res.getNombre(), "funcion");
		assertTrue(res.getArtista("Nuevoart2").equals(art2));
	}
	
	@Test
	void testConfirmarAltaFuncionRepetida() throws Exception{
		Date fecha = new Date();
		Artista art1 = new Artista("Newart1", "Newart1", "art1", "art1@gm", fecha, "", "", "", "Password01");
		Artista art2 = new Artista("Newart2", "Newart2", "art2", "art2@gm", fecha, "", "", "", "Password01");
		ManejadorDeUsuarios manU = ManejadorDeUsuarios.getInstance();
		manU.addArtista(art1, new ImagenDto());
		manU.addArtista(art2, new ImagenDto());
		Plataforma nuevaPlat = new Plataforma("PlataformaNueva2", "hola", "hola@gm");
		ManejadorDePlataformas manP = ManejadorDePlataformas.getInstance();
		try {
			manP.addPlataforma(nuevaPlat);
		} catch (PlataformaYaExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Not yet implemented");
		}
		Espectaculo espec = new Espectaculo(nuevaPlat, art1, "Espectaculo", "", 1, 30, 1, 10, "plat@", 30, fecha, Espectaculo.Estado.ACEPTADO);
		nuevaPlat.addEspectaculo(espec, new ImagenDto());
		
		LinkedList<String> nombresArtistas = new LinkedList<String>();
		nombresArtistas.add("Newart2");
		
		EspectaculoDto dataEspec = new EspectaculoDto(espec);
		PlataformaDto dataPlat = new PlataformaDto("PlataformaNueva2", "hola", "hola@gm");
		
		LinkedList<Artista> artistInvit = new LinkedList<Artista>();
		artistInvit.add(art2);
		
		Funcion func = new Funcion(artistInvit, espec, "funcionNueva", fecha, 1, 30, fecha);
		try {
			espec.addFuncion(func);
		} catch (FuncionYaExisteException e1) {
			e1.printStackTrace();
			fail("Not yet implemented");
		}
		
		try {
			ctrlE.confirmarAltaFuncion("funcionNueva", 1, 30, fecha, nombresArtistas, dataEspec, dataPlat, "");
		} catch (FuncionYaExisteException e) {
			// Esta bien
		} catch (PlataformaNoExisteException e) {
			e.printStackTrace();
			fail("Not yet implemented");
		} catch (EspectaculoNoExisteException e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
		assertThrows(FuncionYaExisteException.class, () -> 
			ctrlE.confirmarAltaFuncion(
					"funcionNueva", 1, 30, fecha, nombresArtistas, dataEspec, dataPlat, ""));
	}

}
