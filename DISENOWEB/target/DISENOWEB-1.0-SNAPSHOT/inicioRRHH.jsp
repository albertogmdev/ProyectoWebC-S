<%-- 
    Document   : inicioRRHH
    Created on : 07-ene-2021, 12:39:32
    Author     : Marina
--%>

<%@page import="util.Log"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="util.ConsultaBd"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Inicio - RRHH</title>
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
        }
    %>
    <body style="height: 1500px; padding-top: 5rem;">
        <div id="nav-placeholder"></div>
        <script>
        $(function(){
            $("#nav-placeholder").load("navbarRRHH.jsp");
        });
        </script>
        <!--end of Navigation bar-->
        <div class="container" style="padding-top: 155px">
            <div class="row">
                <div class="col-md-4">
                    <h1 class="text-center">Empleados</h1>
                </div>
                <div class="col-md-4">
                    <h1 class="text-center">Proyectos</h1>
                </div>
                <div class="col-md-4">
                    <h1 class="text-center">Empresas</h1>
                </div>
            </div>
            <br><br><br>
            <div class="row">
                <div class="col-md-4 d-flex justify-content-center">
                    <a href="./mostrarEmpleados.jsp"><span class="fa fa-users" style="font-size:180px;color: #bd043b;text-align:center;"></span></a>
                </div>
                <div class="col-md-4 d-flex justify-content-center">
                    <a href="./mostrarProyectos.jsp"><span class="fa fa-paperclip" style="font-size:180px;color: #bd043b;text-align:center;"></span></a>
                </div>
                <div class="col-md-4 d-flex justify-content-center">
                   <a href="./mostrarEmpresas.jsp"><span class="fa fa-building" style="font-size:180px;color: #bd043b;text-align:center;"></span></a>
                </div>
            </div>
        </div>
    </body>
</html>
