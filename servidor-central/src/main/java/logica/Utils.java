package logica;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Arrays;
import java.util.LinkedList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import dtos.ArtistaDto;
import dtos.CategoriaDto;
import dtos.EspectaculoDto;
import dtos.EspectadorDto;
import dtos.FuncionDto;
import dtos.ImagenDto;
import dtos.LogActividadDto;
import dtos.PaqueteDto;
import dtos.PlataformaDto;
import dtos.RegistroDto;

public class Utils {
	
	public static void crearCarpeta(String url) {
		File dir = new File(url + "/image-storage");
		pathToImages = url;
		dir.mkdir();		
		
		Arrays.stream(new File(url + "/image-storage").listFiles()).forEach(File::delete);
		
	}

	
	public static String guardarImagen(ImagenDto img, String nombre) throws Exception {
		String imgExtension = img.getExtension();
		
		System.out.print(pathToImages + "/image-storage/" + nombre + "." + imgExtension);
		File copied = new File(pathToImages + "/image-storage/" + nombre + "." + imgExtension);
		if (!copied.createNewFile()) {
			copied.delete();
		}
	    try (OutputStream out = new BufferedOutputStream( new FileOutputStream(copied))) {
	         out.write(img.getContent());
	    }
	    return "http://localhost:8080/servidor-web/Imagen?id=" + nombre + "." + imgExtension;
	}

	
	private static String pathToImages = "";
	
	public static String obtenerRutaImagenes() {
		return pathToImages;
	}
	
	public static void definirRutaImagenes(String ruta) {
		pathToImages = ruta;
	}
	
	/*
	 * Wrapper de una LinkedList<String> 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class StringList {
		public StringList() {};
		private LinkedList<String> _list = new LinkedList<String>();
		public LinkedList<String> getList() { return _list; }
		public void setList(LinkedList<String> list) { _list = list; } 
	}
	/*
	 * Wrapper de una LinkedList<EspectadorDto> 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class EspectadorDtoList {
		public EspectadorDtoList() {};
		private LinkedList<EspectadorDto> _list = new LinkedList<EspectadorDto>();
		public LinkedList<EspectadorDto> getList() { return _list; }
		public void setList(LinkedList<EspectadorDto> list) { _list = list; } 
	}
	/*
	 * Wrapper de una LinkedList<ArtistaDto> 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class ArtistaDtoList {
		public ArtistaDtoList() {};
		private LinkedList<ArtistaDto> _list = new LinkedList<ArtistaDto>();
		public LinkedList<ArtistaDto> getList() { return _list; }
		public void setList(LinkedList<ArtistaDto> list) { _list = list; } 
	}
	/*
	 * Wrapper de una LinkedList<PaqueteDto> 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class PaqueteDtoList {
		public PaqueteDtoList() {};
		private LinkedList<PaqueteDto> _list = new LinkedList<PaqueteDto>();
		public LinkedList<PaqueteDto> getList() { return _list; }
		public void setList(LinkedList<PaqueteDto> list) { _list = list; } 
	}
	/*
	 * Wrapper de una LinkedList<EspectaculoDto> 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class EspectaculoDtoList {
		public EspectaculoDtoList() {};
		private LinkedList<EspectaculoDto> _list = new LinkedList<EspectaculoDto>();
		public LinkedList<EspectaculoDto> getList() { return _list; }
		public void setList(LinkedList<EspectaculoDto> list) { _list = list; } 
	}
	/*
	 * Wrapper de una LinkedList<FuncionDto> 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class FuncionDtoList {
		public FuncionDtoList() {};
		private LinkedList<FuncionDto> _list = new LinkedList<FuncionDto>();
		public LinkedList<FuncionDto> getList() { return _list; }
		public void setList(LinkedList<FuncionDto> list) { _list = list; } 
	}
	/*
	 * Wrapper de una LinkedList<RegistroDto> 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class RegistroDtoList {
		public RegistroDtoList() {};
		private LinkedList<RegistroDto> _list = new LinkedList<RegistroDto>();
		public LinkedList<RegistroDto> getList() { return _list; }
		public void setList(LinkedList<RegistroDto> list) { _list = list; } 
	}
	/*
	 * Wrapper de una LinkedList<CategoriaDto> 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class CategoriaDtoList {
		public CategoriaDtoList() {};
		private LinkedList<CategoriaDto> _list = new LinkedList<CategoriaDto>();
		public LinkedList<CategoriaDto> getList() { return _list; }
		public void setList(LinkedList<CategoriaDto> list) { _list = list; } 
	}
	/*
	 * Wrapper de una LinkedList<PlataformaDto> 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class PlataformaDtoList {
		public PlataformaDtoList() {};
		private LinkedList<PlataformaDto> _list = new LinkedList<PlataformaDto>();
		public LinkedList<PlataformaDto> getList() { return _list; }
		public void setList(LinkedList<PlataformaDto> list) { _list = list; } 
	}
	
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class LogActividadDtoList {
		public LogActividadDtoList() {};
		private LinkedList<LogActividadDto> _list = new LinkedList<LogActividadDto>();
		public LinkedList<LogActividadDto> getList() { return _list; }
		public void setList(LinkedList<LogActividadDto> list) { _list = list; } 
	}
	
	
	
	
	

}
