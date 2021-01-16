/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import logica.Empresa;
import logica.Usuario;

/**
 *
 * @author Alberto
 */
public class Pruebas {
    public static void main(String[] args) {
        
       ConsultaBd consulta = new ConsultaBd();
       
       Empresa empresa = new Empresa();
       empresa.setCodigoPostal(111111);
       empresa.setCorreo("1111111");
       empresa.setIdEmpresa(1111111111);
       empresa.setNombre("111111");
       empresa.setDireccion("1111111111");
       empresa.setTelefono(111111111);
       
        System.out.println("LA CLASE ES - "+ empresa.getClass().getSimpleName());
       //System.out.println("RESULTADO - "+ consulta.anadirEmpresa(empresa));*/
       
       /*Usuario usuario = new Usuario();
       usuario.setEmail("1111111");
       usuario.setIdUsuario(1);
       usuario.setNombre("111111111");
       usuario.setApellidos("1111111");
       usuario.setTelefono(11111111);
       usuario.setContrasenna("111111111");
       System.out.println("RESULTADO - "+ consulta.darAlta(usuario));*/
       
       //System.out.println("RESULTADO - "+ consulta.modificarUsuario(11111111, "1111", "11111", 1111, "auroraguardia@correo.com", "11111"));
       
       //System.out.println("RESULTADO - "+ consulta.modificarProyecto(34812058, 9658));
       
       //System.out.println("RESULTADO - "+ consulta.borrarEmpresa(1111111111));
       
       //System.out.println("RESULTADO - "+ consulta.anadirProyecto(1, 9658));
       
       //System.out.println("RESULTADO - "+ consulta.borrarProyecto(1));
       
       //System.out.println("RESULTADO - "+ consulta.darBaja("arnaupujol@correo.com"));
    }
}
