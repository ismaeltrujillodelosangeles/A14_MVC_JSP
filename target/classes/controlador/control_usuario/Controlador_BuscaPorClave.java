package controlador.control_usuario;

import java.io.IOException;

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
@WebServlet("/Controlador_BuscaPorClave")
public class Controlador_BuscaPorClave extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(lookup="jdbc/MVC_JSP")
	private DataSource miPool;   
	
	private UsuarioDAO usuarioDAO;
   
    public Controlador_BuscaPorClave() {
       
    }

    //método desde el cual arranca el servlet
	public void init(ServletConfig config) throws ServletException {
		
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recibo los parámetros del formulario
		try {
			usuarioDAO =new UsuarioDAO(miPool);
			Long id = Long.parseLong(request.getParameter("id_usuario"));
			Usuario u = usuarioDAO.read(id);
			System.out.println("El id es: " + id + "  " + u);
			
			if(u!=null) {
				request.setAttribute("usuarioBuscado", u);
				request.getRequestDispatcher("vistaUsuario/mostrarUsuario.jsp").forward(request, response);
			}else
				request.getRequestDispatcher("vistaUsuario/error.jsp").forward(request, response);
		}catch(NumberFormatException e1) {
			request.getRequestDispatcher("vistaUsuario/error.jsp").forward(request, response);			
		}catch (Exception e){
			throw new ServletException();
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
