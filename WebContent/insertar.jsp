<!--
Autor: Raquel Más García
Contenido: vista para dar de alta un empleado en la base de datos
 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="modelo.Empleado"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dar de alta empleado</title>
<link rel="stylesheet" href="css/reset.css" />
<link rel="stylesheet" href="css/general.css" />
<link rel="stylesheet" href="css/formularios.css" />
<script>
	document.addEventListener('readystatechange', inicializar, false);
	function inicializar() {
		if (document.readyState == 'complete') {
			var altDisp = (screen.availHeight - 550) / 2;
			var ancDisp = (screen.availWidth - 550) / 2;
			document.getElementsByTagName('div')[0].style.top = altDisp + "px";
			document.getElementsByTagName('div')[0].style.left = ancDisp + "px";
		}
	}
</script>
</head>
<body>
	<div>
		<h1>
			<span id="emp">EMP</span><span id="resa">RESA</span>
		</h1>
		<form id="formAcc" action="EmpleServlet" method="post">
			<fieldset>
				<legend>Dar de alta empleado</legend>
				<c:set var="categos" value="${sessionScope.categorias}" />
				<c:if test="${empty categos }">
					<p>Error al buscar las categorías</p>
				</c:if>
				<c:set var="deptos" value="${sessionScope.departamentos}" />
				<c:if test="${empty deptos }">
					<p>Error al buscar los departamentos</p>
				</c:if>
				<c:if test="${not empty deptos && not empty categos}">
				Nombre*: <input type="text" name="nombre" />
					<br />
					<br />
				Categor&iacute;a*:
						<select name="idcatego">
						<c:forEach items="${categos}" var="catego">
							<option value="${catego.id}">${catego.nombre}</option>
						</c:forEach>
					</select>
					<br />
					<br />
					Departamento*:
						<select name="iddepto">
						<c:forEach items="${deptos}" var="depto">
							<option value="${depto.id}">${depto.nombre}</option>
						</c:forEach>
					</select>
					<br />
					<input type="hidden" name="comando" value="ComandoInsertar" />
					<input type="submit" id="accionB" class="boton" value="Dar de alta">
				</c:if>
			</fieldset>
		</form>
		<form action="EmpleServlet" method="post">
			<input type="hidden" name="opcion" value="menu" /> <input
				id="cancelarB" type="submit" class="boton" value="Cancelar" />
		</form>
	</div>
</body>
</html>
