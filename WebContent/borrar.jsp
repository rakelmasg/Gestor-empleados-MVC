<!--
Autor: Raquel Más García
Contenido: vista para dar de baja un empleado en la base de datos
 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="modelo.Empleado"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dar de baja empleado</title>
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
				<legend>Dar de baja empleado</legend>
				<c:set var="ids" value="${sessionScope.ids}" />
				<c:if test="${empty ids }">
					<p>
						Error al buscar en la base de datos <br />Pulse cancelar para
						volver al menú
					</p>
				</c:if>
				<c:if test="${not empty ids}">
				ID empleado*:
					<select name="idempleado">
						<c:forEach items="${ids}" var="id">
							<option value='${id}'>${id}</option>
						</c:forEach>
					</select>
					<br />
					<input type="hidden" name="comando"
						value="ComandoBorrar">
					<br />
					<input class="boton" id="accionB" type="submit" value="Dar de baja">
				</c:if>
			</fieldset>
		</form>
		<form action="EmpleServlet" method="post">
			<input type="hidden" name="opcion" value="menu" /> <input
				class="boton" id="cancelarB" type="submit" value="Cancelar" />
		</form>

	</div>
</body>
</html>
