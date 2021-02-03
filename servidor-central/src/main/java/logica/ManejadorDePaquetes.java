package logica;

import dominio.Paquete;
import dtos.ImagenDto;
import excepciones.NoExistePaqueteException;
import excepciones.PaqueteYaExisteException;

import java.util.HashMap;
import java.util.Map;

public class ManejadorDePaquetes {
	private Map<String, Paquete> paquetes;
	private static ManejadorDePaquetes instancia;
	
	/*
	 * Constructor privado
	 */
	private ManejadorDePaquetes() {
		paquetes = new HashMap<String, Paquete>();
	}
	
	/*
	 * Devuelve instancia única
	 */
	public static ManejadorDePaquetes getInstance() {
		if (instancia == null)
			instancia = new ManejadorDePaquetes();
		return instancia;
	}
	
	public void agregarPaquete(Paquete paq, ImagenDto img) throws Exception {
		if (!paquetes.containsKey(paq.getNombre())) {
			if (img.getName() != null) {
				String url = Utils.guardarImagen(img, "paquete_" + paq.getNombre());
				paq.setUrlImagen(url);
			} 
			paquetes.put(paq.getNombre(), paq);
		} else {
			throw new PaqueteYaExisteException("Ya existe un paquete llamado " + paq.getNombre());
		}
	}

	public Map<String, Paquete> obtenerPaquetes() {
		return paquetes;
	}

	/*
	 * Retorna el Paquete
	 */
	public Paquete getPaquete(String nomPaq) throws NoExistePaqueteException {
		if (!paquetes.containsKey(nomPaq)) {
			throw new NoExistePaqueteException("No existe el paquete de nombre "+ nomPaq);
		}
		return paquetes.get(nomPaq);
	}
}
