package logica;
import dominio.Espectaculo;
import dominio.Plataforma;
import dtos.PlataformaDto;
import excepciones.PlataformaNoExisteException;
import excepciones.PlataformaYaExisteException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * Manejador de plataformas (singleton)
 * Plataformas almacenadas en Map, usan al plataforma.nombre como key
 */
public class ManejadorDePlataformas {
	private static ManejadorDePlataformas instancia;
	private Map<String, Plataforma> mapPlataformas;
	
	/*
	 * Constructor privado
	 */
	private ManejadorDePlataformas() {
		mapPlataformas = new HashMap<String, Plataforma>();
	}
	
	/*
	 * Devuelve instancia única
	 */
	public static ManejadorDePlataformas getInstance() {
		if (instancia == null)
			instancia = new ManejadorDePlataformas();
		return instancia;
	}
	
	public Map<String, Plataforma> getPlataformas() {
		return mapPlataformas;
	}
	
	/*
	 * Agrega la nuevaPlataforma a la coleccion de plataformas.
	 * Si ya existe una plataforma con el mismo id NO la agrega y retorna false
	 * sino retorna true.
	 */
	public void addPlataforma(Plataforma nuevaPlataforma) throws PlataformaYaExisteException {
		if (mapPlataformas.putIfAbsent(nuevaPlataforma.getNombre(), nuevaPlataforma) != null)
			throw new PlataformaYaExisteException("Ya existe un plataforma con el nombre " + nuevaPlataforma.getNombre());
	}
	
	/*
	 * Retorna a la platafroma con dicho nombre, si no la encuentra
	 * retorna null
	 */
	public Plataforma getPlataforma(String nombre) throws PlataformaNoExisteException {
		if (!mapPlataformas.containsKey(nombre)) {
			throw new PlataformaNoExisteException("No existe un plataforma con el nombre " + nombre);
		}
		else {
			return mapPlataformas.get(nombre);
		}
	}
	
	

	public LinkedList<PlataformaDto> obtenerPlataformasDto() {
		LinkedList<PlataformaDto> resultado = new LinkedList<PlataformaDto>();
		for (Map.Entry<String, Plataforma> entry : mapPlataformas.entrySet()) {
			resultado.add(entry.getValue().getData() );
		}
		return resultado;
	}
	
	public LinkedList<String> getNombrePlataformas() {
		LinkedList<String> res = new LinkedList<String>();
		Set<String> keys = mapPlataformas.keySet();
		for (String key:keys) {
			res.add(mapPlataformas.get(key).getNombre());
		}
		return res;
	}
	
}
