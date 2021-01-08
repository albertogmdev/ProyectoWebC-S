/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import logica.Empresa;
import logica.Proyecto;

/**
 *
 * @author Alberto
 */
public class Pruebas {
    public static void main(String[] args) {
        
       /* ConsultaBd consulta = new ConsultaBd();
        
        Log.log.log("hola");
        Log.logBd.log("holaBd");
        Log.log.log("que tal estas");
        Log.logBd.log("que tal estasBd");
        Log.log.log("cierro");
        Log.logBd.log("cierroBd");
        
        String tipo = consulta.getTipoUsuario("alicianuñez@correo.com", "1234");
        System.out.println(tipo);
        String tipo1 = consulta.getTipoUsuario("carmenlozano@correo.com", "5678");
        System.out.println(tipo1);
        String tipo2 = consulta.getTipoUsuario("1", "1234");
        System.out.println(tipo2);
        String tipo3 = consulta.getTipoUsuario("alicianuñez@correo.com", "1aaa234");
        System.out.println(tipo3);*/
        Empresa emp= new Empresa();
        emp.setIdEmpresa(234234);
        Proyecto p=new Proyecto();
        p.setEmpresa(emp);
        
        System.out.println(emp.getIdEmpresa());
        System.out.println(p.getEmpresa().getIdEmpresa());
        
    }
}
