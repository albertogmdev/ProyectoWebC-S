
<%@page import="logica.Empleado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>NavBar Empleado</title>
    </head>
    <body>
        <%
            Empleado empleado = (Empleado) request.getSession().getAttribute("usuarioSesion");
            String nombre = empleado.getNombre() + " " + empleado.getApellidos();
        %>
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
            <a class="navbar-brand">Gestión de personal</a>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="inicioRRHH.jsp">Inicio</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Peticiones</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                      Gestionar empleados
                    </a>
                    <div class="dropdown-menu dropdown-menu-right">
                      <a class="dropdown-item" href="darAlta.jsp">Dar de alta</a>
                      <a class="dropdown-item" href="darBaja.jsp">Dar de baja</a>
                    </div>
                </li>
            </ul>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                      Solicitar Informe
                    </a>
                    <div class="dropdown-menu dropdown-menu-right">
                      <a class="dropdown-item" href="#">Empresa</a>
                      <a class="dropdown-item" href="#">Proyecto</a>
                      <a class="dropdown-item" href="#">Empleado</a>
                    </div>
                </li>
                <li class="nav-item">
                    Bienvenid@, <%=nombre%>.
                </li>
            </ul>
        </nav>
    </body>
</html>
