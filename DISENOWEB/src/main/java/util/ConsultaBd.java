
package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import logica.Empleado;
import logica.Empresa;
import logica.Proyecto;
import logica.ProyectoEmpleado;
import logica.Usuario;

/**
 *
 * @author Alberto
 */
public class ConsultaBd {
    private Connection conexion;
    
    public String getTipoUsuario(String correo){
        
        String tipo = "";
        
        try{
            conexion = ConexionBd.getConexion();
            Statement s = conexion.createStatement();
            ResultSet rsEmpleado = s.executeQuery("select * from EmpleadoRRHH where Correo="+ correo +";");

            if(rsEmpleado.getRow() == 0){ //Si la consulta es vacia no sera empleado
                ResultSet rsUsuario = s.executeQuery("select * from EmpleadoEmpresa where Correo="+ correo +";");
                
                if(rsUsuario.getRow() == 0){ tipo = "error"; } //Si no es ni usuario ni empleado es error
                else{ tipo = "usuario"; }
                
            }else{ 
                tipo = "empleado"; 
            }
            conexion.close();
        }catch(SQLException error){
            Log.logDb.error("ERROR SQL: "+ error);
            tipo = "error";
        }
        
        return tipo;
    }
    
    public Usuario getUsuario(String correo){
        Usuario usuario = new Usuario();
        try{
            conexion = ConexionBd.getConexion();
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery("select * from EmpleadoEmpresa where Correo="+ correo +";");
            
            while(rs.next()){
                usuario.setEmail(correo);
                usuario.setContrasenna(rs.getString("Contrasenia"));
                usuario.setIdUsuario(rs.getInt("IdEmpleadoEmpresa"));
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setApellidos(rs.getString("Apellidos"));
                usuario.setTelefono(rs.getInt("Telefono"));
                //Para meter la empresa al usuario cogemos el primer proyecto en el que participe,
                //y como solo puede trabajar en una empresa, cogemos el objeto empresa que realiza ese proyecto
                //No puede ser vacia la lista ya que para trabajar en una empresa tienes que estar minimo
                //en un proyecto
                List<ProyectoEmpleado> lista = getListaProyectos(correo);
                usuario.setProyectosList(lista);
                usuario.setEmpresa(lista.get(0).getProyecto().getEmpresa());
            }
            
            conexion.close();
        }catch(SQLException error){
            Log.logDb.error("ERROR SQL: "+ error);
        }
        
        return usuario;
    }
    
    public Empleado getEmpleado(String correo){
        Empleado empleado = new Empleado();
        try{
            conexion = ConexionBd.getConexion();
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery("select * from EmpleadoRRHH where Correo="+ correo +";");
            
            while(rs.next()){
                empleado.setEmail(correo);
                empleado.setContrasenna(rs.getString("Contrasenia"));
                empleado.setIdEmpleado(rs.getInt("IdEmpleadoRRHH"));
                empleado.setNombre(rs.getString("Nombre"));
                empleado.setApellidos(rs.getString("Apellidos"));
                empleado.setTelefono(rs.getInt("Telefono"));
            }
            
            conexion.close();
        }catch(SQLException error){
            Log.logDb.error("ERROR SQL: "+ error);
        }
        
        return empleado;
    }
    
    public Empresa getEmpresa(int idEmpresa){
        Empresa empresa = new Empresa();
        try{
            conexion = ConexionBd.getConexion();
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery("select * from Empresa where IdEmpresa="+ idEmpresa +";");
            
            while(rs.next()){
                empresa.setIdEmpresa(idEmpresa);
                empresa.setNombre(rs.getString("Nombre"));
                empresa.setDireccion(rs.getString("Calle"));
                empresa.setCodigoPostal(rs.getInt("CodigoPostal"));
                empresa.setCorreo(rs.getString("Correo"));
                empresa.setTelefono(rs.getInt("Telefono"));
            }
            
            conexion.close();
        }catch(SQLException error){
            Log.logDb.error("ERROR SQL: "+ error);
        }
        return empresa;
    }
    
    public List<ProyectoEmpleado> getListaProyectos(String correo){
        List<ProyectoEmpleado> lista = new ArrayList<>();
        try{
            conexion = ConexionBd.getConexion();
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery("select * from Proyecto_Empleado where empleado_correo="+ correo +";");
            
            while(rs.next()){
                ProyectoEmpleado proyecto = new ProyectoEmpleado();
                
                proyecto.setHoras(rs.getInt("Horas"));
                proyecto.setProyecto(getProyecto(rs.getInt("proyecto_id_proyecto")));
                lista.add(proyecto);
            }
  
            conexion.close();
        }catch(SQLException error){
            Log.logDb.error("ERROR SQL: "+ error);
        }
        return lista;
    }
    
    public Proyecto getProyecto(int idProyecto){
        Proyecto proyecto = new Proyecto();
        try{
            conexion = ConexionBd.getConexion();
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery("select * from Proyecto where IdProyecto="+ idProyecto +";");
            
            while(rs.next()){
                proyecto.setIdProyecto(idProyecto);
                proyecto.setEmpresa(getEmpresa(rs.getInt("Empresa_IdEmpresa")));
            }
            
            conexion.close();
        }catch(SQLException error){
            Log.logDb.error("ERROR SQL: "+ error);
        }
        
        return proyecto;
    }
    
    //EN ESTA CLASE PONER TODAS LAS CONSULTAS QUE SE PUEDEN HACER EN LA BASE DE DATOS
    //Alta usuario
    //Baja usuario
    //Modificar usuario
    //Buscar usuario por id
    //Buscar empresa
    //Lista usuarios
    //Lista de empresas
    //...
}
