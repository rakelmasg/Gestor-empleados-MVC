/*
 * Autor: Raquel Más García
 * Contenido: recupera los datos introducidos por el usuario, los procesa 
 * y si son correctos modifica al empleado en la base de datos. Devuelve
 * los datos obtenidos de realizar la operación.
 */
package modelo;

import javax.persistence.EntityManager;

import utilidades.Contexto;
import utilidades.Elto;

public class ComandoModificar implements Comando {
	EmpleadoDAO eDAO;
	CategoriaDAO cDAO;
	DepartamentoDAO dDAO;

	public ComandoModificar() {
	}

	@Override
	public Contexto ejecutar(Contexto ctxt) {
		Contexto modif_ctxt = ctxt;
		String nombre;
		int id;
		Categoria catego;
		Departamento depto;
		Elto elemento;
		try {
			EntityManager em = (EntityManager) modif_ctxt.getElto(
					"entityManager").getContenido();
			eDAO = new EmpleadoDAO(em);
			cDAO = new CategoriaDAO(em);
			dDAO = new DepartamentoDAO(em);
			id = Integer.parseInt((String) modif_ctxt.getElto("idempleado")
					.getContenido());
			nombre = (String) modif_ctxt.getElto("nombre").getContenido();
			int idCatego = Integer.parseInt((String) modif_ctxt.getElto(
					"idcatego").getContenido());
			if (idCatego != 0) {
				catego = cDAO.getCategoria(idCatego);
			} else {
				catego = null;
			}
			int idDepto = Integer.parseInt((String) modif_ctxt.getElto(
					"iddepto").getContenido());
			if (idDepto != 0) {
				depto = dDAO.getDepartamento(idDepto);
			} else {
				depto = null;
			}
			Empleado e = new Empleado();
			e.setId(id);
			e.setNombre(nombre);
			e.setCatego(catego);
			e.setDepto(depto);
			if (eDAO.modificar(e)) {
				elemento = new Elto("menu.jsp",
						"Empleado modificado correctamente.");
			} else {
				elemento = new Elto("menu.jsp",
						"No se pudo modificar al empleado.");
			}
		} catch (Exception e) {
			elemento = new Elto("menu.jsp", "No se pudo modificar al empleado.");
			e.printStackTrace();
		}
		modif_ctxt.insertar("respuesta", elemento);
		modif_ctxt.cambiarPersistencia("respuesta");
		return modif_ctxt;
	}

}
