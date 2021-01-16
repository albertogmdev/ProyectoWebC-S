/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function anadirOpcion(opcion){
    var selectEmpresa = document.getElementById("empresaSelect");
    selectEmpresa.options[selectEmpresa.option.length] = new Option(opcion, opcion);
}
