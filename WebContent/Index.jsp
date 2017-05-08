<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/css/index.css" />
</head>
<%@ include file="cabecera.jsp" %>

<body>
	<div class="centro">
	</div>
	<div class="page-header">
	<h1 class="Nazul">BIENVENIDO A LA NEVERA AZUL</h1>
	</div>
	<div id="reconocidos">
	<c:if test="${not empty user}">
		<c:if test="${reconocido=='no_existe'}">
			<p class="rojo">El usuario que está intentando entrar no existe.</p>
			<p>
				Pulsa el botón para <a href="<c:url value="${url}"/>">Volver a Intentarlo</a>
			</p>
		</c:if>
		<c:if test="${reconocido=='hostelero'}">
			<p>Has sido reconocido como <strong>Hostelero</strong></p>
			<p>
				Has entrado como<strong>
				<c:out value="${user}"></c:out></strong>
			</p>
			<a href="/portadaUsuario">Acceder a mi perfil.</a>
<!-- 			<p> -->
<%-- 				Pulsa el botón para <a href="<c:url value="${url}"/>"><c:out --%>
<%-- 						value="${urlLinktext}" /></a> --%>
<!-- 			</p> -->
		</c:if>
		<c:if test="${reconocido=='cliente'}">
			<p>Has sido reconocido como <strong>Cliente</strong></p>
			<p>
				Has entrado como<strong>
				<c:out value="${user}"></c:out></strong>
			</p>
			<a href="/portadaUsuario">Acceder a mi perfil.</a>
<!-- 			<p> -->
<%-- 				Pulsa el botón para <a href="<c:url value="${url}"/>"><c:out --%>
<%-- 						value="${urlLinktext}" /></a> --%>
<!-- 			</p> -->
		</c:if>
	</c:if>
	</div>
	<div class="botones">
	<c:if test="${empty user}">
<!-- 		<p> -->
<%-- 			<a href="<c:url value="${url}"/>" class="boton_entrar"><c:out --%>
<%-- 					value="${urlLinktext}" /></a> --%>
<!-- 		</p> -->
		<input type="button" class="boton_entrar" value="¡Únete!" onClick="location.href = 'registro' "/>
	</c:if>
	</div>
</body>
	<%@ include file="footer.html" %>
</html>