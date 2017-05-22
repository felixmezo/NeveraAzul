package es.upm.dit.isst.neveraAzul;

import java.io.IOException;
import java.sql.Time;
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

public class IndexCliente_servlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private String user = "";

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Dao_impl dao = Dao_impl.getInstancia();	
		user = req.getUserPrincipal().getName();
		
		Cliente clienteLogueado = dao.buscarClientePorEmail(user);
		String nombreCliente = clienteLogueado.getNombre();
		String apellido1 = clienteLogueado.getApellido1();
		String apellido2 = clienteLogueado.getApellido2();
		
//		Pedido pruebaPedido = dao.crearPedido(,clienteLogueado );
		
		List<Pedido> pedidos = dao.leerPedidosPorCliente(user);
		
//		dao.borrarTodosPedidos();
		
		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("nombreCliente", nombreCliente);
		req.getSession().setAttribute("apellido1", apellido1);
		req.getSession().setAttribute("apellido2", apellido2);
		req.getSession().setAttribute("pedidos", pedidos);

		RequestDispatcher view = req.getRequestDispatcher("indexCliente.jsp");
		view.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		user = req.getUserPrincipal().getName();
		Dao_impl dao = Dao_impl.getInstancia();
		
		Cliente clienteActual = dao.buscarClientePorEmail(user);
		
		if(!dao.hayPedidoEligiendo(clienteActual)){
			dao.crearPedido(null, null, user);
		}
		
		resp.sendRedirect("/indexclientepedido");
//		RequestDispatcher view = req.getRequestDispatcher("indexClientePedido.jsp");
//		view.forward(req, resp);
	}

}

