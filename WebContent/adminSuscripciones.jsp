<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Administrar Suscripciones</title>
<link rel="stylesheet" type="text/css" href="/css/bootstrap.css" />
</head>
<%@ include file="cabecera.jsp"%>
<body onload="onload();">

	<script type="text/javascript">
		function comprobarActivar(){
            var s = document.getElementById("descuento");
            if (s.value == 0){
            	alert("No puedes activar un descuento del 0 %. Si quieres, desactiva el actual.");
            	s.value= "";
            }
		}
        function ponerCeroDescuento() {
            var s = document.getElementById("descuento");
            s.value = 0;        
        }
	</script>
		
	<section id="nav"> <%@ include file="navegacion_admin.jsp"%>
	</section>
	<section id="contenido"> <article>
	<h1>PÁGINA DE ADMINISTRACIÓN DE SUSCRIPCIONES</h1>
	<p>
		Hola Admin, bienvenido a la página de gestión de suscripciones de LA NEVERA AZUL.
	</p>
	<BR>
	<hr>
	<c:if test="${descuento == 0}">
		<p>LA NEVERA AZUL no tiene ningun descuento ACTIVO, crea uno con el
			siguiente formulario.</p>
	</c:if> 
	<c:if test="${descuento ne 0}">
		<p>
			LA NEVERA AZUL tiene un descuento actual del <strong>
			<c:out value="${descuento}"></c:out>
			%</strong> en TODOS LOS PEDIDOS.</p>
			<br>
			<p> Puedes desactivar el descuento actual o activar uno diferente con el siguiente botón.</p>
			
	</c:if> <c:out value="${descuentoDao}"></c:out>

	<form role="form" action="/adminSuscripciones" method="post"
		acceptcharset="utf-8">
		<label>Porcentaje de Descuento:</label><br>
		<div class="input-group" style="width:20%; margin: auto;">
			<span class="input-group-addon">%</span> <input type="number"
				name="descuento" required id="descuento" maxLength="3" required
				size="20" placeholder="Descuento" class="form-control" >
		</div>
		<br>
		<button type="submit" class="btn btn-default" name="Activar" onclick="comprobarActivar();"
			value="Activar">Activar</button>
		<button type="submit" class="btn btn-default" name="Desactivar" onclick="ponerCeroDescuento();"
			value="Desactivar">Desactivar</button>
	</form></section>
</body>

<%-- <%@ include file="footer.html"%> --%>


</html>