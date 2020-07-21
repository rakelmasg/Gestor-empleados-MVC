/*
 * Autor: Raquel Más García
 * Contenido: realiza operaciones con entidades Categoria en la base de datos 
 */
package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CategoriaDAO {
	private EntityManager em;
	private Query query;

	public CategoriaDAO(EntityManager em) {
		super();
		this.em = em;
	}

	public Categoria getCategoria(int id) {
		try {
			query = em.createQuery("SELECT c FROM Categoria c WHERE c.id=" + id);
			return (Categoria) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public ArrayList<Categoria> getCategorias() {
		try {
			query = em.createQuery("SELECT c FROM Categoria c");
			List<Categoria> resultado = query.getResultList();
			Iterator<Categoria> it=resultado.iterator();
			ArrayList<Categoria> listaCategorias=new ArrayList<>();
			while(it.hasNext()){
				listaCategorias.add(it.next());
			}
			if(!listaCategorias.isEmpty()){
				return listaCategorias;
			}
		} catch (Exception e) {
		}
		return null;
	}
}
