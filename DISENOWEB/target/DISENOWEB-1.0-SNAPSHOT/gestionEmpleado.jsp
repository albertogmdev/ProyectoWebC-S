
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <title> Gestion empleado </title>
    <meta charset="UTF-8">
    <meta name="title" content="Fichar">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  </head>
  <body>
      <br>
      <div class="row justify-content-center">
          <div class="col-4">
              <h1 class="text-center"> Dar de alta </h1>
              <div class="formularioAlta">
                <form id="altaEmpleado" action="" method="get" onsubmit=altaEmpleado()>
                      <label>DNI:</label><br>
                      <input class="form-control" type="text" id="DNI" placeholder="Introduzca DNI" required><br><br>
                      <label>Nombre:</label><br>
                      <input class="form-control" type="text" id="nombre" placeholder="Introduzca nombre" required><br><br>
                      <label>Apellidos:</label><br>
                      <input class="form-control" type="text" id="apellidos" placeholder="Introduzca apellidos" required><br><br>
                      <label>Telefono:</label><br>
                      <input class="form-control" type="text" id="telefono" placeholder="Introduzca apellidos" required><br><br>
                      <label>Contraseña:</label><br>
                      <input class="form-control" type="text" id="contraseña" placeholder="Introduzca contraseña" required><br><br>

                      <input class="btn btn-danger float-right" type="submit" value="Enviar" style="margin:5px;">
                      <input class="btn btn-danger float-right" type="reset" value="Borrar" style="margin:5px;">
                </form>
              </div>
          </div>
          <div class="col-4">  
            <h1 class="text-center"> Dar de baja </h1>
            <div class="formularioBaja">
                <form id="bajaEmpleado" action="" method="get" onsubmit=bajaEmpleado()>
                    <label>DNI:</label><br>
                    <input class="form-control" type="text" id="DNI" placeholder="Introduzca DNI" required><br><br>

                    <input class="btn btn-danger float-right" type="submit" value="Enviar" style="margin:5px;">
                    <input class="btn btn-danger float-right" type="reset" value="Borrar" style="margin:5px;">
                </form>
            </div>
          </div>
      </div>
         
  </body>
</html>
