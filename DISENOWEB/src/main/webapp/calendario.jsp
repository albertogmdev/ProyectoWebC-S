<%-- 
    Document   : calendario
    Created on : 17 ene. 2021, 19:53:44
    Author     : MARINA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Calendario</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed" rel="stylesheet">
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
     <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
     <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    
    <link rel="stylesheet" href="css/calendario.css"/>
  </head>
  <body style="height: 1500px; padding-top: 5rem;">
    <div id="nav-placeholder"></div>
        <script>
            $(function () {
                $("#nav-placeholder").load("navbarUser.jsp");
            });
        </script>
        
    <div id="calendario">

    </div>

    <script type="text/javascript" src="js/calendario.js"></script>
    <script type="text/javascript" src="js/demo.js"></script>
  </body>
</html>