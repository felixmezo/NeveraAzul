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
<title>Descripcion del Hostelero <c:out
		value="${nombreEstablecimineto}" />
</title>
</head>
<%@ include file="cabecera.jsp"%>

<body>
	<section id="nav"> <%@ include file="navegacion_cliente.jsp"%>
	</section>
	<section id="contenido"> <article> <c:if
		test="${not empty user}">
		<label> Hola <c:out value="${nombreCliente}"></c:out> <c:out
				value="${apellido1}"></c:out> , has selecionado el establecimiento <c:out
				value="${nombreEstablecimineto}" />
		</label>
		<br>
		<label>Esta regentado por: <c:out value="${nombreHostelero}" />
		</label>
		<br>
		<label>Con email: <c:out value="${emailHostelero}" />
		</label>
		<br>
		<br>
		<label> <strong>Estas son las OPINIONES que este
				establecimineto tiene hasta el momento </strong>
		</label>

		<c:if test="${ not empty opiniones }">

			<div class="panel panel-danger">
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
	</c:if>
	<hr>
	<BR>
	<form role="form" action="/crearOpinion" method="post"
		acceptcharset="utf-8">
		<div class="form-group">
			<label>Publica tu propia opinión a <c:out
					value="${nombreEstablecimineto}" />
				</button></label>
			<div class="input-group">
				<input class="form-control" type="text" name="titulo" id="titulo"
					maxLength="255" size="20" required placeholder="Titulo" />
			</div>
			<div class="input-group">
			<label>Puntúa del 1 al 5:</label>
				<select type="number" name="puntuacion" id="puntuacion"
					class="form-control">
					<option>1</option>
					<option>2</option>
					<option>3</option>
					<option>4</option>
					<option>5</option>
				</select>
			</div>
			<hr>
			<br>
			<div class="input_group">
				<textarea class="form-control" rows="3" cols="20" required
					name="descripcion" id="descripcion" placeholder="Descripcion">
					</textarea>
			</div>

			<button type="submit" class="btn btn-default" value="Opinar">Opinar</button>
	</form>

	</article>  </section>
</body>
<%-- <%@ include file="footer.html"%> --%>
</html>