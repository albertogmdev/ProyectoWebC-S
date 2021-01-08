<%-- 
    Document   : mostrarEmpleados
    Created on : 7 ene. 2021, 19:51:46
    Author     : MARINA
--%>

<%@page import="java.util.Iterator"%>
<%@page import="logica.Proyecto"%>
<%@page import="java.util.List"%>
<%@page import="logicaDAO.ProyectoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Proyectos</title>
        <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
        <div class="container">
  <h2>Proyectos</h2> 
  <br>
  <table class="table table-hover">
    <thead>
      <tr>
        <th>ID</th>
        <th>Empresa</th>
        
      </tr>
    </thead>
    <%
        ProyectoDAO proyecto=new ProyectoDAO();
        List<Proyecto> lista_proyectos=proyecto.mostrarProyecto();
        Iterator<Proyecto> iterador=lista_proyectos.iterator();
        Proyecto p=null;
        while(iterador.hasNext()){
            p=iterador.next();
        
    
    
    
    %>
    <tbody> <!-- cambiar para que se actualice dinamicamente -->
      <tr>
        <td><%= p.getIdProyecto() %></td>
        <td><%= p.getEmpresa().getIdEmpresa() %></td>
        
      </tr>
       <% } %>
     
    </tbody>
  </table>
  <div class="col-md-12 text-right">
                <button type="button" class="btn btn-danger text-right" style="height:40px">
                    Editar
                </button>
                <button type="button" class="btn btn-danger text-right" style="height:40px">
                    Eliminar
                </button>
                <button type="button" class="btn btn-danger text-right" style="height:40px">
                    Añadir
                </button>
      
      
  </div>
</div>
    </body>
</html>
