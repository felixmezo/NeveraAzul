
<%@page import="es.upm.dit.isst.neveraAzul.model.Producto"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/bootstrap.css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Perfil de Cliente</title>
</head>
<%@ include file="cabecera.jsp"%>
<body>
	<section id="nav"> 
		<%@ include file="navegacion_cliente.jsp"%>
	</section>
	<section id="contenido"> 
	<article> 
	<c:if test="${not empty user}">
		<label> Hola <c:out value="${nombreCliente}"></c:out> <c:out
				value="${apellido1}"></c:out> <c:out value="${apellido2}"></c:out>
		</label>
		<br>
		<c:if test="${not empty productosPedido}">
			<div id="tabla0">
				<div class="panel panel-danger">
					<div class="panel-heading">CARRITO DE LA COMPRA</div>
					<div class="panel-body">
						<p>Estos son los productos que conforman tu pedido.</p>
					</div>
					<table class="table">
						<tr>
							<th>Nombre</th>
							<th>Descripcion</th>
							<th>Precio</th>
							<th>Establecimiento</th>
						</tr>
						<c:forEach items="${productosPedido}" var="producto">
							<tr>
								<td><c:out value="${producto.nombre}" /></td>
								<td><c:out value="${producto.descripcion}" /></td>
								<td><c:out value="${producto.precio}" /> euro(s)</td>
								<td><c:out value="${producto.establecimientoHostelero}" /></td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<form action="/indexclientepedido" method="post">
					<button type="submit" class="btn btn-default" name="finalizar"
						value="Finalizar Pedido">Finalizar Pedido</button>
				</form>
				<hr>
			</div>
		</c:if>
		<c:if test="${not empty todosProductos}">
			<label>Estos son los productos que hay ahora mismo publicados
				en la NEVERA AZUL.</label>
			<br>
			<label>Hay publicados un total de <c:out
					value="${fn:length(todosProductos)}" /> producto(s).
			</label>
			<br>
			<div id="tablas">
				<section id="tabla1">
				<div class="panel panel-success">
					<div class="panel-heading">PRODUCTOS EN OFERTA</div>
					<div class="panel-body">
						<p>Estos son los productos que los hosteleros han publicado a
							un precio reducido.</p>
					</div>
					<table class="table">
						<tr>
							<th>Nombre</th>
							<th>Descripcion</th>
							<th>Precio</th>
							<th>Establecimiento</th>
							<th>Click para Pedir</th>
						</tr>
						<c:forEach items="${todosProductos}" var="producto">
							<c:if test="${producto.enOferta == 'SI'}">
								<tr>
									<td><c:out value="${producto.nombre}" /></td>
									<td><c:out value="${producto.descripcion}" /></td>
									<td><c:out value="${producto.precio}" /> euro(s)</td>
									<td><c:out value="${producto.establecimientoHostelero}" /></td>
									<td>
										<form action="/indexclientepedido" method="post">
											<button type="submit" class="btn btn-default"
												name="idProducto" value="${producto.idProducto}">Pedir</button>
										</form>
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</table>
				</div>
				</section>
				<hr>
				<section id="tabla2">
				<div class="panel panel-default">
					<div class="panel-heading">PRODUCTOS SIN OFERTA</div>
					<div class="panel-body">
						<p>Estos son los productos que los hosteleros han publicado a
							su precio estándar.</p>
					</div>
					<table class="table">
						<tr>
							<th>Nombre</th>
							<th>Descripcion</th>
							<th>Precio</th>
							<th>Establecimiento</th>
							<th>Click para Pedir</th>
						</tr>
						<c:forEach items="${todosProductos}" var="producto">
							<c:if test="${producto.enOferta == 'NO'}">
								<tr>
									<td><c:out value="${producto.nombre}" /></td>
									<td><c:out value="${producto.descripcion}" /></td>
									<td><c:out value="${producto.precio}" /> euro(s)</td>
									<td><c:out value="${producto.establecimientoHostelero}" /></td>
									<td>
										<form action="/indexclientepedido" method="post">
											<button type="submit" class="btn btn-default"
												name="idProducto" value="${producto.idProducto}">Pedir</button>
										</form>
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</table>
				</div>
				</section>
			</div>
		</c:if>
		<c:if test="${empty todosProductos}">
			<p>No hay ningun producto publicado en la Nevera Azul.</p>
		</c:if>
	</c:if> <c:if test="${empty user}">
		<p>Algo esta fallando.</p>
	</c:if>
	<hr>
	</article> </section>
</body>
<%@ include file="footer.html"%>

</html>