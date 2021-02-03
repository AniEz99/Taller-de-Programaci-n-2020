package dtos;

import java.util.Date;
import java.util.LinkedList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
@XmlAccessorType(XmlAccessType.FIELD)
public class FuncionDto {
	
	private String nombre;
	private Date fechaInicio;
	private int horaComienzoHs;
	private int horaComienzoMin;
	private Date fechaRegistro;
	private LinkedList<UsuarioDto> invitados;
	private String imagen;
	private boolean sorteado;
	private LinkedList<RegistroDto> registros;
	private LinkedList<String> ganadores;
	
	public FuncionDto() {}
	
	public FuncionDto(String nom, Date fechaIni, int comienzoHr, int comienzoMin, Date fechaReg, LinkedList<UsuarioDto> artistas, String urlImagen) {
		nombre = nom;
		fechaInicio = fechaIni;
		fechaRegistro = fechaReg;
		invitados = artistas;
		imagen = urlImagen;
		horaComienzoHs = comienzoHr;
		horaComienzoMin = comienzoMin;
		sorteado = false;
		registros = new LinkedList<RegistroDto>();
	}
	
	public FuncionDto(String nom, Date fechaIni, Date fechaReg, LinkedList<UsuarioDto> artistas) {
		nombre = nom;
		fechaInicio = fechaIni;
		fechaRegistro = fechaReg;
		invitados = artistas;
		sorteado = false;
		registros = new LinkedList<RegistroDto>();
	}
	
	public void setRegistros(LinkedList<RegistroDto> listaReg) {
		registros = listaReg;
	}
	
	public LinkedList<RegistroDto> getRegistros() {
		return registros;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public Date getfechaInicio() {
		return fechaInicio;
	}
	
	public int getHoraComienzoHs() {
		return horaComienzoHs;
	}
	
	public int getHoraComienzoMin() {
		return horaComienzoMin;
	}
	
	public Date getfechaRegistro() {
		return fechaRegistro;
	}
	
	public LinkedList<UsuarioDto> getInvitados(){
		return invitados;
	}
	
	public String getImagen() {
		return imagen;
	}
	/*
	 * Esta operacion sirve para usar los comboBox en la gui
	 */
	@Override
	public String toString() {
		return nombre;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setHoraComienzoHs(int horaComienzoHs) {
		this.horaComienzoHs = horaComienzoHs;
	}

	public void setHoraComienzoMin(int horaComienzoMin) {
		this.horaComienzoMin = horaComienzoMin;
	}

	public void setInvitados(LinkedList<UsuarioDto> invitados) {
		this.invitados = invitados;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public boolean getFuncionSorteada() {
		return sorteado;
	}

	public void setFuncionSorteada(boolean sort) {
		sorteado = sort;
	}

	public void setGanadoresSorteo(LinkedList<String> ganSorteo) {
		ganadores = ganSorteo;
	}
	
	public LinkedList<String> getGanadoresSorteo() {
		return ganadores;
	}
}
