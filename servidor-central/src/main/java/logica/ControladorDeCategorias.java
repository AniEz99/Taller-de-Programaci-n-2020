package logica;

import java.util.LinkedList;
import java.util.List;

import dtos.CategoriaDto;

public class ControladorDeCategorias implements IControladorDeCategorias {

	@Override
	public LinkedList<CategoriaDto> getCategorias() {
		return ManejadorDeCategorias.getInstance().listarCategorias();
	}

}
