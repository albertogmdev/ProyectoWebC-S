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

    public String getTipoUsuario(String correo, String contrasenna) {

        String tipo = "";
        Log.logBd.info("CONSULTA - getTipoUsuario");

        try {
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion");
            Statement s = conexion.createStatement();
            ResultSet resultadoEmpleado = s.executeQuery("select * from EmpleadoRRHH where Correo='" + correo + "';");
            Log.logBd.info("Realizada consulta");
            if (resultadoEmpleado.next()) {
                if (resultadoEmpleado.getString("Contrasenia").equalsIgnoreCase(contrasenna)) {
                    tipo = "empleado";
                } else {
                    tipo = "error";
                }
            } else {
                ResultSet resultadoUsuario = s.executeQuery("select * from EmpleadoEmpresa where Correo='" + correo + "';");  //Si no es ni usuario ni empleado es error

                if (resultadoUsuario.next()) {
                    if (resultadoUsuario.getString("Contrasenia").equalsIgnoreCase(contrasenna)) {
                        tipo = "usuario";
                    } else {
                        tipo = "error";
                    }
                } else {
                    tipo = "error";
                }
            }
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL: " + error);
            tipo = "error";
        }

        Log.logBd.info("Consulta realizada con éxito");
        return tipo;
    }

    public Usuario getUsuario(String correo) {
        Usuario usuario = new Usuario();
        Log.logBd.info("CONSULTA - getUsuario");
        try {
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from EmpleadoEmpresa where Correo='" + correo + "';");
            Log.logBd.info("Realizada consulta");

            while (resultado.next()) {
                usuario.setEmail(correo);
                usuario.setContrasenna(resultado.getString("Contrasenia"));
                usuario.setIdUsuario(resultado.getInt("IdEmpleadoEmpresa"));
                usuario.setNombre(resultado.getString("Nombre"));
                usuario.setApellidos(resultado.getString("Apellidos"));
                usuario.setTelefono(resultado.getInt("Telefono"));
                //Para meter la empresa al usuario cogemos el primer proyecto en el que participe,
                //y como solo puede trabajar en una empresa, cogemos el objeto empresa que realiza ese proyecto
                //No puede ser vacia la lista ya que para trabajar en una empresa tienes que estar minimo
                //en un proyecto
                //List<ProyectoEmpleado> lista = getListaProyectos(correo);
                //usuario.setProyectosList(lista);
                //usuario.setEmpresa(lista.get(0).getProyecto().getEmpresa());
            }

        } catch (SQLException error) {
            System.out.println("ERROR SQL: " + error);
            Log.logBd.error("ERROR SQL: " + error);
        }

        Log.logBd.info("Consulta realizada con éxito");
        return usuario;
    }

    public Empleado getEmpleado(String correo) {
        Empleado empleado = new Empleado();
        Log.logBd.info("CONSULTA - getEmpleado");
        try {
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from EmpleadoRRHH where Correo='" + correo + "';");
            Log.logBd.info("Realizada consulta");

            while (resultado.next()) {
                empleado.setEmail(correo);
                empleado.setContrasenna(resultado.getString("Contrasenia"));
                empleado.setIdEmpleado(resultado.getInt("IdEmpleadoRRHH"));
                empleado.setNombre(resultado.getString("Nombre"));
                empleado.setApellidos(resultado.getString("Apellidos"));
                empleado.setTelefono(resultado.getInt("Telefono"));
            }
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL: " + error);
        }
        
        Log.logBd.info("Consulta realizada con éxito");
        return empleado;
    }

    public Empresa getEmpresa(int idEmpresa) {
        Empresa empresa = new Empresa();
        Log.logBd.info("CONSULTA - getEmpresa");
        try {
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from Empresa where IdEmpresa=" + idEmpresa + ";");
            Log.logBd.info("Realizada consulta");

            while (resultado.next()) {
                empresa.setIdEmpresa(idEmpresa);
                empresa.setNombre(resultado.getString("Nombre"));
                empresa.setDireccion(resultado.getString("Calle"));
                empresa.setCodigoPostal(resultado.getInt("CodigoPostal"));
                empresa.setCorreo(resultado.getString("Correo"));
                empresa.setTelefono(resultado.getInt("Telefono"));
            }
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL: " + error);
        }
        
        Log.logBd.info("Consulta realizada con éxito");
        return empresa;
    }

    public List<ProyectoEmpleado> getListaProyectos(String correo) {
        List<ProyectoEmpleado> lista = new ArrayList<>();
        Log.logBd.info("CONSULTA - getListaProyectos");
        try {
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from Proyecto_Empleado where empleado_correo='" + correo + "';");
            Log.logBd.info("Realizada consulta");

            while (resultado.next()) {
                ProyectoEmpleado proyecto = new ProyectoEmpleado();

                proyecto.setHoras(resultado.getInt("Horas"));
                proyecto.setProyecto(getProyecto(resultado.getInt("proyecto_id_proyecto")));
                
                lista.add(proyecto);
            }
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL: " + error);
        }
        
        Log.logBd.info("Consulta realizada con éxito");
        return lista;
    }

    public Proyecto getProyecto(int idProyecto) {
        Proyecto proyecto = new Proyecto();
        Log.logBd.info("CONSULTA - getProyecto");
        try {
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from Proyecto where IdProyecto=" + idProyecto + ";");
            Log.logBd.info("Realizada consulta");

            while (resultado.next()) {
                proyecto.setIdProyecto(idProyecto);
                proyecto.setEmpresa(getEmpresa(resultado.getInt("Empresa_IdEmpresa")));
            }
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL: " + error);
        }
        
        Log.logBd.info("Consulta realizada con éxito");
        return proyecto;
    }

    public List mostrarEmpleados() {
        ArrayList<Empleado> lista_empleados = new ArrayList<>();
        Log.logBd.info("CONSULTA - mostrarEmpleados");
        try {
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from empleadoempresa");
            Log.logBd.info("Realizada consulta");
            
            while (resultado.next()) {
                Empleado empleado = new Empleado();
                Empresa empresa = new Empresa();
                
                empleado.setIdEmpleado(resultado.getInt("IdEmpleadoEmpresa"));
                empleado.setNombre(resultado.getString("Nombre"));
                empleado.setApellidos(resultado.getString("Apellidos"));
                empleado.setEmail(resultado.getString("Correo"));
                empleado.setTelefono(resultado.getInt("Telefono"));
                empleado.setContrasenna(resultado.getString("Contrasenia"));
                empleado.setEmpresa(empresa);
                
                lista_empleados.add(empleado);
            }
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL: " + error);
        }
        
        Log.logBd.info("Consulta realizada con éxito");
        return lista_empleados;
    }
    
    public List mostrarEmpresa() {
        ArrayList<Empresa> lista_empresas = new ArrayList<>();
        Log.logBd.info("CONSULTA - mostrarEmpresa");
        try {
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from empresa");
            Log.logBd.info("Realizada consulta");
            
            while (resultado.next()) {
                Empresa empresa = new Empresa();
                
                empresa.setCodigoPostal(resultado.getInt("CodigoPostal"));
                empresa.setCorreo(resultado.getString("Correo"));
                empresa.setDireccion(resultado.getString("Calle"));
                empresa.setIdEmpresa(resultado.getInt("IdEmpresa"));
                empresa.setNombre(resultado.getString("Nombre"));
                empresa.setTelefono(resultado.getInt("Telefono"));
                
                lista_empresas.add(empresa);
            }
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL: " + error);
        }

        Log.logBd.info("Consulta realizada con éxito");
        return lista_empresas;
    }
    
     public List mostrarProyecto() {
        ArrayList<Proyecto> lista_proyectos = new ArrayList<>();
        Log.logBd.info("CONSULTA - mostrarProyecto");
        try {
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from proyecto");
            Log.logBd.info("Realizada consulta");
            
            while (resultado.next()) {
                Proyecto proyecto = new Proyecto();
                Empresa empresa = new Empresa();
                
                proyecto.setIdProyecto(resultado.getInt("IdProyecto"));
                empresa.setIdEmpresa(resultado.getInt("Empresa_IdEmpresa"));
                proyecto.setEmpresa(empresa);
                
                lista_proyectos.add(proyecto);
            }
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL: " + error);
        }

        Log.logBd.info("Consulta realizada con éxito");
        return lista_proyectos;
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
