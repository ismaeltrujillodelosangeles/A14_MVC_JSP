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
			<c:url var="linkTemp" value="Controlador_Productos">
				<c:param name="instruccion" value="cargaParaActualizar"></c:param>
				<c:param name="codProducto" value="${elem.cosProd}"></c:param>
			</c:url>
			
			<c:url var="linkTempEliminar" value="Controlador_Productos">
				<c:param name="instruccion" value="eliminar"></c:param>
				<c:param name="codProducto" value="${elem.cosProd}"></c:param>			
			</c:url>
			
			
			<tr>
				<td class="filas">${elem.codProd}</td>
				<td class="filas">${elem.seccion}</td>
				<td class="filas">${elem.nombreProd}</td>
				<td class="filas">${elem.precio}</td>
				<td class="filas">${elem.fecha}</td>
				<td class="filas">${elem.importado}</td>
				<td class="filas">${elem.pais}</td>
				<td class="filas">
					<a href="${linkTemp}">Actualiza</a> &nbsp; &nbsp;
					<a href="${linkTempElimina}">Elimina</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div id="contenedorBoton">
		<input type="button" value="Insertar Registro" onclick="window.location.href='vistaProducto'">
	</div>
</body>
</html>