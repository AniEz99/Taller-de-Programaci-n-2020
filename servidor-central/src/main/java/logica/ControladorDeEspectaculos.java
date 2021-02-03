package logica;

import dominio.Plataforma;

import dominio.Artista;
import dominio.Categoria;
import dominio.Espectaculo;
import dominio.Espectaculo.Estado;
import dominio.Funcion;
import dominio.Paquete;
import dominio.Registro;
import dominio.Valoracion;
import dominio.generadorPDF;
import dominio.Espectador;

import java.util.Map;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.LinkedList;

import dtos.ArtistaDto;
import dtos.CategoriaDto;
import dtos.EspectaculoDto;
import dtos.EspectadorDto;
import dtos.FuncionDto;
import dtos.ImagenDto;
import dtos.PaqueteDto;
import dtos.PlataformaDto;
import dtos.RegistroDto;
import dtos.ValoracionDto;
import excepciones.YaExisteRegistroException;
import excepciones.EspectaculoNoAceptadoException;
import excepciones.EspectaculoNoExisteException;
import excepciones.EspectaculoYaExisteException;
import excepciones.EspectadorNoExisteException;
import excepciones.FaltanRegistroException;
import excepciones.FuncionNoExisteException;
import excepciones.FuncionYaExisteException;
import excepciones.MinEspMayorAMaxEspException;
import excepciones.PlataformaNoExisteException;
import excepciones.RegistroNoExisteException;
import excepciones.UsuarioNoExisteException;
import excepciones.ValoracionNoValidaException;
import excepciones.YaExisteCategoriaException;

public class ControladorDeEspectaculos implements IControladorDeEspectaculos {

	@Override 
	public LinkedList<CategoriaDto> listarCategorias(){
		return ManejadorDeCategorias.getInstance().listarCategorias();
	}
	
	@Override
	public void confirmarAltaEspectaculo(String nPlat, String nArt, String nombre, String descripcion, int duracionHoras, int duracionMinutos,
				int minEspectadores, int maxEspectadores, String url, float costo, Date fechaRegistro, ImagenDto imagen, LinkedList<CategoriaDto> categorias) 
						throws Exception {
		
		ManejadorDePlataformas mplat = ManejadorDePlataformas.getInstance();
		Plataforma platAux = mplat.getPlataforma(nPlat);
		ManejadorDeUsuarios muser = ManejadorDeUsuarios.getInstance();
		Artista artAux = (Artista) muser.getUsuario(nArt);
		
		if (platAux.existeEspectaculo(nombre)) {
			throw  new EspectaculoYaExisteException("Ya existe un espectaculo en la plataforma con nombre: " + nombre + ".");
		}
		else {
			if (minEspectadores > maxEspectadores) {
				throw new  MinEspMayorAMaxEspException("minimo de espectadores > maximo de espectadores");
			}
			else {
				Espectaculo esp = new Espectaculo(platAux, artAux, nombre, descripcion, duracionHoras, duracionMinutos, minEspectadores, maxEspectadores, url, costo, fechaRegistro, Espectaculo.Estado.INGRESADO);
				platAux.addEspectaculo(esp, imagen);
				artAux.addEspectaculo(esp);

				for (CategoriaDto cat : categorias) {
					esp.addCategoria(ManejadorDeCategorias.getInstance().getCategoria(cat.getNombre()));
				}
			}
		}
	}
		
	@Override
	public void cancelarAltaEspectaculo() {// TODO Auto-generated method stub}
	}
	
	@Override
	public LinkedList<EspectaculoDto> obtenerEspectaculosPorPlataforma(String plataforma) throws PlataformaNoExisteException{
		ManejadorDePlataformas mplat = ManejadorDePlataformas.getInstance();
		Plataforma plat = mplat.getPlataforma(plataforma);
		LinkedList<EspectaculoDto> lesp = plat.obtenerEspectaculosAsoc();
		return lesp;
	}
	
	public EspectaculoDto obtenerDatosEspectaculo(String plataforma, String nombre) throws PlataformaNoExisteException, EspectaculoNoExisteException{
		ManejadorDePlataformas mplat = ManejadorDePlataformas.getInstance();
		Plataforma plat = mplat.getPlataforma(plataforma);
		Espectaculo esp = plat.getEspectaculo(nombre);
		EspectaculoDto res = esp.getData();
		return res;
	}
	
