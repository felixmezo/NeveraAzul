package es.upm.dit.isst.neveraAzul.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//import javax.persistence.GeneratedValue;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;


@Entity
public class Pedido implements Serializable {

		private static final long serialVersionUID = 1L;
		
		@Id @Index private Long idPedido;
		@Index private String emailHostelero;
		@Index private String emailCliente;
		@Index private List<Producto> productosPedido;
		private Hostelero hostelero;
		private Cliente cliente;
		private int descuento;
		private float precioTotal;
		
		public Cliente getCliente() {
			return cliente;
		}

		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}

		private boolean entregaAceptadaCliente;
		private boolean entregaAceptadaHostelero;
		private Estado estado;
		
		public Pedido(){}
		
		public Pedido(Hostelero hostelero, String emailHostelero, String emailCliente, Cliente cliente, int descuento) {
			super();
			this.emailHostelero = emailHostelero;
			this.emailCliente = emailCliente;
			this.productosPedido = new ArrayList<Producto>();
			this.entregaAceptadaCliente = false;
			this.entregaAceptadaHostelero = false;
			this.estado = Estado.eligiendo;
			this.hostelero = hostelero;
			this.cliente = cliente;
			this.descuento = descuento;
			this.precioTotal = 0;
		}
		

		public float getPrecioTotal() {
			return precioTotal;
		}

		public void setPrecioTotal(float precioTotal) {
			this.precioTotal = precioTotal;
		}

		public Hostelero getHostelero() {
			return hostelero;
		}

		public void setHostelero(Hostelero hostelero) {
			this.hostelero = hostelero;
		}

		public Long getIdPedido() {
			return idPedido;
		}

		public void setIdPedido(Long idPedido) {
			this.idPedido = idPedido;
		}
		public int getDescuento() {
			return descuento;
		}

		public void setDescuento(int descuento) {
			this.descuento = descuento;
		}


		public String getEmailHostelero() {
			return emailHostelero;
		}

		public void setEmailHostelero(String hostelero) {
			this.emailHostelero = hostelero;
		}

		public String getEmailCliente() {
			return emailCliente;
		}

		public void setEmailCliente(String cliente) {
			this.emailCliente = cliente;
		}

		public List<Producto> getProductosPedido() {
			return productosPedido;
		}

		public void setProductosPedido(List<Producto> productosPedido) {
			this.productosPedido = productosPedido;
		}

		public boolean isEntregaAceptadaCliente() {
			return entregaAceptadaCliente;
		}

		public void setEntregaAceptadaCliente(boolean entregaAceptadaCliente) {
			this.entregaAceptadaCliente = entregaAceptadaCliente;
		}

		public boolean isEntregaAceptadaHostelero() {
			return entregaAceptadaHostelero;
		}

		public void setEntregaAceptadaHostelero(boolean entregaAceptadaHostelero) {
			this.entregaAceptadaHostelero = entregaAceptadaHostelero;
		}

		public Estado getEstado() {
			return estado;
		}

		public void setEstado(Estado estado) {
			this.estado = estado;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}


}		