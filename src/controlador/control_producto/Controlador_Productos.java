package controlador.control_producto;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import DAO.ProductoDAO;
import DAO.UsuarioDAO;
import modelo.Producto;
import modelo.Usuario;

/**
 * Servlet implementation class Controlador_Productos1
 */
@WebServlet("/Controlador_Productos")
public class Controlador_Productos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(lookup="jdbc/MVC_JSP")
	private DataSource miPool;   
	
	private ProductoDAO productoDAO;
   
    public Controlador_Productos() {
       
    }

    //método desde el cual arranca el servlet
	public void init(ServletConfig config) throws ServletException {
		
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recibo los parámetros del formulario
		try {
			productoDAO =new ProductoDAO(miPool);
		}catch (Exception e){
			throw new ServletException();
		}

		request.setCharacterEncoding("UTF-8");
		
		String comando = request.getParameter("instruccion");
		
		if(comando == null)
			comando="listar";
		
		switch(comando) {
		case "listar":
			listarProductos(request, response);
			break;
			
		case "cargaParaActualizar":
			cargarProductos(request, response);
			break;
		
		case "actualizar":
			actualizarProductos(request, response);
			break;
		
		case "elimina":
			eliminarProductos(request, response);
			break;
			
		default:
			listarProductos(request, response);
		}
	}
	
	private void eliminarProductos(HttpServletRequest request, HttpServletResponse response) {
		//lee la información del producto que viene del formulario
		String codProd = request.getParameter("codProd");
		System.out.println(codProd);
		
		//borra
		productoDAO.delete(codProd);
		listarProductos(request, response);
	}
	
	private void cargarProductos(HttpServletRequest request, HttpServletResponse response) {
		//lee la información del producto que viene del formulario, crea un objeto de tipo Producto
		String codProd=request.getParameter("codProd");
		System.out.println(codProd);
		
		//enviar el producto al modelo
		Producto producto = productoDAO.read(codProd);
		System.out.println(producto);
		
		//colocar atributo correspondiente al codProd
		request.setAttribute("productoActualizar", producto);
		
		try {
			request.getRequestDispatcher("vistaProducto/actualizaProducto.jsp").forward(request, response);
		}catch(ServletException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		//volver al listado de productos
		listarProductos(request, response);
	}
	
	private void listarProductos(HttpServletRequest request, HttpServletResponse response) {
		productoDAO = new ProductoDAO(miPool);
		List<Producto> productos = productoDAO.readAll();
		//lo guardo en una sesión para mostrarlo en una lista
		
		//lo guardo en request mejor (porque siempre vamos actuaqlizando la lista)
		request.setAttribute("productos", productos);
		//despacho al jsp
		//enviar el producto al modelo
				
		try {
			request.getRequestDispatcher("vistaProducto/mostrarTodos.jsp").forward(request, response);
		}catch(ServletException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		//volver al listado de productos
		listarProductos(request, response);
	}
	
	private void insertarProductos(HttpServletRequest request, HttpServletResponse response) {
		//lee la información del producto que viene del formulario, crea un objeto de tipo Producto
		String codProd=request.getParameter("codProd");
		String seccion=request.getParameter("seccion");
		String nombreProd=request.getParameter("nombreProd");
		Double precio=Double.parseDouble(request.getParameter("precio"));
		//cuidado con errores de formato o con no pasarle una cadena para el precio
		
		String sFecha = request.getParameter("fecha");
		System.out.println(sFecha);
		//convertirlo a java.time.LocalDate
		LocalDate fechaLocalDate = LocalDate.parse(sFecha, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		Boolean importado = Boolean.valueOf(request.getParameter("importado"));
		System.out.println(importado);
		
		String pais = request.getParameter("pais");
		
		Producto producto = new Producto(codProd, seccion, nombreProd, precio, fechaLocalDate, importado, pais);
		
		try {
			this.productoDAO.create(producto);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		//volver al listado de productos
		listarProductos(request, response);
	}
	
	private void actualizarProductos(HttpServletRequest request, HttpServletResponse response) {
		//lee la información del producto que viene del formulario, crea un objeto de tipo Producto
		String codProd=request.getParameter("codProd");
		String seccion=request.getParameter("seccion");
		String nombreProd=request.getParameter("nombreProd");
		Double precio=Double.parseDouble(request.getParameter("precio"));
		//cuidado con errores de formato o con no pasarle una cadena para el precio
		
		String sFecha = request.getParameter("fecha");
		System.out.println(sFecha);
		//convertirlo a java.time.LocalDate
		LocalDate fechaLocalDate = null; 
		
		try {
			fechaLocalDate = LocalDate.parse(sFecha, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}catch(DateTimeParseException e) {
			System.out.println("Error en el formato de fecha");
		}
		
		Boolean importado = Boolean.valueOf(request.getParameter("importado"));
		System.out.println(importado);
		
		String pais = request.getParameter("pais");
		
		//crea un objeto producto. Con el producto actualizado.
		Producto productoActualizado = new Producto(codProd, seccion, nombreProd, precio, fechaLocalDate, importado, pais);
		System.out.println(productoActualizado);
		
		if(productoDAO.update(productoActualizado)) {
			listarProductos(request, response);
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
