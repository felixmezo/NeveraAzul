package es.upm.dit.isst.neveraAzul.dao;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;

import es.upm.dit.isst.neveraAzul.model.Cliente;
import es.upm.dit.isst.neveraAzul.model.Estado;
import es.upm.dit.isst.neveraAzul.model.Hostelero;
import es.upm.dit.isst.neveraAzul.model.Pedido;
import es.upm.dit.isst.neveraAzul.model.Producto;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class Dao_impl implements Dao{
	
	private static Dao_impl instancia;
	private Dao_impl() {
	}
	public static Dao_impl getInstancia() {
		if (instancia == null)
			instancia= new Dao_impl();
		return instancia;
	}
	
	@Override
	public Producto BuscarProductoPorId(long idProducto){
		Producto producto = ofy().load().type(Producto.class).filterKey(Key.create(Producto.class, idProducto)).first().now();
		return producto;
		
	}
	
	public boolean hayPedidoEligiendo(Cliente cliente){
		List<Pedido> pedidos = this.leerTodosPedido();
		for(Pedido pedido: pedidos){
			if(pedido.getEmailCliente().equals(cliente.getEmail()) && pedido.getEstado() == Estado.eligiendo){
				return true;
			}
		}
		return false;
	}

	
	public Pedido buscarPedidoEligiendo(Cliente cliente){
		List<Pedido> pedidos = ofy().load().type(Pedido.class).filter("estado", Estado.eligiendo).list();
//		List<Pedido> pedidos = this.leerTodosPedido();
		for(Pedido pedido: pedidos){
			if(pedido.getEmailCliente().equals(cliente.getEmail()) && pedido.getEstado() == Estado.eligiendo){
				return pedido;
			}
		}
		return null;
	}
	
//	public List<Pedido> buscarPedidosEligiendo(Cliente cliente){
//		List<Pedido> pedidosEligiendo = ofy().load().type(Pedido.class).filter("estado", Estado.eligiendo).list();
//		for(Pedido pedido: pedidosEligiendo){
//			if (pedido.getCliente().getEmail() == )
//		}
//	}

	@Override
	public Pedido crearPedido(String emailHostelero, Hostelero hostelero, String emailCliente) {
		Cliente cliente = this.buscarClientePorEmail(emailCliente);
		Pedido pedido = new Pedido(hostelero, emailHostelero, emailCliente, cliente);
		ofy().save().entity(pedido).now();
		return pedido;
	}
	
	@Override
	public Producto crearProducto(String nombre, String descripcion, String emailHostelero, float precio, String establecimientoHostelero, String enOferta){
		Producto producto = new Producto(nombre, descripcion, emailHostelero, precio, establecimientoHostelero, enOferta);
		ofy().save().entity(producto).now();
		return producto;
	}

	@Override
	public List<Pedido> leerTodosPedido() {
		List<Pedido> pedidos = ofy().load().type(Pedido.class).list();
		return pedidos;
	}

	@Override
	public List <Pedido> leerPedidosPorCliente(String emailCliente) {
		List<Pedido> pedidos1 = ofy().load().type(Pedido.class).filter("emailCliente", emailCliente).list();
//		List<Pedido> pedidos = this.leerTodosPedido();
//		for(Pedido pedido: pedidos){
//			if(!(emailCliente.equals(pedido.getCliente().getEmail()))){
//				pedidos.remove(pedido);
//			}
//		}
		return pedidos1;
	}

	@Override
	public List<Pedido> leerPedidosPorHostelero(String emailHostelero) {
		List<Pedido> pedidos = ofy().load().type(Pedido.class).filter("emailHostelero", emailHostelero).list();
		return pedidos;
	}
	
	public List<Pedido> leerPedidosPorHosteleroNoEligiendo(String emailHostelero) {
		List<Pedido> pedidosHostelero = this.leerPedidosPorHostelero(emailHostelero);
		List<Pedido> pedidos =  new ArrayList<Pedido>();
		for(Pedido pedido: pedidosHostelero){
			if(pedido.getEstado() != Estado.eligiendo){
				pedidos.add(pedido);
			}
		}
		return pedidos;
	}
	
	@Override
	public List<Producto> leerProductosPorHostelero(String emailHostelero){
		List<Producto> productosHostelero = ofy().load().type(Producto.class).filter("emailHostelero", emailHostelero).list();
		return productosHostelero;
	}
	
	@Override
	public List<Producto> leerProductosPorId(List<Long> idProductosPedido){
		List<Producto> productosHostelero = ofy().load().type(Producto.class).filter("idProductosPedido", idProductosPedido).list();
		return productosHostelero;
	}




	@Override
	public Pedido borraPedido(Pedido pedido) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean existeCliente(String emailCliente){
		Cliente cliente = ofy().load().type(Cliente.class).filterKey(Key.create(Cliente.class, emailCliente)).first().now();
		if (cliente != null) {
			return true;
		}
		return false;
	}
	
	public boolean existeHostelero(String emailHostelero){
		Hostelero hostelero = ofy().load().type(Hostelero.class).filterKey(Key.create(Hostelero.class, emailHostelero)).first().now();
		if (hostelero != null) {
			return true;
		}
		return false;
	}
	
	@Override
	public Hostelero crearHostelero(String email, String nombre, String apellido1, String apellido2, String nombreEstablecimiento) {
		Hostelero hostelero = new Hostelero(email, nombre, apellido1, apellido2, nombreEstablecimiento);
		ofy().save().entity(hostelero).now();
		return hostelero;
	}
	
	@Override
	public Cliente crearCliente(String email, String nombre, String apellido1, String apellido2) {
		Cliente cliente = new Cliente(email, nombre, apellido1, apellido2);
		ofy().save().entity(cliente).now();
		return cliente;
	}
	
	@Override
	public void borrarTodosClientes() {
		List<Cliente> clientes = ofy().load().type(Cliente.class).list();
		ofy().delete().entities(clientes).now();
	}
	
	@Override
	public void borrarTodosHosteleros() {
		List<Hostelero> hosteleros = ofy().load().type(Hostelero.class).list();
		ofy().delete().entities(hosteleros).now();
	}
	
	@Override
	public void borrarTodosProductos() {
		List<Producto> productos = ofy().load().type(Producto.class).list();
		ofy().delete().entities(productos).now();
	}
	
	@Override
	public void borrarTodosPedidos() {
		List<Pedido> pedidos = ofy().load().type(Pedido.class).list();
		ofy().delete().entities(pedidos).now();
	}
	
	@Override
	public List<Hostelero> leerTodosHostelero() {
		List<Hostelero> hosteleros = ofy().load().type(Hostelero.class).list();
		return hosteleros;
	}
	
	@Override
	public List<Cliente> leerTodosCliente() {
		List<Cliente> clientes = ofy().load().type(Cliente.class).list();
		return clientes;
	}
	
	@Override
	public List<Producto> leerTodosProducto() {
		List<Producto> productos = ofy().load().type(Producto.class).list();
		return productos;
	}

	@Override
	public Cliente buscarClientePorEmail(String email) {
		Cliente cliente = ofy().load().type(Cliente.class).filterKey(Key.create(Cliente.class, email)).first().now();
		if (cliente != null) {
			return cliente;
		}
		return null;
	}
	@Override
	public Hostelero buscarHosteleroPorEmail(String email) {
		Hostelero hostelero = ofy().load().type(Hostelero.class).filterKey(Key.create(Hostelero.class, email)).first().now();
		if (hostelero != null) {
			return hostelero;
		}
		return null;
	}
	/*@Override
	public List<Long> listaProductosDePedido(long idPedido) {
		Pedido pedido = ofy().load().type(Pedido.class).filterKey(Key.create(Pedido.class, idPedido)).first().now();
		return  pedido.getProductos();
	}*/
	@Override
	public void actualizarProductosPedido(Pedido pedido, Producto producto) {
		if((pedido != null) && (pedido.getProductosPedido() == null)){
			List<Producto> arrayProductosAux = new ArrayList<Producto>();
			arrayProductosAux.add(producto);
			pedido.setProductosPedido(arrayProductosAux);
			pedido.setEmailHostelero(producto.getEmailHostelero());
			pedido.setHostelero(this.buscarHosteleroPorEmail(producto.getEmailHostelero()));
			ofy().save().entity(pedido).now();
		}else{
			pedido.getProductosPedido().add(producto);
			ofy().save().entity(pedido).now();
		}
	}
	@Override
	public Pedido actualizaEstadoSolicitado(Pedido pedido) {
		pedido.setEstado(Estado.solicitado);
		ofy().save().entity(pedido).now();

		return pedido;
	}
	
}
