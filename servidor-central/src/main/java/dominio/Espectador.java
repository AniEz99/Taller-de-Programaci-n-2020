package dominio;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


import dtos.EspectadorDto;
import dtos.FuncionMinDto;
import dtos.PremioDto;
import dtos.UsuarioDto;
import dtos.ValoracionDto;
import excepciones.PaqueteYaExisteException;
import excepciones.RegistroNoExisteException;
import excepciones.YaExisteRegistroException;

public class Espectador extends Usuario {
	private LinkedList<Registro> registros;
	private LinkedList<Paquete> paquetes;
	
	private Map<String, Date> comprasPaquetes;
	private LinkedList<String> espectaculosFavoritos;
	private LinkedList<Premio> premiosGanados;
	
	private Map<LinkedList<String>, Valoracion> valoraciones;
	
	public Espectador(String nickname, String nombre, String apellido, String correo, Date fechaNacimiento, String contrasenia) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.fechaNacimiento = fechaNacimiento;
		this.contrasenia = contrasenia;
		registros = new LinkedList<Registro>();
		paquetes = new LinkedList<Paquete>();
		comprasPaquetes = new HashMap<String, Date>();
		seguidores = new LinkedList<String>();
		seguidos = new LinkedList<String>();
		urlImagen = "https://i.blogs.es/2d5264/facebook-image/450_1000.jpg";
		espectaculosFavoritos = new LinkedList<String>();
		valoraciones = new HashMap<LinkedList<String>, Valoracion>();
		premiosGanados = new LinkedList<Premio>();
	}
	
	

	
	/*
	 * Retorna true si tiene un registro asociado a nombreFuncion
	 */
	public boolean estaRegistradoA(String nombreFuncion) {
		for (Registro reg : registros) {
			if (reg.getNombreFuncion().contentEquals(nombreFuncion))
				return true;
		}
		return false;
	}

	/*
	 * Retorna la lista de paquetes que tiene asociado el espectador
	 */
	public LinkedList<Paquete> obtenerPaquetes() {
		return paquetes;
	}
	
	/*
	 *	Retorna el paquete del espectador con nombre codPack
	 *	Sino null
	 */
	public Paquete obtenerPaquete(String codPack) {
		for (Paquete pack : paquetes) {
			if (pack.getNombre().equals(codPack))
				return pack;
		}
		return null;
	}
	
	public void agregarPaquete(Paquete paq) throws PaqueteYaExisteException {
		for (Paquete p : paquetes) {
			if (p.getNombre() == paq.getNombre()) {
				throw new PaqueteYaExisteException("");
			}
		}
		paquetes.add(paq);
	}
	public void compraPaquete(String nombre, Date fecha) throws PaqueteYaExisteException {
		for (String p : comprasPaquetes.keySet()) {
			if (p.equals(nombre)) {
				throw new PaqueteYaExisteException("");
			}
		}
		comprasPaquetes.put(nombre, fecha);
	}

	/*
	 * Retorna la lista de registros asociado al espectador
	 */
	public LinkedList<Registro> obtenerRegistros() {
		return registros;
	}

	/*
	 * Retorna el registro con dicho id del espectador, si no existe retorna null
	 */
	public Registro getRegistro(String nombreFuncion) throws RegistroNoExisteException {
		for (Registro reg : registros) {
			if (reg.getNombreFuncion().equals(nombreFuncion)) {
				return reg;
			}
		}
		throw new RegistroNoExisteException("No existe el registro de la funcion "+nombreFuncion+ "en el espectador " + nickname);
	}

	/*
	 * Agrega el registro al espectador
	 */
	public void agregarRegistro(Registro nuevoRegistro) throws YaExisteRegistroException {
		for (Registro reg : registros) {
			if (reg.getNombreFuncion() == nuevoRegistro.getNombreFuncion()) {
				throw new YaExisteRegistroException("");
			}
		}
		registros.add(nuevoRegistro);
	}
	
	@Override
	public UsuarioDto getData() {
		LinkedList<FuncionMinDto> funciones = new LinkedList<FuncionMinDto>();
		registros.forEach(r -> funciones.add(new FuncionMinDto(r.getNombrePlataforma(), r.getNombreEspectaculo(), r.getNombreFuncion())));
		LinkedList<String> paquetes = new LinkedList<String>();
		this.paquetes.forEach(p -> paquetes.add(p.getNombre()));
		
		LinkedList<ValoracionDto> vals = new LinkedList<ValoracionDto>();
		valoraciones.entrySet().forEach(v -> vals.add(v.getValue().getData()));
		EspectadorDto res = new EspectadorDto(nickname, nombre, apellido, correo, contrasenia, fechaNacimiento, funciones, paquetes, seguidores,
				seguidos, urlImagen, espectaculosFavoritos, vals);
		LinkedList<PremioDto> listaPre = new LinkedList<PremioDto>();
		for (Premio prem: premiosGanados) {
			listaPre.add(prem.getData());
		}
		res.setPremios(listaPre);
		return res;
	}

	/*
	 * Retorna el datatype de Espectador
	 */
	public EspectadorDto getDataEspectador() {
		return new EspectadorDto(nickname, nombre, apellido, correo, fechaNacimiento, "", urlImagen);
	}

	public LinkedList<Registro> obtenerRegistrosCanjeables() {
		LinkedList<Registro> registrosCanjeables = new LinkedList<Registro>();
		for (Registro reg : registros) {
			if (reg.esCanjeable())
				registrosCanjeables.add(reg);
		}
		return registrosCanjeables;
	}
	
	public LinkedList<Registro> obtenerTodosRegistros() {
		return registros;
	}

	public void quitarRegistro(String nombreFuncionRegistro) {
		for (Registro reg : registros) {
			if (reg.getNombreFuncion() == nombreFuncionRegistro) {
				registros.remove(reg);
			}
		}
	}

	public boolean tieneRegistro(String nombreFuncion) {	
		for (Registro reg : registros) {
			if (reg.getNombreFuncion() == nombreFuncion)
				return true;
		}
		return false;
	}
	
	public Map<String, Date> obtenerComprasPaquetes() {
		return comprasPaquetes;
	}
	
	public void setRegistros(LinkedList<Registro> registros_) {
		registros = registros_;
	}
	
	public void setPaquetes(LinkedList<Paquete> paquetes_) {
		paquetes = paquetes_;
	}
	
	public void setComprasPaquete(Map<String, Date> comprasPaquetes_) {
		comprasPaquetes = comprasPaquetes_;
	}
	
	public void setSeguidores(LinkedList<String> seguidores_) {
		seguidores = seguidores_;
	}
	
	public void setSeguidos(LinkedList<String> seguidos_) {
		seguidos = seguidos_;
	}
	
	
	public void addEspectaculoFavorito(String espectaculo) {
		espectaculosFavoritos.add(espectaculo);
	}
	
	public void removeEspectaculoFavorito(String espectaculo) {
		espectaculosFavoritos.remove(espectaculo);
	}
	
	public void addValoracion(String plataforma, String espectaculo, Valoracion valoracion) {
		LinkedList<String> aux = new LinkedList<String>();
		aux.add(plataforma);
		aux.add(espectaculo);
		valoraciones.put(aux, valoracion);
	}


	public String getNombre() {
		return nombre;
	}
	
	public void addPremio (Premio premio) {
		premiosGanados.add(0, premio);
	}
	
	public LinkedList<Premio> getPremios(){
		return premiosGanados;
	}
	
}
