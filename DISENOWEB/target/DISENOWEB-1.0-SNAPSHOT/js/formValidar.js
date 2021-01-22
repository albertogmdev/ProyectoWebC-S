function validarAnadirEmpresa() {
    var alerta = "Corrija los siguientes campos: \n";
    var ok = true;
    try {
        var uno = parseInt(document.getElementById("idEmpresa"));
    } catch (error) {
        alerta += "   Los datos recibidos no son números enteros\n";
        ok = false;
    }

    if(!validarLongitud(document.getElementById("idEmpresa").value,11)){
        alerta += "  Id demasiado largo\n";
        ok = false;
    }
    if(!validarLongitud(document.getElementById("nombre").value,45)){
        alerta += "  Nombre demasiado largo\n";
        ok = false;
    }
    
    if(!validarLongitud(document.getElementById("calle").value,45)){
        alerta += "  Nombre demasiado largo\n";
        ok = false;
    }
    try {
        var uno = parseInt(document.getElementById("codigo"));
    } catch (error) {
        alerta += "  -El telefono introducido no es un numero entero\n";
        ok = false;
    }
    if(!validarLongitud(document.getElementById("codigo").value,5)){
        alerta += "  -Telefono demasiado largo\n";
        ok = false;
    }

    if(!validarEmail(document.getElementById("correo").value) || !validarLongitud(document.getElementById("correo").value,45)){
        alerta += "  Correo invalido(demasiado largo o incorrecto)\n";
        ok = false;
    }
    try {
        var uno = parseInt(document.getElementById("telefono"));
    } catch (error) {
        alerta += "  -El telefono introducido no es un numero entero\n";
        ok = false;
    }
    if(!validarLongitud(document.getElementById("telefono").value,11)){
        alerta += "  -Telefono demasiado largo\n";
        ok = false;
    }

    if(ok == true){
<<<<<<< Updated upstream
        alerta = "Empresa" +idEmpresa.value + "añadida correctamente"
        
=======
        alerta += "Empresa" +idEmpresa.value + "añadida correctamente"
>>>>>>> Stashed changes
    }
    alert(alerta);
    return ok;
}

function validarAnadirProyecto() {
    var alerta = "Corrija los siguientes campos: \n";
    var ok = true;
    try {
        var uno = parseInt(document.getElementById("idProyecto"));
    } catch (error) {
        alerta += "   Los datos recibidos de idProyecto no son números enteros\n";
        ok = false;
    }

    if(!validarLongitud(document.getElementById("idProyecto").value,9)){
        alerta += "  Id demasiado largo\n";
        ok = false;
    }
    
    try {
        var uno = parseInt(document.getElementById("idEmpresa"));
    } catch (error) {
        alerta += "   Los datos recibidos de idEmpresa no son números enteros\n";
        ok = false;
    }

    if(!validarLongitud(document.getElementById("idEmpresa").value,4)){
        alerta += "  Id demasiado largo\n";
        ok = false;
    }

    if(ok == true){
<<<<<<< Updated upstream
        alerta = "Proyecto " +idProyecto.value +" añadido correctamente"
=======
        alerta += "Proyecto " +idProyecto.value +" añadido correctamente"
>>>>>>> Stashed changes
    }
    alert(alerta);
    return ok;
}

function validarDarAlta() {
    var alerta = "Corrija los siguientes campos: \n";
    var ok = true;
    if(!validarLongitud(document.getElementById("nombre").value,45)){
        alerta += "  -Nombre demasiado largo\n";
        ok = false;
    }
    if(!validarLongitud(document.getElementById("apellidos").value,45)){
        alerta += "  -Apellidos demasiado largos\n";
        ok = false;
    }
    try {
        var uno = parseInt(telefono.value);
    } catch (error) {
        alerta += "  -El telefono introducido no es un numero entero\n";
        ok = false;
    }
    if(!validarLongitud(document.getElementById("telefono").value,11)){
        alerta += "  -Telefono demasiado largo\n";
        ok = false;
    }
    if(!validarEmail(correo.value) || !validarLongitud(correo.value,45)){
        alerta += "  -Correo invalido (demasiado largo o formato incorrecto)\n";
        ok = false;
    }
    if(!validarLongitud(document.getElementById("contraseña").value,45)){
        alerta += "  -Contraseña demasiado larga\n";
        ok = false;
    }
    if(ok == true){
<<<<<<< Updated upstream
        alerta = nombre.value + apellidos.value +"dado de alta correctamente"
=======
        alerta += nombre.value + apellidos.value +"dado de alta correctamente"
>>>>>>> Stashed changes
    }
    alert(alerta);
    return ok;
}

