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
import es.upm.dit.isst.neveraAzul.model.Hostelero;
import es.upm.dit.isst.neveraAzul.model.Pedido;
import es.upm.dit.isst.neveraAzul.model.Producto;

public class IndexHostelero_servlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Dao_impl dao = Dao_impl.getInstancia();	
		String user = req.getUserPrincipal().getName();
		
		Hostelero hosteleroLogueado = dao.buscarHosteleroPorEmail(user);
		String nombreEstablecimiento = hosteleroLogueado.getNombreEstablecimiento();
		
		ArrayList<Producto> productosPublicados = new ArrayList<>();	
		productosPublicados.addAll(dao.leerProductosPorHostelero(user));

				
		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("productosPublicados", productosPublicados);
		req.getSession().setAttribute("nombreEstablecimiento", nombreEstablecimiento);

		
		RequestDispatcher view = req.getRequestDispatcher("indexHostelero.jsp");
		view.forward(req, resp);
	}
	
}
