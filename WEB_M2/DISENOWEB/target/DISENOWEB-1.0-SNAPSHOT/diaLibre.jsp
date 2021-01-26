
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
        <script src="js/formValidar.js"></script>
        <script src="js/informes.js"></script>
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
    <body style="height: 1500px; padding-top: 5rem;">
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
                    <form id="diaLibre" action="CalendarController?action=Libre" method="POST" onsubmit=diaLibre()>
                        <input type="radio" id="tiempo" name="tiempo" value="diaLibre" onclick="vacacionesODiaLibre()">
                            <label for="diaLibre">Día Libre</label><br>
                            <input type="radio" id="tiempo" name="tiempo" value="vacaciones" onclick="vacacionesODiaLibre()">
                          
                            <label for="vacaciones">Vacaciones</label><br>
                            <span id='boxDia' style="display:none;">
                            <p>Elija una fecha:</p>
                            <input class="form-control" name="unDia" type="date" id="selectDiaLibre"><br>
                                
                        </span>
                            
                        <span id='boxVacaciones' style="display:none;">
                                <p>Inicio:</p>
                            <input class="form-control" name="dia1" type="date" id="inicioVacaciones"><br>
                                <p>Fin:</p>
                            <input class="form-control" name="dia2" type="date" id="finVacaciones"><br>
                        </span>
                        
                        <label>Motivo:</label><br>
                        <textarea class="form-control" id="motivo" name="motivo" rows="5" cols="50" placeholder="Mensaje (max 255car)."></textarea><br><br>

                        <input class="btn btn-danger float-right" type="submit" id='enviar' name="enviar" value="Enviar" style="margin:5px;" onclick="validarDiaLibre()">
                        <input class="btn btn-danger float-right" type="reset" id='borrar' name="borrar" value="Borrar" style="margin:5px;">
                    </form>
                </div>
            </div>
        </div>
    </body>
    <% }%>
</html>
