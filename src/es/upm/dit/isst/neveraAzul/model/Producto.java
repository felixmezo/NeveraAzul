package es.upm.dit.isst.neveraAzul.model;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Producto implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id @Index private Long idProducto;
	@Index private String nombre;
	private String descripcion;
	@Index private String emailHostelero;
	@Index private String establecimientoHostelero;
	@Index private String enOferta;
	private float precio;
	
	public Producto(){}

	public Producto(String nombre, String descripcion, String emailHostelero, float precio, String establecimientoHostelero, String enOferta) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.emailHostelero = emailHostelero;
		this.precio = precio;
		this.establecimientoHostelero = establecimientoHostelero;
		this.enOferta = enOferta;
	}
	
	public String getEnOferta() {
		return enOferta;
	}

	public void setEnOferta(String enOferta) {
		this.enOferta = enOferta;
	}

	public Long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEmailHostelero() {
		return emailHostelero;
	}

	public void setEmailHostelero(String emailHostelero) {
		this.emailHostelero = emailHostelero;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getEstablecimientoHostelero() {
		return establecimientoHostelero;
	}

	public void setEstablecimientoHostelero(String establecimientoHostelero) {
		this.establecimientoHostelero = establecimientoHostelero;
	}
	
	
}