	//esta creo que hay que borrarla
	public LinkedList<EspectaculoDto> ingresarPlataforma(String urlPlataforma) throws PlataformaNoExisteException {
		ManejadorDePlataformas manejador = ManejadorDePlataformas.getInstance();		
		Plataforma plataformaIngresada = manejador.getPlataforma(urlPlataforma);
		LinkedList<EspectaculoDto> listaEspec = plataformaIngresada.obtenerEspectaculosAsoc();
		return listaEspec;		
	}


	/* -------------- Registro a Función de Espectáculo ------------*/
	@Override
	public LinkedList<PlataformaDto> obtenerPlataformas() {
		ManejadorDePlataformas manejador = ManejadorDePlataformas.getInstance();
		return manejador.obtenerPlataformasDto();
	}
	
	@Override
	public LinkedList<EspectaculoDto> obtenerEspectaculosAceptadosPorPlataforma(String plataforma) throws PlataformaNoExisteException {
		ManejadorDePlataformas mplat = ManejadorDePlataformas.getInstance();
		Plataforma plat = mplat.getPlataforma(plataforma);
		LinkedList<EspectaculoDto> lesp = plat.obtenerEspectaculosAceptados();
		return lesp;
	}
	
	@Override
	public LinkedList<FuncionDto> obtenerFuncionesVigentesDeEspectaculo(String nombrePlataforma, String nombreEspectaculo) throws PlataformaNoExisteException, EspectaculoNoExisteException {
		Espectaculo espectaculo = ManejadorDePlataformas.getInstance().getPlataforma(nombrePlataforma).getEspectaculo(nombreEspectaculo);
		Map<String, Funcion> funciones = espectaculo.getFunciones();
		LinkedList<FuncionDto> resultado = new LinkedList<FuncionDto>();
		Date fechaActual = new Date(); // crea una fecha con hora actual
		for (Map.Entry<String, Funcion> item : funciones.entrySet()) {
			if (fechaActual.before(item.getValue().getFechaInicio()) && item.getValue().hayEspacio()) {
				resultado.add(item.getValue().getData());			  
			}
		}
		return resultado;
	}
	
	@Override
	public LinkedList<EspectadorDto> obtenerEspectadoresNoRegistradosEnFuncion(String nombreFuncion) {
		return ManejadorDeUsuarios.getInstance().getEspectadoresNoRegistradosEnFuncion(nombreFuncion);
	}
	@Override
	public LinkedList<PaqueteDto> obtenerPaquetesEspectadorEspectaculo(String nickname) throws EspectadorNoExisteException  {
		LinkedList<PaqueteDto> resultado = new LinkedList<PaqueteDto>();
		Espectador esp = ManejadorDeUsuarios.getInstance().getEspectador(nickname);
		LinkedList<Paquete> esp_paq = esp.obtenerPaquetes();
		for (Paquete item : esp_paq) {
			resultado.add(item.getData());
		}
		return resultado;
	}
	@Override
	public LinkedList<RegistroDto> obtenerRegistrosEspectador(String nickname) throws EspectadorNoExisteException {
		LinkedList<RegistroDto> resultado = new LinkedList<RegistroDto>();
		Espectador esp = ManejadorDeUsuarios.getInstance().getEspectador(nickname);
		LinkedList<Registro> esp_reg = esp.obtenerRegistrosCanjeables();
		for (Registro item : esp_reg) {
			resultado.add(item.getData());
		}
		return resultado;
	}
	
