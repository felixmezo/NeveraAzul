package es.upm.dit.isst.neveraAzul.model;

import java.io.Serializable;

import com.google.appengine.api.datastore.Blob;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Cliente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id @Index private String email;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private Blob imagen;
	private String keyImagen;
	private int numeroPedidos = 0;
	
	public int getNumeroPedidos(){
		return numeroPedidos;
	}
	
	public void setNumeroPedidos(int numeroPedidos){
		this.numeroPedidos = numeroPedidos;
	}	
	public Blob getImagen() {
		return imagen;
	}

	public void setImagen(Blob imagen) {
		this.imagen = imagen;
	}

	public String getKeyImagen() {
		return keyImagen;
	}

	public void setKeyImagen(String keyImagen) {
		this.keyImagen = keyImagen;
	}

	public Cliente(){}
	
	public Cliente(String email, String nombre, String apellido1, String apellido2) {
		super();
		this.email = email;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
	}
	
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
