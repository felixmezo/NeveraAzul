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

<title>Suscripcciones</title>

</head>
<%@ include file="cabecera.jsp"%>

<body>
	<section id="nav"><%@ include file="navegacion_cliente.jsp"%></section>
	<section id="contenido">
	<label>Estas son las suscripciones disponibles actualmente en La Nevera Azul:</label>
	
	<div id="tabla0" class="panel panel-success">
		<div class="panel-heading">SUSCRIPCIONES</div>
		<div class="panel-body">
			<p>Suscripciones de fidelización y descuentos.</p>
		</div>
		<table class="table">
			<tr>
				<th>Nombre</th>
				<th>Descripcion</th>
				<th>Condición</th>
			</tr>
			<tr>
				<td>Suscripción de Fidelización</td>
				<td>El sexto pedido (inferior a 30 €) le sale gratis</td>
				<td>Lleva <c:out value="${numeroPedidos}"></c:out> pedidos</td>
			</tr>
			<c:if test="${descuento ne '0'}">
				<td>Promoción de descuento</td>
				<td>Descuento de <c:out value="${descuento}"></c:out> % en todos los pedidos</td>
				<td>NO</td>
			</c:if>
		</table>
	</div>
	</section>
</body>
</body>
<%-- <%@ include file="footer.html"%> --%>
</html>