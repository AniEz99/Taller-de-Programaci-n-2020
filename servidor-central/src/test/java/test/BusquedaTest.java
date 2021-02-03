package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import dtos.EspectaculoDto;
import logica.Fabrica;
import logica.IControladorDeEspectaculos;

class BusquedaTest {
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}
	
	@Test
	void busquedaEspectaculos() throws Exception {
		IControladorDeEspectaculos ICE = Fabrica.getControladorEspectaculos();
		/*
		 * Encuentra todo
		 */
		LinkedList<EspectaculoDto> esp = ICE.buscarEspectaculos("");
		assertEquals(7, esp.size());
		
		/*
		 * Solo encuentra Bien de Familia (busqueda nombre incompleto)
		 */
		esp = ICE.buscarEspectaculos("Bien");
		assertTrue(esp.size() == 1 && esp.get(0).getNombre().equals("Bien de Familia"));
		
		/*
		 * Solo encuentra Los Village Volvieron (busqueda descripcion incompleta)
		 */
		esp = ICE.buscarEspectaculos("ulo de retorno de l");
		assertTrue(esp.size() == 1 && esp.get(0).getNombre().equals("Los Village Volvieron"));
		
		/*
		 * Solo encuentra Bien de Familia (busqueda descripcion incompleta y tildes)
		 */
		esp = ICE.buscarEspectaculos("El duo estara presenta");
		assertTrue(esp.size() == 1 && esp.get(0).getNombre().equals("Bien de Familia"));
		
		/*
		 * No encuentra nada
		 */
		esp = ICE.buscarEspectaculos("Las olas y el viento "
				+ "sucundún sucundún "
				+ "y el frio del mar "
				+ "shalalalala");
		assertTrue(esp.size() == 0);
		
		
	}
	
	

}
