package es.upm.dit.isst.neveraAzul;


import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.neveraAzul.dao.Dao_impl;
import es.upm.dit.isst.neveraAzul.model.Cliente;
import es.upm.dit.isst.neveraAzul.model.Hostelero;
import es.upm.dit.isst.neveraAzul.model.Opiniones;
import es.upm.dit.isst.neveraAzul.model.Producto;

public class DescripcionHostelero_servlet extends HttpServlet {
	
	public String emailHostelero = null;

	private static final long serialVersionUID = 1L;
	private String user = ""; 

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Dao_impl dao = Dao_impl.getInstancia();	
		user = req.getUserPrincipal().getName();
		
		Cliente clienteLogueado = dao.buscarClientePorEmail(user);
		String nombreCliente = clienteLogueado.getNombre();
		List<Opiniones> opiniones = new ArrayList<Opiniones>();
		System.out.println("clinete y lista de opiniones");
		String emailHostelero2 = null;
		
		if (req.getParameter("emailHostelero") != null){
		try{
		emailHostelero2 = req.getParameter("emailHostelero");
		}
		catch (Exception e) {
		}
		
		System.out.println("el email del hostelero es: "+ emailHostelero2.toString() );
		
		}else{
		try{
			emailHostelero2 = req.getSession().getAttribute("emailHostelero").toString();
			}
			catch (Exception e) {
			}
		}
		System.out.println("el email del hostelero es: "+ emailHostelero2.toString() );

		String emailHostelero = emailHostelero2.toString();
		Hostelero hostelero = dao.buscarHosteleroPorEmail(emailHostelero2);
		String nombreHostelero = hostelero.getNombre();
		String nombreEstablecimineto = hostelero.getNombreEstablecimiento();
		opiniones = dao.leerOpinionesPorHostelero(nombreEstablecimineto);

	
		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("nombreCliente", nombreCliente);
		req.getSession().setAttribute("nombreHostelero", nombreHostelero);
		req.getSession().setAttribute("nombreEstablecimineto", nombreEstablecimineto);
		req.getSession().setAttribute("emailHostelero", emailHostelero);
		
		System.out.println("pasa a la sesion el email del hostelero " +emailHostelero );
		
		req.getSession().setAttribute("opiniones", opiniones);
		
		RequestDispatcher view = req.getRequestDispatcher("descripcionHostelero.jsp");
		view.forward(req, resp);
		System.out.println("renderiza la la servlet");
	
	}
	
}
