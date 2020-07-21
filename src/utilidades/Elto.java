/*
 * Autor: Raquel Más García
 * Contenido: auxiliar para almacenar datos de cualquier tipo
 * en el contexto. Contiene el destino de los datos
 */
package utilidades;

public class Elto {
	private boolean persistencia;
	private String destino;
	private Object contenido;

	public Elto(String destino, Object contenido) {
		this.persistencia = true;
		this.destino = destino;
		this.contenido = contenido;
	}
	public Elto(Object contenido) {
		this.persistencia = true;
		this.contenido = contenido;
	}
	public boolean isPersistencia() {
		return persistencia;
	}

	public String getDestino() {
		return destino;
	}

	public Object getContenido() {
		return contenido;
	}

	public void setPersistencia(boolean persistencia) {
		this.persistencia = persistencia;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}
}
