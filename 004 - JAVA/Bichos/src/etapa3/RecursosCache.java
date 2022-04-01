package etapa3;

import java.io.File;
import java.net.URL;
import java.util.HashMap;

public abstract class RecursosCache {
	
	protected HashMap recursos;
	
	public RecursosCache() { recursos = new HashMap(); }
	
	protected Object cargaRecurso(String nombre) {
		URL url = null;
		url = getClass().getClassLoader().getResource("etapa3/res/");
		return cargaRecurso (new File(url.getPath() + nombre));
	}
	
	protected Object getRecurso(String nombre) {
		Object res = recursos.get(nombre);
		if(res == null) {
			res = cargaRecurso(nombre);
			recursos.put(nombre, res);
		}
		return res;
	}
	
	protected abstract Object cargaRecurso(File f);
	
	protected File getFicheroRecurso(String nombre) {
		URL url = null;
		url = getClass().getClassLoader().getResource("etapa3/res/");
		return new File(url.getPath() + nombre);
	}

}
