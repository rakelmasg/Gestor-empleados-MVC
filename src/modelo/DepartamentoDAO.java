/*
 * Autor: Raquel Más García
 * Contenido:  realiza operaciones con entidades Departamento en la base de datos 
 */
package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Entity implementation class for Entity: Departamento
 * 
 */

public class DepartamentoDAO {
	private EntityManager em;
	private Query query;

	public DepartamentoDAO(EntityManager em) {
		super();
		this.em = em;
	}
	public Departamento getDepartamento(int id){
		try{
			query=em.createQuery("SELECT d FROM Departamento d WHERE d.id="+id);
			return (Departamento)query.getSingleResult();
		}catch(Exception e){
			return null;
		}
	}
	public ArrayList<Departamento> getDepartamentos() {
		try {
			query = em.createQuery("SELECT d FROM Departamento d");
			List<Departamento> resultado = query.getResultList();
			Iterator<Departamento> it=resultado.iterator();
			ArrayList<Departamento> listaDepartamentos=new ArrayList<>();
			while(it.hasNext()){
				listaDepartamentos.add(it.next());
			}
			if(!listaDepartamentos.isEmpty()){
				return listaDepartamentos;
			}
		} catch (Exception e) {
		}
		return null;
	}
}
