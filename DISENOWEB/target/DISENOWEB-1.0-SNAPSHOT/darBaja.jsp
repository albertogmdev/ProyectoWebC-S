<%-- 
    Document   : darBaja
    Created on : 7 ene. 2021, 17:04:05
    Author     : MARINA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dar de baja</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/estilos.css">
    </head>
    <body style="height: 1500px; padding-top: 5rem;">
     <div id="nav-placeholder">

    </div>

    <script>
    $(function(){
    $("#nav-placeholder").load("navbarRRHH.html");
    });
    </script>
<!--end of Navigation bar-->
      
      <br>
      <div class="row justify-content-center">
          <div class="col-4">  
            <h1 class="text-center"> Dar de baja </h1>
            <div class="formularioBaja">
                <form id="bajaEmpleado" action="Prueba" method="post">
                    <label>ID:</label><br>
                    <input class="form-control" type="text" name="txtdni" id="DNI" placeholder="Introduzca ID" required><br><br>

                    <input class="btn btn-danger float-right" type="submit" name="accion" value="Eliminar" style="margin:5px;">
                     <input class="btn btn-danger float-right" type="reset" name="accion" value="Cancelar" style="margin:5px;">
                    
                </form>
            </div>
          </div>
      </div>
         
    </body>
</html>
