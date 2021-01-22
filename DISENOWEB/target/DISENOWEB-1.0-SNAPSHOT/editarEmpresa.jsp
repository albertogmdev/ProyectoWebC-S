<%-- 
    Document   : editarEmpresa
    Created on : 10 ene. 2021, 20:57:34
    Author     : MARINA
--%>


<%@page import="logica.Empresa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Empresa</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/estilos.css">
        <script type="text/javascript" src="js/errores.js"/></script>
        <script src="js/formValidar.js"></script>
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
            $(function () {
                $("#nav-placeholder").load("navbarRRHH.jsp");
            });
        </script>
        <!--end of Navigation bar-->
        <div class="row justify-content-center">
            <div class="col-4">
                <h1 class="text-center"> Editar Empresa </h1>
                <div>
                    <%
                        Empresa empresa = (Empresa)request.getAttribute("empresa");
                    %>
                    <br>
                    <form id="editarEmpresa"  action="MainController?action=editarEmpresa" method="POST">
                        <label>ID Empresa:</label><br>
                        <input class="form-control" type="text" name="idEmpresa" id="idEmpresa" value="<%= empresa.getIdEmpresa() %>" readonly><br>
                        <label>Nombre:</label><br>
                        <input class="form-control" type="text" name="nombre" id="nombre" value="<%= empresa.getNombre()%>" required><br>
                        <label>Dirección:</label><br>
                        <input class="form-control" type="text" name="calle" id="calle" value="<%= empresa.getDireccion() %>" required><br>  
                        <label>Código Postal:</label><br>
                        <input class="form-control" type="text"  name="codigo" id="codigo" value="<%= empresa.getCodigoPostal() %>" required><br>
                        <label>Correo:</label><br>
                        <input class="form-control" type="text" name="correo" id="correo" value="<%= empresa.getCorreo() %>" required><br>
                        <label>Telefono:</label><br>
                        <input class="form-control" type="text"  name="telefono" id="telefono"  value="<%= empresa.getTelefono() %>" required><br>

                        <input class="btn btn-danger float-right" type="submit" name="accion" value="Confirmar" style="margin:5px;" onclick=" return validarAnadirEmpresa()">
                    </form>
                </div>
            </div>
        </div>
    </body>
    <% }%>
</html>