	@Override
	public LinkedList<RegistroDto> obtenerTodosRegistrosEspectador(String nickname) throws EspectadorNoExisteException {
		LinkedList<RegistroDto> resultado = new LinkedList<RegistroDto>();
		Espectador esp = ManejadorDeUsuarios.getInstance().getEspectador(nickname);
		LinkedList<Registro> esp_reg = esp.obtenerTodosRegistros();
		for (Registro item : esp_reg) {
			resultado.add(item.getData());
		}
		return resultado;
	}
	
	
	@Override
	public void altaRegistroConCanje(String nickname, String nombrePlat, String nombreEsp, String nombreFuncion, LinkedList<String> nombreFuncionesRegistros) throws PlataformaNoExisteException, EspectaculoNoExisteException, FuncionNoExisteException, EspectadorNoExisteException, YaExisteRegistroException, RegistroNoExisteException, FaltanRegistroException {
		Espectador esp = ManejadorDeUsuarios.getInstance().getEspectador(nickname);
		LinkedList<Registro> aCanjear = new LinkedList<Registro>();
		if (nombreFuncionesRegistros.size() != 3) {
			throw new FaltanRegistroException("Cantidad de registros menor a 3");
		}
		Funcion func = ManejadorDePlataformas.getInstance().getPlataforma(nombrePlat).getEspectaculo(nombreEsp).getFuncion(nombreFuncion);
		for (String registro : nombreFuncionesRegistros) {
			Registro reg = esp.getRegistro(registro);
			reg.deshabilitar();
			aCanjear.add(reg);
		}
		Registro nuevoRegistro = new Registro(func, esp, null, 0, false, aCanjear, func.getTopeIdRegistro());
		func.agregarRegistro(nuevoRegistro);
		esp.agregarRegistro(nuevoRegistro);
	}
	@Override
	public void altaRegistroPaquete(String nickname, String nombrePlat, String nombreEsp, String nombreFuncion, String nombrePaquete) throws PlataformaNoExisteException, EspectaculoNoExisteException, FuncionNoExisteException, EspectadorNoExisteException, YaExisteRegistroException{
		Espectador espectador = ManejadorDeUsuarios.getInstance().getEspectador(nickname);
		Paquete pack = espectador.obtenerPaquete(nombrePaquete);
		pack.removerEspectaculo(nombreEsp); // Deshabilito el espectaculo del paquete del espectador
		Espectaculo espectaculo = ManejadorDePlataformas.getInstance().getPlataforma(nombrePlat).getEspectaculo(nombreEsp);
		Funcion func = espectaculo.getFuncion(nombreFuncion);
		Registro nuevoRegistro = new Registro(func, espectador, pack, espectaculo.getCosto()*(100-pack.getDescuento()), true, null, func.getTopeIdRegistro()); 
		func.agregarRegistro(nuevoRegistro); // Agrego el registro a func y esp
		espectador.agregarRegistro(nuevoRegistro);
	}
	@Override
	public void altaRegistroComun(String nickname, String nombrePlat, String nombreEsp, String nombreFuncion) throws PlataformaNoExisteException, EspectaculoNoExisteException, FuncionNoExisteException, EspectadorNoExisteException, YaExisteRegistroException, EspectaculoNoAceptadoException {
		Espectador espectador = ManejadorDeUsuarios.getInstance().getEspectador(nickname);
		Espectaculo espectaculo = ManejadorDePlataformas.getInstance().getPlataforma(nombrePlat).getEspectaculo(nombreEsp);
		if (espectaculo.getEstado() != Espectaculo.Estado.ACEPTADO) {
			throw new EspectaculoNoAceptadoException("El espectaculo no fue aceptado");
		}
		Funcion func = espectaculo.getFuncion(nombreFuncion);
		Registro nuevoRegistro = new Registro(func, espectador, null, espectaculo.getCosto(), true, null, func.getTopeIdRegistro());
		if (espectador.tieneRegistro(nombreFuncion)) {
			throw new YaExisteRegistroException("Ya tiene registro para funcion " + nombreFuncion + " el espectador " + nickname);
		} else {
			espectador.agregarRegistro(nuevoRegistro);
			func.agregarRegistro(nuevoRegistro);
		}
	}

	/*---------Fin registro a funcion---------*/

	/* ---------- Aceptar/Rechazar espectaculo ---------- */
	
	@Override
	public LinkedList<EspectaculoDto> obtenerEspectaculosIngresados(String nomPlat) throws PlataformaNoExisteException {
		ManejadorDePlataformas mplat = ManejadorDePlataformas.getInstance();
		Plataforma plat = mplat.getPlataforma(nomPlat);
		LinkedList<EspectaculoDto> lesp = plat.obtenerEspectaculosIngresados();
		return lesp; 
	}
	
