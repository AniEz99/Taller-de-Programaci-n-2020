package dominio;

import dtos.ValoracionDto;
import excepciones.ValoracionNoValidaException;

public class Valoracion {
	
	private int estrellas;
	private String plataforma;
	private String espectaculo;
	
	public Valoracion(int estrellas, String plataforma, String espectaculo) throws ValoracionNoValidaException {
		if (estrellas > 5 || estrellas < 1) {
			throw new ValoracionNoValidaException("La cantidad de estrellas debe estar entre 1 y 5");
		}
		this.estrellas = estrellas;
		this.plataforma = plataforma;
		this.espectaculo = espectaculo;
	}
	
	public int getEstrellas() {
		return this.estrellas;
	}
	
	public ValoracionDto getData() {
		return new ValoracionDto(estrellas, plataforma, espectaculo);
	}

}