function validarDarBaja() {
    var alerta = "Corrija los siguientes campos: \n";
    var ok = true;
    try {
        var uno = parseInt(document.getElementById("idUsuario"));
    } catch (error) {
        alerta += "   Los datos recibidos no son números enteros\n";
        ok = false;
    }

    if(!validarLongitud(document.getElementById("idUsuario").value,11)){
        alerta += "  Id demasiado largo\n";
        ok = false;
    }
    if(!validarLongitud(document.getElementById("nombre").value,45)){
        alerta += "  Nombre demasiado largo\n";
        ok = false;
    }
    if(!validarLongitud(document.getElementById("apellidos").value,45)){
        alerta += "  Apellidos demasiado largos\n";
        ok = false;
    }
    if(!validarEmail(document.getElementById("correo").value) || !validarLongitud(document.getElementById("correo").value,45)){
        alerta += "  Correo invalido(demasiado largo o incorrecto)\n";
        ok = false;
    }
    if(ok == true){
<<<<<<< Updated upstream
        alerta = correo.value + "dado de baja correctamente."
=======
        alerta += correo.value + "dado de baja correctamente."
>>>>>>> Stashed changes
    }
    alert(alerta);
    return ok;
}

function validarDarBajaNav() {
    var alerta = "Corrija los siguientes campos: \n";
    var ok = true;
    
    if(!validarEmail(document.getElementById("correo").value) || !validarLongitud(document.getElementById("correo").value,45)){
        alerta += "  Correo invalido(demasiado largo o incorrecto)\n";
        ok = false;
    }
    if(ok == true){
<<<<<<< Updated upstream
        alerta = correo.value + "dado de baja correctamente."
=======
        alerta += correo.value + "dado de baja correctamente."
>>>>>>> Stashed changes
    }
    alert(alerta);
    return ok;
}

function validardiaLibre() {
    var alerta = "Corrija los siguientes campos: \n";
    var ok = true;
    
    if(!validarLongitud(document.getElementById("motivo").value,255)){
        alerta += "  Exceso de caracteres\n";
        ok = false;
    }
    
<<<<<<< Updated upstream
    if(ok == false){
        alert(alerta);
    }
    
=======
    if(ok == true){
        alerta += "  Todo OK!!"
    }
    alert(alerta);
>>>>>>> Stashed changes
    return ok;
}

function validarEditarUsuario() {
    var alerta = "Corrija los siguientes campos: \n";
    var ok = true;

    try {
        var uno = parseInt(document.getElementById("idUsuario"));
    } catch (error) {
        alerta += "  -El Id introducido no es un numero entero\n";
        ok = false;
    }

    if(!validarLongitud(document.getElementById("idUsuario").value,2)){
        alerta += "  -Id demasiado largo\n";
        ok = false;
    }

    if(!validarLongitud(document.getElementById("nombre").value,45)){
        alerta += "  -Nombre demasiado largo\n";
        ok = false;
    }
    if(!validarLongitud(document.getElementById("apellidos").value,45)){
        alerta += "  -Apellidos demasiado largos\n";
        ok = false;
    }

    try {
        var uno = parseInt(document.getElementById("telefono"));
    } catch (error) {
        alerta += "  -El telefono introducido no es un numero entero\n";
        ok = false;
    }

    if(!validarLongitud(document.getElementById("telefono").value,11)){
        alerta += "  -Telefono demasiado largo\n";
        ok = false;
    }

    if(!validarEmail(document.getElementById("correo").value) || !validarLongitud(document.getElementById("correo").value,45)){
        alerta += "  -Correo invalido(demasiado largo o incorrecto)\n";
        ok = false;
    }

    if(!validarLongitud(document.getElementById("contraseña").value,4)){
        alerta += "  -Contraseña demasiado larga\n";
        ok = false;
    }

<<<<<<< Updated upstream
    if(ok == false){
        alert(alerta);
    }
=======
    if(ok == true){
        alerta += "  Todo OK!!"
    }
    alert(alerta);
>>>>>>> Stashed changes
    return ok;
}

