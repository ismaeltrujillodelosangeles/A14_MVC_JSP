<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mostrar Productos</title>
</head>
<body>
	<h2>Listado de productos</h2>
	<table border=1>
		<tr>
			<td class="cabecera">codProd</td>
			<td class="cabecera">seccion</td>
			<td class="cabecera">nombreProd</td>
			<td class="cabecera">precio</td>
			<td class="cabecera">fecha</td>
			<td class="cabecera">importado</td>
			<td class="cabecera">pais</td>
		</tr>
		
		<c:forEach var="elem" items="${requestScope.productos}">
			<tr>
				<td class="filas">${elem.codProd}</td>
				<td class="filas">${elem.seccion}</td>
				<td class="filas">${elem.nombreProd}</td>
				<td class="filas">${elem.precio}</td>
				<td class="filas">${elem.fecha}</td>
				<td class="filas">${elem.importado}</td>
				<td class="filas">${elem.pais}</td>
			</tr>
		</c:forEach>
	</table>
	<div id="contenedorBoton">
		<input type="button" value="Insertar Registro" onclick="window.location.href='vistaProducto/insertaProducto2'">
	</div>
</body>
</html>