/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.List;
import logica.ProyectoEmpleado;
import logica.Usuario;

/**
 *
 * @author Alberto
 */
public class Pruebas {
    public static void main(String[] args) {
        ConsultaBd consulta = new ConsultaBd();
        Usuario usuario = consulta.getUsuario("alicianuñez@correo.com");
        System.out.println(usuario.toString());
                
    }
}

