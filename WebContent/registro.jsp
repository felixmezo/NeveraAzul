<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<link rel="stylesheet" type="text/css" href="/css/bootstrap.css" />
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>PAGINA DE REGISTRO</title>
</head>

<%@ include file="cabecera.jsp" %>

<body onload="onload();">

		<script type="text/javascript">
		function onload() { 
			document.getElementById("establecimiento").style.display = "none";
			}
		
        function toggle(elemento) {
            if(elemento.value=="hostelero") {
                document.getElementById("establecimiento").style.display = "block";
             }else{
     			document.getElementById("establecimiento").style.display = "none";
              }
        }
		</script>

	<h1>PÁGINA DE REGISTRO</h1>
	<p>Formulario registro en LA NEVERA AZUL</p>

	<section id="anchura_registro">
	<form role="form" action="/registro" method="post" acceptcharset="utf-8">
		<div class="form-group">
			<label>Email</label>
				<input class="form-control" 
					   type="email" 
					   name="email"
					   id="email" 
					   maxLength="255"
					   size="20" 
					   required 
					   placeholder="email" />
		</div>
		<div class="form-group">
			<label>Nombre</label>
					<input class="form-control"
						   type="text"
						   name="nombre" 
						   id="nombre" 
						   maxLength="255" 
						   required 
						   size="20"
						   placeholder="Nombre" />
		</div>
		<div class="form-group">
			<label>Apellido 1</label> 
					<input class="form-control"
						   type="text" 
						   name="apellido1"
						   id="apellido1" 
						   maxLength="255" 
						   required
						   size="20"
						   placeholder="Apellido 1" />
		</div>
		<div class="form-group">
			<label>Apellido 2</label> 
					<input class="form-control"
						   type="text" 
						   name="apellido2"
						   id="apellido2" 
						   maxLength="255" 
						   required 
						   size="20"
						   placeholder="Apellido 2" />
		</div>
		<label>¿Cómo te quieres registrar?</label>
		<div class="input_group">	     
					<label><input 
							  type="radio" 
							  onclick="toggle(this);" 
							  name="modo" 
							  value="hostelero" 
							  id="radioHostelero"/>
							  Hostelero</label>
		</div>
		<div class="input_group">	     
					<label><input 
							  checked
							  onClick="toggle(this);"
							  type="radio" 
							  name="modo" 
							  value="cliente" 
							  id="radioCliente"/>
							  Cliente</label>
		</div>
		<div id="establecimiento" class="form-group">
			<label>Nombre de Establecimiento</label> 					
					<input 
						   class="form-control"
						   type="text" 
						   name="nombreEstablecimiento"
						   id="nombreEstablecimiento" 
						   maxLength="255"  
						   size="60"
						   placeholder="Nombre del Establecimiento" />
		</div>
			<button type="submit" class="btn btn-default" name="Enviar" value="Enviar">Enviar</button>
		</form>
		</section>
		<hr>
</body>

<%@ include file="footer.html" %>


</html>