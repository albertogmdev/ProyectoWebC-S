<%-- 
    Document   : editarUsuario
    Created on : 10 ene. 2021, 20:27:17
    Author     : MARINA
--%>

<%@page import="logica.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Empleado</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/estilos.css">
        <script type="text/javascript" src="js/errores.js"/></script>
</head>
<body>
    <div id="nav-placeholder">  </div>

    <script>
        $(function () {
            $("#nav-placeholder").load("navbarRRHH.html");
        });
    </script>
    <!--end of Navigation bar-->
    <div class="row justify-content-center">
        <div class="col-4">
            <h1 class="text-center"> Editar Empleado</h1>
            <div>
                <%
                    Usuario usuario = (Usuario)request.getAttribute("usuario");
                    
                %>
                
                <!--  LA FUNCION DE EDITAR TIENE COMO PARAMETROS
                    int idUsuario, String nombre, String apellidos, int telefono, String correo, String contrasenna
                    El nombre de la funcion es modificarUsuario()
                -->
                
                <br>  
                <form id="editarEmpleado" action="MainController?action=editarEmpleado" method="POST">
                    <!-- EN EL ID USUSARIO HAY QUE PONER EL ID SEGUN EL USUARIO SELECCIONADO -->
                    <label>ID EMPLEADO:</label><br>
                    <input class="form-control" type="text" name="idUsuario" id="idUsuario" value="<%= usuario.getIdUsuario()%>" readonly><br>
                    <label>Nombre:</label><br>
                    <input class="form-control" type="text" name="nombre" id="nombre" value="<%= usuario.getNombre()%>"><br>
                    <label>Apellidos:</label><br>
                    <input class="form-control" type="text" name="apellidos" id="apellidos" value="<%= usuario.getApellidos()%>"><br>                    
                    <label>Telefono:</label><br>
                    <input class="form-control" type="text" name="telefono" id="telefono" value="<%= usuario.getTelefono() %>"><br>
                    <label>Correo:</label><br>
                    <input class="form-control" type="text" name="correo" id="correo" value="<%= usuario.getEmail()%>"><br>
                    <label>Contraseña:</label><br>
                    <input class="form-control" type="text"  name="password" id="password"  value="<%= usuario.getContrasenna() %>"><br><br>

                    <input class="btn btn-danger float-right" type="submit" name="accion" value="Confirmar" style="margin:5px;">
                </form>
            </div>
        </div
    </div>
</body>

</html>
