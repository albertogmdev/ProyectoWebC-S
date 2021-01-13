/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function alerta(mensaje){
    alert(mensaje);
}

function valorBoton(){
    //valor = document.getElementById("empresa").value;
    valor = document.querySelector('input[name="empresa"]:checked').value;
    console.log("HOLAAAA " + valor);
}