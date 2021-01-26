
<%@page import="logica.Empleado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>NavBar Empleado</title>
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
    <body>
        <%
            Empleado empleado = (Empleado) request.getSession().getAttribute("usuarioSesion");
            String nombreUsuario = empleado.getNombre() + " " + empleado.getApellidos();
        %>
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
            <a class="navbar-brand">Gestión de personal</a>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="./inicioRRHH.jsp">Inicio</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="solicitudesPendientes.jsp">Peticiones</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                      Gestionar empleados
                    </a>
                    <div class="dropdown-menu dropdown-menu-right">
                      <a class="dropdown-item" href="./darAlta.jsp">Dar de alta</a>
                      <a class="dropdown-item" href="./darBajaNav.jsp">Dar de baja</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                      Solicitar Informe
                    </a>
                    <div class="dropdown-menu dropdown-menu-right">
                      <a class="dropdown-item" href="informeEmpresa.jsp">Empresa</a>
                      <a class="dropdown-item" href="informeProyecto.jsp">Proyecto</a>
                      <a class="dropdown-item" href="informeEmpleado.jsp">Empleado</a>
                    </div>
                </li>
            </ul>
            <ul class="navbar-nav ml-auto align-middle">
                <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                    <img src="./images/usuario.png" align="center" width="20" height="20">
                    <%= nombreUsuario %>
                </a>
                <div class="dropdown-menu dropdown-menu-right">
                    <a class="dropdown-item" href="LoginController?action=cerrarSesion">Cerrar Sesion</a>
                </div>
            </ul>
        </nav>
    </body>
    <% }%>
</html>
