
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title> Inicio de sesión </title>
        <meta charset="UTF-8">
        <meta name="title" content="Inicio de sesion">
        <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="css/estilos.css">
    </head>
    <style>
        body {
          background-image: url("images/fondo-IS8.jpg");
          background-repeat: no-repeat;
          background-size: cover;
        }
    </style> 
    <body>
        <script src="js/login.js"></script>
            <div class="container h-100">
                <div class="d-flex justify-content-center h-100">
                    <div class="user_card">
                        <div class="d-flex justify-content-center">
                            <div class="brand_logo_container">
                                <img src="images/logo-rrhh.png" class="brand_logo" >
                            </div>
                        </div>
                        <div class="d-flex justify-content-center form_container">
                            <form id="inicioSesion" action="LoginController" method="POST">
                                <div class="input-group mb-3">
                                    <div class="input-group-append">
                                        <span class="input-group-text"><i class="fa fa-user-circle"></i></span>
                                    </div>
                                    <input type="text" name="usuario" id="usuario" class="form-control input_user" placeholder="Email" required>
                                </div>
                                <div class="input-group mb-2">
                                    <div class="input-group-append">
                                        <span class="input-group-text"><i class="fa fa-key"></i></span>
                                    </div>
                                    <input type="password" name="password" id="password" class="form-control input_pass" placeholder="Contraseña" required>
                                </div>
                                <div class="d-flex justify-content-center mt-3 login_container">
                                  <!--  <button type="button" name="button" id="login" class="btn login_btn">Iniciar sesion</button>!-->
                                  <input type="submit" name="accion" value="Iniciar sesion" class="btn login_btn">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        <!-- jQuery, Popper.js, and Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>