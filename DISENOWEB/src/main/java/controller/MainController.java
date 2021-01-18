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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Empresa;
import logica.Proyecto;
import logica.Usuario;
import util.ConsultaBd;
import util.Log;

/**
 *
 * @author Alberto
 */
public class MainController extends HttpServlet {
    private static final String MOSTRAR_EMPLEADOS = "mostrarEmpleados.jsp";
    private static final String EDITAR_EMPLEADO = "editarUsuario.jsp";
    private static final String DAR_ALTA = "darAlta.jsp";
    private static final String DAR_BAJA = "darBaja.jsp"; 
    private static final String MOSTRAR_EMPRESAS = "mostrarEmpresas.jsp";
    private static final String EDITAR_EMPRESA = "editarEmpresa.jsp";
    private static final String ANADIR_EMPRESA = "anadirEmpresa.jsp";
    private static final String ELIMINAR_EMPRESA = "eliminarEmpresa.jsp";
    private static final String MOSTRAR_PROYECTOS = "mostrarProyectos.jsp";
    private static final String EDITAR_PROYECTO = "editarProyecto.jsp";
    private static final String ANADIR_PROYECTO = "anadirProyecto.jsp";
    private static final String ELIMINAR_PROYECTO = "eliminarProyecto.jsp";
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
        
        HttpSession sesion = request.getSession();
        
        Log.log.info("ACCION "+ accion);
        
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
            
