<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-Transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es">
	<head> 
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title>Index</title>
	</head>
	<body>
		<h1 align="center">Login del usuario</h1>
		
		<form action="../Controlador_LoginUsuario" method="post">
			<table align="left">
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
					<td><input type="submit" name="login" value="login"/></td>
				</tr>		
			</table>
		</form>
	</body>
</html>