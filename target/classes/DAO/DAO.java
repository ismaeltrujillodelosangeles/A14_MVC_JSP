package DAO;

import java.sql.SQLException;
import java.util.List;

public interface DAO <T>{
	//se usa esta interface genérica para que todos los programadores empleen los mismos nombres de métodos
	//declaro los métodos que  se van a usar para acceder a la base de datos
	//CRUD   create-read-update-delete
	
	//inserta un nuevo registro c  de tipo genérico T
	public boolean create(T c) throws SQLException ;
	
	//borra un registro por su clave
	public boolean delete(Object key);
	
	//modifica un registro a c
	public boolean update(T c);
	
	//obtiene un registro por su clave
	public T read(Object key);
	
	//obtine todos los registros
	public List<T> readAll();
	
	
}
