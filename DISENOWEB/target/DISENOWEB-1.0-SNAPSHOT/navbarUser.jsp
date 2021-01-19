
<%@page import="logica.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>NavBar Usuario</title>
    </head>
    <body>
        <%
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioSesion");
            String nombre = usuario.getNombre() + " " + usuario.getApellidos();
        %>
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
            <a class="navbar-brand">Gestión de personal</a>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="./inicioUser.jsp">Inicio</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="./calendario.jsp">Calendario</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="./fichar.jsp">Fichar</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="./diaLibre.jsp">Solicitar dia libre</a>
                </li>
            </ul>
            <ul class="navbar-nav ml-auto">
                <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                    <img src="./images/usuario.png" align="center" width="20" height="20">
                    <%=nombre%>
                </a>
                <div class="dropdown-menu dropdown-menu-right">
                    <a class="dropdown-item" href="#">Perfil</a>
                    <a class="dropdown-item" href="LoginController?action=cerrarSesion">Cerrar Sesion</a>
                </div>
            </ul>
        </nav>
    </body>
</html>
