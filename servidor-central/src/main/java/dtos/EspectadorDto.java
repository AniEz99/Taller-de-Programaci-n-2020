package dtos;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso(UsuarioDto.class)
@XmlAccessorType(XmlAccessType.FIELD)
public class EspectadorDto extends UsuarioDto {
	private static final long serialVersionUID = 1L;
	
	public EspectadorDto() {}
	
	private LinkedList<FuncionMinDto> funciones;
	private LinkedList<String> paquetes;
	private LinkedList<String> espectaculosFavoritos;
	private LinkedList<ValoracionDto> valoraciones;
	private LinkedList<PremioDto> premiosGanados;

	public EspectadorDto(String nickname, String nombre, String apellido, String correo, Date fechaNacimiento, String contrasenia, String urlImagen) {
		this.seguidores = new LinkedList<String>();
		this.seguidos = new LinkedList<String>();
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.fechaNacimiento = fechaNacimiento;
		this.contrasenia = contrasenia;
		this.paquetes = new LinkedList<String>();
		this.urlImagen = urlImagen;
		premiosGanados = new LinkedList<PremioDto>();
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setFunciones(LinkedList<FuncionMinDto> funciones) {
		this.funciones = funciones;
	}

	public void setPaquetes(LinkedList<String> paquetes) {
		this.paquetes = paquetes;
	}

	public EspectadorDto(String nickname, String nombre, String apellido, String correo, String contrasenia, Date fechaNacimiento,
			LinkedList<FuncionMinDto> funciones, LinkedList<String> paquetes, LinkedList<String> seguidores, LinkedList<String> seguidos, String urlImagen,
			LinkedList<String> espectaculosFavoritos, LinkedList<ValoracionDto> valoraciones) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.contrasenia = contrasenia;
		this.fechaNacimiento = fechaNacimiento;
		this.funciones = funciones;
		this.paquetes = paquetes;
		this.seguidores = seguidores;
		this.seguidos = seguidos;
		this.urlImagen = urlImagen;
		this.espectaculosFavoritos = espectaculosFavoritos;
		this.valoraciones = valoraciones;
		premiosGanados = new LinkedList<PremioDto>();
	}
	
	public LinkedList<FuncionMinDto> getFunciones() {
		return funciones;
	}
	
	public LinkedList<String> getPaquetes() {
		return paquetes;
	}
	
	public List<String> getEspectaculosFavoritos() {
		return espectaculosFavoritos;
	}
	
	public ValoracionDto getValoracion(String plataforma, String espectaculo) {
		return valoraciones.stream().filter(val -> val.getPlataforma().equals(plataforma) && val.getEspectaculo().equals(espectaculo))
							 .findAny()
							 .orElse(null);
	}
	
	public LinkedList<PremioDto> getPremios() {
		return premiosGanados;
	}
	
	public void setPremios(LinkedList<PremioDto> premios) {
		premiosGanados = premios;
	}
}
