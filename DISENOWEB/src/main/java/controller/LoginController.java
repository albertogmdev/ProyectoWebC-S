
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Empleado;
import logica.Empresa;
import logica.Usuario;
import util.Log;
import util.ConsultaBd;

/**
 *
 * @author Alberto
 */
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String LOGIN_USUARIO = "/login.jsp"; //PAGINA PRINCIPAL USUARIO
    private static final String LOGIN_EMPLEADO = "/index.jsp"; //PAGINA PRINCIPAL EMPLEADO
    private static final String ERROR = "/index.jsp"; //CUANDO NO SE PUEDE LOGEAR VOLVEMOS A LA MISMA PAGINA
                                                     //podriamos poner la misma pag pero con un mensaje de error
    private ConsultaBd consulta = new ConsultaBd();
    private Log log;
    
    public LoginController(){
        super();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UsuarioController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UsuarioController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("usuarios")) {
            Usuario usuario = consulta.getUsuario("alicianuñez@correo.com");
            request.setAttribute("usuario", usuario);
        }
        
        RequestDispatcher view = request.getRequestDispatcher("./newjsp.jsp");
        view.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String siguientePagina = "";
        String action = request.getParameter("action");
        if(action.equalsIgnoreCase("login")){
            Log.log.info("LoginController DoPost - parámetro["+ action +"]");
        
            String usuarioLogin = request.getParameter("usuario");
            String contrasennaLogin = request.getParameter("password");

            String tipo = consulta.getTipoUsuario(usuarioLogin, contrasennaLogin);
            System.out.println("TIPO USUARIO - "+ tipo);

            if(tipo.equalsIgnoreCase("empleado")){
                siguientePagina = LOGIN_EMPLEADO;
                //Creamos el empleado
                Empleado empleado = consulta.getEmpleado(usuarioLogin);
                request.setAttribute("usuario", empleado);//No se si sirve para guardar al empleado en las demas pags
                Log.log.info("Empleado va a iniciar sesion - usuario["+ usuarioLogin +"]");
                Log.log.info("INFO USUARIO - "+ empleado.toString());
            }else if(tipo.equalsIgnoreCase("usuario")){
                siguientePagina = LOGIN_USUARIO;
                //Creamos el usuario
                Usuario usuario = consulta.getUsuario(usuarioLogin);
                request.setAttribute("usuario", usuario); //No se si sirve para guardar al usuario en las demas pags
                Log.log.info("Usuario va a iniciar sesion - usuario["+ usuarioLogin +"]");
                Log.log.info("INFO USUARIO - "+ usuario.toString());
            }else{
                siguientePagina = ERROR;
                Log.log.error("ERROR: Usuario no encontrado en la base de datos");
                //¿PONER ALGO MAS PARA CONTROLAR EL ERROR O MOSTRAR EL ERROR AL USUARIO?
        }
        
        
        }
        //Mirar la forma de seguir con el objeto creado de empledo o usuario
        RequestDispatcher view = request.getRequestDispatcher(siguientePagina);
        view.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
