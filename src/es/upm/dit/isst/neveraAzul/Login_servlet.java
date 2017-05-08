package es.upm.dit.isst.neveraAzul;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;

import es.upm.dit.isst.neveraAzul.dao.Dao_impl;
import es.upm.dit.isst.neveraAzul.model.Cliente;
import es.upm.dit.isst.neveraAzul.model.Hostelero;
import es.upm.dit.isst.neveraAzul.model.Pedido;
import es.upm.dit.isst.neveraAzul.model.Producto;


public class Login_servlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		ObjectifyService.register(Pedido.class);
		ObjectifyService.register(Cliente.class);
		ObjectifyService.register(Hostelero.class);
		ObjectifyService.register(Producto.class);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserService userService = UserServiceFactory.getUserService();
		String url = userService.createLoginURL(req.getRequestURI());
		String urlLinktext = "Iniciar Sesión";
		String user = "";
		boolean esHostelero = false;
		boolean esCliente = false;
		String reconocido = "";
		Dao_impl dao = Dao_impl.getInstancia();	
		
//		dao.borrarTodosClientes();
//		dao.borrarTodosHosteleros();
//		dao.borrarTodosPedidos();
//		dao.borrarTodosProductos();
		
		if (req.getUserPrincipal() != null){
				user = req.getUserPrincipal().getName();
				url = userService.createLogoutURL(req.getRequestURI());
				urlLinktext = "Cerrar Sesión";
				esHostelero = dao.existeHostelero(user);
				esCliente = dao.existeCliente(user);
				if (esHostelero) {
					reconocido = "hostelero";
				}else if (esCliente){
					reconocido = "cliente";
				}else{
					reconocido="no_existe";
				}
		}
		
		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("url", url);
		req.getSession().setAttribute("urlLinktext", urlLinktext);
		req.getSession().setAttribute("reconocido", reconocido);
		
		RequestDispatcher view = req.getRequestDispatcher("Index.jsp");
		view.forward(req, resp);
	}
			

}
