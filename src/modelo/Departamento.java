/*
 * Autor: Raquel Más García
 * Contenido: entidad (bean) departamento que se puede persistir en la base de datos.
 */
package modelo;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

import com.sun.istack.internal.NotNull;

/**
 * Entity implementation class for Entity: Departamento
 * 
 */
@Entity
public class Departamento implements Serializable {

	@Id
	@GeneratedValue
	private int id;
	@NotNull
	private String nombre;
	private static final long serialVersionUID = 1L;

	public Departamento() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
