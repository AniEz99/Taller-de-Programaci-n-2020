package dtos;

import java.util.Date;
import java.util.LinkedList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class UsuarioDto implements java.io.Serializable{

	private static final long serialVersionUID = 1L;


	public UsuarioDto() {}
	
	protected String nickname;
	protected String nombre;
	protected String apellido;
	protected String correo;
	protected Date fechaNacimiento;
	protected String contrasenia;
	protected LinkedList<String> seguidores;
	protected LinkedList<String> seguidos;
	protected String urlImagen;
	
	
	/*
	 * Esta operacion sirve para usar los comboBox en la gui
	 */
	@Override
	public String toString() {
		return nickname;
	}
	
	public String getContrasenia() {
		return contrasenia;
	}
	
	public String getNombreCompleto() {
		return nombre + " " + apellido;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public LinkedList<String> getSeguidores() {
		return seguidores;
	}
	
	public LinkedList<String> getSeguidos() {
		return seguidos;
	}
	
	public String getUrlImagen() {
		return urlImagen;
	}
	
	public void setUrlImagen(String url) {
		urlImagen = url;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public void setSeguidores(LinkedList<String> seguidores) {
		this.seguidores = seguidores;
	}

	public void setSeguidos(LinkedList<String> seguidos) {
		this.seguidos = seguidos;
	}

}
