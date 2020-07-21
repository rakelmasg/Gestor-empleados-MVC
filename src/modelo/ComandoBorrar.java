/*
 * Autor: Raquel Más García
 * Contenido: recupera los datos introducidos por el usuario, los procesa 
 * y si son correctos da de baja al empleado en la base de datos. Devuelve
 * los datos obtenidos de realizar la operación.
 */
package modelo;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;

import utilidades.Contexto;
import utilidades.Elto;


public class ComandoBorrar implements Comando {
	EmpleadoDAO eDAO;
	public ComandoBorrar() {
	}

	@Override
	public Contexto ejecutar(Contexto ctxt) {
		Contexto modif_ctxt = ctxt;
		Calendar fecha = GregorianCalendar.getInstance();
		Elto elemento;
		int id;
		try {
			eDAO = new EmpleadoDAO((EntityManager) modif_ctxt.getElto(
					"entityManager").getContenido());
			id = Integer.parseInt((String) modif_ctxt.getElto("idempleado")
					.getContenido());
			Empleado e = new Empleado();
			e.setId(id);
			e.setFecbaj(fecha.getTime());
			if (eDAO.borrar(e)) {
				elemento = new Elto("menu.jsp",
						"Empleado dado de baja correctamente.");
			} else {
				elemento = new Elto("menu.jsp",
						"No se pudo dar de baja al empleado.");
			}
		} catch (Exception e) {
			elemento = new Elto("menu.jsp",
					"No se pudo dar de baja al empleado.");
		}
		modif_ctxt.insertar("respuesta", elemento);
		modif_ctxt.cambiarPersistencia("respuesta");
		return modif_ctxt;
	}
}
