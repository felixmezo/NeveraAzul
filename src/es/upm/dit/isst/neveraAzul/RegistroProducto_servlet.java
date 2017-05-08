package es.upm.dit.isst.neveraAzul;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.neveraAzul.dao.Dao_impl;
import es.upm.dit.isst.neveraAzul.model.Hostelero;
import es.upm.dit.isst.neveraAzul.model.Producto;

public class RegistroProducto_servlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private String user = "";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		user = req.getUserPrincipal().getName();
		
		req.getSession().setAttribute("user", user);
		
		RequestDispatcher view = req.getRequestDispatcher("registro_producto.jsp");
		view.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		user = req.getUserPrincipal().getName();

		String descripcion = req.getParameter("descripcion");
		String nombre = req.getParameter("nombre");
		String enOferta = req.getParameter("enOferta");
		float precio = Float.parseFloat(req.getParameter("precio"));

		Dao_impl dao = Dao_impl.getInstancia();
		
		Hostelero hostelero = dao.buscarHosteleroPorEmail(user);
		String establecimientoHostelero = hostelero.getNombreEstablecimiento();
		
		if ((descripcion != null) && (nombre != null)) {
			Producto producto = dao.crearProducto(nombre, descripcion, user, precio, establecimientoHostelero, enOferta);
//			resp.setContentType("text/html");
//			resp.getWriter().print("<html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"/css/bootstrap.css\" /></head> "
//					+ "<body><div class=\"alert alert-success\" role=\"alert\">Enhorabuena, acabas de publicar un producto en LA NEVERA AZUL.</div></body>"
//					+ "</html>");
//			resp.getWriter().print("<p><a href=/indexhostelero>Volver a mi perfil</a></p>");
		}else{
			resp.setContentType("text/html");
			resp.getWriter().print("<html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"/css/bootstrap.css\" /></head> "
					+ "<body><div class=\"alert alert-danger\" role=\"alert\">Ha habido algún error. Vuelve a intentarlo.</div></body>"
					+ "</html>");			
			resp.getWriter().print("<p><a href=/indexhostelero>Volver a mi perfil</a></p>");
		}
		
		resp.sendRedirect("indexhostelero");

	}

}
