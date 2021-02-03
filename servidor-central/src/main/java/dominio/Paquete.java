package dominio;
import dtos.CategoriaDto;
import dtos.PaqueteDto;
import excepciones.EspectaculoYaExisteException;

import java.util.Date;
import java.util.LinkedList;

public class Paquete {
	
	private String nombre;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaFin;
	private float descuento;
	private Date fechaAlta;
	
	private LinkedList<Registro> registros;
	private LinkedList<Espectaculo> espectaculos;
	
	private String urlImagen;
	
	public Paquete(String nombre, String descripcion, Date inicio, Date fin, float descuento) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		fechaInicio = inicio;
		fechaFin = fin;
		this.descuento = descuento;
		urlImagen = "https://www.logobory.cl/wp-content/uploads/2017/07/pack-oferta-logo-web.png";
		fechaAlta = new Date();
		espectaculos = new LinkedList<Espectaculo>();
	}
	
	public PaqueteDto getData() {
		LinkedList<String> listEsp = new LinkedList<String>();
		LinkedList<CategoriaDto> listCat = new LinkedList<CategoriaDto>();
		LinkedList<String> listPlat = new LinkedList<String>();
		for (Espectaculo esp : espectaculos) {
			listEsp.add(esp.getNombre());
			listPlat.add(esp.getPlataforma());
			for (Categoria cat : esp.getCategorias()) {
				CategoriaDto catDto = cat.getData();
				if (!listCat.contains(catDto)) {
					listCat.add(catDto);
				}
			}
		}
		
		PaqueteDto res = new PaqueteDto(nombre, descripcion, fechaInicio, fechaFin, fechaAlta, descuento, listEsp, listCat, listPlat, urlImagen);
		return res;
	}

	/*
	 * Retorna el descuento
	 */
	public float getDescuento() {
		return descuento;
	}

	public String getNombre() {
		return nombre;
	}

	/*
	 * Remueve el espectaculo de la lista de espectaculos
	 */
	public void removerEspectaculo(String nombreEsp) {
		for (Espectaculo esp : espectaculos)
			if (esp.getNombre() == nombreEsp)
				espectaculos.remove(esp);
	}

	public void setFechaAlta(Date fechaAltaPaq) {
		fechaAlta = fechaAltaPaq;
	}

	public void addEspectaculo(Espectaculo esp) throws EspectaculoYaExisteException {
		for (Espectaculo espcs : espectaculos) {
			if (espcs.getNombre().equals(esp.getNombre()))
				throw new EspectaculoYaExisteException("El paquete ya posee a dicho espectaculo");
		}
		espectaculos.add(esp);
	}

	public LinkedList<Espectaculo> getEspectaculos() {
		return espectaculos;
	}
	
	/*
	 * Retorna true si la desc o el nombre poseen el string
	 */
	public boolean contieneTira(String tira) {
		if (nombre.contains(tira) || descripcion.contains(tira))
			return true;
		return false;
	}
	
	public void setUrlImagen(String url) {
		this.urlImagen = url;
	}
	
	public LinkedList<Registro> getRegistros() {
		return registros;
	}
}
