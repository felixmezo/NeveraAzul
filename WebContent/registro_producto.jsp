<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/bootstrap.css" />
</head>
<%@ include file="cabecera.jsp"%>
<body>
	<section id="nav"> <%@ include
		file="navegacion_hostelero.jsp"%> </section>
	<section id="contenido"> <article>
	<h1>PÁGINA DE REGISTRO DE PRODUCTOS</h1>
	<p>
		Hola
		<c:out value="${user}"></c:out>
		, rellena el siguiente formulario para publicar productos en LA NEVERA
		AZUL.
	</p>
	<div id="tabla0">
	<form role="form" action="/registroProducto" method="post"
		acceptcharset="utf-8">
		<div class="form-group">
			<label>Nombre</label> <input class="form-control" type="text"
				name="nombre" required id="nombre" maxLength="255" size="20"
				required placeholder="Nombre" />
		</div>
		<div class="input-group">
			<span class="input-group-addon">€</span> <input type="number"
				name="precio" required id="precio" maxLength="6" required size="20"
				placeholder="Precio" class="form-control"
				aria-label="Amount (to the nearest dollar)"> <span
				class="input-group-addon">.00</span>
		</div>
		<hr>
		<br>
		<div class="input_group">
			<textarea class="form-control" rows="8" cols="50" required
				name="descripcion" id="descripcion" required
				placeholder="Descripcion">
					</textarea>
		</div>
		<label>En Oferta?</label>
		<div class="input_group">
			<label><input checked type="radio" name="enOferta" value="SI"
				selected="selected" id="radioSi" /> Si</label>
		</div>
		<div class="input_group">
			<label><input type="radio" name="enOferta" value="NO"
				id="radioNo" /> No</label>
		</div>

		<button type="submit" class="btn btn-default" name="Enviar"
			value="Enviar">Enviar</button>
	</form>
	</div>
	<hr>
	</article> </section>
</body>
<%-- <%@ include file="footer.html"%> --%>

</html>