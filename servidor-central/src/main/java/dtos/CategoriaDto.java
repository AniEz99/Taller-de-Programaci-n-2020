package dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriaDto {
	
	public CategoriaDto() {}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	private String nombre;
	
	public CategoriaDto(String nombre_) {
		nombre = nombre_;
	}
	
	public String getNombre() {
		return nombre;
	}
	@Override
	public String toString() {
		return nombre;
	}
	
	public boolean equals(CategoriaDto cat) {
		return this.nombre == cat.getNombre();		
	}

}
