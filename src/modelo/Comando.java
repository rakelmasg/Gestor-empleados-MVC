/*
 * Autor: Raquel Más García
 * Contenido: interfaz para ejecutar la lógica del negocio
 */
package modelo;

import utilidades.Contexto;

public interface Comando {
	public Contexto ejecutar(Contexto cxt);

}
