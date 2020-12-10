package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import modelo.Producto;

//conecta JavaBean  con la base de datos

public class ProductoDAO implements DAO<Producto>{

	// atributos_
		private static final String SQL_INSERT = "INSERT INTO PRODUCTOS (codprod,seccion,nombreprod,precio,fecha,importado,paisorigen)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
		private static final String SQL_DELETE = "DELETE FROM PRODUCTOS WHERE codprod= ?";
		private static final String SQL_UPDATE = "UPDATE PRODUCTOS SET codprod = ?, seccion = ?,"
				+ "nombreprod = ?, precio = ?, fecha= ? ,importado= ?, paisorigen= ? WHERE codprod = ?";
		private static final String SQL_READ = "SELECT * FROM PRODUCTOS WHERE codprod = ?";
		private static final String SQL_LOGIN = "SELECT * FROM PRODUCTOS WHERE nombreprod = ? and precio=?";
		private static final String SQL_READALL = "SELECT * FROM PRODUCTOS";

		

		private DataSource origenDatos;

		

		public ProductoDAO(DataSource origenDatos) {
			
			this.origenDatos = origenDatos;
		}

	
	@Override
	public boolean create(Producto prod) throws  SQLException{
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection con=null;
		// con es mi conexion
		
			try {
				con=origenDatos.getConnection();
				ps = con.prepareStatement(SQL_INSERT,Statement.RETURN_GENERATED_KEYS);
				
				ps.setString(1, prod.getCodProd());
				ps.setString(2, prod.getSeccion());
				ps.setString(3, prod.getNombreProd());
				ps.setDouble(4, prod.getPrecio());
				ps.setDate(5, Date.valueOf(prod.getFecha()));
				ps.setBoolean(6, prod.getImportado());
				ps.setString(7, prod.getPais());
				
				if (ps.executeUpdate() > 0){ //SI la sentencia tiene resultados
					rs=ps.getGeneratedKeys(); //Coge las claves y las guarda en el result set
					if(rs.next()){ //para recorrer los resultados
						prod.setCodProd(rs.getString(1)); //c es usuario, se le asigna las id a los usuarios
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
	public boolean delete(Object prod) {
		PreparedStatement ps=null;
		Connection con=null;// con es mi conexion
		try {
			con=origenDatos.getConnection();
			ps = con.prepareStatement(SQL_DELETE); // utilizo la
															// conexión
			ps.setString(1, prod.toString());
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
	public boolean update(Producto prod)  {
		PreparedStatement ps=null;
		Connection con=null;// con es mi conexion

		try {
			con=origenDatos.getConnection();
			ps = con.prepareStatement(SQL_UPDATE);
		
			
			ps.setString(1, prod.getCodProd());
			ps.setString(2, prod.getSeccion());
			ps.setString(3, prod.getNombreProd());
			ps.setDouble(4, prod.getPrecio());
			ps.setDate(5, Date.valueOf(prod.getFecha()));
			ps.setBoolean(6, prod.getImportado());
			ps.setString(7, prod.getPais());
			
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
	public Producto read(Object prod) {
		PreparedStatement ps;
		ResultSet rs;
		Connection con=null;
		Producto s=null;
		try {
			con=origenDatos.getConnection();
			ps = con.prepareStatement(SQL_READ);
			ps.setString(1,  prod.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				s = new Producto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDate(5).toLocalDate(), rs.getBoolean(6), rs.getString(7));
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
	public List<Producto> readAll() {
		Connection con=null;
		PreparedStatement ps;
		ResultSet rs;
		List<Producto> productos=new ArrayList<Producto>();
		try {
			con=origenDatos.getConnection();
			ps = con.prepareStatement(SQL_READALL);
			rs = ps.executeQuery();
			while (rs.next()) {
				productos.add( new Producto(rs.getString(1),rs.getString(2), 
						rs.getString(3), rs.getDouble(4), rs.getDate(5).toLocalDate(), rs.getBoolean(6), 
						rs.getString(7)));
			}
			return productos;
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
		return productos;
	}

	public Producto login(Object prod,Object c) {
		PreparedStatement ps;
		ResultSet rs;
		Connection con=null;
		Producto devuelve=null;
		
		try {
			con=origenDatos.getConnection();
			ps = con.prepareStatement(SQL_LOGIN);
			ps.setString(1, prod.toString());
			ps.setString(2, c.toString());
			rs = ps.executeQuery();
			if (rs.next()) {
				devuelve = new Producto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDate(5).toLocalDate(), rs.getBoolean(6), rs.getString(7));
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
