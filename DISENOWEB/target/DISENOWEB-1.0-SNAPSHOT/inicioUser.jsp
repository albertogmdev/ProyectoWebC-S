<%-- 
    Document   : inicioUser
    Created on : 9 ene. 2021, 9:52:25
    Author     : MARINA
--%>

<%@page import="util.Log"%>
<%@page import="logica.Solicitud"%>
<%@page import="java.util.List"%>
<%@page import="util.ConsultaBd"%>
<%@page import="logica.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title> Inicio - Empleado</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/estilos.css">
    </head>  
    <%
        HttpSession sesion = request.getSession();
        //Si el usuario no tiene una sesion el redirige
        if(sesion.getAttribute("usuarioSesion") == null){
            response.sendRedirect("./index.jsp");
        }
        //Solo puede acceder un empleado de RRHH, si lo intenta un empleado de una empresa le 
        //redirige a la pagina de inicio de sesion
        else{
            String nombre = sesion.getAttribute("usuarioSesion").getClass().getSimpleName();
            if(nombre.equalsIgnoreCase("Empleado")){
                response.sendRedirect("./inicioRRHH.jsp");
            }
        
    %>
    <%
        String mensajeAlerta = "";
        Object objetoAlerta = sesion.getAttribute("mensaje");
        if(objetoAlerta != null){
            mensajeAlerta = objetoAlerta.toString();
            sesion.setAttribute("mensaje", null);
        }
    %>
    <script>
        var mensaje = "<%=mensajeAlerta%>";
        if(mensaje.length !== 0){
            alert(mensaje);
        }
    </script>
    <%
        ConsultaBd consulta = new ConsultaBd();
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioSesion");
        List<Solicitud> listaSolicitudes = consulta.getSolicitudesUsuario(usuario.getEmail());
        
        String mensajeAprobada = "";
        String mensajeDenegada = "";
        
        for(int i=0; i<listaSolicitudes.size(); i++){
            Solicitud solicitud = listaSolicitudes.get(i);
            
            //De las solicitudes tramitadas vemos cuales están aprobadas o denegadas 
            if(solicitud.isAprobado()){
                mensajeAprobada += "   - Solicitud dia libre fecha["+ solicitud.getFechaInicio() +"] x";
            }
            else{
                mensajeDenegada += "   - Solicitud dia libre fecha["+ solicitud.getFechaInicio() +"] x";
            }
        }
    %>
    <script>
        var alertaAprobada = "<%=mensajeAprobada%>";
        var alertaDenegada = "<%=mensajeDenegada%>";
        
        var alertaFinal = "";
        
        if(alertaAprobada.length !== 0){
            alertaFinal += "SOLICITUDES APROBADAS: \n"+ alertaAprobada.replaceAll('x', '\n');
        }
        if(alertaDenegada.length !== 0){
            alertaFinal += "SOLICITUDES DENEGADAS: \n"+ alertaDenegada.replaceAll('x', '\n');
        }
        
        if(alertaFinal.length !== 0){
            alert(alertaFinal);
        }
    </script>
    <body style="height: 1500px; padding-top: 5rem;">
        <div id="navUser-placeholder"></div>
        <script>
        $(function(){
            $("#navUser-placeholder").load("navbarUser.jsp");
        });
        </script>
        <!--end of Navigation bar-->
        <div class="container" style="padding-top: 155px"">
            <h1>Bienvenido al sistema de gestión de empleados. </h1> 
            <h2>No olvide introducir el número de horas de su jornada </h2>
            <br><br>

            <a href="./fichar.jsp"><button type="button" class="btn btn-danger text-right" style=" font-size: 30px; height:60px">Introducir Jornada</button></a>
        </div>
    </body>
    <% }%>
</html>
