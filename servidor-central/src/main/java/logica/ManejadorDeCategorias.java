package logica;

import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dominio.Categoria;
import dtos.CategoriaDto;
import excepciones.CategoriaNoExisteException;
import excepciones.YaExisteCategoriaException;

public class ManejadorDeCategorias {
	private static ManejadorDeCategorias instancia;
	private Map<String, Categoria> mapCategorias;
	
	/*
	 * Constructor privado
	 */
	private ManejadorDeCategorias() {
		mapCategorias = new HashMap<String, Categoria>();
	}
	/*
	 * Devuelve instancia única
	 */
	public static ManejadorDeCategorias getInstance() {
		if (instancia == null)
			instancia = new ManejadorDeCategorias();
		return instancia;
	}
	
	public void addCategoria(Categoria nuevaCat) throws YaExisteCategoriaException {
		if (mapCategorias.putIfAbsent(nuevaCat.getNombre(), nuevaCat) != null)
			throw new YaExisteCategoriaException("Ya existe una categoria con el nombre " + nuevaCat.getNombre());
	}
	
	/*
	 * Retorna a la platafroma con dicho nombre, si no la encuentra
	 * retorna null
	 */
	public Categoria getCategoria(String nombre) throws CategoriaNoExisteException {
		if (!mapCategorias.containsKey(nombre)) {
			throw new CategoriaNoExisteException("No existe un categoria con el nombre " + nombre);
		}
		else {
			return mapCategorias.get(nombre);
		}
	}
	
	public LinkedList<CategoriaDto> listarCategorias(){
		LinkedList<CategoriaDto> lista = new LinkedList<CategoriaDto>();
		for (Map.Entry<String, Categoria> cat : mapCategorias.entrySet()) {
			lista.add(cat.getValue().getData());
		}
		return lista;
	}
}
