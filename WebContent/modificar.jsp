<!--
Autor: Raquel Más García
Contenido: vista que permite modificar un empleado de la base de datos 
 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="modelo.Empleado"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modificar empleado</title>
<link rel="stylesheet" href="css/reset.css" />
<link rel="stylesheet" href="css/general.css" />
<link rel="stylesheet" href="css/formularios.css" />
<style type="css/txt">
h1{
position:relative;
top:30px;
left:125px;
}
</style>
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
				<legend>Modificar empleado</legend>
				<c:set var="ids" value="${sessionScope.ids}" />
				<c:set var="categos" value="${sessionScope.categorias}" />
				<c:set var="deptos" value="${sessionScope.departamentos}" />
				<c:if test="${empty ids }">
					<p>Error al buscar los empleados</p>
				</c:if>
				<c:if test="${empty categos }">
					<p>Error al buscar las categorías</p>
				</c:if>
				<c:if test="${empty deptos }">
					<p>Error al buscar los departamentos</p>
				</c:if>
				<c:if
					test="${not empty deptos && not empty categos && not empty ids}">
					ID empleado*:
					<select name="idempleado">
						<c:forEach items="${ids}" var="id">
							<option value='${id}'>${id}</option>
						</c:forEach>
					</select>
					<br /> Nombre: <input type="text" name="nombre" />
					<br />Categor&iacute;a:
					<select name="idcatego">
						<option value="0">Escoja una</option>
						<c:forEach items="${categos}" var="catego">
							<option value='${catego.id}'>${catego.nombre}</option>
						</c:forEach>
					</select>
					<br />
					<br /> Departamento: <select name="iddepto">
						<option value="0">Escoja uno</option>
						<c:forEach items="${deptos}" var="depto">
							<option value='${depto.id}'>${depto.nombre}</option>
						</c:forEach>
					</select>
					<br />
					<br />
					<input type="hidden" id="comando" name="comando"
						value="ComandoModificar">
					<input type="submit" class="boton" id="accionB" value="Modificar">
				</c:if>
			</fieldset>
		</form>
		<form action="EmpleServlet" method="post">
			<input type="hidden" name="opcion" value="menu" /> <input
				type="submit" class="boton" id="cancelarB" value="Cancelar" />
		</form>
	</div>
</body>
</html>