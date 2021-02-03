package dominio;

import dtos.CategoriaDto;

public class Categoria {
	
	private String nombre;
	
	public Categoria(String nombre_) {
		nombre = nombre_;
	}
	
	public CategoriaDto getData() {
		return new CategoriaDto(nombre);
	}

	public String getNombre() {
		return nombre;
	}
	
	

}
