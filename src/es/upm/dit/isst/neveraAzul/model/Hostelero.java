package es.upm.dit.isst.neveraAzul.model;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Hostelero implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id @Index private String email;
	private String nombre;
	private String apellido1;
	private String apellido2;
	@Index private String nombreEstablecimiento;
	
	public Hostelero(){}
	
	public Hostelero(String email, String nombre, String apellido1, String apellido2, String nombreEstablecimiento) {
		super();
		this.email = email;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.nombreEstablecimiento = nombreEstablecimiento;
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

	public String getNombreEstablecimiento() {
		return nombreEstablecimiento;
	}

	public void setNombreEstablecimiento(String nombreEstablecimiento) {
		this.nombreEstablecimiento = nombreEstablecimiento;
	}
	
	
}
