/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


document.getElementById("box").style.display="none";
function mostrarDatepicker() {
    var form_elements = document.getElementById('informe').elements;
    var selected = form_elements['tiempo'].value;
    
    var text = document.getElementById("box");
    if (selected === "otro"){
        text.style.display = "block";
    } else {
       text.style.display = "none";
    }
}