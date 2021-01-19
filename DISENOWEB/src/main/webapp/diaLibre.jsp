
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title> Dia libre </title>
        <meta charset="UTF-8">
        <meta name="title" content="Fichar">
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
        //redirige a la pagina de inicio de sesion
        else{
            String nombre = sesion.getAttribute("usuarioSesion").getClass().getSimpleName();
            if(nombre.equalsIgnoreCase("Empleado")){
                response.sendRedirect("./inicioRRHH.jsp");
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
    <body>
        <div id="nav-placeholder"></div>
        <script>
        $(function(){
            $("#nav-placeholder").load("navbarUser.jsp");
        });
        </script>
        <!--end of Navigation bar-->
        <br><br>
        <div class="titulo">
            <h1 class="text-center"> Solicitud de día libre </h1>
        </div>
        <br><br>
        <div class="row justify-content-center">
            <div class="col-4">
                <div class="formulario justify-content-center">
                    <form id="diaLibre" action="" method="get" onsubmit=diaLibre()>
                        <label>Hora salida:</label><br>
                        <input class="form-control" id="diaLibre" type="date" name="fechanacimiento" required>
                        <br><br>
                        <label>Motivo:</label><br>
                        <textarea class="form-control" id="motivo" rows="5" cols="50" placeholder="Mensaje (max 255car)."></textarea><br><br>

                        <input class="btn btn-danger float-right" type="submit" id='enviar' name="enviar" value="Enviar" style="margin:5px;" onclick="validarDiaLibre()">
                        <input class="btn btn-danger float-right" type="reset" id='borrar' name="borrar" value="Borrar" style="margin:5px;">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
