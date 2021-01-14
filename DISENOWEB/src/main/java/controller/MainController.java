/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Empresa;
import logica.Proyecto;
import logica.Usuario;
import util.ConsultaBd;
import util.Log;

/**
 *
 * @author ccris
 */
//@WebServlet(name = "MainController", urlPatterns = {"/MainContoller"})
public class MainController extends HttpServlet {
    private static final String MOSTRAR_EMPLEADOS = "mostrarEmpleados.jsp";
    private static final String EDITAR_EMPLEADOS = "editarUsuario.jsp";
    private static final String MOSTRAR_EMPRESAS = "mostrarEmpresas.jsp";
    private static final String EDITAR_EMPRESA = "editarEmpresa.jsp";
    private static final String MOSTRAR_PROYECTOS = "mostrarProyectos.jsp";
    private static final String EDITAR_PROYECTOS = "editarProyecto.jsp";
    private static final String DAR_ALTA = "darAlta.jsp";
    private static final String DAR_BAJA = "darBaja.jsp"; 
    private static final String INICIO = "inicioRRHH.jsp";
    
    private ConsultaBd consulta = new ConsultaBd();
    
    public MainController(){
        super();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MainContoller</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MainContoller at " + request.getContextPath() + "</h1>");
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
        String siguientePagina="";
        String accion=request.getParameter("accion");
        if(accion.equalsIgnoreCase("mostrarEmpleados")){
            siguientePagina = MOSTRAR_EMPLEADOS;
        }
        else if(accion.equalsIgnoreCase("mostrarEmpresas")){
            siguientePagina = MOSTRAR_EMPRESAS;
        }
        else if(accion.equalsIgnoreCase("mostrarProyectos")){
            siguientePagina = MOSTRAR_PROYECTOS;
        }
        else if(accion.equalsIgnoreCase("baja")){
            siguientePagina = DAR_BAJA;
        }
        else if(accion.equalsIgnoreCase("alta")){
            siguientePagina = DAR_ALTA;
        }
        else if(accion.equalsIgnoreCase("inicio")){
            siguientePagina = INICIO;
        }
        
        RequestDispatcher vista=request.getRequestDispatcher(siguientePagina);
        vista.forward(request, response);
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
        String accion = request.getParameter("action");
        if(accion.equalsIgnoreCase("altaEmpleado")){
            Usuario usuario = new Usuario();
            usuario = consulta.generarId(usuario);
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            String telefono = request.getParameter("telefono");
            String correo = request.getParameter("correo");
            String pass = request.getParameter("password");
            usuario.setNombre(nombre);
            usuario.setApellidos(apellidos);
            usuario.setTelefono(Integer.parseInt(telefono));
            usuario.setEmail(correo);
            usuario.setContrasenna(pass);
            boolean agregar=consulta.darAlta(usuario);
           
            if(agregar==false){///si el usuario ya existe tendria q salir un mensaje de error (cambiar)
                boolean v=false;
                request.setAttribute("mensaje", v);
                siguientePagina=MOSTRAR_PROYECTOS;
            }
            else{
                boolean v=true;
                request.setAttribute("mensaje", v);
                siguientePagina=MOSTRAR_EMPLEADOS;
            }
        }
        else if(accion.equalsIgnoreCase("Eliminar")){
            //Usuario u = new Usuario();
            String correo = request.getParameter("correo");
            boolean baja=consulta.darBaja(correo);
            if(baja==true){
               siguientePagina=MOSTRAR_EMPLEADOS;
            }
            else{///mostrar ventana (cambiar)
               siguientePagina=MOSTRAR_PROYECTOS;
            }
        }
        else if(accion.equalsIgnoreCase("editarEmpresa")){
            int idEmpresa = Integer.parseInt(request.getParameter("idEmpresa"));
            String nombre = request.getParameter("nombre");
            String calle = request.getParameter("calle");
            int codigoPostal = Integer.parseInt(request.getParameter("codigo"));
            String correo = request.getParameter("correo");
            int telefono = Integer.parseInt(request.getParameter("telefono"));
            
            boolean exito = consulta.modificarEmpresa(idEmpresa, nombre, calle, codigoPostal, correo, telefono);
            
            if(exito){
                siguientePagina = MOSTRAR_EMPRESAS;
                request.setAttribute("mensaje", "Empresa "+ idEmpresa +" modificada con éxito");
            }else{
                siguientePagina = EDITAR_EMPRESA;
                request.setAttribute("mensaje", "ERROR: no se ha podido modificar la empresa");
            }
        }
        else if(accion.equalsIgnoreCase("editarEmpleado")){
            int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            int telefono = Integer.parseInt(request.getParameter("telefono"));
            String correo = request.getParameter("correo");
            String contrasenna = request.getParameter("password");            
            
            boolean exito = consulta.modificarUsuario(idUsuario, nombre, apellidos, telefono, correo, contrasenna);
            
            if(exito){
                siguientePagina = MOSTRAR_EMPLEADOS;
                request.setAttribute("mensaje", "Usuario "+ idUsuario +" modificado con éxito.");
            }else{
                siguientePagina = EDITAR_EMPLEADOS;
                request.setAttribute("mensaje", "ERROR: no se ha podido modificar al usuario.");
            }
        }
        else if(accion.equalsIgnoreCase("editarProyecto")){
            int idProyecto = Integer.parseInt(request.getParameter("idUsuario"));
            int idEmpresa = Integer.parseInt(request.getParameter("idEmpresa"));    
            
            boolean exito = consulta.modificarProyecto(idProyecto, idEmpresa);
            
            if(exito){
                siguientePagina = MOSTRAR_PROYECTOS;
                request.setAttribute("mensaje", "Proyecto "+ idProyecto +" modificado con éxito.");
            }else{
                siguientePagina = EDITAR_PROYECTOS;
                request.setAttribute("mensaje", "ERROR: no se ha podido modificar el proyecto.");
            }
        }
        else if(accion.equalsIgnoreCase("elegirUsuario")){
            int id = Integer.parseInt(request.getParameter("empleado"));
            //int id = 1;
            Usuario usuario = consulta.getUsuarioById(id);
            siguientePagina = "/editarUsuario.jsp";
            request.setAttribute("usuario", usuario);
            Log.log.info("INFO USUARIO - "+ usuario.getNombre());
            
        }
        else if(accion.equalsIgnoreCase("elegirProyecto")){
            int id = Integer.parseInt(request.getParameter("proyecto"));
            Proyecto proyecto = consulta.getProyecto(id);
            siguientePagina = "/editarProyecto.jsp";
            request.setAttribute("proyecto", proyecto);
            
        }else if(accion.equalsIgnoreCase("elegirEmpresa")){
            int id = Integer.parseInt(request.getParameter("empresa"));
            Empresa empresa = consulta.getEmpresa(id);
            siguientePagina = "/editarEmpresa.jsp";
            request.setAttribute("empresa", empresa);
            
        }
        
        
        RequestDispatcher vista=request.getRequestDispatcher(siguientePagina);
        vista.forward(request, response);
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