	@Override
	public void aceptarEspectaculo(String nomPlat, String nomEsp) throws EspectaculoNoExisteException, PlataformaNoExisteException {
		ManejadorDePlataformas mplat = ManejadorDePlataformas.getInstance();
		mplat.getPlataforma(nomPlat).getEspectaculo(nomEsp).setEstado(Espectaculo.Estado.ACEPTADO);
	}
	
	@Override
	public void cancelarEspectaculo(String nomPlat, String nomEsp) throws EspectaculoNoExisteException, PlataformaNoExisteException {
		ManejadorDePlataformas mplat = ManejadorDePlataformas.getInstance();
		mplat.getPlataforma(nomPlat).getEspectaculo(nomEsp).setEstado(Espectaculo.Estado.RECHAZADO);
	}
	
	/* -------- Fin Aceptar/Rechazar espectaculo -------- */
	
	/* ------------ Busqueda de espectáculos ------------ */
	
	@Override
	public LinkedList<EspectaculoDto> buscarEspectaculos(String query) {
		LinkedList<EspectaculoDto> lista = new LinkedList<EspectaculoDto>();
		Map<String, Plataforma> plats = ManejadorDePlataformas.getInstance().getPlataformas();
		for (Map.Entry<String, Plataforma> parPlat : plats.entrySet()) {
			Plataforma plat = parPlat.getValue();
			for (Map.Entry<String, Espectaculo> parEsp : plat.getEspectaculos().entrySet()) {
				Espectaculo esp = parEsp.getValue();
				if (esp.contieneTira(query) && (esp.getEstado() == Estado.ACEPTADO)) {
					lista.add(esp.getData());
				}
			}
		}
		return lista;
	}
	
	/* ---------- Fin Busqueda de espectáculos ---------- */
	
	@Override
	public LinkedList<ArtistaDto> obtenerArtistas() {
		LinkedList<ArtistaDto> res = new LinkedList<ArtistaDto>();
		ManejadorDeUsuarios.getInstance().obtenerArtistas().forEach(a -> res.add((ArtistaDto) a.getData()));
		return res;
	}
	@Override	
	public void confirmarAltaFuncion(String nombreFunc, int hora, int min, Date fecha, LinkedList<String> artistas, EspectaculoDto espectaculo, PlataformaDto plataforma, String imagen) throws FuncionYaExisteException, PlataformaNoExisteException, EspectaculoNoExisteException {
		ManejadorDePlataformas manP = ManejadorDePlataformas.getInstance();
		Plataforma plat = manP.getPlataforma(plataforma.getNombre());
		Espectaculo espec = plat.getEspectaculo(espectaculo.getNombre());		
		if (espec.existeFuncion(nombreFunc)) {
			throw new FuncionYaExisteException("Ya existe una funcion con nombre: " + nombreFunc + ".");
		} else {
			Date fechaAlta = new Date();
			LinkedList<Artista> artistasInvit = new LinkedList<Artista>();
			ManejadorDeUsuarios manU = ManejadorDeUsuarios.getInstance();
			for (int i = 0; i < artistas.size(); i++) {
				String nombreArt = artistas.get(i);
				Artista art = manU.getArtista(nombreArt);
				artistasInvit.add(art);
			}
			Funcion nuevaFuncion = new Funcion(artistasInvit, espec, nombreFunc, fecha, hora, min, fechaAlta, imagen);
			espec.addFuncion(nuevaFuncion);
			for (int i = 0; i < artistasInvit.size(); i++) {
				artistasInvit.get(i).addFuncion(nuevaFuncion);
			}
		}
	}
	
