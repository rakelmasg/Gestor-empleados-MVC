<!--
Autor: Raquel Más García
Contenido: vista que muestra los empleados buscados en la base de datos
 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="modelo.Empleado"%>
<%@page import="modelo.Categoria"%>
<%@page import="modelo.Departamento"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista empleados</title>
<link rel="stylesheet" href="css/reset.css" />
<style type="text/css">
html {
	background: no-repeat linear-gradient(bottom, white, #708090);
	background: no-repeat -moz-linear-gradient(bottom, white, #708090);
	background: no-repeat -o-linear-gradient(bottom, white, #708090);
	background: no-repeat -webkit-linear-gradient(bottom, white, #708090);
	background: no-repeat -o-linear-gradient(bottom, white, #708090);
}

p {
	text-shadow: 1px 1px 0 rgba(255, 255, 255, 0.7);
	font-size: 18px;
	color: dimgray;
	font-family: Arial;
}

#emp {
	text-shadow: 1px 1px 0 rgba(255, 255, 255, 0.7), -1px -1px 0
		rgba(255, 255, 255, 0.7), 1px -1px 0 rgba(255, 255, 255, 0.7), -1px
		1px 0 rgba(255, 255, 255, 0.7), -9px -7px 3px rgba(122, 122, 122, 0.6);
	color: navy;
}

#resa {
	text-shadow: 1px 1px 0 rgba(255, 255, 255, 0.7), -1px -1px 0
		rgba(255, 255, 255, 0.7), 1px -1px 0 rgba(255, 255, 255, 0.7), -1px
		1px 0 rgba(255, 255, 255, 0.7), 9px -7px 3px rgba(122, 122, 122, 0.6);
	color: cornflowerblue;
}

.boton {
	border: 1px;
	border-style: outset;
	border-color: grey;
	border-radius: 20px;
	padding: 5px;
	padding-left: 10px;
	padding-right: 10px;
	font-size: 1.3em;
	font-family: Arial;
	color: rgb(115, 115, 115);
	background-image: url("../img/boton.png");
	text-shadow: 1px -1px 0 rgba(122, 122, 122, 0.6);
	border-style: outset;
	box-shadow: 3px 3px 4px rgba(122, 122, 122, 0.5);
}

h1 {
	font-size: 48px;
	font-family: 'Croissant One';
	letter-spacing: -3px;
	margin: 40px;
	margin-left: 150px;
}

table {
	border: 1px solid #5e5e5e;
	font-size: 18px;
	border-radius: 20px;
}

th {
	background-color: #5e5e5e;
	font-size: 18px;
	color: white;
	padding: 10px;
}

td {
	background-color: #D0D0D0;
	font-size: 18px;
	padding: 10px;
}

caption {
	text-shadow: 1px 1px 0 rgba(255, 255, 255, 0.7);
	font-size: 24px;
	font-family: Arial;
	color: white;
	margin-bottom: 10px;
}
</style>
</head>
<body>
	<div>
		<h1>
			<span id="emp">EMP</span><span id="resa">RESA</span>
		</h1>
		<center>
			<c:set var="empleados" value="${sessionScope.respuesta}" />
			<c:if test="${empty empleados || empleados==null}">
				<p>Error al buscar en la base de datos</p>
			</c:if>
			<c:if test="${not empty empleados}">
				<table border="1">
					<caption>
						<b>Resultado B&uacute;squeda</b>
					</caption>
					<tr>
						<th>ID</th>
						<th>Nombre</th>
						<th>Categor&iacute;a</th>
						<th>Departamento</th>
						<th>Fecha Ingreso</th>
						<th>Fecha Baja</th>
					</tr>
					<c:forEach items="${empleados}" var="empleado">
						<tr>
							<td><c:out value="${empleado.id}" /></td>
							<td>${empleado.nombre }</td>
							<td>${empleado.catego.nombre }</td>
							<td>${empleado.depto.nombre }</td>
							<td>${empleado.fecing }</td>
							<td>${empleado.fecbaj }</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
			<c:remove var="respuesta" scope="session"/>
			<br />
			<form action="EmpleServlet" method="post">
				<input type="hidden" name="opcion" value="menu" /> <input
					type="submit" class="boton" value="Volver" />
			</form>
		</center>
	</div>
</body>
</html>