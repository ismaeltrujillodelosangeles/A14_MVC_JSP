package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import modelo.Usuario;

//conecta JavaBean  con la base de datos

public class UsuarioDAO implements DAO<Usuario>{

	// atributos_
		private static final String SQL_INSERT = "INSERT INTO USUARIOS (nombre,apellidos,usuario,contrasena,pais,tecnologia)"
				+ " VALUES (?, ?, ?, ?, ?, ?)";
		private static final String SQL_DELETE = "DELETE FROM USUARIOS WHERE id_usuario= ?";
		private static final String SQL_UPDATE = "UPDATE USUARIOS SET nombre = ?, apellidos = ?,"
				+ "usuario = ?, contrasena = ?, pais= ? ,tecnologia= ? WHERE id_usuario = ?";
		private static final String SQL_READ = "SELECT * FROM USUARIOS WHERE id_usuario = ?";
		private static final String SQL_LOGIN = "SELECT * FROM USUARIOS WHERE usuario = ? and contrasena=?";
		private static final String SQL_READALL = "SELECT * FROM USUARIOS";

		

		private DataSource origenDatos;

		

		public UsuarioDAO(DataSource origenDatos) {
			
			this.origenDatos = origenDatos;
		}

	
	@Override
	public boolean create(Usuario c) throws  SQLException{
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection con=null;
		// con es mi conexion
		
			try {
				con=origenDatos.getConnection();
				ps = con.prepareStatement(SQL_INSERT,Statement.RETURN_GENERATED_KEYS);
				
				ps.setString(1, c.getNombre());
				ps.setString(2, c.getApellidos());
				ps.setString(3, c.getUsuario());
				ps.setString(4, c.getContrasena());
				ps.setString(5, c.getPais());
				ps.setString(6, c.getTecnologia());
				
				if (ps.executeUpdate() > 0){ //SI la sentencia tiene resultados
					rs=ps.getGeneratedKeys(); //Coge las claves y las guarda en el result set
					if(rs.next()){ //para recorrer los resultados
						c.setId_usuario(rs.getLong(1)); //c es usuario, se le asigna las id a los usuarios
					}
					return true;
				}
					
			}  finally{
				try {
					con.close(); //cerramos la conexion
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
			return false;
	}

	@Override
	public boolean delete(Object usuario) {
		PreparedStatement ps=null;
		Connection con=null;// con es mi conexion
		try {
			con=origenDatos.getConnection();
			ps = con.prepareStatement(SQL_DELETE); // utilizo la
															// conexión
			ps.setString(1, usuario.toString());
			if (ps.executeUpdate() > 0)
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean update(Usuario c)  {
		PreparedStatement ps=null;
		Connection con=null;// con es mi conexion

		try {
			con=origenDatos.getConnection();
			ps = con.prepareStatement(SQL_UPDATE);
		
			
			ps.setString(1, c.getNombre());
			ps.setString(2, c.getApellidos());
			ps.setString(3, c.getUsuario());
			ps.setString(4, c.getContrasena());
			ps.setString(5, c.getPais());
			ps.setString(6, c.getTecnologia());
			ps.setLong(7, c.getId_usuario());
			
			if (ps.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;

	}

	@Override
	public Usuario read(Object usuario) {
		PreparedStatement ps;
		ResultSet rs;
		Connection con=null;
		Usuario s=null;
		try {
			con=origenDatos.getConnection();
			ps = con.prepareStatement(SQL_READ);
			ps.setString(1,  usuario.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				s = new Usuario(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
			}
			return s;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return s;
	}

	@Override
	public List<Usuario> readAll() {
		Connection con=null;
		PreparedStatement ps;
		ResultSet rs;
		List<Usuario> usuarios=new ArrayList<Usuario>();
		try {
			con=origenDatos.getConnection();
			ps = con.prepareStatement(SQL_READALL);
			rs = ps.executeQuery();
			while (rs.next()) {
				usuarios.add( new Usuario(rs.getLong(1),rs.getString(2), 
						rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), 
						rs.getString(7)));
			}
			return usuarios;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return usuarios;
	}

	public Usuario login(Object usuario,Object c) {
		PreparedStatement ps;
		ResultSet rs;
		Connection con=null;
		Usuario devuelve=null;
		
		try {
			con=origenDatos.getConnection();
			ps = con.prepareStatement(SQL_LOGIN);
			ps.setString(1, usuario.toString());
			ps.setString(2, c.toString());
			rs = ps.executeQuery();
			if (rs.next()) {
				devuelve = new Usuario(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
			}
		} catch (SQLException e) {
			System.out.println("error en Login de DAO");
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("error al cerrar en Login de DAO");
			}
		}
		return devuelve;
	}

	
	public DataSource getOrigenDatos() {
		return origenDatos;
	}

}
