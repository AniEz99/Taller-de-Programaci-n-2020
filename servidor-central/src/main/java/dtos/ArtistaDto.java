package dtos;

import java.util.Date;
import java.util.LinkedList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso(UsuarioDto.class)
@XmlAccessorType(XmlAccessType.FIELD)
public class ArtistaDto extends UsuarioDto {
	private static final long serialVersionUID = 1L;
	
	public ArtistaDto() {}
	
	private String descripcion;
	private String biografia;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public void setSitioWeb(String sitioWeb) {
		this.sitioWeb = sitioWeb;
	}

	public void setEspectaculos(LinkedList<EspectaculoMinDto> espectaculos) {
		this.espectaculos = espectaculos;
	}

	private String sitioWeb;
	private LinkedList<EspectaculoMinDto> espectaculos = new LinkedList<EspectaculoMinDto>();

	public ArtistaDto(String nickname, String nombre, String apellido, String correo, Date fechaNacimiento, String descripcion, 
			String biografia, String sitioWeb, String contrasenia, String urlImagen) {
		this.seguidores = new LinkedList<String>();
		this.seguidos = new LinkedList<String>();
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.fechaNacimiento = fechaNacimiento;
		this.descripcion = descripcion;
		this.biografia = biografia;
		this.sitioWeb = sitioWeb;
		this.contrasenia = contrasenia;
		this.urlImagen = urlImagen;
		
	}
	
	public ArtistaDto(String nickname, String nombre, String apellido, String correo, String contrasenia, Date fechaNacimiento, String descripcion, 
			String biografia, String sitioWeb, LinkedList<EspectaculoMinDto> espectaculos, LinkedList<String> seguidores, LinkedList<String> seguidos, String urlImagen) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.contrasenia = contrasenia;
		this.fechaNacimiento = fechaNacimiento;
		this.descripcion = descripcion;
		this.biografia = biografia;
		this.sitioWeb = sitioWeb;
		this.espectaculos = espectaculos;
		this.seguidores = seguidores;
		this.seguidos = seguidos;
		this.urlImagen = urlImagen;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public String getBiografia() {
		return biografia;
	}
	
	public String getSitioWeb() {
		return sitioWeb;
	}
	
	public LinkedList<EspectaculoMinDto> getEspectaculos() {
		return espectaculos;
	}

}

