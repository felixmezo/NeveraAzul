package es.upm.dit.isst.neveraAzul.dao;

import java.util.List;

import es.upm.dit.isst.neveraAzul.model.Administrador;
import es.upm.dit.isst.neveraAzul.model.Cliente;
import es.upm.dit.isst.neveraAzul.model.Hostelero;
import es.upm.dit.isst.neveraAzul.model.Opiniones;
import es.upm.dit.isst.neveraAzul.model.Pedido;
import es.upm.dit.isst.neveraAzul.model.Producto;

public interface Dao {
	
	//Metodos de Pedido
	public Pedido crearPedido(String emailHostelero, Hostelero hostelero, String cliente);
	public List<Pedido> leerTodosPedido();
	public List<Pedido> leerPedidosPorCliente(String emailCliente);
	public List<Pedido> leerPedidosPorHostelero(String emailHhostelero);
	public Pedido actualizaEstadoSolicitado(Pedido pedido);
	public Pedido borraPedido(Pedido pedido);
	public void borrarTodosPedidos();
	public void actualizarProductosPedido(Pedido pedido, Producto producto);
	public void ponerDescuentoATodosPedidos(int descuento);
	public void sumaPedidoACliente (String email);
	public void resetearNumeroPedidosACliente(String email);
	public void setPrecioTotalPedido(Pedido pedidoActual, float precioTotal);
	public Pedido actualizaEstadoAceptadoHostelero(Pedido pedido);
	public Pedido BuscarPedidoPorId(long IdPedido);

	//M�todos de Producto
	public Producto crearProducto(String nombre, String descripcion, String emailHostelero, float precio, String establecimientoHostelero, String enOferta);
	public List<Producto> leerProductosPorHostelero(String emailHostelero);
	public Producto BuscarProductoPorId(long IdProducto);
	public List<Producto> leerTodosProducto();
	public List<Producto> leerProductosPorId(List<Long> idProductosPedido);
	public void borrarTodosProductos();
	public void borrarProducto(long idProducto);
	
	//M�todos de Cliente
	public Cliente crearCliente(String email, String nombre, String apellido1, String apellido2);
	public boolean existeCliente(String emailCliente);
	public List<Cliente> leerTodosCliente();
	public void borrarTodosClientes();
	public Cliente buscarClientePorEmail(String email);
	
	//M�todos de Hostelero
	public Hostelero crearHostelero(String email, String nombre, String apellido1, String apellido2, String nombreEstablecimiento);
	public boolean existeHostelero(String emailHostelero);
	public List<Hostelero> leerTodosHostelero();
	public void borrarTodosHosteleros();
	public Hostelero buscarHosteleroPorEmail(String email);
	public Pedido buscarPedidoEligiendo(Cliente cliente);
	public boolean hayPedidoEligiendo(Cliente cliente);
	public List<Pedido> leerPedidosPorHosteleroNoEligiendo(String emailHostelero);
	
	//Metodos opiniones
	public Opiniones crearOpinion(String establecimientoHostelero, String emailHostelero, String emailCliente, String descripcion,String titulo,int puntuacion);
	public boolean existeOpinionHostelero(String establecimientoHostelero);
	public boolean existeOpinionCliente(String emailCliente);
	public List<Opiniones> leerOpinionesPorHostelero(String establecimientoHostelero);
	public List<Opiniones> leerOpinionesPorCliente(String emailCliente);
	public void borrarTodasOpiniones();
	
	//Metodos Administrador
	public Administrador crearAdmin(String apellido1);
	public boolean existeAdmin(String emailAdmin);
	public Administrador buscarAdminPorEmail(String email);
	public int saberDescuento();
	public void borrarAdmin();


	
}
