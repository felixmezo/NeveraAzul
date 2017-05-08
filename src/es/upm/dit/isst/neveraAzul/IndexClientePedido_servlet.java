package es.upm.dit.isst.neveraAzul;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.neveraAzul.dao.Dao_impl;
import es.upm.dit.isst.neveraAzul.model.Cliente;
import es.upm.dit.isst.neveraAzul.model.Hostelero;
import es.upm.dit.isst.neveraAzul.model.Pedido;
import es.upm.dit.isst.neveraAzul.model.Producto;

public class IndexClientePedido_servlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private String user = "";

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Dao_impl dao = Dao_impl.getInstancia();	
		user = req.getUserPrincipal().getName();
		
		
		
		Cliente clienteLogueado = dao.buscarClientePorEmail(user);
		String nombreCliente = clienteLogueado.getNombre();
//		String apellido1 = clienteLogueado.getApellido1();
//		String apellido2 = clienteLogueado.getApellido2();
//		if(!dao.hayPedidoEligiendo(clienteLogueado)){
//			dao.crearPedido(null, null, user);
//			RequestDispatcher view = req.getRequestDispatcher("indexclientepedido");
//			view.forward(req, resp);
//			}
		
		Pedido pedidoActual = null;
		List<Producto> todosProductos = null;
		List<Producto> productosPedido = null;
		try{
			pedidoActual = dao.buscarPedidoEligiendo(clienteLogueado);
			todosProductos = dao.leerTodosProducto();
			productosPedido = pedidoActual.getProductosPedido();
		} catch(Exception e){
			System.out.print(e);
		}
		
		if(pedidoActual != null && (pedidoActual.getHostelero() != null || pedidoActual.getEmailHostelero() != null)){
			List<Producto> productosHostelero = dao.leerProductosPorHostelero(pedidoActual.getEmailHostelero());
			todosProductos = productosHostelero;
		}
		
		
		
		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("nombreCliente", nombreCliente);
//		req.getSession().setAttribute("apellido1", apellido1);
//		req.getSession().setAttribute("apellido2", apellido2);
		req.getSession().setAttribute("productosPedido", productosPedido);
		req.getSession().setAttribute("todosProductos", todosProductos);
		
//		req.getSession().setAttribute("porductosHostelero", productosHostelero);
		

		

		
		RequestDispatcher view = req.getRequestDispatcher("indexClientePedido.jsp");
		view.forward(req, resp);
		
		
		

//		ArrayList<Producto> todosProductos = new ArrayList<>();	
//		todosProductos.addAll(dao.leerTodosProducto());

//		List<Producto> arrayProductos = new ArrayList<>();
//		List<Pedido> pedidos = dao.leerPedidosPorCliente(user);
//		if (pedidos != null){
//			if(!pedidos.isEmpty()){
//			Pedido pedido1Cliente = pedidos.get(0);
//			arrayProductos = pedido1Cliente.getProductosPedido();
//			req.getSession().setAttribute("productosDePedido", arrayProductos);
//			}
//		}
		
//		dao.borrarTodosPedidos();
		

		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		user = req.getUserPrincipal().getName();
		Dao_impl dao = Dao_impl.getInstancia();
		
		
//		if (req.getParameter("borrarpedido") != null){
//			dao.borrarTodosPedidos();
//			resp.getWriter().print("Pedido borrado!");
//		}else{

		//Producto
		
		if(req.getParameter("finalizar") != null){
			dao.actualizaEstadoSolicitado(dao.buscarPedidoEligiendo(dao.buscarClientePorEmail(user)));
//			dao.actualizaEstadoSolicitado(dao.leerTodosPedido().get(0));
			resp.setContentType("text/html");
			resp.getWriter().print("<html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"/css/bootstrap.css\" /></head> "
					+ "<body><div class=\"alert alert-success\" role=\"alert\">Pedido Finalizado.</div></body>"
					+ "</html>");
			resp.getWriter().print("<p><a href=/indexcliente>Volver a mi perfil</a></p>");
			
		}else{
		
		String idProducto = req.getParameter("idProducto");
		String idProducto2 = idProducto.toString();
		long longidproducto = Long.parseLong(idProducto2);
		Producto producto = dao.BuscarProductoPorId(longidproducto);
		
		
		
//
//		if (dao.leerPedidosPorCliente(user))
//		Pedido pruebaPedido = dao.crearPedido(producto.getEmailHostelero(), user);
		
		Cliente clienteLogueado = dao.buscarClientePorEmail(user);
		
		List<Pedido> pedidos =  dao.leerPedidosPorCliente(user);
		
//		Pedido pedido0Cliente = pedidos.get(0);
		
		Pedido pedido0Cliente = dao.buscarPedidoEligiendo(clienteLogueado);
		
		
		
		dao.actualizarProductosPedido(pedido0Cliente, producto);
//		List<Producto> arrayProductos = pedido0Cliente.getProductosPedido();	
//		List<Producto> arrayProductosAux = new ArrayList<Producto>();
//		if(arrayProductos != null){
//			for (Producto productoIter : arrayProductos) {
//				arrayProductosAux.add(productoIter);
//				}
//		}
//		arrayProductosAux.add(producto);
//		for (Producto productoIter : arrayProductos) {
//			arrayProductosAux.add(productoIter);
//			}
//		arrayProductosAux.add(producto);
//		
//		arrayProductos.add(producto);
//		arrayProductos.add(producto2);
////		arrayProductos.add(producto3);
////		arrayProductos.add(producto4);
//
//		pedido0Cliente.setProductosPedido(arrayProductosAux);
//		List<Producto> arrayProductos1 = pedido0Cliente.getProductosPedido();
//
//		for (Producto productoIter : arrayProductos) {
//			if (productoIter == null){
//				arrayProductos.add(producto);
//
//			}
//			resp.getWriter().println(productoIter.getNombre());
//		
//		}
//		resp.getWriter().println(arrayProductosAux.get(0).getNombre());
		String productoPedido = "";
		for (Producto productoIter : pedido0Cliente.getProductosPedido()) {
			productoPedido = productoIter.getNombre();
			}
		resp.setContentType("text/html");
		resp.getWriter().print("<html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"/css/bootstrap.css\" /></head> "
				+ "<body><div class=\"alert alert-success\" role=\"alert\">El producto \""+ productoPedido +"\" ha sido añadido al carrito de la compra.</div></body>"
				+ "</html>");
		resp.getWriter().print("<p><a href=/indexclientepedido>Volver a mi perfil</a></p>");
		}
//	}
	}

}

