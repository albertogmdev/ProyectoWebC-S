/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Alberto
 */
public class Pruebas {
    public static void main(String[] args) {
        ConsultaBd consulta = new ConsultaBd();
        
        String tipo = consulta.getTipoUsuario("alicianuñez@correo.com", "1234");
        System.out.println(tipo);
        String tipo1 = consulta.getTipoUsuario("carmenlozano@correo.com", "5678");
        System.out.println(tipo1);
        String tipo2 = consulta.getTipoUsuario("1", "1234");
        System.out.println(tipo2);
        String tipo3 = consulta.getTipoUsuario("alicianuñez@correo.com", "1aaa234");
        System.out.println(tipo3);
    }
}
