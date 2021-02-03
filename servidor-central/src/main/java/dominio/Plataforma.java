package dominio;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import dtos.EspectaculoDto;
import dtos.ImagenDto;
import dtos.PlataformaDto;
import excepciones.EspectaculoNoExisteException;
import excepciones.EspectaculoYaExisteException;
import logica.Utils;


public class Plataforma {
	
	private String nombre;
	private String descripcion;
	private String url;
	private Map<String, Espectaculo> espectaculos;
	
	public Plataforma(String nombre_, String descripcion_, String URL_) {
		espectaculos = new HashMap<String, Espectaculo>();
		nombre = nombre_;
		descripcion = descripcion_;
		url = URL_;
	}
	
	public String getNombre() { 
		return nombre; 
	}
	
	public boolean existeEspectaculo(String nombre) {
		 return espectaculos.containsKey(nombre);
	}
	
	/*
	 * Agrega un espectaculo a la plataforma, si ya existe tira EspectaculoYaExisteException
	 */
	public void addEspectaculo(Espectaculo esp, ImagenDto imagen) throws EspectaculoYaExisteException, Exception{
		if (espectaculos.containsKey(nombre)) {
			throw new EspectaculoYaExisteException("Ya existe un espectaculo con el nombre " + esp.getNombre());
		}
		if (imagen.getName() != null) {
			String url = Utils.guardarImagen(imagen, "espectaculo_" + esp.getNombre());
			esp.setUrlImagen(url);
		} 
		espectaculos.put(esp.getNombre(), esp);
		
	}
	
	public Espectaculo getEspectaculo(String nombre) throws EspectaculoNoExisteException{
		if (!espectaculos.containsKey(nombre)) {
			throw new EspectaculoNoExisteException("No existe un espectaculo con el nombre " + nombre);
		}
		return espectaculos.get(nombre);
	}
	
	public Map<String, Espectaculo> getEspectaculos() {
		return espectaculos;
	}

	public LinkedList<EspectaculoDto> obtenerEspectaculosAsoc() {
		LinkedList<EspectaculoDto> res = new LinkedList<EspectaculoDto>();
		Iterator<Map.Entry<String, Espectaculo>> iterador = espectaculos.entrySet().iterator();
		while (iterador.hasNext()) {
		    Map.Entry<String, Espectaculo> pair = iterador.next();
		    EspectaculoDto dato = pair.getValue().getData();
		    res.add(dato);
		}
		return res;
	}
	
	/*
	 * Retorna una lista de EspectaculoDto de los espectaculos aceptados
	 */
	public LinkedList<EspectaculoDto> obtenerEspectaculosAceptados() {
		LinkedList<EspectaculoDto> res = new LinkedList<EspectaculoDto>();
		for (Map.Entry<String, Espectaculo> esp : espectaculos.entrySet()) {
			if (esp.getValue().getEstado() == Espectaculo.Estado.ACEPTADO) {
			    EspectaculoDto dato = new EspectaculoDto(esp.getValue());
			    res.add(dato);
			}
		}
		return res;
	}
	
	/*
	 * Retorna una lista de EspectaculoDto de los espectaculos ingresados
	 */
	public LinkedList<EspectaculoDto> obtenerEspectaculosIngresados() {
		LinkedList<EspectaculoDto> res = new LinkedList<EspectaculoDto>();
		for (Map.Entry<String, Espectaculo> esp : espectaculos.entrySet()) {
			if (esp.getValue().getEstado() == Espectaculo.Estado.INGRESADO) {
			    EspectaculoDto dato = new EspectaculoDto(esp.getValue());
			    res.add(dato);
			}
		}
		return res;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public String getURL() {
		return url;
	}

	public PlataformaDto getData() { 
		return new PlataformaDto(nombre, descripcion, url); 	
	}
	
	/*
	 * Retorna true si la desc o el nombre poseen el string
	 */
	public boolean contieneTira(String tira) {
		if (nombre.contains(tira) || descripcion.contains(tira))
			return true;
		return false;
	}

	
}

	

