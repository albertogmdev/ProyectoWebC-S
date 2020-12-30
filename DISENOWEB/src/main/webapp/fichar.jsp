<%-- 
    Document   : fichar
    Created on : 30-dic-2020, 18:20:34
    Author     : Alberto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <title> Fichar </title>
    <meta charset="UTF-8">
    <meta name="title" content="Fichar">
  </head>
  <body>
    <div class="titulo">
        <h1> FICHAR </h1>
    </div>
    <div class="formulario">
        <form id="ficharEmpleado" action="" method="get" onsubmit=ficharEmpleado()>
            <label>Hora entrada:</label><br>
            <input type="datetime-local" id="entrada" required><br><br>
            <label>Hora salida:</label><br>
            <input type="datetime-local" id="salida" required><br><br>
            <label>Proyecto:</label><br>
            <input type="text" id="proyecto" placeholder="Introduzca ID proyecto" required><br><br>
            
            <input type="submit" value="Enviar">
            <input type="reset" value="Borrar">
        </form>
    </div>
  </body>
</html>
