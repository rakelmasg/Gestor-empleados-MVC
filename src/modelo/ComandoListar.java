/*
 * Autor: Raquel Más García
 * Contenido: recupera los datos introducidos por el usuario, los procesa 
 * y si son correctos recupera los empleados con dichos datos de la base
 * de datos. Devuelve los datos obtenidos de realizar la operación.
 */package modelo;

import java.util.List;

import javax.persistence.EntityManager;

import utilidades.Contexto;
import utilidades.Elto;

public class ComandoListar implements Comando {
	EmpleadoDAO eDAO;

	public ComandoListar() {
	}

	@Override
	public Contexto ejecutar(Contexto ctxt) {
		Contexto modif_ctxt = ctxt;
		List<Empleado> lista;
		String nombre;
		int id;
		Categoria catego = new Categoria();
		Departamento depto = new Departamento();
		try {
			EntityManager em = (EntityManager) modif_ctxt.getElto(
					"entityManager").getContenido();
			eDAO = new EmpleadoDAO(em);
			try {
				id = Integer.parseInt((String) modif_ctxt.getElto("idempleado")
						.getContenido());
			} catch (Exception e) {
				id = -1;
			}
			nombre = (String) modif_ctxt.getElto("nombre").getContenido();
			int idCatego = Integer.parseInt((String) modif_ctxt.getElto(
					"idcatego").getContenido());
			if (idCatego != 0) {
				catego = new Categoria();
				catego.setId(idCatego);
			} else {
				catego = null;
			}
			int idDepto = Integer.parseInt((String) modif_ctxt.getElto(
					"iddepto").getContenido());
			if (idDepto != 0) {
				depto = new Departamento();
				depto.setId(idDepto);
			} else {
				depto = null;
			}
			
			Empleado e = new Empleado();
			e.setId(id);
			e.setNombre(nombre);
			e.setCatego(catego);
			e.setDepto(depto);
			lista = eDAO.listar(e);
		} catch (Exception ex) {
			lista = null;
		}
		modif_ctxt.insertar("respuesta", new Elto("listar.jsp", lista));
		modif_ctxt.cambiarPersistencia("respuesta");
		return modif_ctxt;
	}
}
