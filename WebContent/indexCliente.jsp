<%@page import="es.upm.dit.isst.neveraAzul.model.Producto"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" media="screen" href="/css/bootstrap.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Perfil de Cliente</title>
</head>
<%@ include file="cabecera.jsp"%>

<body>
	<section id="nav"> <%@ include file="navegacion_cliente.jsp"%>
	</section>
	<section id="contenido"> <article> <c:if
		test="${not empty user}">
		<label> Hola <c:out value="${nombreCliente}"></c:out> <c:out
				value="${apellido1}"></c:out> <c:out value="${apellido2}"></c:out>
		</label>
		<br>
		<c:if test="${not empty pedidos}">
			<div id="tabla0" class="panel panel-danger">
				<div  class="panel-heading">
					Pedidos del usuario
					<c:out value="${user}"></c:out>
					.
				</div>
				<div class="panel-body">
					<p>Estos son tus pedidos hasta la fecha:</p>
				</div>
				<table class="table">
					<tr>
						<th id="cabecera">Id Pedido</th>
						<th id="cabecera">Establecimiento</th>
						<th id="cabecera">Estado</th>
						<th id="cabecera">Precio</th>
					</tr>
					<c:forEach items="${pedidos}" var="pedido">
						<tr>
							<td><c:out value="${pedido.idPedido}" /></td>
							<td><c:out value="${pedido.hostelero.nombreEstablecimiento}" /></td>
							<td><c:out value="${pedido.estado}" /></td>
							<td><c:out value="${pedido.precioTotal}"/> €</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</c:if>
		<c:if test="${empty pedidos}">
			<p>No has solicitado ningún pedido hasta el momento</p>
			<p>Dale a al botón para inciar uno nuevo o continuar uno en curso.</p>
		</c:if>
		<form action="/indexcliente" method="post">
			<button type="submit" class="btn btn-default" name="crearPedido"
				value="Crear pedido nuevo o continuar pedido en curso">Crear
				pedido nuevo o continuar pedido en curso</button>
		</form>

		<!-- 				<input type="button" class="btn btn-default" name="crearPedido" value="Crear pedido nuevo o continuar pedido en curso" onClick="location.href = 'indexclientepedido' "/> -->

	</c:if> <c:if test="${empty user}">
		<p>Algo esta fallando.</p>
	</c:if>
	<hr>
	</article> </section>
</body>
<%-- <%@ include file="footer.html"%> --%>
</html>