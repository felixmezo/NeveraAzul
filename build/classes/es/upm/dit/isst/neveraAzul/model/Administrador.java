package es.upm.dit.isst.neveraAzul.model;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Administrador implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id @Index private String email;
	private String nombre;
	private String apellido1;
	
	
	

	public Administrador(){}
	
	public Administrador(String apellido1) {
		super();
		this.email = "neveraazul7@gmail.com";
		this.nombre = "Administrador";
		this.apellido1 = apellido1;
	}
	
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
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