	public boolean confirmarAltaFuncion2(String nombreFunc, int hora, int min, Date fecha, LinkedList<String> artistasInvitados, String nombreEspectaculo, String nickArtista, ImagenDto imagen) throws Exception {
		ManejadorDeUsuarios manejadorUsuarios = ManejadorDeUsuarios.getInstance();
		Artista artistaLogeado;
		try {
			artistaLogeado = (Artista) manejadorUsuarios.getUsuario(nickArtista);
			Espectaculo espectaculo = artistaLogeado.getEspectaculo(nombreEspectaculo);
			@SuppressWarnings("unused")
			Funcion aux = espectaculo.getFuncion(nombreFunc);
			return false;
			
		} catch (UsuarioNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FuncionNoExisteException e) {
			artistaLogeado = (Artista) manejadorUsuarios.getUsuario(nickArtista);
			Espectaculo espectaculo = artistaLogeado.getEspectaculo(nombreEspectaculo);
			Date fechaAlta = new Date();
			LinkedList<Artista> artistas = new LinkedList<Artista>();
	    	if (artistasInvitados != null) {
		    	for (String art: artistasInvitados) {
		    		artistas.add(manejadorUsuarios.getArtista(art));
		    	}
	    	}
	    	Funcion nuevaFuncion = new Funcion(artistas, espectaculo, nombreFunc, fecha, hora, min, fechaAlta);
	    	if (imagen != null) {
	    		artistaLogeado.addFuncionConImagen(nuevaFuncion, imagen);
	    		espectaculo.addFuncion(nuevaFuncion);
	    	}else {
	    		artistaLogeado.addFuncion(nuevaFuncion);
	    		espectaculo.addFuncion(nuevaFuncion);
	    	}
	    	return true;
		}
		return false;
    	
	}
	
	
	

	@Override
	public LinkedList<FuncionDto> seleccionarEspectaculoYRetornarFunciones(String nombreEspectaculo, String nombrePlataforma) throws EspectaculoNoExisteException, PlataformaNoExisteException {
		EspectaculoDto aux;
		LinkedList<FuncionDto> resultado = new LinkedList<FuncionDto>();
	
		Plataforma platIngresada = ManejadorDePlataformas.getInstance().getPlataforma(nombrePlataforma);
		aux = platIngresada.getEspectaculo(nombreEspectaculo).getData();
		resultado = aux.getFunciones();
		
		return resultado;
	}
	
	
	@Override
	public FuncionDto seleccionarFuncion(String nombreFuncion, String nombreEspectaculo, String nombrePlataforma) throws PlataformaNoExisteException, EspectaculoNoExisteException {
		ManejadorDePlataformas manejadorPlat = ManejadorDePlataformas.getInstance();
		FuncionDto res = null;
		
		Plataforma plataformaSeleccionada = manejadorPlat.getPlataforma(nombrePlataforma);
		EspectaculoDto espectaculoSeleccionado = plataformaSeleccionada.getEspectaculo(nombreEspectaculo).getData();
		LinkedList<FuncionDto> aux = espectaculoSeleccionado.getFunciones();
		res = null;
		for (int i = 0; i < aux.size(); i++) {
			if (aux.get(i).getNombre().equals(nombreFuncion)) {
				res = aux.get(i);
			}
		}
		return res;
	}
	
	//Alta de categoria 
	@Override
	public void altaCategoria(String nombreCat) throws YaExisteCategoriaException {
		Categoria nuevaCat = new Categoria(nombreCat);
		ManejadorDeCategorias man = ManejadorDeCategorias.getInstance();
		man.addCategoria(nuevaCat);
	}
	
	@Override
	public LinkedList<EspectaculoDto> obtenerEspectaculosPorCategoria(String nomCat) {
		LinkedList<EspectaculoDto> lista = new LinkedList<EspectaculoDto>();
		Map<String, Plataforma> plats = ManejadorDePlataformas.getInstance().getPlataformas();
		for (Map.Entry<String, Plataforma> plat : plats.entrySet()) {
			Map<String, Espectaculo> esps = plat.getValue().getEspectaculos();
			for (Map.Entry<String, Espectaculo> esp : esps.entrySet()) {
				if (esp.getValue().getCategoriasNombre().contains(nomCat) && (esp.getValue().getEstado() == Estado.ACEPTADO)) {
					lista.add(esp.getValue().getData());
				}
			}
		}
		return lista;
	}

	@Override
	public void marcarEspectaculoFavorito(String usuario, String plataforma, String espectaculo) throws PlataformaNoExisteException,
			EspectaculoNoExisteException, EspectadorNoExisteException {
		Plataforma plat = ManejadorDePlataformas.getInstance().getPlataforma(plataforma);
		Espectaculo esp = plat.getEspectaculo(espectaculo);
		Espectador usu = ManejadorDeUsuarios.getInstance().getEspectador(usuario);
		esp.incCantidadFavoritos();
		usu.addEspectaculoFavorito(espectaculo);
	}

