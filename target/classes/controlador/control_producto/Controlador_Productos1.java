package controlador.control_producto;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

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
@WebServlet("/Controlador_Productos1")
public class Controlador_Productos1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(lookup="jdbc/MVC_JSP")
	private DataSource miPool;   
	
	private ProductoDAO productoDAO;
   
    public Controlador_Productos1() {
       
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
		String codigo=request.getParameter("codProd");
		String seccion=request.getParameter("seccion");
		String nombreProd=request.getParameter("nombreProd");
		Double precio=Double.parseDouble(request.getParameter("precio"));
		LocalDate fecha=LocalDate.parse(request.getParameter("fecha"));
		Boolean importado=Boolean.valueOf(request.getParameter("importado"));
		String pais=request.getParameter("pais");
		
		if (codigo!="" && seccion!="" && nombreProd!="" && precio!=0) {
			
			Producto prod = new Producto(codigo,seccion,nombreProd,precio,fecha,importado,pais);
			try {
				if (productoDAO.create(prod))
						request.getRequestDispatcher("vistaProducto/exito.jsp").forward(request, response);
			} catch (SQLException e) {
				request.getRequestDispatcher("vistaProducto/error.jsp").forward(request, response);
			}
		}	
		else
			request.getRequestDispatcher("vistaProducto/error.jsp").forward(request, response);
			
		
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
