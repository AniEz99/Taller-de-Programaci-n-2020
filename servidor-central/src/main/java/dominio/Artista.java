package dominio;
import java.util.Date;
import java.util.LinkedList;

import dominio.Espectaculo.Estado;
import dtos.ArtistaDto;
import dtos.EspectaculoDto;
import dtos.EspectaculoMinDto;
import dtos.ImagenDto;
import dtos.UsuarioDto;
import logica.Utils;

public class Artista extends Usuario {
	
	private String descripcion;
	private String biografia;
	private String sitioWeb;
	
	private LinkedList<Espectaculo> espectaculos;
	private LinkedList<Funcion> funciones;
	private LinkedList<Paquete> paquetes;
	
	
	public Artista(String nickname, String nombre, String apellido, String correo, Date fechaNacimiento, String descripcion, String biografia, String sitioWeb, String contrasenia) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.fechaNacimiento = fechaNacimiento;
		this.descripcion = descripcion;
		this.biografia = biografia;
		this.sitioWeb = sitioWeb;
		this.contrasenia = contrasenia;
		espectaculos = new LinkedList<Espectaculo>();
		funciones = new LinkedList<Funcion>();
		seguidores = new LinkedList<String>();
		paquetes = new LinkedList<Paquete>();
		seguidos = new LinkedList<String>();
		urlImagen = "https://i.blogs.es/2d5264/facebook-image/450_1000.jpg";
	}
	
	
	public void setPaquetes(LinkedList<Paquete> paquetes_) {
		paquetes = paquetes_;
	}
	
	public void setSeguidos(LinkedList<String> seguidos_) {
		seguidos = seguidos_;
	}
	
	public void setSeguidores(LinkedList<String> seguidores_) {
		seguidores = seguidores_;
	}
	
	public void setFunciones(LinkedList<Funcion> funciones_) {
		funciones = funciones_;
	}
	
	public void setEspectaculos(LinkedList<Espectaculo> espectaculos_) {
		espectaculos = espectaculos_;
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
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public String getBiografia() {
		return biografia;
	}
	
	public String getSitioWeb() {
		return sitioWeb;
	}
	
	public LinkedList<Paquete> getPaquetes() {
		return paquetes;
	}
	
	public LinkedList<Espectaculo> getEspectaculos() {
		return espectaculos;
	}
	
	public LinkedList<Funcion> getFunciones() {
		return funciones;
	}
	
	public void addPaquete(Paquete paq) {
		paquetes.add(paq);
	}
	
	@Override
	public UsuarioDto getData() {
		LinkedList<EspectaculoMinDto> listaEsp = new LinkedList<EspectaculoMinDto>();
		espectaculos.forEach(e -> {
			Estado estado = e.getEstado();
			String estadoStr = "";
			switch(estado) {
			  case INGRESADO:
				  estadoStr = "INGRESADO";
				  break;
			  case RECHAZADO:
				  estadoStr = "RECHAZADO";
				  break;
			  case ACEPTADO:
				  estadoStr = "ACEPTADO";
				  break;
			  case FINALIZADO:
				  estadoStr = "FINALIZADO";
				  break;

			}
			listaEsp.add(new EspectaculoMinDto(e.getNombre(), e.getPlataforma(), estadoStr));
		});
		return new ArtistaDto(nickname, nombre, apellido, correo, contrasenia, fechaNacimiento, descripcion, biografia, sitioWeb, listaEsp, seguidores, seguidos, urlImagen);
	}

	public void addEspectaculo(Espectaculo esp) {
		espectaculos.add(esp);
	}
	
	public void addFuncion(Funcion func) {
		funciones.add(func);
	}
	
	public void addFuncionConImagen(Funcion func, ImagenDto imagen) throws Exception {
		if (imagen.getName() != null) {
			String url = Utils.guardarImagen(imagen, "funcion_" + func.getNombre());
			func.setURLImagen(url);
		} 
		funciones.add(func);
	}
	
	public LinkedList<EspectaculoDto> getEspectaculosAprobados(){
		LinkedList<EspectaculoDto> resultado = new LinkedList<EspectaculoDto>();
		for (int i = 0; i < this.espectaculos.size(); i++) {
			if (this.espectaculos.get(i).getEstado() == Estado.ACEPTADO) {
				resultado.add(this.espectaculos.get(i).getData());
			}
		}
		return resultado;
	}
	
	public Espectaculo getEspectaculo(String nombre){
		Espectaculo resultado = new Espectaculo();
		for (int i = 0; i < espectaculos.size(); i++) {
			if (espectaculos.get(i).getNombre().equals(nombre)) {
				resultado = espectaculos.get(i);
			}
		}
		return resultado;
	}
}