	@Override
	public void desmarcarEspectaculoFavorito(String usuario, String plataforma, String espectaculo) throws PlataformaNoExisteException,
			EspectaculoNoExisteException, EspectadorNoExisteException {
		Plataforma plat = ManejadorDePlataformas.getInstance().getPlataforma(plataforma);
		Espectaculo esp = plat.getEspectaculo(espectaculo);
		Espectador usu = ManejadorDeUsuarios.getInstance().getEspectador(usuario);
		esp.decCantidadFavoritos();
		usu.removeEspectaculoFavorito(espectaculo);
				
	}

	@Override
	public void valorarEspectaculo(String plataforma, String espectaculo, String usuario, ValoracionDto valoracion) throws PlataformaNoExisteException, 
			EspectaculoNoExisteException, EspectadorNoExisteException, ValoracionNoValidaException {
		Plataforma plat = ManejadorDePlataformas.getInstance().getPlataforma(plataforma);
		Espectaculo esp = plat.getEspectaculo(espectaculo);
		Espectador usu = ManejadorDeUsuarios.getInstance().getEspectador(usuario);
		Valoracion val = new Valoracion(valoracion.getEstrellas(), plataforma, espectaculo);
		if (!usu.obtenerRegistros().stream().anyMatch(r -> r.getNombrePlataforma().equals(plataforma) && r.getNombreEspectaculo().equals(espectaculo))) {
			throw new ValoracionNoValidaException("El espectador no esta registrado a ninguna funcion de este espectaculo.");
		}
		usu.addValoracion(plataforma, espectaculo, val);
		esp.addValoracion(val);
	}
	
	@Override
	public void guardarPremios(String descPremio, int cantPremio, String nomEspectaculo, String nomPlataforma) throws PlataformaNoExisteException, EspectaculoNoExisteException {
		Plataforma plat = ManejadorDePlataformas.getInstance().getPlataforma(nomPlataforma);
		Espectaculo esp = plat.getEspectaculo(nomEspectaculo);
		esp.setCantPremio(cantPremio);
		esp.setPremio(descPremio);
	}
	
	@Override
	public void hacerSorteoPremio(String nomEspectaculo, String nomPlataforma, String nomFuncion ) throws PlataformaNoExisteException, EspectaculoNoExisteException, FuncionNoExisteException {
		Plataforma plat = ManejadorDePlataformas.getInstance().getPlataforma(nomPlataforma);
		Espectaculo esp = plat.getEspectaculo(nomEspectaculo);
		Funcion func = esp.getFuncion(nomFuncion);
		int cantPremios = esp.getCantPremio();
		func.sorteoFuncion(plat, esp, func, esp.getPremio(), cantPremios);	
	}
	
	@Override
	public void guardarUrlVideo(String urlVideo, String nomPlataforma, String nomEspectaculo) throws PlataformaNoExisteException, EspectaculoNoExisteException {
		Plataforma plat = ManejadorDePlataformas.getInstance().getPlataforma(nomPlataforma);
		Espectaculo esp = plat.getEspectaculo(nomEspectaculo);
		esp.setUrlVideo(urlVideo);
	}
	
	@Override
	public void finalizarEspectaculo(String plat, String nomEsp) throws PlataformaNoExisteException, EspectaculoNoExisteException {
		Plataforma plataforma = ManejadorDePlataformas.getInstance().getPlataforma(plat); 
		Espectaculo espectaculo = plataforma.getEspectaculo(nomEsp);
		espectaculo.setEstado(Estado.FINALIZADO);
	}
	
	@Override 
	public byte[] generarPdf (String nomPremio, String nomEspec, String nomFunc, String nomEsp, String fecha, String fechaFin) {
		generadorPDF gen = new generadorPDF();
		byte[] pdfReport =  gen.getPDF(nomPremio, nomEspec, nomFunc, nomEsp, fecha, fechaFin).toByteArray();
		return pdfReport;
	}
	
}







