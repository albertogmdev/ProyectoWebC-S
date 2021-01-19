<%-- 
    Document   : eliminarEmpresa
    Created on : 17-ene-2021, 21:39:17
    Author     : Alberto
--%>

<%@page import="logica.Empresa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar Empresa</title>
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
            Empresa empresa = (Empresa)request.getAttribute("empresa");
        %>
        <div class="row justify-content-center">
            <div class="col-4">  
                <br><h1 class="text-center"> Eliminar Empresa </h1>
                <div class="formularioBaja">
                    <form id="bajaEmpleado" action="MainController?action=eliminarEmpresa" method="POST">
                        <label>ID Empresa:</label><br>
                        <input class="form-control" type="text" name="idEmpresa" id="idEmpresa" value="<%= empresa.getIdEmpresa() %>" readonly><br>
                        <label>Nombre:</label><br>
                        <input class="form-control" type="text" name="nombre" id="nombre" value="<%= empresa.getNombre()%>" readonly><br>
                        <label>Correo:</label><br>
                        <input class="form-control" type="text" name="correo" id="correo" value="<%= empresa.getCorreo() %>" readonly><br>
                        <label>Telefono:</label><br>
                        <input class="form-control" type="text"  name="telefono" id="telefono"  value="<%= empresa.getTelefono() %>" readonly><br><br>
                        
                        <h6 class="text-danger text-right"> Se va a eliminar la siguiente empresa, ¿está seguro? </h6>
                        <input class="btn btn-danger float-right" type="submit" formaction="mostrarEmpresas.jsp" name="accion" value="Cancelar" style="margin:5px;">
                        <input class="btn btn-success float-right" type="submit" name="accion" value="Confirmar" style="margin:5px;" onclick="validarEliminarEmpresa()">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
