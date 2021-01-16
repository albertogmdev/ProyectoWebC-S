
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Calendario</title>
        <meta charset="UTF-8" />
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
        }
    %>
    <body>
        <div id="nav-placeholder"></div>
        <script>
            $(function () {
                $("#nav-placeholder").load("navbarUser.jsp");
            });
        </script>
        <!--end of Navigation bar-->
        <div id="app"></div>
        <script type="module" src="calendario/calendario.js">
                
        </script>
    </body>
</html>
