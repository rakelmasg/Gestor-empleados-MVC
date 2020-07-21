/*
 * Autor: Raquel M�s Garc�a
 * Contenido: devuelve una �nica conexi�n a la base de datos
 */
package utilidades;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexion {
	private static EntityManagerFactory emf = null;
	private static EntityManager em = null;

	protected Conexion() {
	}

	public static EntityManager conectar() {
		//nos aseguramos de que a conexi�n siempre sea la misma
		if (em == null) {
			emf = Persistence.createEntityManagerFactory("Empresa");
			em = emf.createEntityManager();
		}
		return em;
	}

	public static void cerrar() {
		em.close();
		emf.close();
	}

}
