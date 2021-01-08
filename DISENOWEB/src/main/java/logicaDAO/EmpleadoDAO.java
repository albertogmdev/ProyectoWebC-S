/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import logica.Empleado;
import logica.Empresa;
import logica.Usuario;
import util.ConexionBd;
import interfaces.CRUDempleado;

/**
 *
 * @author ccris
 */
public class EmpleadoDAO implements CRUDempleado{
    ConexionBd con = new ConexionBd();
    Connection conexion;
    ResultSet resultado;
    PreparedStatement consulta;
    Empleado emp = new Empleado();


    public List mostrarEmp() {
        ArrayList<Empleado> lista_empleados=new ArrayList<>();
        String sql="select * from empleadoempresa";
        
        try{
            conexion=con.getConexion();
            consulta=conexion.prepareStatement(sql);
            resultado=consulta.executeQuery();
            while(resultado.next()){
                Empleado empleado=new Empleado();
                Empresa empresa=new Empresa();
                empleado.setIdEmpleado(resultado.getInt("IdEmpleadoEmpresa"));
                empleado.setNombre(resultado.getString("Nombre"));
                empleado.setApellidos(resultado.getString("Apellidos"));
                empleado.setEmail(resultado.getString("Correo"));
                empleado.setTelefono(resultado.getInt("Telefono"));
                empleado.setContrasenna(resultado.getString("Contrasenia"));
                empleado.setEmpresa(empresa);
                lista_empleados.add(empleado);
            }
            
        }
        catch(Exception e){
            
        }
        
        
        
        return lista_empleados;
        
        
    }

   
    public Usuario mostrar(int id) {
        return null;
 
    }

   
    
}
