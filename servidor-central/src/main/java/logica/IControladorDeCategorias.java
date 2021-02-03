package logica;

import java.util.LinkedList;
import java.util.List;

import dtos.CategoriaDto;

public interface IControladorDeCategorias {
	
	/*
	 * Retorna los Dto de todas las categorias del sistema
	 */
	public LinkedList<CategoriaDto> getCategorias();

}
