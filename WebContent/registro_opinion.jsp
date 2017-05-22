<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/bootstrap.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registra tu opinion</title>
</head>

<%@ include file="cabecera.jsp"%>

<body onload="onload();">

	<h1>QUEREMOS QUE OPINES</h1>
	<p>Introduce tu opinion</p>

	<form role="form" action="/descripcionHostelero" method="post"
		acceptcharset="utf-8">
		<div class="form-group">
			<label>Titulo</label> <input class="form-control" type="text"
				name="titulo" id="titulo" maxLength="255" size="20" required
				placeholder="Titulo" />
		</div>
		<div class="input-group">
			<input type="text" name="puntuacion" id="puntuacion" maxLength="1"
				required size="20" placeholder="Puntúa del 1 al 5"
				class="form-control">
		</div>
		<hr>
		<br>
		<div class="input_group">
			<textarea class="form-control" rows="8" cols="50" required
				name="descripcion" id="descripcion" placeholder="Descripcion">
					</textarea>
		</div>
		<button type="submit" class="btn btn-default" name="Enviar"
			value="Enviar">Enviar</button>
	</form>
	</section>
	<hr>
</body>

<%-- <%@ include file="footer.html"%> --%>


</html>