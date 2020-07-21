/*
 * Autor: Raquel Más García
 * Contenido: realiza operaciones con entidades Empleado en la base de datos 
 */
package modelo;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class EmpleadoDAO {
	private EntityManager em;
	private String txtquery;
	private Query query;

	public EmpleadoDAO(EntityManager em) {
		super();
		this.em = em;
	}

	// da de alta un empleado en la base de datos
	public boolean insertar(Empleado nuevoEmpleado) {
		if (!nuevoEmpleado.getNombre().equals("")
				&& nuevoEmpleado.getCatego() != null
				&& nuevoEmpleado.getDepto() != null) {
			em.getTransaction().begin();
			try {
				em.persist(nuevoEmpleado);
				em.getTransaction().commit();
				return true;
			} catch (Exception e1) {
				em.getTransaction().rollback();
			}
		}
		return false;
	}

	// da de baja un empleado en la base de datos
	public boolean borrar(Empleado empleadoDatos) {
		em.getTransaction().begin();
		try {
			Empleado empleadoBorr = getEmpleado(empleadoDatos.getId());
			empleadoBorr.setFecbaj(empleadoDatos.getFecbaj());
			em.merge(empleadoBorr);
			em.getTransaction().commit();
			return true;
		} catch (Exception ex) {
			em.getTransaction().rollback();
		}
		return false;
	}

	// modifica los datos introducidos un empleado en la base de datos
	public boolean modificar(Empleado empleadoDatos) {
		em.getTransaction().begin();
		try {
			Empleado empleadoMod = getEmpleado(empleadoDatos.getId());
			if (!empleadoDatos.getNombre().equals("")) {
				empleadoMod.setNombre(empleadoDatos.getNombre());
			}
			if (empleadoDatos.getCatego() != null) {
				empleadoMod.setCatego(empleadoDatos.getCatego());
			}
			if (empleadoDatos.getDepto() != null) {
				empleadoMod.setDepto(empleadoDatos.getDepto());
			}
			em.merge(empleadoMod);
			em.getTransaction().commit();
			return true;
		} catch (Exception ex) {
			em.getTransaction().rollback();
		}
		return false;
	}

	//lista los empleados dinamicamente con datos introducidos
	public List<Empleado> listar(Empleado empleadoDatos) {
		txtquery = "SELECT e FROM Empleado e WHERE 1=1";
		if (empleadoDatos.getId() != -1) {
			txtquery += " AND e.id=" + empleadoDatos.getId();
		}
		if (!empleadoDatos.getNombre().equals("")) {
			txtquery += " AND e.nombre LIKE '" + empleadoDatos.getNombre()
					+ "'";
		}
		if (empleadoDatos.getDepto() != null) {
			txtquery += " AND e.depto.id=" + empleadoDatos.getDepto().getId();
		}
		if (empleadoDatos.getCatego() != null) {
			txtquery += " AND e.catego.id=" + empleadoDatos.getCatego().getId();
		}
		try {
			query = em.createQuery(txtquery);
			return ((List<Empleado>) query.getResultList());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Empleado getEmpleado(int id) {
		try {
			query = em.createQuery("SELECT e FROM Empleado e WHERE e.id=" + id);
			return (Empleado) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	//recupera las ids de los empleados activos (no dados de baja) de la base de datos
	public int[] getIDsEmpleados() {
		try {
			query = em
					.createQuery("SELECT e.id FROM Empleado e WHERE e.fecbaj IS NULL");
			List<Integer> resultado = query.getResultList();
			if (!resultado.isEmpty()) {
				int[] ids = new int[resultado.size()];
				int cont = 0;
				Iterator<Integer> it = resultado.iterator();
				while (it.hasNext()) {
					ids[cont] = it.next();
					cont++;
				}
				return ids;
			}
		} catch (Exception e) {
		}
		return null;
	}
}
