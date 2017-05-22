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
		
		float precioTotal = 0;
		
		try{
			
		for(Producto producto: productosPedido){
			float precioProducto = producto.getPrecio();
			precioTotal = precioTotal +  precioProducto;
			dao.setPrecioTotalPedido(pedidoActual, precioTotal);
		}
		}catch(Exception e){
			System.out.print(e);
		}
		
		if((clienteLogueado.getNumeroPedidos() >= 5) && (precioTotal <= 30)){
			dao.resetearNumeroPedidosACliente(user);
			int numeroPedidos = clienteLogueado.getNumeroPedidos();
			System.out.println ("Ponemos a 0 el numeor de pedidos: " + numeroPedidos);
			precioTotal = 0;
			dao.setPrecioTotalPedido(pedidoActual, precioTotal);


		}
		
		int descuento = 0;
		
		if (pedidoActual.getDescuento() != 0){
			descuento = pedidoActual.getDescuento();
			System.out.println("el descuento aplicado es: " +descuento);
			precioTotal = (float)(precioTotal*(100-descuento)/100);
			dao.setPrecioTotalPedido(pedidoActual, precioTotal);

		}
		
		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("nombreCliente", nombreCliente);
		req.getSession().setAttribute("productosPedido", productosPedido);
		req.getSession().setAttribute("todosProductos", todosProductos);
		req.getSession().setAttribute("precioTotal", precioTotal);
		req.getSession().setAttribute("descuento", descuento);
				
		RequestDispatcher view = req.getRequestDispatcher("indexClientePedido.jsp");
		view.forward(req, resp);
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		user = req.getUserPrincipal().getName();
		Dao_impl dao = Dao_impl.getInstancia();
		
		if(req.getParameter("finalizar") != null){
			dao.actualizaEstadoSolicitado(dao.buscarPedidoEligiendo(dao.buscarClientePorEmail(user)));
			Cliente clienteLogueado = dao.buscarClientePorEmail(user);
			
			dao.sumaPedidoACliente(user);
			int numeroPedidos = clienteLogueado.getNumeroPedidos();
			 System.out.println("el numeor de pedidos hasta el momento es: " + numeroPedidos);
			
			 
			resp.sendRedirect("/indexcliente");
			
//			resp.setContentType("text/html");
//			resp.getWriter().print("<html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"/css/bootstrap.css\" /></head> "
//					+ "<body><div class=\"alert alert-success\" role=\"alert\">Pedido Finalizado.</div></body>"
//					+ "</html>");
//			resp.getWriter().print("<p><a href=/indexcliente>Volver a mi perfil</a></p>");
			
		}else{
		
		String idProducto = req.getParameter("idProducto");
		String idProducto2 = idProducto.toString();
		long longidproducto = Long.parseLong(idProducto2);
		Producto producto = dao.BuscarProductoPorId(longidproducto);
		
		Cliente clienteLogueado = dao.buscarClientePorEmail(user);
		
		List<Pedido> pedidos =  dao.leerPedidosPorCliente(user);
		
		Pedido pedido0Cliente = dao.buscarPedidoEligiendo(clienteLogueado);
		
		dao.actualizarProductosPedido(pedido0Cliente, producto);
		
		String productoPedido = "";
		for (Producto productoIter : pedido0Cliente.getProductosPedido()) {
			productoPedido = productoIter.getNombre();
			}
		
		resp.sendRedirect("/indexclientepedido");

//		resp.setContentType("text/html");
//		resp.getWriter().print("<html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"/css/bootstrap.css\" /></head> "
//				+ "<body><div class=\"alert alert-success\" role=\"alert\">El producto \""+ productoPedido +"\" ha sido a�adido al carrito de la compra.</div></body>"
//				+ "</html>");
//		resp.getWriter().print("<p><a href=/indexclientepedido>Volver a mi perfil</a></p>");
		}
//	}
	}

}

