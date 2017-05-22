package es.upm.dit.isst.neveraAzul;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.neveraAzul.dao.Dao_impl;
import es.upm.dit.isst.neveraAzul.model.Administrador;
import es.upm.dit.isst.neveraAzul.model.Cliente;
import es.upm.dit.isst.neveraAzul.model.Pedido;

public class AdminSuscripciones_servlet  extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private String user = "";

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Dao_impl dao = Dao_impl.getInstancia();	
		user = req.getUserPrincipal().getName();
		
		Administrador adminLogueado = dao.buscarAdminPorEmail(user);
		String nombreAdmin = adminLogueado.getNombre();
		String apellido1 = adminLogueado.getApellido1();
		int descuento = dao.saberDescuento();
				
		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("nombreAdmin", nombreAdmin);
		req.getSession().setAttribute("apellido1", apellido1);
		req.getSession().setAttribute("descuento", descuento);
		
		RequestDispatcher view = req.getRequestDispatcher("adminSuscripciones.jsp");
		view.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		user = req.getUserPrincipal().getName();
		Dao_impl dao = Dao_impl.getInstancia();
		
		if(req.getParameter("Activar") != null){
			String descuento2 = req.getParameter("descuento");
			int descuento =  Integer.parseInt(descuento2);		
			dao.ponerDescuentoATodosPedidos(descuento);
			System.out.println("el entero que le paso al metodo de ponerDescuento es : " + descuento);
			resp.sendRedirect("adminSuscripciones");
		}else if (req.getParameter("Desactivar") != null){	
			int descuento = 0;
			dao.ponerDescuentoATodosPedidos(descuento);
			System.out.println("el entero que le paso al metodo de ponerDescuento es : " + descuento);
			resp.sendRedirect("adminSuscripciones");			
		}else{
			resp.sendRedirect("adminSuscripciones");
		}
		
		
	}

}
