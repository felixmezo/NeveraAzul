package es.upm.dit.isst.neveraAzul;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.neveraAzul.dao.Dao_impl;
import es.upm.dit.isst.neveraAzul.model.Cliente;

public class Suscripciones_servlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private String user = "";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		user = req.getUserPrincipal().getName();
		Dao_impl dao = Dao_impl.getInstancia();
		
		Cliente clienteLogueado = dao.buscarClientePorEmail(user);
		String nombreCliente = clienteLogueado.getNombre();
		String apellido1 = clienteLogueado.getApellido1();
		String apellido2 = clienteLogueado.getApellido2();
		int numeroPedidos = clienteLogueado.getNumeroPedidos();
		
		int descuento = dao.saberDescuento();

		
		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("nombreCliente", nombreCliente);
		req.getSession().setAttribute("apelldo1", apellido1);
		req.getSession().setAttribute("apellido2", apellido2);
		req.getSession().setAttribute("numeroPedidos", numeroPedidos);
		req.getSession().setAttribute("descuento", descuento);
		
		 System.out.println("el numeor de pedidos en la servlet de suscripciones es: " + numeroPedidos);

		
		RequestDispatcher view = req.getRequestDispatcher("suscripciones.jsp");
		view.forward(req, resp);
	}
	
}
