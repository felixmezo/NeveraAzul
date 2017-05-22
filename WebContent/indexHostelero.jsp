<%@page import="es.upm.dit.isst.neveraAzul.model.Producto"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<link rel="stylesheet" type="text/css" href="/css/bootstrap.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Perfil de Hostelero</title>
</head>
<%@ include file="cabecera.jsp"%>
<body>
	<section id="nav"> <%@ include
		file="navegacion_hostelero.jsp"%> </section>
	<section id="contenido"> <article> <c:if
		test="${not empty user}">
		<c:if test="${not empty productosPublicados}">
			<div id="tabla0" class="panel panel-default">
				<div  class="panel-heading">Productos publicados en La Nevera
					Azul</div>
				<div class="panel-body">
					<p>
						<strong><c:out value="${user}"></c:out></strong>
					</p>
					<p>
						Establecimiento:
						<c:out value="${nombreEstablecimiento}"></c:out>
					</p>
					<p>
						Tienes publicados un total de
						<c:out value="${fn:length(productosPublicados)}" />
						productos(s).
					</p>
				</div>
				<table class="table">
					<tr>
						<th>Nombre</th>
						<th>Descripcion</th>
						<th>Precio</th>
						<th>Borrar</th>
						<th>En Oferta</th>
					</tr>
					<c:forEach items="${productosPublicados}" var="producto">
						<tr>
							<td><c:out value="${producto.nombre}" /></td>
							<td><c:out value="${producto.descripcion}" /></td>
							<td><c:out value="${producto.precio}" /> euro(s)</td>
							<td>
								<form action="/indexhostelero" method="post">
								<button type="submit" class="btn btn-default"
										name="idProducto" value="${producto.idProducto}">Borrar</button>
								</form>
							</td>
							<td><c:out value="${producto.enOferta}" /></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</c:if>


		<c:if test="${empty productosPublicados}">
			<p>
				Hola <strong><c:out value="${user}"></c:out></strong>
			</p>
			<p>
				Establecimiento:
				<c:out value="${nombreEstablecimiento}"></c:out>
			</p>
			<p>No tienes ningun producto publicado en la Nevera Azul.</p>
		</c:if>


	</c:if> <c:if test="${empty user}">
		<p>Algo esta fallando.</p>
	</c:if> <!-- 	<p>Pulsa el boton para <a href="/">Volver</a></p> --> <!-- 	<p>Pulsa el boton para <a href="/registroProducto">Publicar Nuevo Producto</a></p> -->
	<!-- 	<p>Pulsa el boton para <a href="/indexhostelero">Actualizar</a></p> -->
	</article> </section>
</body>
<%-- <%@ include file="footer.html"%> --%>
</html>