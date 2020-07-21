/*
 * Autor: Raquel Más García
 * Contenido: recupera los datos introducidos por el usuario, los procesa 
 * y si son correctos introduce un empleado en la base de datos. Devuelve
 * los datos obtenidos de realizar la operación.
 */
package modelo;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import utilidades.Contexto;
import utilidades.Elto;

import javax.persistence.EntityManager;

public class ComandoInsertar implements Comando {
	EmpleadoDAO eDAO;
	CategoriaDAO cDAO;
	DepartamentoDAO dDAO;

	public ComandoInsertar() {
	}

	@Override
	public Contexto ejecutar(Contexto cxt) {
		Contexto modif_ctxt = cxt;
		String nombre;
		Categoria catego;
		Departamento depto;
		Elto elemento;
		try {
			EntityManager em = (EntityManager) modif_ctxt.getElto(
					"entityManager").getContenido();
			eDAO = new EmpleadoDAO(em);
			cDAO = new CategoriaDAO(em);
			dDAO = new DepartamentoDAO(em);
			nombre = (String) modif_ctxt.getElto("nombre").getContenido();
			catego = cDAO.getCategoria(Integer.parseInt((String) modif_ctxt
					.getElto("idcatego").getContenido()));
			depto = dDAO.getDepartamento(Integer.parseInt((String) modif_ctxt
					.getElto("iddepto").getContenido()));
			Calendar fecingC = GregorianCalendar.getInstance();
			Date fecingD = fecingC.getTime();
			Empleado e = new Empleado();
			e.setNombre(nombre);
			e.setCatego(catego);
			e.setDepto(depto);
			e.setFecing(fecingD);
			if (catego != null && depto != null && eDAO.insertar(e)) {
				elemento = new Elto("menu.jsp",
						"Empleado dado de alta correctamente.");
			} else {
				elemento = new Elto("menu.jsp",
						"No se pudo dar de alta al empleado.");
			}
		} catch (Exception e) {
			elemento = new Elto("menu.jsp",
					"No se pudo dar de alta al empleado.");
		}
		modif_ctxt.insertar("respuesta", elemento);
		modif_ctxt.cambiarPersistencia("respuesta");
		return modif_ctxt;
	}
}