            if(agregar){
                sesion.setAttribute("mensaje", "Usuario "+ usuario.getEmail() +" dado de alta correctamente");
                siguientePagina=MOSTRAR_EMPLEADOS;
            }
            else{
                sesion.setAttribute("mensaje", "ERROR: No se pudo dar de alta al usuario "+ usuario.getEmail());
                siguientePagina=DAR_ALTA;
            }
        }
        else if(accion.equalsIgnoreCase("bajaEmpleado")){
            String correo = request.getParameter("correo");
            boolean baja=consulta.darBaja(correo);
            
            if(baja){
                sesion.setAttribute("mensaje", "Usuario "+ correo +" dado de baja correctamente");
            }
            else{
                sesion.setAttribute("mensaje", "ERROR: No se pudo dar de baja al usuario "+ correo);
            }
            
            siguientePagina=MOSTRAR_EMPLEADOS;
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
                sesion.setAttribute("mensaje", "Empresa "+ idEmpresa +"-"+ nombre +" modificada con éxito");
            }else{
                siguientePagina = EDITAR_EMPRESA;
                sesion.setAttribute("mensaje", "ERROR: No se pudo modificar la empresa "+ idEmpresa +"-"+ nombre);
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
                sesion.setAttribute("mensaje", "Usuario "+ correo +" modificado con exito.");
            }else{
                siguientePagina = EDITAR_EMPLEADO;
                sesion.setAttribute("mensaje", "ERROR: No se ha podido modificar al usuario "+ correo);
            }
        }
        else if(accion.equalsIgnoreCase("editarProyecto")){
            int idProyecto = Integer.parseInt(request.getParameter("idProyecto"));
            int idEmpresa = Integer.parseInt(request.getParameter("idEmpresa"));
            
            Log.log.info("Proyecto seleccionado id("+ idEmpresa +")");
            
            boolean exito = consulta.modificarProyecto(idProyecto, idEmpresa);
            
            if(exito){
                siguientePagina = MOSTRAR_PROYECTOS;
                sesion.setAttribute("mensaje", "Proyecto "+ idProyecto +" modificado con éxito.");
            }else{
                siguientePagina = EDITAR_PROYECTO;
                sesion.setAttribute("mensaje", "ERROR: No se ha podido modificar el proyecto "+ idProyecto);
            }
        }
        else if(accion.equalsIgnoreCase("elegirUsuario")){
            int id = Integer.parseInt(request.getParameter("empleado"));
            Usuario usuario = consulta.getUsuarioById(id);
            String boton = request.getParameter("boton");
            
            Log.log.info("Boton seleccionado nombre("+ boton +")");            
            
            if(boton.equalsIgnoreCase("editar")){
                siguientePagina = EDITAR_EMPLEADO;
            }
            else if(boton.equalsIgnoreCase("eliminar")){
                siguientePagina = DAR_BAJA;
            }

            request.setAttribute("usuario", usuario);
            Log.log.info("USUARIO ELEGIDO - "+ usuario.getNombre());
        }
        else if(accion.equalsIgnoreCase("elegirProyecto")){
            String boton = request.getParameter("boton");
            
            Log.log.info("Boton seleccionado nombre("+ boton +")"); 
            
            if(boton.equalsIgnoreCase("anadir")){
                request.setAttribute("empresas", consulta.mostrarEmpresa());
                siguientePagina = ANADIR_PROYECTO;
            }
            else{
                int idProyecto = Integer.parseInt(request.getParameter("proyecto"));
                Proyecto proyecto = consulta.getProyecto(idProyecto);
                if(boton.equalsIgnoreCase("editar")){
                    siguientePagina = EDITAR_PROYECTO;
                    request.setAttribute("empresas", consulta.mostrarEmpresa());
                }
                else if(boton.equalsIgnoreCase("eliminar")){
                    siguientePagina = ELIMINAR_PROYECTO;
                }
                else{
                    sesion.setAttribute("mensaje", "ERROR: Formulario no enviado correctamente accion-"+ boton +" no reconocida");
                    siguientePagina = MOSTRAR_PROYECTOS;
                }

                request.setAttribute("proyecto", proyecto);
                Log.log.info("PROYECTO ELEGIDO - "+ proyecto.getIdProyecto());
            }
        }
        else if(accion.equalsIgnoreCase("elegirEmpresa")){
            int idEmpresa = Integer.parseInt(request.getParameter("empresa"));
            Empresa empresa = consulta.getEmpresa(idEmpresa);
            String boton = request.getParameter("boton");
            
            Log.log.info("Boton seleccionado nombre("+ boton +")"); 
            
            if(boton.equalsIgnoreCase("editar")){
                siguientePagina = EDITAR_EMPRESA;
            }
            else if(boton.equalsIgnoreCase("eliminar")){
                siguientePagina = ELIMINAR_EMPRESA;
            }
            else{
                sesion.setAttribute("mensaje", "ERROR: Formulario no enviado correctamente accion-"+ boton +" no reconocida");
                siguientePagina = MOSTRAR_EMPRESAS;
            }
            
            request.setAttribute("empresa", empresa);
            Log.log.info("EMPRESA ELEGIDA - "+ empresa.getNombre());
        }
        else if(accion.equalsIgnoreCase("anadirProyecto")){
            int idProyecto = Integer.parseInt(request.getParameter("idProyecto"));
            int idEmpresa = Integer.parseInt(request.getParameter("idEmpresa"));
            
            boolean exito = consulta.anadirProyecto(idProyecto, idEmpresa);
            
            if(exito){
                sesion.setAttribute("mensaje", "Proyecto "+ idProyecto +" añadido con éxito.");
            }else{
                sesion.setAttribute("mensaje", "ERROR: No se ha podido añadir el proyecto "+ idProyecto);
            }
            
            siguientePagina = MOSTRAR_PROYECTOS;
        }
        else if(accion.equalsIgnoreCase("anadirEmpresa")){
            Empresa empresa = new Empresa();
            int idEmpresa = Integer.parseInt(request.getParameter("idEmpresa"));
            String nombre = request.getParameter("nombre");
            empresa.setIdEmpresa(idEmpresa);
            empresa.setNombre(nombre);
            empresa.setDireccion(request.getParameter("calle"));
            empresa.setCodigoPostal(Integer.parseInt(request.getParameter("codigo")));
            empresa.setCorreo(request.getParameter("correo"));
            empresa.setTelefono(Integer.parseInt(request.getParameter("telefono")));            
            
            boolean exito = consulta.anadirEmpresa(empresa);
            
            if(exito){
                siguientePagina = MOSTRAR_EMPRESAS;
                sesion.setAttribute("mensaje", "Empresa "+ idEmpresa +"-"+ nombre +" añadida con éxito");
            }else{
                siguientePagina = ANADIR_EMPRESA;
                sesion.setAttribute("mensaje", "ERROR: No se pudo modificar la empresa "+ idEmpresa +"-"+ nombre);
            }
        }
        else if(accion.equalsIgnoreCase("eliminarProyecto")){
            int idProyecto = Integer.parseInt(request.getParameter("idProyecto"));
            
            boolean borrado = consulta.borrarProyecto(idProyecto);
            
            if(borrado){
                sesion.setAttribute("mensaje", "Proyecto "+ idProyecto +" eliminado con éxito.");                
            }else{
                sesion.setAttribute("mensaje", "ERROR: No se ha podido eliminar el proyecto "+ idProyecto);
            }
            
            siguientePagina = MOSTRAR_PROYECTOS;
        }
        else if(accion.equalsIgnoreCase("eliminarEmpresa")){
            int idEmpresa = Integer.parseInt(request.getParameter("idEmpresa"));
            
            boolean borrado = consulta.borrarEmpresa(idEmpresa);
            
            if(borrado){
                sesion.setAttribute("mensaje", "Empresa "+ idEmpresa +" eliminada con éxito.");                
            }else{
                sesion.setAttribute("mensaje", "ERROR: No se ha podido eliminar la empresa "+ idEmpresa);
            }
            
            siguientePagina = MOSTRAR_EMPRESAS;
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
