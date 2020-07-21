<!--
Autor: Raquel Más García
Contenido: vista para mostrar las opciones que puede realizar el usuario con la base de datos.
			Informa si se han realizado correctamente las operaciones realizadas.
 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Men&uacute;</title>
<link rel="stylesheet" href="css/reset.css" />
<link rel="stylesheet" href="css/general.css" />
<style type="text/css">
p {
	text-shadow: 1px 1px 0 rgba(255, 255, 255, 0.7);
	font-size: 18px;
	color: dimgray;
	font-family: Arial;
}

h1 {
	position: relative;
	top: 30px;
	left: 155px;
}

.boton {
	margin-left: 150px;
}
</style>
<script type="text/javascript">
	document.addEventListener('readystatechange', inicializar, false);
	function inicializar() {
		if (document.readyState == 'complete') {
			var altDisp = (screen.availHeight - 550) / 2;
			var ancDisp = (screen.availWidth - 550) / 2;
			document.getElementsByTagName('div')[0].style.top = altDisp + "px";
			document.getElementsByTagName('div')[0].style.left = ancDisp + "px";
		}
	}
	function enviar(op) {
		document.getElementById('opcion').value = op;
		document.form1.submit();
	}
</script>
</head>
<body>
	<div>
		<h1>
			<span id="emp">EMP</span><span id="resa">RESA</span>
		</h1>
		<br /><br />
		<c:set var="txt" value="${sessionScope.respuesta}" />
		<c:if test="${empty txt}">
			<br /><br />
		</c:if>
		<c:if test="${not empty txt}">
			<br />
			<b><p>
					<c:out value="${txt}" />
				</p></b>
		</c:if>
		<form action="EmpleServlet" method="post" name="form1">
			<p>Escoja una opci&oacute;n:</p>
			<input type="button" class="boton" value="Dar de alta empleado"
				onclick="enviar('insertar')" /> <br /> <input type="button"
				class="boton" value="Dar de baja empleado"
				onclick="enviar('borrar')" /> <br /> <input type="button"
				class="boton" value="Modificar datos empleado"
				onclick="enviar('modificar')" /> <br /> <input type="button"
				class="boton" value="Listar empleados" onclick="enviar('listar')" /><br />
			<input type="hidden" name="opcion" id="opcion" value="" />
		</form>
		<form action="EmpleServlet" method="post">
			<input type="submit" class="boton" value="Salir" />
		</form>
	</div>
</body>
</html>