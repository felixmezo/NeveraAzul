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
import es.upm.dit.isst.neveraAzul.model.Opiniones;

public class MisOpiniones_servlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private String user = "";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Dao_impl dao = Dao_impl.getInstancia();	
		user = req.getUserPrincipal().getName();
		if(dao.existeCliente(user)){
			List<Opiniones> opiniones = dao.leerOpinionesPorCliente(user);
			user = req.getUserPrincipal().getName();
			Cliente clienteLogueado = dao.buscarClientePorEmail(user);
			String nombreCliente = clienteLogueado.getNombre();
			String apellido1 = clienteLogueado.getApellido1();
			
			req.getSession().setAttribute("user", user);
			req.getSession().setAttribute("opiniones", opiniones);	
			req.getSession().setAttribute("nombreCliente", nombreCliente);
			req.getSession().setAttribute("apellido1", apellido1);
			
			RequestDispatcher view = req.getRequestDispatcher("opinionesCliente.jsp");
			view.forward(req, resp);
		}
		else{
			
			Hostelero hosteleroLogueado = dao.buscarHosteleroPorEmail(user);
			String nombreEstablecimiento = hosteleroLogueado.getNombreEstablecimiento();
			List<Opiniones> opiniones = dao.leerOpinionesPorHostelero(nombreEstablecimiento);
			
			req.getSession().setAttribute("nombreEstablecimiento", nombreEstablecimiento);
			req.getSession().setAttribute("user", user);
			req.getSession().setAttribute("opiniones", opiniones);
			RequestDispatcher view = req.getRequestDispatcher("opinionesHostelero.jsp");
			view.forward(req, resp);
		}

	}
	
	
}
