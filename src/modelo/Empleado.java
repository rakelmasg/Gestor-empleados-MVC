/*
 * Autor: Raquel Más García
 * Contenido: entidad (bean) empleado que se puede persistir en la base de datos.
 */
package modelo;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.*;

import com.sun.istack.internal.NotNull;

import modelo.Categoria;
import modelo.Departamento;

/**
 * Entity implementation class for Entity: Empleado
 *
 */
@Entity

public class Empleado implements Serializable {

	   
	@Id
	@GeneratedValue
	private int id;
	@NotNull
	private String nombre;
	@NotNull
	@ManyToOne
	private Departamento depto;
	@NotNull
	@ManyToOne
	private Categoria catego;
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date fecing;
	@Temporal(TemporalType.DATE)
	private Date fecbaj=null;
	private static final long serialVersionUID = 1L;

	public Empleado() {
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
	public Departamento getDepto() {
		return this.depto;
	}

	public void setDepto(Departamento depto) {
		this.depto = depto;
	}   
	public Categoria getCatego() {
		return this.catego;
	}

	public void setCatego(Categoria catego) {
		this.catego = catego;
	}   
	public Date getFecing() {
		return this.fecing;
	}

	public void setFecing(Date fecing) {
		this.fecing = fecing;
	}   
	public Date getFecbaj() {
		return this.fecbaj;
	}

	public void setFecbaj(Date fecbaj) {
		this.fecbaj = fecbaj;
	}
   
}
