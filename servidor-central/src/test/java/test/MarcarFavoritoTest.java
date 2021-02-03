package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dtos.EspectadorDto;
import logica.Fabrica;
import logica.IControladorDeEspectaculos;

class MarcarFavoritoTest {

	@Test
	void marcaYDesmarcaExitosamente() throws Exception {
		IControladorDeEspectaculos ctrl = Fabrica.getControladorEspectaculos();
		int cantOriginalDeFavoritos = ctrl.obtenerDatosEspectaculo("Instagram Live", "Los Village Volvieron").getCantidadFavoritos();
		ctrl.marcarEspectaculoFavorito("chino", "Instagram Live", "Los Village Volvieron");
		assertEquals(cantOriginalDeFavoritos + 1, ctrl.obtenerDatosEspectaculo("Instagram Live", "Los Village Volvieron").getCantidadFavoritos());
		
		ctrl.desmarcarEspectaculoFavorito("chino", "Instagram Live", "Los Village Volvieron");
		assertEquals(cantOriginalDeFavoritos, ctrl.obtenerDatosEspectaculo("Instagram Live", "Los Village Volvieron").getCantidadFavoritos());
	}
	
	@Test
	void favoritoGuardadoEnEspectador() throws Exception {
		IControladorDeEspectaculos ctrl = Fabrica.getControladorEspectaculos();
		ctrl.marcarEspectaculoFavorito("tonyp", "Instagram Live", "Los Village Volvieron");
		EspectadorDto esp = (EspectadorDto) Fabrica.getControladorDeUsuarios().obtenerUsuario("tonyp");
		assertTrue(esp.getEspectaculosFavoritos().contains("Los Village Volvieron"));
	}

}
