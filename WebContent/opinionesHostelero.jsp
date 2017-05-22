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
<title>Opiniones del hostelero <c:out
		value="${nombreEstablecimineto}" />
</title>
</head>
<%@ include file="cabecera.jsp"%>

<body>
	<section id="nav"> <%@ include
		file="navegacion_hostelero.jsp"%> </section>
	<section id="contenido"> <article> <c:if
		test="${not empty user}">
		<label> Hola <c:out value="${nombreEstablecimiento}"></c:out>,
			<label> <strong>las opiniones que te han dejado
					hasta el momento son:</strong></label>
		</label>

		<c:if test="${ not empty opiniones }">

			<div id="tabla0" class="panel panel-danger">
				<div class="panel-heading">Opiniones de los usuarios</div>
				<table class="table">
					<tr>
						<th id="cabecera">Usuario</th>
						<th id="cabecera">Título</th>
						<th id="cabecera">Descripción</th>
						<th id="cabecera">Puntuación</th>
					</tr>
					<c:forEach items="${opiniones}" var="opinion">
						<tr>
							<td><c:out value="${opinion.emailCliente}" /></td>
							<td><c:out value="${opinion.titulo}" /></td>
							<td><c:out value="${opinion.descripcion}" /></td>
							<td><c:out value="${opinion.puntuacion}" /></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</c:if>
	</c:if> <c:if test="${ empty opiniones }">
		<p>
			<strong>No hay ninguna opinión registrada por el momento</strong>
		</p>
	</c:if> </article>  </section>
</body>
<%-- <%@ include file="footer.html"%> --%>
</html>