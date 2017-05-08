package es.upm.dit.isst.neveraAzul;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Contacto_servlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher view = req.getRequestDispatcher("contacto.jsp");
		view.forward(req, resp);
}

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	resp.setContentType("text/html");
	resp.getWriter().print("<html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"/css/bootstrap.css\" /></head> "
			+ "<body><div class=\"alert alert-success\" role=\"alert\">Gracias por contactar con nosotros.</div>"
			+ "<div class=\"alert alert-success\" role=\"alert\">Nuestro equipo se pondrá en contacto con usted.</div></body>"
			+ "</html>");
	resp.getWriter().print("<p><a href=/>Volver</a></p>");

}

}
