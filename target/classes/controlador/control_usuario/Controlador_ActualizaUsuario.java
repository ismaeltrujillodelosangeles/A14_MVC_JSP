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
@WebServlet("/Controlador_ActualizaUsuario")
public class Controlador_ActualizaUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(lookup="jdbc/MVC_JSP")
	private DataSource miPool;   
	
	private UsuarioDAO usuarioDAO;
   
    public Controlador_ActualizaUsuario() {
       
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
		Usuario usuario = null;
		
		if("Buscar usuario".equals(request.getParameter("buscar"))) {
			buscaUsuario(usuario,request,response);
		}else if("Actualizar usuario".equals(request.getParameter("actualizar"))) {
			System.out.println("Entra en actualiza después de buscar");
			actualizaUsuario(usuario,request,response);
		}
		
		
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	//Buscar Usuario
	protected void buscaUsuario(Usuario usu, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String usuario = request.getParameter("usuario");
		String contrasena = request.getParameter("contrasena");
		
		//si los dos valores están vacíos, no entraría
		if(!usuario.isEmpty() && !contrasena.isEmpty()) {
			usu = usuarioDAO.login(usuario, contrasena);
			if(usu!=null) {
				//EN EL CONTROLADOR: GUARDAMOS EN LA SESION EL USUARIO ENCONTRADO
				request.getSession().setAttribute("usuarioEncontrado", usu);
				System.out.println(usu);
				request.getRequestDispatcher("vistaUsuario/actualizaUsuario.jsp").forward(request, response);
				System.out.println("detrás de la vista");
			}else
				request.getRequestDispatcher("vistaUsuario/error.jsp").forward(request, response);
		}else
			request.getRequestDispatcher("vistaUsuario/error.jsp").forward(request, response);

	}
	
	//Actualizar Usuario
		protected void actualizaUsuario(Usuario usu, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			usu=(Usuario)request.getSession().getAttribute("usuarioEncontrado");
			
			String usuario = request.getParameter("usuario");
			String contrasena = request.getParameter("contrasena");
			
			if(usu!=null) {
				Usuario usu2 = new Usuario(Long.parseLong(request.getParameter("id_usuarioEnc")),
						request.getParameter("nombreEnc"),
						request.getParameter("apellidosEnc"),
						request.getParameter("usuarioEnc"),
						request.getParameter("contrasenaEnc"),
						request.getParameter("paisEnc"),
						request.getParameter("tecnologiaEnc"));
				request.getSession().setAttribute("usuarioEncontrado", new Usuario());
				if(usuarioDAO.update(usu2)) {
					request.getRequestDispatcher("vistaUsuario/exito.jsp").forward(request, response);;
				}else
					request.getRequestDispatcher("vistaUsuario/error.jsp").forward(request, response);
			}else
				request.getRequestDispatcher("vistaUsuario/error.jsp").forward(request, response);

		}

}