function validarEliminarEmpresa() {
    var alerta = "Corrija los siguientes campos: \n";
    var ok = true;
    try {
        var uno = parseInt(document.getElementById("idEmpresa"));
    } catch (error) {
        alerta += "   Los datos recibidos no son números enteros\n";
        ok = false;
    }

    if(!validarLongitud(document.getElementById("idEmpresa").value,11)){
        alerta += "  Id demasiado largo\n";
        ok = false;
    }
    if(!validarLongitud(document.getElementById("nombre").value,45)){
        alerta += "  Nombre demasiado largo\n";
        ok = false;
    }

    if(!validarEmail(document.getElementById("correo").value) || !validarLongitud(document.getElementById("correo").value,45)){
        alerta += "  Correo invalido(demasiado largo o incorrecto)\n";
        ok = false;
    }
    try {
        var uno = parseInt(document.getElementById("telefono"));
    } catch (error) {
        alerta += "  -El telefono introducido no es un numero entero\n";
        ok = false;
    }
    if(!validarLongitud(document.getElementById("telefono").value,11)){
        alerta += "  -Telefono demasiado largo\n";
        ok = false;
    }

    if(ok == true){
        alerta += "Empresa " +idEmpresa.value + "eliminada correctamente";
    }
    alert(alerta);
    return ok;
}


function validareliminarProyecto() {
    var alerta = "Corrija los siguientes campos: \n";
    var ok = true;
    try {
        var uno = parseInt(document.getElementById("idProyecto"));
    } catch (error) {
        alerta += "   Los datos recibidos de idProyecto no son números enteros\n";
        ok = false;
    }

    if(!validarLongitud(document.getElementById("idProyecto").value,9)){
        alerta += "  Id demasiado largo\n";
        ok = false;
    }
    
    if(!validarLongitud(document.getElementById("nombre").value,45)){
        alerta += "  Nombre de empresa demasiado largo\n";
        ok = false;
    }

    if(ok == true){
        alerta += "Proyecto " +idProyecto.value +" eliminado correctamente";
    }
    alert(alerta);
    return ok;
}

function validarFichar() {
    var alerta = "Corrija los siguientes campos: \n";
    var ok = true;
    try {
        var uno = parseInt(document.getElementById("proyecto"));
    } catch (error) {
        alerta += "   Los datos recibidos de idProyecto no son números enteros\n";
        ok = false;
    }

    if(!validarLongitud(document.getElementById("proyecto").value,9)){
        alerta += "  Id demasiado largo\n";
        ok = false;
    }
    
    if(!validarLongitud(document.getElementById("nombre").value,45)){
        alerta += "  Nombre de empresa demasiado largo\n";
        ok = false;
    }

    if(ok == false){
        alert(alerta);
    }
    
    return ok;
}

function validarIndex() {
    var alerta = "Corrija los siguientes campos: \n";
    var ok = true;
    if(!validarEmail(document.getElementById("usuario").value) || !validarLongitud(document.getElementById("usuario").value,45)){
        alerta += "  Correo invalido(demasiado largo o incorrecto)\n";
        ok = false;
    }
    try {
        var uno = parseInt(document.getElementById("password"));
    } catch (error) {
        alerta += "   Los datos recibidos de password no son números enteros\n";
        ok = false;
    }

    if(!validarLongitud(document.getElementById("password").value,4)){
        alerta += "  Id demasiado largo\n";
        ok = false;
    }
    if(ok == false){
        alert(alerta);
    }
    return ok;
}







function validarEmail(valor) {

    emailRegex = /^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i;

    if (emailRegex.test(valor)){
        return true;
    } else {
        return false;
    }
}

function validarLongitud(valor, longitud){
    if(valor.length <= longitud){
        return true;
    } else {
        return false;
    }
}
