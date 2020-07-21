/*
 * Autor: Raquel Más García
 * Contenido: encargado de almacenar y transportar datos de
 * una capa a otra.
 */
package utilidades;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class Contexto {
	HashMap<String, Elto> hash;

	public Contexto() {
		this.hash = new HashMap<String, Elto>();
	}

	public void insertar(String nombre, Elto elemento) {
		hash.put(nombre, elemento);
	}

	public Elto getElto(String nombre) {
		return hash.get(nombre);
	}

	//indica que se tienen que borrar todos los elementos
	//menos el introducido por parámetro
	public void cambiarPersistencia(String nombre) {
		Set<Entry<String, Elto>> set = hash.entrySet();
		for (Entry<String, Elto> entrada : set) {
			if (!entrada.getKey().equals(nombre)) {
				hash.get(entrada.getKey()).setPersistencia(false);
			}
		}
	}
	//elimina los elementos marcados para borrar
	public void borrarImpersistentes() {
		Iterator<Entry<String, Elto>> it = hash.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Elto> e = it.next();
			if (!(e.getValue()).isPersistencia()) {
				it.remove();
			}
		}
	}
}
