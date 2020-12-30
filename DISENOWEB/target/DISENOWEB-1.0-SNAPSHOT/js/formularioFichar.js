function ficharProyectos(){
    var numPro = document.getElementById("numeroProyectos").value;
    if(numPro <= 0){
        alert("Introduzca el número de proyectos")
    }else {
        var codigoHTML = '';
        for(var i = 0; i < numPro; i++){
            codigoHTML += '<label>Proyecto'+(i+1).toString()+':</label><br>\n'
            codigoHTML += '<input type="text" id="numeroProyecto'+(i+1).toString()+' placeholder="Introduzca ID proyecto" required</input><br>\n'
        }

        console.log(codigoHTML)

        var generateHere = document.getElementById("generar");
        generateHere.innerHTML = codigoHTML+'<br>';

        enviar();
    }
}

function enviar(){
    var numero = parseInt(document.getElementById("numeroProyectos").value);
    console.log(numero+1);

    for(var i = 1; i < numero+1; i++){
        console.log("hola");
    }
}