
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <title> Dia libre </title>
    <meta charset="UTF-8">
    <meta name="title" content="Fichar">
  </head>
  <body>
    <div class="titulo">
        <h1> DIA LIBRE </h1>
    </div>
    <div class="formulario">
        <form id="diaLibre" action="" method="get" onsubmit=diaLibre()>
            <label>Hora salida:</label><br>
            <input type="date" id="diaLibre" required><br><br>
            <label>Motivo:</label><br>
            <textarea id="motivo" rows="5" cols="50" placeholder="Mensaje max(255car)."></textarea><br><br>
            
            <input type="submit" value="Enviar">
            <input type="reset" value="Borrar">
        </form>
    </div>
  </body>
</html>