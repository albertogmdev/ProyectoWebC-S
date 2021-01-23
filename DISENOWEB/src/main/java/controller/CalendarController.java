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
import util.ConsultaBd;
import util.Log;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Usuario;

/**
 *
 * @author Alberto
 */
public class CalendarController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
     */
    
    private ConsultaBd consulta = new ConsultaBd();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CalendarController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CalendarController at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        String accion = request.getParameter("action");
          String siguientePagina = "";
        
        HttpSession sesion = request.getSession();
        
        Log.log.info("ACCION "+ accion);
        if(accion.equalsIgnoreCase("fichar")){
             String fecha1 = request.getParameter("hora_entrada");
             String fecha2 = request.getParameter("hora_salida");
             String idProyecto=request.getParameter("idProyecto");
             
             String fecha_1=fecha1.substring(0, 10);
             
             
             String hora_entrada=fecha1.substring(11)+":00";
             String hora_salida=fecha2.substring(11)+":00";
             Usuario usuario=(Usuario) sesion.getAttribute("usuarioSesion");
             
             
            try {
                boolean hecho=consulta.ficharEmpleado(Date.valueOf(fecha_1), Time.valueOf(hora_entrada), Time.valueOf(hora_salida),usuario.getEmail(), Integer.parseInt(idProyecto));
            if(hecho){
                siguientePagina="inicioUser.jsp";
                sesion.setAttribute("mensaje", "Operación realizada con éxito.");
            }
            else{
                 siguientePagina="fichar.jsp";
                 sesion.setAttribute("mensaje", "ERROR: No se ha podido realizar la operación");
            }
            
            
            } catch (ParseException ex) {
                Logger.getLogger(CalendarController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           
            
            
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
