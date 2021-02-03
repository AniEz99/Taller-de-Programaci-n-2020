package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import dtos.EspectaculoDto;
import dtos.ValoracionDto;
import excepciones.EspectaculoNoExisteException;
import excepciones.EspectadorNoExisteException;
import excepciones.PlataformaNoExisteException;
import excepciones.ValoracionNoValidaException;
import logica.Fabrica;

class ValorarEspectaculoTest {

	@Test
	void valorarEspectculoCorrectamente() throws PlataformaNoExisteException, EspectaculoNoExisteException, EspectadorNoExisteException, ValoracionNoValidaException {
		Fabrica.getControladorEspectaculos().valorarEspectaculo("Instagram Live", "Los Village Volvieron", "costas", new ValoracionDto(2, "Instagram Live", "Los Village Volvieron" ));
		EspectaculoDto esp = Fabrica.getControladorEspectaculos().obtenerDatosEspectaculo("Instagram Live", "Los Village Volvieron");
		
		assertEquals(5, esp.getValoraciones().size());
		assertEquals(5, esp.getValoraciones().get(0).getEstrellas());
	}
	
	@Test
	void valorarConDemasiadasEstrellas() throws Exception{
		try {
			Fabrica.getControladorEspectaculos().valorarEspectaculo("Instagram Live", "Los Village Volvieron", "costas", new ValoracionDto(77, "Instagram Live", "Los Village Volvieron" ));
			fail("No tir� excepcion");
		} catch (ValoracionNoValidaException e) {
			//do nothing
		}
	}
	
	@Test
	void valorarSinRegistrarse() throws Exception{
		try {
			Fabrica.getControladorEspectaculos().valorarEspectaculo("Instagram Live", "Los Village Volvieron", "eleven11", new ValoracionDto(5, "Instagram Live", "Los Village Volvieron" ));
			fail("No tir� excepcion");
		} catch (ValoracionNoValidaException e) {
			//do nothing
		}
	}

}
