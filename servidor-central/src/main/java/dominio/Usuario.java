package dominio;
import java.util.Date;
import java.util.LinkedList;

import dtos.UsuarioDto;

public abstract class Usuario {
	
	protected String nickname;
	protected String nombre;
	protected String apellido;
	protected String correo;
	protected Date fechaNacimiento;
	protected String contrasenia;
	protected LinkedList<String> seguidores;
	protected LinkedList<String> seguidos;
	
	protected String urlImagen;
	
	public String getNickname() {
		return nickname;
	}
	
	
	public abstract UsuarioDto getData();
	public String getCorreo() {
		return correo;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	
	public LinkedList<String> getSeguidores(){
		return seguidores;
	}
	
	public LinkedList<String> getSeguidos() {
		return seguidos;
	}
	
	public void addSeguidor(String seguidor) {
		seguidores.add(seguidor);
	}
	
	public void addSeguido(String seguidor) {
		seguidos.add(seguidor);
	}
	
	public void removeSeguidor(String seguidor) {
		seguidores.remove(seguidor);
	}
	
	public void removeSeguido(String seguidor) {
		seguidos.remove(seguidor);
	}
	
	public String getUrlImagen() {
		return urlImagen;
	}
	
	public void setUrlImagen(String url) {
		urlImagen = url;
	}
}
