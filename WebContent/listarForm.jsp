<!--
Autor: Raquel Más García
Contenido: vista que permite buscar empleados en la base de datos dinamicamente
 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="modelo.Empleado"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listar empleados</title>
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
<style type="text/css">
#formAcc {
	top: 40px;
}

#formCa {
	position: relative;
	top: 0px;
}

cancelarB {
	margin-top: -80px;
}
</style>
</head>
<body>
	<div>
		<h1>
			<span id="emp">EMP</span><span id="resa">RESA</span>
		</h1>
		<form id="formAcc" action="EmpleServlet" method="post">
			<fieldset>
				<legend>Listar empleado/s</legend>
				<c:set var="categos" value="${sessionScope.categorias}" />
				<c:set var="deptos" value="${sessionScope.departamentos}" />
				<c:if test="${empty categos }">
					<p>Error al buscar las categor&iacute;as</p>
				</c:if>
				<c:if test="${empty deptos }">
					<p>Error al buscar los departamentos</p>
				</c:if>
				<c:if test="${not empty deptos && not empty categos}">
				ID empleado: <input type="text" name="idempleado" />
					<br />
					<br />
			 Nombre: <input type="text" name="nombre" />
					<br />
					<br />
					 Categor&iacute;a:
					<select name="idcatego">
						<option value="0">Escoja una</option>
						<c:forEach items="${categos}" var="catego">
							<option value='${catego.id}'>${catego.nombre}</option>
						</c:forEach>
					</select>
					<br />
					<br />
					Departamento*:
					<select name="iddepto">
						<option value="0">Escoja uno</option>
						<c:forEach items="${deptos}" var="depto">
							<option value='<c:out value="depto.id"/>'>
								${depto.nombre}</option>
						</c:forEach>
					</select>
					<input type="hidden" id="comando" name="comando"
						value="ComandoListar">
					<br />
					<input type="submit" class="boton" id="accionB" value="Listar">
				</c:if>
			</fieldset>
		</form>
		<form id="formCa" action="EmpleServlet" method="post">
			<input type="hidden" name="opcion" value="menu" /> <input
				type="submit" class="boton" id="cancelarB" value="Cancelar" />
		</form>
	</div>
</body>
</html>

