<%-- 
    Document   : index
    Created on : 30-dic-2020, 18:07:15
    Author     : Alberto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina Prueba</title>
    </head>
    <body>
        <h1>PAGINA DE PRUEBA CON TODOS LOS LINKS</h1>
        <div class="nav-index">
            <a href="LoginController?action=usuarios">NOMBRE</a> 
            <h1><c:out value="${usuario.nombre}"/></h1>
            <nav>
                <a href="./login.jsp"> LOGIN </a><br>
                <a href="./calendario.jsp"> CALENDARIO </a><br>
                <a href="./diaLibre.jsp"> DIA-LIBRE-EMPLEADO </a><br>
                <a href="./fichar.jsp"> FICHAR-EMPLEADO </a><br>
                <a href="./gestionEmpleado.jsp"> GESTION-EMPLEADO </a><br>
            </nav> 
        </div>
        <div class="footer-index">
            <footer>
                <h3>Placeholder para el footer</h3>
            </footer>
        </div>
    </body>
</html>
