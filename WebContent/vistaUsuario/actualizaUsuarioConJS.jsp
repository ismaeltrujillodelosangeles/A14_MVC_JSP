<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-Transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es">
	<head> 
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title>Index</title>
		<script src="${pageContext.request.contextPath}/resources/js/botonActualiza.js" type="text/javascript"></script>
	</head>
	<body>
		<h1>Teclee nombre y contraseña del usuario a modificar:</h1>
		
		<form action="${pageContext.request.contextPath}/Controlador_ActualizaUsuario" method="post">
			<table>
				<tr>
					<td>Usuario:</td>
					<td><input type="text" name="usuario"/></td>
				</tr>
				<tr>
					<td>Contraseña:</td>
					<td><input type="text" name="contrasena"/></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" name="buscar" value="Buscar usuario" id="busca"/></td>
				</tr>
				
				<c:set var="usu" value="${sessionScope.usuarioEncontrado}"></c:set>
				<c:out value="usu"></c:out>
				
				<tr>
					<td>usu</td>
				</tr>
				<tr>
					<td>Id_usuario:</td>
					<td><input type="text" name="id_usuarioEnc" value="${usu.id_usuario}"/></td>
				</tr>
				<tr>
					<td>Nombre:</td>
					<td><input type="text" name="nombreEnc" value="${usu.nombre}"/></td>
				</tr>
				<tr>
					<td>Apellidos:</td>
					<td><input type="text" name="apellidosEnc" value="${usu.apellidos}"/></td>
				</tr>
				<tr>
					<td>Usuario:</td>
					<td><input type="text" name="usuarioEnc" value="${usu.usuario}"/></td>
				</tr>
				<tr>
					<td>Contraseña:</td>
					<td><input type="text" name="contrasenaEnc" value="${usu.contrasena}"/></td>
				</tr>
				<tr>
					<td>Pais:</td>
					<td><input type="text" name="paisEnc" value="${usu.pais}"/></td>
				</tr>
				<tr>
					<td>Tecnología:</td>
					<td><input type="text" name="tecnologiaEnc" value="${usu.tecnologia}"/></td>
				</tr>	
				<tr>
					<td></td>
					<td><input type="submit" name="actualizarEnc" value="Actualizar usuario" id="actualiza"/></td>
					
				</tr>
				<tr><td><br /></td></tr>
				<tr>
					<td><a href="../tratarUsuarios.html">Ir al menú usuario</a></td>
				</tr>
			</table>
		</form>
		
	</body>
</html>