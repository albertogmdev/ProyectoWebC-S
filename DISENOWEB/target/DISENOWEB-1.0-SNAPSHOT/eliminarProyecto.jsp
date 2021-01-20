<%-- 
    Document   : eliminarProyecto
    Created on : 17-ene-2021, 21:39:31
    Author     : Alberto
--%>

<%@page import="logica.Proyecto"%>
<%@page import="logica.Empresa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar Proyecto</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/estilos.css">
        <script src="js/formValidar.js"></script>
    </head>
    <%
        HttpSession sesion = request.getSession();
        //Si el usuario no tiene una sesion el redirige
        if(sesion.getAttribute("usuarioSesion") == null){
            response.sendRedirect("./index.jsp");
            sesion.setAttribute("mensaje", "ERROR: Tienes que iniciar sesión para acceder a la aplicación");
        }
        //Solo puede acceder un empleado de RRHH, si lo intenta un empleado de una empresa le 
        //redirige a la pagina de inicio de sesion
        else{
            String nombre = sesion.getAttribute("usuarioSesion").getClass().getSimpleName();
            if(nombre.equalsIgnoreCase("Usuario")){
                response.sendRedirect("./inicioUser.jsp");
                sesion.setAttribute("mensaje", "ERROR: Tienes restringido el acceso a esta página");
            }
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
        <!--end of Navigation bar-->
        <br>
        <%
            Proyecto proyecto = (Proyecto)request.getAttribute("proyecto");
            String empresa = proyecto.getEmpresa().getNombre() +" - ID:"+ proyecto.getEmpresa().getIdEmpresa();
        %>
        <div class="row justify-content-center">
            <div class="col-4">  
                <br><h1 class="text-center"> Eliminar Proyecto </h1>
                <div class="formularioBaja">
                    <form id="bajaEmpleado" action="MainController?action=eliminarProyecto" method="POST">
                        <label>ID Proyecto:</label><br>
                        <input class="form-control" type="text" name="idProyecto" id="idProyecto" value="<%= proyecto.getIdProyecto() %>" readonly><br>
                        <label>Empresa:</label><br>
                        <input class="form-control" type="text" name="nombre" id="nombre" value="<%= empresa %>" readonly><br>

                        <h6 class="text-danger text-right"> Se va a eliminar el siguiente proyecto, ¿está seguro? </h6>
                        <input class="btn btn-danger float-right" type="submit" formaction="mostrarProyectos.jsp" name="accion" value="Cancelar" style="margin:5px;">
                        <input class="btn btn-success float-right" type="submit" name="accion" value="Confirmar" style="margin:5px;" onclick="return validareliminarProyecto()">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
