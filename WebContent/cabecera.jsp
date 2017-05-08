<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<html lang="es">
<head>
	<meta charset="utf-8">
    <link rel="stylesheet" href="/css/cabecera_style.css">
</head>
 
<body>
	<header id="main-header">
		<a id="logo-header" href="/">
			<img src="/images/logo_nevera.png"
				 width="55" 
				 height="55"
				 >		
		</a> 
		<nav>
			<ul>
				<li class="no_rojo"><a href="/">Inicio</a></li>
				<li class="no_rojo"><a href="/contacto">Contacto</a></li>
				<li class="rojo_sesion">
					<a href="<c:url value="${url}"/>">
					<c:out value="${urlLinktext}" />
					</a>
				</li>
			</ul>
		</nav>
	</header>	
</body>
</html>