package logica;

import dtos.CategoriaDto;
import dtos.EspectaculoDto;
import dtos.ImagenDto;
import dtos.PaqueteDto;
import dtos.PlataformaDto;
import excepciones.EspectaculoNoExisteException;
import excepciones.EspectaculoYaExisteException;
import excepciones.EspectadorNoExisteException;
import excepciones.NoExistePaqueteException;
import excepciones.PaqueteYaExisteException;
import excepciones.PlataformaNoExisteException;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.List;
import java.util.Map;

import dominio.Espectaculo;
import dominio.Espectador;
import dominio.Paquete;
import dominio.Plataforma;

public class ControladorDePaquetes implements IControladorDePaquetes{
	
	@Override
	public void crearPaquete(PaqueteDto infopaquete, ImagenDto imagen, String nArt) throws Exception{
		Paquete paq = new Paquete(
				infopaquete.getNombre(),
				infopaquete.getDescripcion(),
				infopaquete.getFechaInicio(),
				infopaquete.getFechaFin(),
				infopaquete.getDescuento()
		);
		if (nArt != null) {
			ManejadorDeUsuarios.getInstance().getArtista(nArt).addPaquete(paq);
		}
		ManejadorDePaquetes.getInstance().agregarPaquete(paq, imagen);
	}
	
	@Override	
	public LinkedList<PaqueteDto> obtenerPaquetes(){
		LinkedList<PaqueteDto> lista = new LinkedList<PaqueteDto>();
		Map<String, Paquete> paquetes = ManejadorDePaquetes.getInstance().obtenerPaquetes();
		for (Map.Entry<String, Paquete> paq : paquetes.entrySet()) {
			lista.add(paq.getValue().getData());			
		}
		return lista;
	}
	
	@Override
	public LinkedList<EspectaculoDto> obtenerEspectaculosDePaquete(String nombrePack) throws NoExistePaqueteException{
		LinkedList<EspectaculoDto> listaEsp = new LinkedList<EspectaculoDto>();
		Paquete pack = ManejadorDePaquetes.getInstance().getPaquete(nombrePack);
		for (Espectaculo es : pack.getEspectaculos()) {
			listaEsp.add(es.getData());
		}
		return listaEsp;
	}
	
	@Override
	public LinkedList<PlataformaDto> obtenerPlataformas() {
		ManejadorDePlataformas manejador = ManejadorDePlataformas.getInstance();
		return manejador.obtenerPlataformasDto();
	}
	
	@Override
	public LinkedList<EspectaculoDto> obtenerEspectaculosSinPaquete(String nombrePlat, String nombrePaq) throws PlataformaNoExisteException {
		Plataforma plat = ManejadorDePlataformas.getInstance().getPlataforma(nombrePlat);
		Map<String, Espectaculo> espectaculos = plat.getEspectaculos();
		LinkedList<EspectaculoDto> lista = new LinkedList<EspectaculoDto>();
		for (Map.Entry<String, Espectaculo> esp : espectaculos.entrySet()) {
			if (!esp.getValue().tienePaquete(nombrePaq))
				lista.add(esp.getValue().getData());
		}
		return lista;
	}
	
	@Override
	public void agregarEspectaculoAPaquete(String nombrePaq, String nombrePlat, String nombreEsp) throws PlataformaNoExisteException, EspectaculoNoExisteException, NoExistePaqueteException, PaqueteYaExisteException, EspectaculoYaExisteException {
		Plataforma plat = ManejadorDePlataformas.getInstance().getPlataforma(nombrePlat);
		Espectaculo esp = plat.getEspectaculo(nombreEsp);
		Paquete paq = ManejadorDePaquetes.getInstance().getPaquete(nombrePaq);
		esp.addPaquete(nombrePaq, paq);
		paq.addEspectaculo(esp);
	}
	
	/* ------------ Busqueda de espectáculos ------------ */
	
	@Override
	public LinkedList<PaqueteDto> buscarPaquetes(String query) {
		LinkedList<PaqueteDto> lista = new LinkedList<PaqueteDto>();
		Map<String, Paquete> paqs = ManejadorDePaquetes.getInstance().obtenerPaquetes();
		for (Map.Entry<String, Paquete> parPaq : paqs.entrySet()) {
			if (parPaq.getValue().contieneTira(query)) {
				lista.add(parPaq.getValue().getData());
			}
		}
		return lista;
	}
	
	@Override
	public LinkedList<PaqueteDto> obtenerPaquetesPorPlataforma(String nomPlat) {
		LinkedList<PaqueteDto> lista = new LinkedList<PaqueteDto>();
		Map<String, Paquete> paqs = ManejadorDePaquetes.getInstance().obtenerPaquetes();
		for (Map.Entry<String, Paquete> paq : paqs.entrySet()) {
			if (paq.getValue().getData().getPlataformas().contains(nomPlat))
				lista.add(paq.getValue().getData());
		}
		return lista;
	}
	
	/* ---------- Fin Busqueda de espectáculos ---------- */
	
	@Override
	public PaqueteDto getPaquete(String nPaq) throws NoExistePaqueteException {
		return ManejadorDePaquetes.getInstance().getPaquete(nPaq).getData();
	}
	
	@Override
	public LinkedList<String> getCategoriasDePaquete(String nombrePack) throws NoExistePaqueteException{
		LinkedList<String> listaCat = new LinkedList<String>();
		Paquete pack = ManejadorDePaquetes.getInstance().getPaquete(nombrePack);
		for (Espectaculo es : pack.getEspectaculos()) {
			for (String auxCat : es.getCategoriasNombre()) {
				if (!listaCat.contains(auxCat)) {
					listaCat.add(auxCat);
				}
			}
		}
		return listaCat;
	}
	
	@Override
	public LinkedList<PaqueteDto> obtenerPaquetesPorCategoria(String nomCat) {
		LinkedList<PaqueteDto> lista = new LinkedList<PaqueteDto>();
		Map<String, Paquete> paqs = ManejadorDePaquetes.getInstance().obtenerPaquetes();
		for (Map.Entry<String, Paquete> paq : paqs.entrySet()) {
			LinkedList<CategoriaDto> cats = paq.getValue().getData().getCategorias();
			for (CategoriaDto cat : cats) {
				if (cat.getNombre().equals(nomCat))
					lista.add(paq.getValue().getData());
			}
		}
		return lista;
	}
	
	@Override
	public void comprarPaquete(String nickUsuario, String nomPaquete) throws EspectadorNoExisteException, PaqueteYaExisteException, NoExistePaqueteException {
		Espectador esp = ManejadorDeUsuarios.getInstance().getEspectador(nickUsuario);
		esp.compraPaquete(nomPaquete, new Date());
		Paquete paquete = ManejadorDePaquetes.getInstance().getPaquete(nomPaquete);
		esp.agregarPaquete(paquete);
	}
	
}
