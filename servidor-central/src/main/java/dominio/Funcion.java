package dominio;
import dtos.FuncionDto;
import dtos.RegistroDto;
import dtos.UsuarioDto;
import excepciones.YaExisteRegistroException;

import java.util.Date;
import java.util.LinkedList;
import java.util.Random;

public class Funcion {
	
	private String nombre;
	private Date fechaInicio;
	private Date fechaRegistro;
	private int horaComienzoHs;
	private int horaComienzoMin;
	private int topeIdRegistro;
	private String imagen;
	private boolean sorteado;
	
	private Espectaculo esp;
	private LinkedList<Registro> registros;
	private LinkedList<Artista> invitados;
	private LinkedList<String> ganSorteo;

	public Funcion(LinkedList<Artista> artistInvit, Espectaculo espectaculo, String nombreFun, Date fechaIni, int horaHs, int horaMin, Date fechaAlta, String urlImagen ) {
		registros = new LinkedList<Registro>();
		invitados = new LinkedList<Artista>();
		invitados = artistInvit;
		esp = espectaculo;
		nombre = nombreFun;
		fechaInicio =  fechaIni;
		horaComienzoHs = horaHs;
		horaComienzoMin = horaMin;
		fechaRegistro = fechaAlta;
		imagen = urlImagen;
		sorteado = false;
		ganSorteo = new LinkedList<String>();
	}
	
	public Funcion(LinkedList<Artista> artistInvit, Espectaculo espectaculo, String nombreFun, Date fechaIni, int horaHs, int horaMin, Date fechaAlta) {
		registros = new LinkedList<Registro>();
		invitados = new LinkedList<Artista>();
		invitados = artistInvit;
		esp = espectaculo;
		nombre = nombreFun;
		fechaInicio =  fechaIni;
		horaComienzoHs = horaHs;
		horaComienzoMin = horaMin;
		fechaRegistro = fechaAlta;
		imagen = "http://productshow.ispeboston.org/wp-content/uploads/2018/06/raffle-tickets-300x231.jpg";
		sorteado = false;
		ganSorteo = new LinkedList<String>();
	}


	//Agrego parametro artistasInvitados
	public Funcion(FuncionDto funcion, LinkedList<Artista> artistasInvitados) {
		registros = new LinkedList<Registro>();
		invitados = new LinkedList<Artista>();
		nombre = funcion.getNombre();
		fechaInicio =  funcion.getfechaInicio();
		fechaRegistro = funcion.getfechaRegistro();
		invitados = artistasInvitados;		
		topeIdRegistro = 0;
		sorteado = false;
		ganSorteo = new LinkedList<String>();
	}

	public FuncionDto getData() {
		LinkedList<UsuarioDto> dataInvitados = new LinkedList<UsuarioDto>();
		for (Artista art: invitados) {
			dataInvitados.add(art.getData());
		}
		LinkedList<RegistroDto> regEspectadores = new LinkedList<RegistroDto>();
		for (Registro reg: registros) {
			regEspectadores.add(reg.getData());
		}
		FuncionDto res = new FuncionDto(nombre, fechaInicio, horaComienzoHs, horaComienzoMin, fechaRegistro, dataInvitados, imagen);
		res.setRegistros(regEspectadores);
		res.setFuncionSorteada(sorteado);
		res.setGanadoresSorteo(ganSorteo);
		return res;
	}
	
	public void setURLImagen(String url) {
		this.imagen = url;
	}


	public void setArtInvitados(LinkedList<Artista> artInvitados) {
		invitados = artInvitados;		
	}
	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	
	/*
	 * Agrega un nuevo registro a la funcion
	 */
	public void agregarRegistro(Registro nuevoRegistro) throws YaExisteRegistroException {
		for (Registro reg : registros) {
			if (reg.getId() == nuevoRegistro.getId())
				throw new YaExisteRegistroException("Ya existe este registro");
		}
		registros.add(nuevoRegistro);		
	}

	public String getNombre() {
		return nombre;
	}
	
	public String getNombreEspectaculo() {
		return esp.getNombre();
	}
	
	public String getNombrePlataforma() {
		return esp.getPlataforma();
	}

	/*
	 * Retorna el tope de IdRegistro y lo incrementa
	 */
	public int getTopeIdRegistro() {
		return topeIdRegistro++;
	}


	public Artista getArtista(String nombreArt) {
		for (int i = 0; i < invitados.size(); i++) {
			if (invitados.get(i).getNickname() == nombreArt)
				return invitados.get(i);
		}
		return null;
	}

	/*
	 * Retorna true si hay espacio para más registros
	 */
	public boolean hayEspacio() {
		return esp.getMaxEspectadores() > registros.size();
	}
	
	public boolean getFuncionSorteada() {
		return sorteado;
	}

	public void setFuncionSorteada() {
		sorteado = true;
	}

	public void sorteoFuncion(Plataforma plat, Espectaculo esp, Funcion func, String nomPremio, int cantPremios) {
		LinkedList<Espectador> espectadores = new LinkedList<Espectador>();
		LinkedList<String> ganadores = new LinkedList<String>();
		for (Registro reg : registros) {
			espectadores.add(reg.getEspectador());
		}
		int j = 1;
		while((!espectadores.isEmpty()) && (j <= cantPremios)) {
			Random rand = new Random();
			int randomIndex = rand.nextInt(espectadores.size());
		    Espectador ganador = espectadores.get(randomIndex);
		    ganadores.add(ganador.getNickname());
		    espectadores.remove(randomIndex);
		    ganador.addPremio(new Premio(plat, nomPremio, esp, func, new Date()));
		    j++;
		}
		ganSorteo = ganadores;
		sorteado = true;
	}
	
	public LinkedList<String> getGanadoresSorteo() {
		return ganSorteo;
	}
	
	public void setGanadoresSorteo(LinkedList<String> ganadores) {
		ganSorteo.addAll(ganadores);
	}
}








