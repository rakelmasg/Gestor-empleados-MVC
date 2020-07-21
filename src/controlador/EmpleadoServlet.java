/*
 * Autor: Raquel Más García
 * Contenido: recibe las peticiones del usuario y las procesa. Recupera información de la base de datos,
 *  ejecuta los comandos (clases con la lógica del negocio) y redirige según los datos obtenidos.
 */
package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Categoria;
import modelo.CategoriaDAO;
import modelo.Comando;
import modelo.Departamento;
import modelo.DepartamentoDAO;
import modelo.EmpleadoDAO;

import utilidades.Conexion;
import utilidades.Contexto;
import utilidades.Elto;

public class EmpleadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmpleadoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession(true);
		if (request.getParameter("opcion") != null) {
			String opcion = request.getParameter("opcion");
			if (opcion.equals("menu")) {
				response.sendRedirect("menu.jsp");
			} else {
				sesion.removeAttribute("respuesta");
				try {
					EntityManager em = Conexion.conectar();
					if (opcion.equals("insertar")) {
						DepartamentoDAO dDAO = new DepartamentoDAO(em);
						CategoriaDAO cDAO = new CategoriaDAO(em);
						ArrayList<Departamento> departamentos = dDAO
								.getDepartamentos();
						ArrayList<Categoria> categorias = cDAO
								.getCategorias();
						if (departamentos != null && categorias != null) {
							sesion.setAttribute("departamentos", departamentos);
							sesion.setAttribute("categorias", categorias);
							response.sendRedirect("insertar.jsp");
						} else {
							response.sendRedirect("error.html");
						}
					}
					if (opcion.equals("borrar")) {
						EmpleadoDAO eDAO = new EmpleadoDAO(em);
						int[] ids = eDAO.getIDsEmpleados();
						if (ids != null) {
							sesion.setAttribute("ids", ids);
							response.sendRedirect("borrar.jsp");
						} else {
							response.sendRedirect("error.html");
						}
					}
					if (opcion.equals("modificar")) {
						DepartamentoDAO dDAO = new DepartamentoDAO(em);
						CategoriaDAO cDAO = new CategoriaDAO(em);
						EmpleadoDAO eDAO = new EmpleadoDAO(em);
						ArrayList<Departamento> departamentos = dDAO
								.getDepartamentos();
						ArrayList<Categoria> categorias = cDAO
								.getCategorias();
						int[] ids = eDAO.getIDsEmpleados();
						if (departamentos != null && categorias != null
								&& ids != null) {
							sesion.setAttribute("departamentos", departamentos);
							sesion.setAttribute("categorias", categorias);
							sesion.setAttribute("ids", ids);
							response.sendRedirect("modificar.jsp");
						} else {
							response.sendRedirect("error.html");
						}
					}
					if (opcion.equals("listar")) {
						DepartamentoDAO dDAO = new DepartamentoDAO(em);
						CategoriaDAO cDAO = new CategoriaDAO(em);
						ArrayList<Departamento> departamentos = dDAO
								.getDepartamentos();
						ArrayList<Categoria> categorias = cDAO
								.getCategorias();
						if (departamentos != null && categorias != null) {
							sesion.setAttribute("departamentos", departamentos);
							sesion.setAttribute("categorias", categorias);
							response.sendRedirect("listarForm.jsp");
						} else {
							response.sendRedirect("error.html");
						}
					}
				} catch (Exception e) {
					response.sendRedirect("error.html");
				}
			}
		} else if (request.getParameter("comando") != null) {
			try {
				sesion.removeAttribute("respuesta");
				String comando = request.getParameter("comando");
				Comando cmd = (Comando) Class.forName("modelo." + comando)
						.newInstance();
				Contexto ctxt = new Contexto();
				//Recuperamos todos los parametros del request para hacerlos llegar al modelo
				Enumeration<String> e = request.getParameterNames();
				while (e.hasMoreElements()) {
					String nom_param = (String) e.nextElement();
					Elto elem = new Elto(request.getParameter(nom_param));
					ctxt.insertar(nom_param, elem);
				}
				EntityManager em = Conexion.conectar();
				ctxt.insertar("entityManager", new Elto("servlet", em));	
				//Realiza la lógica de negocio y devuelve los datos de la operación
				ctxt = cmd.ejecutar(ctxt);
				ctxt.borrarImpersistentes();
				sesion.setAttribute("respuesta", ctxt.getElto("respuesta")
						.getContenido());
				response.sendRedirect(ctxt.getElto("respuesta").getDestino());
			} catch (Exception e) {
				response.sendRedirect("error.html");
			}
		} else {
			sesion.invalidate();
			response.sendRedirect("index.html");
		}
	}
}
