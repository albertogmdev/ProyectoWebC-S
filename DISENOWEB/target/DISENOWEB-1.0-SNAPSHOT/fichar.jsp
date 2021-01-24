<%-- 
    Document   : fichar
    Created on : 30-dic-2020, 18:20:34
    Author     : Alberto
--%>

<%@page import="logica.Usuario"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="logica.Proyecto"%>
<%@page import="util.ConsultaBd"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title> Fichar </title>
        <meta charset="UTF-8">
        <meta name="title" content="Fichar">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
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
            if(nombre.equalsIgnoreCase("Empleado")){
                response.sendRedirect("./inicioRRHH.jsp");
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
    <body>
        <div id="nav-placeholder"></div>
        <script>
            $(function () {
                $("#nav-placeholder").load("navbarUser.jsp");
            });
        </script>
        <!--end of Navigation bar-->
        <br><br>
        <div class="titulo">
            <br>
            <h1 class="text-center"> FICHAR </h1>
        </div>
        <br><br>
        <div class="row justify-content-center">
            <div class="col-4">
                <div class="formulario justify-content-center">
                    <form id="ficharEmpleado" action="CalendarController?action=fichar" method="POST" onsubmit="ficharEmpleado()">
                        <label>Hora entrada:</label>
                        <input class="form-control" type="datetime-local" name="hora_entrada" id="entrada" required><br><br>
                        <label>Hora salida:</label>
                        <input class="form-control" type="datetime-local" name="hora_salida" id="salida" required><br><br>
                        <label>Proyecto en el que se ha trabajado:</label><br>
                        <select class="custom-select" name="idProyecto" name="idProyecto" id="idProyecto" required>
                        <%
                            ConsultaBd consulta = new ConsultaBd();
                            Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioSesion");
                            int idEmpresa = usuario.getEmpresa().getIdEmpresa();
                            List<Proyecto> lista_proyecto = consulta.getProyectoEmpresa(idEmpresa);
                            Iterator<Proyecto> iterador = lista_proyecto.iterator();
                            Proyecto proyecto = null;
                            while (iterador.hasNext()) {
                                proyecto = iterador.next();
                        %>
                            <option name="proyecto" value="<%= proyecto.getIdProyecto()%>">
                                <%= proyecto.getIdProyecto()%>
                            </option>
                               
                        <% }%>  
                        </select><br><br>

                        <input class="btn btn-danger float-right" type="submit" name="accion" value="Enviar" style="margin:5px;" onclick="return validarFichar()">
                        <input class="btn btn-danger float-right" type="reset" name="accion" value="Borrar" style="margin:5px;">
                    </form>
                </div>
            </div>
        </div>
    </body>
    <% }%>
</html>
