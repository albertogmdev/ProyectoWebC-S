<%-- 
    Document   : aprobarSolicitud
    Created on : 12 ene. 2021, 10:46:01
    Author     : MARINA
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Solicitudes</title>
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
            if(nombre.equalsIgnoreCase("Usuario")){
                response.sendRedirect("./inicioUser.jsp");
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
    <body style="height: 1500px; padding-top: 5rem;">
        <div id="nav-placeholder"></div>
        <script>
        $(function(){
            $("#nav-placeholder").load("navbarRRHH.jsp");
        });
        </script>
        <!--Fin de navbar-->
        <div class="container" style="padding-top: 155px">
            <div class="shadow p-3 mb-5 bg-white rounded">
                <p>00324 - Carlos Martinez</p>
                <p>23/01/2021 - 23/01/2021</p>
                <p>Motivo: Enfermedad de familiar</p>

                <div class="d-flex flex-row-reverse">
                    <button type="button" class="btn btn-danger btn-circle btn-sm" style="margin:5px;">Rechazar</button> 
                    <button type="button" class="btn btn-success btn-circle btn-sm" style="margin:5px;">Aprobar</button> 
                </div>
            </div>
            <div class="shadow p-3 mb-5 bg-white rounded">
                <p>00024 - Macarena Garcia</p>
                <p>23/01/2021 - 29/01/2021</p>
                <p>Vacaciones</p>
                <div class="d-flex flex-row-reverse">
                    <button type="button" class="btn btn-danger btn-circle btn-sm" style="margin:5px;">Rechazar</button> 
                    <button type="button" class="btn btn-success btn-circle btn-sm" style="margin:5px;">Aprobar</button> 
                </div>
            </div>
        </div>
    </body>
    <% }%>
</html>
