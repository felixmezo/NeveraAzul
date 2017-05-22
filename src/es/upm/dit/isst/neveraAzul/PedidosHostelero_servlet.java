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

public class PedidosHostelero_servlet extends HttpServlet{
	

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Dao_impl dao = Dao_impl.getInstancia();	
		String user = req.getUserPrincipal().getName();
		
		Hostelero hosteleroLogueado = dao.buscarHosteleroPorEmail(user);
		String nombreEstablecimiento = hosteleroLogueado.getNombreEstablecimiento();
	
		List<Pedido> pedidos = dao.leerPedidosPorHosteleroNoEligiendo(user);
				
		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("nombreEstablecimiento", nombreEstablecimiento);
		req.getSession().setAttribute("pedidos", pedidos);

		
		RequestDispatcher view = req.getRequestDispatcher("pedidos_hostelero.jsp");
		view.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Dao_impl dao = Dao_impl.getInstancia();
		
		String id = req.getParameter("idPedido");
		String id2 = id.toString();
		long idLong = Long.parseLong(id2);
		
		Pedido pedido = dao.BuscarPedidoPorId(idLong);
		
		dao.actualizaEstadoAceptadoHostelero(pedido);
		
		resp.sendRedirect("pedidoshostelero");

	}
	
}
