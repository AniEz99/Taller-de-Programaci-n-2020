package dominio;

import java.util.Date;

import dtos.PremioDto;

public class Premio {
	
	private String nombrePremio;
	private Espectaculo espAsociado;
	private Funcion funcAsociada;
	private Date fechaPremio;
	private Plataforma platAsociada;
	
	public Premio(Plataforma plat, String nombre, Espectaculo esp, Funcion func, Date fecha) {
		nombrePremio = nombre;
		espAsociado = esp;
		funcAsociada = func;
		fechaPremio = fecha;
		platAsociada = plat;
	}
	
	public Plataforma getPlataforma() {
		return platAsociada;
	}
	
	public String getPremio() {
		return nombrePremio;
	}
	
	public Espectaculo getEspectaculo() {
		return espAsociado;
	}
	
	public Funcion getFuncion() {
		return funcAsociada;
	}
	
	public Date getFecha() {
		return fechaPremio;
	}
	
	public PremioDto getData() {
		PremioDto premio = new PremioDto(platAsociada.getNombre(), nombrePremio, espAsociado.getNombre(), funcAsociada.getNombre(), fechaPremio);
		return premio;
	}
}
