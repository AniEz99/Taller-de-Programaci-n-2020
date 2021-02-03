package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dominio.Categoria;
import excepciones.CategoriaNoExisteException;
import excepciones.YaExisteCategoriaException;
import logica.Fabrica;
import logica.IControladorDeEspectaculos;
import logica.ManejadorDeCategorias;



class AltaCategoriaTest {
	private static IControladorDeEspectaculos ctrle;
	
	@BeforeAll
	static void setUpBeforeClass() {
		ctrle = Fabrica.getControladorEspectaculos();
	}

	@Test
	void confirmarAltaCategoria() throws CategoriaNoExisteException, YaExisteCategoriaException {
		ctrle.altaCategoria("categoria");
		ManejadorDeCategorias man = ManejadorDeCategorias.getInstance();
		Categoria cat = man.getCategoria("categoria");		
		assertEquals(cat.getNombre(), "categoria");
	}
	
	@Test
	void altaCategoriaRepetida() throws CategoriaNoExisteException, YaExisteCategoriaException {
		Categoria cat2 = new Categoria("categoria2");
		ManejadorDeCategorias man = ManejadorDeCategorias.getInstance();
		man.addCategoria(cat2);
		try {
			ctrle.altaCategoria("categoria2");
		} catch (YaExisteCategoriaException e) {
			//Esta bien
		}		
		assertThrows(YaExisteCategoriaException.class, () -> 
			ctrle.altaCategoria("categoria2"));
	}
}
