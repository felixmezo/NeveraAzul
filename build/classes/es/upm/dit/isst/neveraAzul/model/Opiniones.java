package es.upm.dit.isst.neveraAzul.model;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Opiniones implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id @Index private Long idOpinion;
	@Index private String establecimientoHostelero;
	@Index private String emailHostelero;
	@Index private String emailCliente;
	@Index private int puntuacion;
	private String descripcion;
	private String titulo;
		
	public Opiniones(){}
	
	public Opiniones(String establecimientoHostelero, String emailHostelero, String emailCliente, String descripcion, String titulo, int puntuacion){
		super();
		this.establecimientoHostelero = establecimientoHostelero;
		this.emailHostelero = emailHostelero;
		this.emailCliente = emailCliente;
		this.descripcion = descripcion;
		this.puntuacion = puntuacion;
		this.titulo = titulo;

	}
	
	public void setEstablecimientoHostelero(String establecimientoHostelero){
		this.establecimientoHostelero = establecimientoHostelero;
	}
	
	public String getEstablecimientoHostelero(){
		return establecimientoHostelero;
	}
	
	public String getEmailHostelero() {
		return emailHostelero;
	}

	public void setEmailHostelero(String emailHostelero) {
		this.emailHostelero = emailHostelero;
	}
	
	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}
	
	public String getDescripcion(){
		return descripcion;
	}
	
	public void setTitulo(String titulo){
		this.titulo = titulo;
	}
	
	public String getTitulo(){
		return titulo;
	}
	
	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public Long getidOpinion() {
		return idOpinion;
	}
	public void setidOpinion(Long idOpinion) {
		this.idOpinion = idOpinion;
	}
}
