package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dominio.Artista;
import dominio.Espectaculo;
import dominio.Funcion;
import dominio.Plataforma;
import dtos.EspectaculoDto;
import dtos.FuncionDto;
import dtos.ImagenDto;
import dtos.UsuarioDto;
import excepciones.EspectaculoNoExisteException;
import excepciones.PlataformaNoExisteException;
import logica.IControladorDeEspectaculos;
import logica.ManejadorDePlataformas;
import logica.Fabrica;

class ConsultaFuncionEspectaculoTest {

	private static ManejadorDePlataformas manejador;
	private static IControladorDeEspectaculos controladorEsp;
	private static Date fechaNac;
	private static Plataforma plataforma1;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		controladorEsp = Fabrica.getControladorEspectaculos();
		
		manejador = ManejadorDePlataformas.getInstance();
		plataforma1 = new Plataforma("plataforma1", "plataforma1Des", "plataforma1Url");
		manejador.addPlataforma(plataforma1);
		

		fechaNac = new Date();
		Artista nuevoArtista = new Artista( "Juan",  "Juan",  "Perez", "", fechaNac, "", "", "", "Password01");
		
		LinkedList<UsuarioDto> auxListArtistasDto = new LinkedList<UsuarioDto>();
		auxListArtistasDto.add(nuevoArtista.getData());
		
		LinkedList<Artista> auxListArtista = new LinkedList<Artista>();
		auxListArtista.add(nuevoArtista);
		
		Espectaculo espectaculoNuevo = new Espectaculo(plataforma1, nuevoArtista, "Espectaculo1", "", 1 , 1, 1, 3, "", 3, fechaNac, Espectaculo.Estado.ACEPTADO);
		
		FuncionDto auxFuncion = new FuncionDto("Funcion 1", fechaNac, fechaNac, auxListArtistasDto);
		Funcion nuevaFuncion = new Funcion(auxFuncion, auxListArtista);
		
		espectaculoNuevo.addFuncion(nuevaFuncion);
		plataforma1.addEspectaculo(espectaculoNuevo, new ImagenDto());
	}
	
	@Test
	void ingresarPlataformaOk() {
		try {
			LinkedList<EspectaculoDto> resultado = controladorEsp.ingresarPlataforma("plataforma1");
			for (EspectaculoDto esp: resultado) {
				assertEquals(esp.getNombre(), "Espectaculo1");
				assertEquals(esp.getFechaRegistro(), fechaNac);
			}
			
		} catch (PlataformaNoExisteException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	void ingresarPlataformaNoExistente() {
		assertThrows(PlataformaNoExisteException.class, () -> controladorEsp.ingresarPlataforma("plataformaQueNoExiste"));
	}
	
	@Test
	void seleccionarEspectaculoOk() {
			try {
				LinkedList<FuncionDto> resultado;
				resultado = controladorEsp.seleccionarEspectaculoYRetornarFunciones("Espectaculo1", plataforma1.getNombre());
				for (FuncionDto func: resultado) {
					assertEquals(func.getNombre(), "Funcion 1");
				}
			} catch (EspectaculoNoExisteException | PlataformaNoExisteException e) {
				// TODO Auto-generated catch block
				fail(e.getMessage());
				e.printStackTrace();
			}
	}
	
	@Test
	void seleccionarEspectaculoNoExistente() {
		assertThrows(EspectaculoNoExisteException.class, () -> controladorEsp.seleccionarEspectaculoYRetornarFunciones("espectaculoQueNoExiste", plataforma1.getNombre()));
	}
	
	@Test
	void seleccionarFuncionOk() {
		try {
			FuncionDto resultado = controladorEsp.seleccionarFuncion("Funcion 1", "Espectaculo1", "plataforma1");
			assertEquals(resultado.getNombre(), "Funcion 1");
		} catch (PlataformaNoExisteException | EspectaculoNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void seleccionarFuncionNoExistePlataforma() {
		assertThrows(PlataformaNoExisteException.class, () -> controladorEsp.seleccionarFuncion("Funcion 1", "Espectaculo1", "plataformaQueNoExiste"));
	}
	
	@Test
	void seleccionarFuncionNoExisteEspectaculo() {
		assertThrows(EspectaculoNoExisteException.class, () -> controladorEsp.seleccionarFuncion("Funcion 1", "espectaculoQueNoExiste", "plataforma1"));	
		}

}
