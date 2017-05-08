package es.upm.dit.isst.neveraAzul;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.neveraAzul.dao.Dao_impl;
import es.upm.dit.isst.neveraAzul.model.Cliente;
import es.upm.dit.isst.neveraAzul.model.Hostelero;

public class PortadaUsuario_servlet extends HttpServlet{
	 
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Dao_impl dao = Dao_impl.getInstancia();	
		String user = req.getUserPrincipal().getName();
		String tipoUsuario = "";
		String nombre = "";
		String apellido1 = "";
		String apellido2 = "";
		String establecimiento = "";
		
		if (dao.buscarHosteleroPorEmail(user) != null) {
			tipoUsuario = "Hostelero";
			Hostelero hostelero = dao.buscarHosteleroPorEmail(user);
			nombre = hostelero.getNombre();
			apellido1 = hostelero.getApellido1();
			apellido2 = hostelero.getApellido2();
			establecimiento = hostelero.getNombreEstablecimiento();
		}else if (dao.buscarClientePorEmail(user) != null){
			tipoUsuario = "Cliente";
			Cliente cliente = dao.buscarClientePorEmail(user);
			nombre = cliente.getNombre();
			apellido1 = cliente.getApellido1();
			apellido2 = cliente.getApellido2();
		}else{
			tipoUsuario = "Error";
		}
		
		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("tipoUsuario", tipoUsuario);
		req.getSession().setAttribute("nombre", nombre);
		req.getSession().setAttribute("apellido1", apellido1);
		req.getSession().setAttribute("apellido2", apellido2);
		req.getSession().setAttribute("establecimiento", establecimiento);
		
		RequestDispatcher view = req.getRequestDispatcher("portadaUsuario.jsp");
		view.forward(req, resp);
	}
}
