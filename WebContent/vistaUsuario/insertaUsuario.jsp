<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-Transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es">
	<head> 
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title>Insertar Usuario</title>
	</head>
	<body>
		<h1 align="center">Registro del usuario</h1>
		
		<form action="../Controlador_InsertaUsuario" method="post">
			<table align="left">
				<tr>
					<td>Nombre:</td>
					<td><input type="text" name="nombre"/></td>
				</tr>
				<tr>
					<td>Apellidos:</td>
					<td><input type="text" name="apellidos"/></td>
				</tr>
				<tr>
					<td>Usuario:</td>
					<td><input type="text" name="usuario"/></td>
				</tr>
				<tr>
					<td>Contraseña:</td>
					<td><input type="text" name="contrasena"/></td>
				</tr>
				<tr>
					<td>Pais:</td>
					<td><input type="text" name="pais"/></td>
				</tr>
				<tr>
					<td>Tecnología:</td>
					<td><input type="text" name="tecnologia"/></td>
				</tr>	
				<tr>
					<td></td>
					<td><input type="submit" name="crear" value="Crear usuario"/></td>
				</tr>		
			</table>
		</form>
	</body>
</html>