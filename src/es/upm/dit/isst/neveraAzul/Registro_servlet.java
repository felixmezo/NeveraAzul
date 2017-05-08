package es.upm.dit.isst.neveraAzul;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.neveraAzul.dao.Dao_impl;

public class Registro_servlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher view = req.getRequestDispatcher("registro.jsp");
		view.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String nombre = req.getParameter("nombre");
		String apellido1 = req.getParameter("apellido1");
		String apellido2 = req.getParameter("apellido2");
		String nombreEstablecimiento = req.getParameter("nombreEstablecimiento");
		String modo = req.getParameter("modo");
		Dao_impl dao = Dao_impl.getInstancia();
		if ((modo.equals("hostelero")) && (!dao.existeCliente(email)) && (nombreEstablecimiento != null) && (!nombreEstablecimiento.equals(""))) {	
			dao.crearHostelero(email, nombre, apellido1, apellido2, nombreEstablecimiento);
			resp.setContentType("text/html");
			resp.getWriter().print("<html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"/css/bootstrap.css\" /></head> "
					+ "<body><div class=\"alert alert-success\" role=\"alert\">Enhorabuena, te acabas de registrar LA NEVERA AZUL como hostelero.</div></body>"
					+ "</html>");
			resp.getWriter().print("<p><a href=/login>Volver</a></p>");
		}else if ((modo.equals("cliente")) && (!dao.existeHostelero(email))){
			dao.crearCliente(email, nombre, apellido1, apellido2);
			resp.setContentType("text/html");
			resp.getWriter().print("<html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"/css/bootstrap.css\" /></head> "
					+ "<body><div class=\"alert alert-success\" role=\"alert\">Enhorabuena, te acabas de registrar LA NEVERA AZUL como cliente.</div></body>"
					+ "</html>");
			resp.getWriter().print("<p><a href=/login>Volver</a></p>");
		}else{
			resp.setContentType("text/html");
			resp.getWriter().print("<html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"/css/bootstrap.css\" /></head> "
					+ "<body><div class=\"alert alert-danger\" role=\"alert\">Ha debido de haber algún error. Intentalo de nuevo.</div></body>"
					+ "</html>");
			resp.getWriter().print("<p><a href=/login>Volver</a></p>");
		}		
	}

}
