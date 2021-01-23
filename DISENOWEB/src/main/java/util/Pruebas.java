/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Locale;

/**
 *
 * @author Alberto
 */
public class Pruebas {
    public static void main(String[] args) throws ParseException, SQLException {
        
       ConsultaBd consulta = new ConsultaBd();
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
      DateTimeFormatter f= DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        String dateInString = "2098-03-02";
        Time hora_entrada=Time.valueOf("15:00:00");
        Time hora_salida=Time.valueOf("18:00:00");
       
       LocalDate da=LocalDate.parse(dateInString);
       
     
        
        //System.out.println("S "+java.sql.Date.valueOf(da));
        //System.out.println(hora_entrada);
        //System.out.println(hora_salida);
        Connection conexion;
        conexion=ConexionBd.getConexion();
        System.out.print(consulta.solicitarDiaLibre(Date.valueOf(dateInString), Date.valueOf(dateInString), "asd", "franciscocabrera@correo.com"));
        System.out.print(consulta.aprobarSolicitud(true, true, true, Date.valueOf(dateInString), Date.valueOf(dateInString), "franciscocabrera@correo.com"));
        //Statement s = conexion.createStatement();
        //int c=s.executeUpdate("INSERT INTO Calendario VALUES('"+Date.valueOf(dateInString)+"','"+hora_entrada+"','"+hora_salida+"','franciscocabrera@correo.com');");
        //System.out.print(c);
       // String correo="franciscocabrera@correo.com";
       
     // System.out.print(consulta.ficharEmpleado(Date.valueOf(dateInString), hora_entrada, hora_salida, correo, 123456789));
      //System.out.print(s.executeUpdate("UPDATE Proyecto_Empleado SET horas="+5+" where proyecto_id_proyecto="+123456789+" and empleado_correo='"+correo+"';"));
      /* Empresa empresa = new Empresa();
       empresa.setCodigoPostal(111111);
       empresa.setCorreo("1111111");
       empresa.setIdEmpresa(1111111111);
       empresa.setNombre("111111");
       empresa.setDireccion("1111111111");
       empresa.setTelefono(111111111);
       
        System.out.println("LA CLASE ES - "+ empresa.getClass().getSimpleName());*/
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
