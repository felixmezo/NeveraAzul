package es.upm.dit.isst.neveraAzul.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;

import es.upm.dit.isst.neveraAzul.model.Administrador;
import es.upm.dit.isst.neveraAzul.model.Cliente;
import es.upm.dit.isst.neveraAzul.model.Estado;
import es.upm.dit.isst.neveraAzul.model.Hostelero;
import es.upm.dit.isst.neveraAzul.model.Opiniones;
import es.upm.dit.isst.neveraAzul.model.Pedido;
import es.upm.dit.isst.neveraAzul.model.Producto;

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
		List<Pedido> pedidos = this.leerTodosPedido();
		for(Pedido pedido: pedidos){
			if(pedido.getEmailCliente().equals(cliente.getEmail()) && pedido.getEstado() == Estado.eligiendo){
				return pedido;
			}
		}
		return null;
	}
	
	public Pedido BuscarPedidoPorId(long IdPedido){
		Pedido pedido = ofy().load().type(Pedido.class).filterKey(Key.create(Pedido.class, IdPedido)).first().now();
		return pedido;
	}

	
	
	public void setPrecioTotalPedido(Pedido pedidoActual, float precioTotal){
		pedidoActual.setPrecioTotal(precioTotal);
		ofy().save().entity(pedidoActual).now();
	}
	
	@Override
	public Pedido crearPedido(String emailHostelero, Hostelero hostelero, String emailCliente) {
		Cliente cliente = this.buscarClientePorEmail(emailCliente);
		int descuento = this.saberDescuento();
		Pedido pedido = new Pedido(hostelero, emailHostelero, emailCliente, cliente, descuento);
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
	public void borrarProducto(long idProducto){
		Producto producto = this.BuscarProductoPorId(idProducto);
		ofy().delete().entity(producto).now();
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
	
	@Override
	public Pedido actualizaEstadoAceptadoHostelero(Pedido pedido) {
		pedido.setEstado(Estado.aceptado_hostelero);
		ofy().save().entity(pedido).now();
		return pedido;
	}
	
	
	
	@Override
	public Opiniones crearOpinion(String establecimientoHostelero, String emailHostelero, String emailCliente, String descripcion, String titulo ,int puntuacion){
		Opiniones opinion = new Opiniones(establecimientoHostelero,emailHostelero,emailCliente,descripcion,titulo,puntuacion);
		ofy().save().entity(opinion).now();
		return opinion;
	}
	
	@Override
	public boolean existeOpinionHostelero(String establecimientoHostelero){
		Opiniones opinion = ofy().load().type(Opiniones.class).filterKey(Key.create(Opiniones.class, establecimientoHostelero)).first().now();
		if (opinion != null) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean existeOpinionCliente(String emailCliente){
		Opiniones opinion = ofy().load().type(Opiniones.class).filterKey(Key.create(Opiniones.class, emailCliente)).first().now();
		if (opinion != null) {
			return true;
		}
		return false;
	}
	
	@Override
	public List<Opiniones> leerOpinionesPorHostelero(String establecimientoHostelero){
		List<Opiniones> opinionesHostelero = ofy().load().type(Opiniones.class).filter("establecimientoHostelero", establecimientoHostelero).list();
		return opinionesHostelero;
	}
	
	@Override
	public List<Opiniones> leerOpinionesPorCliente(String emailCliente){
		List<Opiniones> opinionesCliente = ofy().load().type(Opiniones.class).filter("emailCliente", emailCliente).list();
		return opinionesCliente;
	}
	
	@Override
	public void borrarTodasOpiniones(){
		List<Opiniones> opiniones = ofy().load().type(Opiniones.class).list();
		ofy().delete().entities(opiniones).now();
	}
	@Override
	public Administrador crearAdmin(String apellido1){
		Administrador admininstrador = new Administrador(apellido1);
		ofy().save().entity(admininstrador).now();
		return admininstrador;
	}
	
	public boolean existeAdmin(String emailAdmin){
		Administrador admninstrador = ofy().load().type(Administrador.class).filterKey(Key.create(Administrador.class, emailAdmin)).first().now();
		if (admninstrador != null) {
			return true;
		}
		return false;
	}
	public Administrador buscarAdminPorEmail(String email){
		Administrador administrador = ofy().load().type(Administrador.class).filterKey(Key.create(Administrador.class, email)).first().now();
		if (administrador != null) {
			return administrador;
		}
		return null;
	}
	
	@Override
	public void ponerDescuentoATodosPedidos(int descuento){
		List<Pedido> pedidos = this.leerTodosPedido();
		for(Pedido pedido: pedidos){
				pedido.setDescuento(descuento);
				ofy().save().entity(pedido).now();
		}
		int descuento2 = this.saberDescuento();
		System.out.println("el valor del descuento en el DAO es : " + descuento2);
	}
	@Override
	public int saberDescuento(){
		 int descuento = 0;
		List<Pedido> pedidos = this.leerTodosPedido();
		for(Pedido pedido: pedidos){
			if (descuento != pedido.getDescuento())
			descuento = pedido.getDescuento();
			}
		return descuento;
	}
	
	@Override
	public void borrarAdmin() {
		Administrador administrador = ofy().load().type(Administrador.class).filterKey(Key.create(Administrador.class, "neveraazul7@gmail.com")).first().now();
		ofy().delete().entities(administrador).now();
	}
	@Override
	public void sumaPedidoACliente (String email){
		Cliente cliente = ofy().load().type(Cliente.class).filterKey(Key.create(Cliente.class, email)).first().now();
		int numerPedidosAntes = cliente.getNumeroPedidos();
		int numeroPedidos = numerPedidosAntes +1;
		cliente.setNumeroPedidos(numeroPedidos);
		ofy().save().entity(cliente).now();		
	}
	@Override
	public void resetearNumeroPedidosACliente(String email){
		Cliente cliente = ofy().load().type(Cliente.class).filterKey(Key.create(Cliente.class, email)).first().now();
		cliente.setNumeroPedidos(0);
		ofy().save().entity(cliente).now();	
		
	}

	
	
}
