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
<title>Perfil de Cliente</title>
</head>
<%@ include file="cabecera.jsp"%>
<body>
	<c:if test="${tipoUsuario=='Hostelero'}">
		<section id="nav"> <%@ include
			file="navegacion_hostelero.jsp"%> </section>
	</c:if>
	<c:if test="${tipoUsuario=='Cliente'}">
		<section id="nav"> <%@ include file="navegacion_cliente.jsp"%>
		</section>
	</c:if>
	<c:if test="${tipoUsuario=='Admin'}">
		<section id="nav"> <%@ include file="navegacion_admin.jsp"%>
		</section>
	</c:if>

	<section id="contenido"
		style="	text-align:left;
									font-size: 25px;					"> <c:if
		test="${not empty user}">
		<label> Hola <c:out value="${nombre}"></c:out> <c:out
				value="${apellido1}"></c:out> <c:out value="${apellido2}"></c:out>
		</label>
		<p>Esta es la página principal de tu perfil</p>
		<p>
			Tipo de usuario:<strong>
			<c:out value="${tipoUsuario}"></c:out></strong>
			<c:if test="${tipoUsuario=='Hostelero'}">
				<p id="establecimiento">
					Nombre de Establecimiento:
					<c:out value="${establecimiento}"></c:out>
				</p>
			</c:if>
	</c:if> </section>
</body>
<%-- <%@ include file="footer.html"%> --%>
</html>