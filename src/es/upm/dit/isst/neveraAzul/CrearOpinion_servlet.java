package es.upm.dit.isst.neveraAzul;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.neveraAzul.dao.Dao_impl;
import es.upm.dit.isst.neveraAzul.model.Cliente;
import es.upm.dit.isst.neveraAzul.model.Hostelero;

public class CrearOpinion_servlet extends HttpServlet {

	private String user = "";

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 System.out.println("Entro post Nueva servlet");

		 String emailCliente = req.getUserPrincipal().getName().toString();
		 
		 String emailHostelero = req.getSession().getAttribute("emailHostelero").toString();
		
		 System.out.println("coge el nombre del usuario actual" + req.getUserPrincipal().getName().toString());
		 System.out.println("coge el email del hostelero a comentar " + emailHostelero);
		
		Dao_impl dao = Dao_impl.getInstancia();
		int puntuacion = 0;
		
		Cliente cliente = dao.buscarClientePorEmail(emailCliente);
		System.out.println("consigue el parametro email hostelero " + emailHostelero );
		
		Hostelero hostelero = dao.buscarHosteleroPorEmail(emailHostelero);
		String nombreEstablecimineto = hostelero.getNombreEstablecimiento();
		String titulo= req.getParameter("titulo");
		String puntuacion2 = req.getParameter("puntuacion");
		System.out.println("POST MUCHAS COSAS");
		if (puntuacion2 != null){
		  puntuacion =  Integer.parseInt(puntuacion2);
		}
		
		String descripcion= req.getParameter("descripcion");
		
		 System.out.println("puntuacion vale esto: "+ puntuacion);
		
		if ((descripcion != null) && (emailCliente != null) && (titulo != null) ) {
			
			 dao.crearOpinion(nombreEstablecimineto, emailHostelero, emailCliente, descripcion,titulo, puntuacion);
			 System.out.println("SI guarda las cosas");

		}else{
			System.out.println("NO guarda las cosas");
			//ESTO NO CONCIGO QUE SE PINTE
			resp.sendRedirect("descripcionHostelero");
//			resp.setContentType("text/html");
//			resp.getWriter().print("<html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"/css/bootstrap.css\" /></head> "
//					+ "<body><div class=\"alert alert-danger\" role=\"alert\">Ha habido algún error. Vuelve a intentarlo.</div></body>"
//					+ "</html>");			
//			resp.getWriter().print("<p><a href=/descripcionHostelero>Volver</a></p>");
		}
		
		req.getSession().setAttribute("emailHostelero", emailHostelero);
		 System.out.println("pasa a la siguiente el emailhostelero: " + emailHostelero);

		
		resp.sendRedirect("descripcionHostelero");
	
	
	
	}

}

