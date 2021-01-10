<%-- 
    Document   : editarProyecto
    Created on : 10 ene. 2021, 20:57:51
    Author     : MARINA
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Proyecto</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/estilos.css">



        <script type="text/javascript" src="js/errores.js"/></script>


</head>
<body style="height: 1500px; padding-top: 5rem;">

    <div id="nav-placeholder">

    </div>

    <script>
        $(function () {
            $("#nav-placeholder").load("navbarRRHH.html");
        });
    </script>
    <!--end of Navigation bar-->
    <div class="row justify-content-center">
        <div class="col-4">
            <h1 class="text-center"> Editar Proyecto</h1>
            <div>
                <%String s=(String)request.getAttribute("mensaje");
              
                boolean u=Boolean.valueOf(s);
                %>
                

                <br><br>
                <form id="editarProyecto" onsubmit="alerta(<%=s%>)" action="editarProyecto" method="post">
                    <label>ID:</label><br>
                    <label>00001</label><br><br><!--id dinamico, no se puede editar-->
                    <label>Empresa:</label><br>
                    <input class="form-control" type="text" name="empresa" id="nombre" value="UAH"><br><br>
               
                    <input class="btn btn-danger float-right" type="submit" name="accion" value="Confirmar" style="margin:5px;">
                </form>
            </div>
        </div
    </div>
</body>

</html>
