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
            <h1 class="text-center"> Editar Proyecto</h1> <!--SE PODRIA PONER AQUI EL ID - EJ: Editar Proyecto 1453-->
            <div>
                <%
                    String s=(String)request.getAttribute("mensaje");
                    boolean u=Boolean.valueOf(s);
                %>
                
                <!--  LA FUNCION DE EDITAR TIENE COMO PARAMETROS
                    int idProyecto, int idEmpresa
                    El nombre de la funcion es modificarProyecto()
                    Habria que hacer que para elegir empresa saliera una un desplegable con las empresas <select>
                    con todas las <option> que seran todas las empresas que se obtienen con mostrarEmpresa
                    Una vez modificado si se cambia el proyecto de empresa, la funcion retornara true si se ha cambiado, 
                    hay que borrar todas las entrada de la tabla proyecto_empleado con el id del proyecto, 
                    funcion borrarProyecto(idProyecto), SOLO si la funcion modificarProyecto retorno true, es decir,
                    que SI se ha modificado.
                -->

                <br><br>
                <form id="editarProyecto" onsubmit="alerta(<%=s%>)" action="MainController?action=editarProyecto" method="post">
                    <label>ID:</label><br>
                    <label>00001</label><br><br><!--id dinamico, no se puede editar-->
                    <!-- EN EMPRESA SE PUEDE PONER UN SELECT DE VARIOS OPCIONES -->
                    <label>Empresa:</label><br>
                    <input class="form-control" type="text" name="idEmpresa" id="idEmpresa" value="UAH"><br><br>
               
                    <input class="btn btn-danger float-right" type="submit" name="accion" value="Confirmar" style="margin:5px;">
                </form>
            </div>
        </div
    </div>
</body>

</html>
