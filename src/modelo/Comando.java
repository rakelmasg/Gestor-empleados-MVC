/*
 * Autor: Raquel M�s Garc�a
 * Contenido: interfaz para ejecutar la l�gica del negocio
 */
package modelo;

import utilidades.Contexto;

public interface Comando {
	public Contexto ejecutar(Contexto cxt);

}
