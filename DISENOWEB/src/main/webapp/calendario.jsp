<%-- 
    Document   : calendario
    Created on : 17 ene. 2021, 19:53:44
    Author     : MARINA
--%>

<%@page import="logica.Solicitud"%>
<%@page import="logica.Usuario"%>
<%@page import="java.util.Iterator"%>
<%@page import="logica.Jornada"%>
<%@page import="java.util.List"%>
<%@page import="util.ConsultaBd"%>
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
    <%
        HttpSession sesion = request.getSession();
        //Si el usuario no tiene una sesion el redirige
        if(sesion.getAttribute("usuarioSesion") == null){
            response.sendRedirect("./index.jsp");
            sesion.setAttribute("mensaje", "ERROR: Tienes que iniciar sesión para acceder a la aplicación");
        }
        //Solo puede acceder un empleado de una empresa, si lo intenta un empleado de RRHH le 
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
            $(function () {
                $("#nav-placeholder").load("navbarUser.jsp");
            });
        </script>
        <div id="calendario">

        </div>
        <script type="text/javascript" src="js/calendario.js"></script>
        <script>
        //lista de eventos que añadir al calendario
        var events = [ //enero es el mes 0
           
        ];
        
        </script>
         <%
                        ConsultaBd consulta =new ConsultaBd();
                        Usuario usuario = (Usuario) sesion.getAttribute("usuarioSesion");
                        String email = usuario.getEmail(); //email del empleado con la sesion iniciada
                        List<Jornada> lista_jornadas = consulta.getCalendario(email); //lista de todas las jornadas trabajadas por este empleado
                        Iterator<Jornada> iterador= lista_jornadas.iterator();
                         Jornada jornada =null;
                        while(iterador.hasNext()){ //recorre la lista de jornadas
                            jornada =iterador.next();
                            
                    
             %>
             <script>
                 var fechaSQL = '<%=jornada.getFecha().toString() %>';
                 var dateParts = fechaSQL.split("-");
                
                 events.push({'Tipo': 'jornada','Date': new Date(dateParts[0], dateParts[1] - 1, dateParts[2].substr(0,2)), 'Title': '<%=jornada.getHora_entrada() %> - <%= jornada.getHora_salida() %> <br> Proyecto: <%=jornada.getId_proyecto() %>'});
             </script>
             <% }
                    List<Solicitud> lista_diasLibres = consulta.getDiasLibres(email); //lista de todos los dias libres de este empleado
                    Iterator<Solicitud> iter = lista_diasLibres.iterator();
                    Solicitud diaLibre =null;
                    while(iter.hasNext()){ //recorre la lista de diaslibres
                        diaLibre = iter.next();                   
             %>
             <script>
                 var fechaInicio = '<%=diaLibre.getFechaInicio().toString() %>';
                 var fechaFin = '<%=diaLibre.getFechaFin().toString() %>';
                 var datePartsInicio = fechaInicio.split("-");
                 if (fechaInicio === fechaFin){ //si solo hay un dia libre se pinta
                    
                    events.push({'Tipo': 'libre','Date': new Date(datePartsInicio[0], datePartsInicio[1] - 1, datePartsInicio[2].substr(0,2)), 'Title': 'DIA LIBRE'});
                 }else{ //si son vacaciones
                     
                     var datePartsFin = fechaFin.split("-");
                     var diaIni = parseInt(datePartsInicio[2].substr(0,2));
                     var fechahoy = new Date(datePartsInicio[0], datePartsInicio[1] - 1, datePartsInicio[2].substr(0,2));
                     var dateFechaFin = new Date(datePartsFin[0], datePartsFin[1] - 1, datePartsFin[2].substr(0,2));
                     var diferencia = Math.abs(dateFechaFin - fechahoy);
                     var ndias = Math.ceil(diferencia / (1000 * 60 * 60 * 24)); //numero de dias de vacaciones
                     for(var i = 0; i<ndias; i++){ //mientras la fecha por la que vamos no es igual a la final
                        
                         events.push({'Tipo': 'libre','Date': new Date(fechahoy.getFullYear(), fechahoy.getMonth(), fechahoy.getDay()) , 'Title': 'DIA LIBRE'});
                         fechahoy.setDate(fechahoy.getDate()+1); //incrementamos la fecha
                     }
                     
                 }
                 </script>
             <% }%>
             
       <script>
        var settings = {};
        var element = document.getElementById('calendario');
        calendario(element, events, settings);
        </script>
    
   

        
    </body>
    <% }%>
</html>