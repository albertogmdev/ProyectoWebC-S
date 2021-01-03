
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
import logica.ProyectoEmpleado;
import logica.Usuario;
import util.Log;
import util.ConsultaBd;

/**
 *
 * @author Alberto
 */
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String LOGIN_USUARIO = "/.jsp"; //PAGINA PRINCIPAL USUARIO
    private static final String LOGIN_EMPLEADO = "/.jsp"; //PAGINA PRINCIPAL EMPLEADO
    private static final String ERROR = "/index.jsp"; //CUANDO NO SE PUEDE LOGEAR VOLVEMOS A LA MISMA PAGINA
                                                     //podriamos poner la misma pag pero con un mensaje de error
    private ConsultaBd consulta;
    private Log log;
    
    public LoginController(){
        super();
        consulta = new ConsultaBd();
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
        processRequest(request, response);
        
        String siguientePagina = "";
        String action = request.getParameter("action");
        
        Log.log.info("LoginController DoPost - parámetro["+ action +"]");
        
        String usuarioLogin = request.getParameter("usuario");
        String contrasennaLogin = request.getParameter("password");
        
        String tipo = "";
        //HACER FUNCION EN ConsultaBd que retorne una variable tipo
        //  - "empleado" si el usuario es empleado
        //  - "usuario" si el usuario es un usuario de la app
        //  - "error" si no se ha encontrado el usuario introducido
        //      (se podria hacer tambien con admin)
        if(tipo.equalsIgnoreCase("empleado")){
            siguientePagina = LOGIN_EMPLEADO;
            //Creamos el empleado
            Empleado empleado = new Empleado();
            empleado.setEmail(usuarioLogin);
            empleado.setContrasenna(contrasennaLogin);
            //PARA ESTOS DATOS HABRIA QUE HACER UNA CONSULTA A LA BD
            empleado.setIdEmpleado(999999999);
            empleado.setNombre("NOMBRE");
            empleado.setApellidos("APELLIDOS");
            empleado.setTelefono(1);
            //******************************************************
            request.setAttribute("usuario", empleado);//No se si sirve para guardar al empleado en las demas pags
            Log.log.info("Empleado va a iniciar sesion - usuario["+ usuarioLogin +"]");
        }else if(tipo.equalsIgnoreCase("usuario")){
            siguientePagina = LOGIN_USUARIO;
            //Creamos el usuario
            Usuario usuario = new Usuario();
            usuario.setEmail(usuarioLogin);
            usuario.setContrasenna(contrasennaLogin);
            //PARA ESTOS DATOS HABRIA QUE HACER UNA CONSULTA A LA BD
            usuario.setIdUsuario(999999999);
            usuario.setNombre("NOMBRE");
            usuario.setApellidos("APELLIDOS");
            Empresa empresa = new Empresa();
            usuario.setEmpresa(empresa);
            usuario.setTelefono(999999999);
            List<ProyectoEmpleado> proyectosList = new ArrayList<ProyectoEmpleado>();
            usuario.setProyectosList(proyectosList);
            //******************************************************
            request.setAttribute("usuario", usuario); //No se si sirve para guardar al usuario en las demas pags
            Log.log.info("Usuario va a iniciar sesion - usuario["+ usuarioLogin +"]");
        }else{
            siguientePagina = ERROR;
            Log.log.error("ERROR: Usuario no encontrado en la base de datos");
            //¿PONER ALGO MAS PARA CONTROLAR EL ERROR O MOSTRAR EL ERROR AL USUARIO?
        }
        
        //Mirar la forma de seguir con el objeto creado de empledo o usuario
        RequestDispatcher view = request.getRequestDispatcher(siguientePagina);
        view.forward(request, response);
        return;
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
