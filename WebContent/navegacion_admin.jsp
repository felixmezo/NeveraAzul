<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="/css/navegacion_style.css">
</head>
<body>

	<script type="text/javascript">
		function enviar_formulario() {
			document.formulario1.submit()
		}
	</script>
	<div id="nav_izda">
		<div class="titulonav">Mi Perfil</div>

		<div class="cuerporec">
			<ul>
				<li><a href="/portadaUsuario">Portada</a></li>
				<li><a href="/adminSuscripciones">Suscripciones</a>
			</ul>
		</div>
	</div>
</body>
</html>