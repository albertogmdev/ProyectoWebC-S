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
import logica.Usuario;
import util.ConsultaBd;

/**
 *
 * @author ccris
 */
@WebServlet(name = "Prueba", urlPatterns = {"/Prueba"})
public class Prueba extends HttpServlet {
    String mostrarEmpleados="mostrarEmpleados.jsp";
    String mostrarEmpresas="mostrarEmpresas.jsp";
    String mostrarProyectos="mostrarProyectos.jsp";
    String verDarAlta="darAlta.jsp";
    String verDarBaja="darBaja.jsp"; 

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
            out.println("<title>Servlet Prueba</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Prueba at " + request.getContextPath() + "</h1>");
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
        String acceso="";
        String op=request.getParameter("accion");
        if(op.equalsIgnoreCase("mostrarEmpleados")){
            acceso=mostrarEmpleados;
        }
        else if(op.equalsIgnoreCase("mostrarEmpresas")){
            acceso=mostrarEmpresas;
            
        }
        else if(op.equalsIgnoreCase("mostrarProyectos")){
            acceso=mostrarProyectos;
        }
        else if(op.equalsIgnoreCase("agregar")){
            acceso=verDarAlta;
            
        }
        
        else if(op.equalsIgnoreCase("eliminar")){
            acceso=verDarBaja;
        }
         else if(op.equalsIgnoreCase("Borrar")){
            acceso=verDarAlta;
        }
        else if(op.equalsIgnoreCase("Cancelar")){
            acceso=verDarBaja;
        }
        RequestDispatcher vista=request.getRequestDispatcher(acceso);
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
         String acceso="";
        String op=request.getParameter("accion");
        if(op.equalsIgnoreCase("Enviar")){
            Usuario u=new Usuario();
            ConsultaBd b=new ConsultaBd();
            u=b.generarId(u);
            
            
            

            String nombre=request.getParameter("tnombre");
            String apellidos=request.getParameter("tapellidos");
            String telefono=request.getParameter("ttelefono");
            String correo=request.getParameter("tcorreo");
            String pass=request.getParameter("tpassword");
         
            u.setNombre(nombre);
            u.setApellidos(apellidos);
            u.setTelefono(Integer.parseInt(telefono));
            u.setEmail(correo);
            u.setContrasenna(pass);
            boolean agregar=b.darAlta(u);
           
            if(agregar==false){///si el usuario ya existe tendria q salir un mensaje de error (cambiar)
                acceso=mostrarProyectos;
                
            
            }
            else{ acceso=mostrarEmpleados;}
           
            
                   
            
        }
       
        else if(op.equalsIgnoreCase("Eliminar")){
          //  Usuario u = new Usuario();
             ConsultaBd b=new ConsultaBd();
             int id=Integer.parseInt(request.getParameter("txtdni"));
             
             boolean baja=b.darBaja(id);
             if(baja==true){
                 
                  acceso=mostrarEmpleados;
                 
             }
             else{///mostrar ventana (cambiar)
                 acceso=mostrarProyectos;
                 
             }
            
           
            
        }
       
        
         RequestDispatcher vista=request.getRequestDispatcher(acceso);
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
