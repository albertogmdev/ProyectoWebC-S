<%-- 
    Document   : informeProyecto
    Created on : 20 ene. 2021, 17:03:36
    Author     : MARINA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Elegir Empresa</title>
         <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </head>
    <%
        HttpSession sesion = request.getSession();
        //Si el usuario no tiene una sesion el redirige
        if(sesion.getAttribute("usuarioSesion") == null){
            response.sendRedirect("./index.jsp");
        }
        //Solo puede acceder un empleado de RRHH, si lo intenta un empleado de una empresa le 
        //redirige a la pagina de inicio de sesion.
        else{
            String nombre = sesion.getAttribute("usuarioSesion").getClass().getSimpleName();
            if(nombre.equalsIgnoreCase("Usuario")){
                response.sendRedirect("./inicioUser.jsp");
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
            $(function () {
                $("#nav-placeholder").load("navbarRRHH.jsp");
            });
        </script>
        <!--end of Navigation bar-->
    <div class="row justify-content-center">
            <div class="col-4">
                <h1 class="text-center"> Seleccione la empresa de la que generar un informe </h1>
                <div>
                    <br><br>
                    <form id="editarProyecto" action="MainController?action=getInformeEmpresa" method="post">
                        <label>Empresa:</label><br><!--SELECT con opciones de empresa -->
                        <select class="custom-select" name="idEmpresa" id="idEmpresa" required>
                            <c:forEach items="${empresas}" var="empresas">
                                <option value="${empresas.getIdEmpresa()}" ${empresas != null ? 'selected' : ''}>
                                    ${empresas.getNombre()}       
                                </option>
                            </c:forEach>
                        </select><br><br>
                        <input class="btn btn-danger float-right" type="submit" name="accion" value="Confirmar" style="margin:5px;">
                    </form>
                </div>
            </div>
        </div>
               
    </body>
</html>
