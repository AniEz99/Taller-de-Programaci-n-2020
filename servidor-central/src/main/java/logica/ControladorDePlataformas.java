package logica;

import java.util.LinkedList;
import java.util.List;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dominio.Plataforma;
import dtos.PlataformaDto;
import excepciones.CamposIncompletosException;
import excepciones.PlataformaYaExisteException;

/*
 * Controlador de plataforma:
 * No posee memoria
 */
public class ControladorDePlataformas implements IControladorDePlataformas {
	
	public ControladorDePlataformas() {}
	
	/*
	 * Crea una nueva plataforma con la informacion del PlataformaDtos 
	 */	
	@Override
	public void altaPlataforma(PlataformaDto infoPlataforma) throws PlataformaYaExisteException, CamposIncompletosException {
		if (infoPlataforma.getNombre().length() == 0)
			throw new CamposIncompletosException("Ingrese un nombre para la Plataforma");
		Plataforma nuevaPlataforma = new Plataforma(
				infoPlataforma.getNombre(),
				infoPlataforma.getDescripcion(),
				infoPlataforma.getURL()
		);
		ManejadorDePlataformas manejador = ManejadorDePlataformas.getInstance();
		manejador.addPlataforma(nuevaPlataforma);
	}
	@Override
	public LinkedList<String> getNombrePlataformas() {
		LinkedList<String> res = new LinkedList<String>();
		ManejadorDePlataformas mplat = ManejadorDePlataformas.getInstance();
		Map<String, Plataforma> aux = mplat.getPlataformas();
		Set<String> keys = aux.keySet();
		for (String key:keys) {
			res.add(aux.get(key).getNombre());
		}
		return res;
	}	
	
}
