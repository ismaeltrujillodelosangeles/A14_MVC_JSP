package controlador.control_usuario;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import DAO.UsuarioDAO;
import modelo.Usuario;

/**
 * Servlet implementation class Controlador_InsertaUsuario
 */
@WebServlet("/Controlador_InsertaUsuario")
public class Controlador_InsertaUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(lookup="jdbc/MVC_JSP")
	private DataSource miPool;   
	
	private UsuarioDAO usuarioDAO;
   
    public Controlador_InsertaUsuario() {
       
    }

    //método desde el cual arranca el servlet
	public void init(ServletConfig config) throws ServletException {
		
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recibo los parámetros del formulario
		try {
			usuarioDAO =new UsuarioDAO(miPool);
		}catch (Exception e){
			throw new ServletException();
		}
		request.setCharacterEncoding("UTF-8");
		String nombre=request.getParameter("nombre");
		String apellidos=request.getParameter("apellidos");
		String usuario=request.getParameter("usuario");
		String contrasena=request.getParameter("contrasena");
		String pais=request.getParameter("pais");
		String tecno=request.getParameter("tecnologia");
		
		if (nombre!="" && apellidos!="" && usuario!="" && contrasena!="") {
			
			Usuario u=new Usuario(nombre,apellidos,usuario,contrasena,pais,tecno);
			try {
				if (usuarioDAO.create(u))
						request.getRequestDispatcher("vistaUsuario/exito.jsp").forward(request, response);
			} catch (SQLException e) {
				request.getRequestDispatcher("vistaUsuario/error.jsp").forward(request, response);
			}
		}	
		else
			request.getRequestDispatcher("vistaUsuario/error.jsp").forward(request, response);
			
		
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
