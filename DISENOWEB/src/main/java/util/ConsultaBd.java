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
        Log.logBd.info("CONSULTA GetTipoUsuario");
        try {
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion - getTipoUsuario()");
            Statement s = conexion.createStatement();
            ResultSet resultadoEmpleado = s.executeQuery("select * from EmpleadoRRHH where Correo='" + correo + "';");
            Log.logBd.info("Realizada consulta - getTipoUsuario()");
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
            Log.logBd.error("ERROR SQL en getTipoUsuario(): " + error);
            Log.logBd.error("                               SQL State - " + error.getSQLState());
            Log.logBd.error("                               ErrorCode - " + error.getErrorCode());

            tipo = "error";
        }

        Log.logBd.info("Consulta realizada con éxito - getTipoUsuario()");
        return tipo;
    }

    public Usuario getUsuario(String correo) {
        Usuario usuario = new Usuario();
        Log.logBd.info("CONSULTA GetUsuario");
        try {
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion - getUsuario()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from EmpleadoEmpresa where Correo='" + correo + "';");
            Log.logBd.info("Realizada consulta - getUsuario()");

            while (resultado.next()) {
                usuario.setEmail(correo);
                usuario.setContrasenna(resultado.getString("Contrasenia"));
                usuario.setIdUsuario(resultado.getInt("IdEmpleadoEmpresa"));
                usuario.setNombre(resultado.getString("Nombre"));
                usuario.setApellidos(resultado.getString("Apellidos"));
                usuario.setTelefono(resultado.getInt("Telefono"));
                List<ProyectoEmpleado> lista = getListaProyectos(correo);
                usuario.setProyectosList(lista);
                //Todos los usuarios estan en almenos un proyecto y solo estan en una empresa, por ello,
                //cogemos la empresa del primer proyecto en el participe el usuario
                usuario.setEmpresa(lista.get(0).getProyecto().getEmpresa());
            }

        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getUsuario(): " + error);
            Log.logBd.error("                           SQL State - " + error.getSQLState());
            Log.logBd.error("                           ErrorCode - " + error.getErrorCode());
        }

        Log.logBd.info("Consulta realizada con éxito - getUsuario()");
        return usuario;
    }

    public Empleado getEmpleado(String correo) {
        Empleado empleado = new Empleado();
        Log.logBd.info("CONSULTA GetEmpleado");
        try {
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion - getEmpleado()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from EmpleadoRRHH where Correo='" + correo + "';");
            Log.logBd.info("Realizada consulta - getEmpleado()");

            while (resultado.next()) {
                empleado.setEmail(correo);
                empleado.setContrasenna(resultado.getString("Contrasenia"));
                empleado.setIdEmpleado(resultado.getInt("IdEmpleadoRRHH"));
                empleado.setNombre(resultado.getString("Nombre"));
                empleado.setApellidos(resultado.getString("Apellidos"));
                empleado.setTelefono(resultado.getInt("Telefono"));
            }
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getEmpleado(): " + error);
            Log.logBd.error("                            SQL State - " + error.getSQLState());
            Log.logBd.error("                            ErrorCode - " + error.getErrorCode());
        }

        Log.logBd.info("Consulta realizada con éxito - getEmpleado()");
        return empleado;
    }

    public Empresa getEmpresa(int idEmpresa) {
        Empresa empresa = new Empresa();
        Log.logBd.info("CONSULTA GetEmpresa");
        try {
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion - getEmpresa()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from Empresa where IdEmpresa=" + idEmpresa + ";");
            Log.logBd.info("Realizada consulta - getEmpresa()");

            while (resultado.next()) {
                empresa.setIdEmpresa(idEmpresa);
                empresa.setNombre(resultado.getString("Nombre"));
                empresa.setDireccion(resultado.getString("Calle"));
                empresa.setCodigoPostal(resultado.getInt("CodigoPostal"));
                empresa.setCorreo(resultado.getString("Correo"));
                empresa.setTelefono(resultado.getInt("Telefono"));
            }
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getEmpresa(): " + error);
            Log.logBd.error("                           SQL State - " + error.getSQLState());
            Log.logBd.error("                           ErrorCode - " + error.getErrorCode());
        }

        Log.logBd.info("Consulta realizada con éxito - getEmpresa()");
        return empresa;
    }

    public List<ProyectoEmpleado> getListaProyectos(String correo) {
        List<ProyectoEmpleado> lista = new ArrayList<>();
        Log.logBd.info("CONSULTA GetListaProyectos");
        try {
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion - getListaProyectos()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from Proyecto_Empleado where empleado_correo='" + correo+ "';");
            Log.logBd.info("Realizada consulta - getListaProyectos()");

            while (resultado.next()) {
                ProyectoEmpleado proyecto = new ProyectoEmpleado();

                proyecto.setHoras(resultado.getInt("Horas"));
                proyecto.setProyecto(getProyecto(resultado.getInt("proyecto_id_proyecto")));

                lista.add(proyecto);
            }
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getListaProyectos(): " + error);
            Log.logBd.error("                                  SQL State - " + error.getSQLState());
            Log.logBd.error("                                  ErrorCode - " + error.getErrorCode());
        }

        Log.logBd.info("Consulta realizada con éxito - getListaProyectos()");
        return lista;
    }

    public Proyecto getProyecto(int idProyecto) {
        Proyecto proyecto = new Proyecto();
        Log.logBd.info("CONSULTA GetProyecto");
        try {
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion- getProyecto()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from Proyecto where IdProyecto=" + idProyecto + ";");
            Log.logBd.info("Realizada consulta - getProyecto()");

            while (resultado.next()) {
                proyecto.setIdProyecto(idProyecto);
                proyecto.setEmpresa(getEmpresa(resultado.getInt("Empresa_IdEmpresa")));
            }
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getProyecto(): " + error);
            Log.logBd.error("                            SQL State - " + error.getSQLState());
            Log.logBd.error("                            ErrorCode - " + error.getErrorCode());
        }

        Log.logBd.info("Consulta realizada con éxito - getProyecto()");
        
        return proyecto;
    }

    public List mostrarEmpleados() {
        ArrayList<Usuario> lista_empleados = new ArrayList<>();
        Log.logBd.info("CONSULTA MostrarEmpleados");
        try {
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion - mostrarEmpleados()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from empleadoempresa");
            Log.logBd.info("Realizada consulta - mostrarEmpleados()");

            while (resultado.next()) {
                Usuario usuario = new Usuario();

                usuario.setIdUsuario(resultado.getInt("IdEmpleadoEmpresa"));
                usuario.setNombre(resultado.getString("Nombre"));
                usuario.setApellidos(resultado.getString("Apellidos"));
                usuario.setEmail(resultado.getString("Correo"));
                usuario.setTelefono(resultado.getInt("Telefono"));
                usuario.setContrasenna(resultado.getString("Contrasenia"));
                List<ProyectoEmpleado> lista = getListaProyectos(resultado.getString("Correo"));
                usuario.setProyectosList(lista);
                

               if(usuario.getProyectosList().size()>0){
                   usuario.setEmpresa(usuario.getProyectosList().get(0).getProyecto().getEmpresa());
               }
               
               lista_empleados.add(usuario);
            }
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en mostrarEmpleados(): " + error);
            Log.logBd.error("                                 SQL State - " + error.getSQLState());
            Log.logBd.error("                                 ErrorCode - " + error.getErrorCode());
        }

        Log.logBd.info("Consulta realizada con éxito - mostrarEmpleados()");
        return lista_empleados;
    }

    public List mostrarEmpresa() {
        ArrayList<Empresa> lista_empresas = new ArrayList<>();
        Log.logBd.info("CONSULTA MostrarEmpresa");
        try {
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion - mostrarEmpresa()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from empresa");
            Log.logBd.info("Realizada consulta - mostrarEmpresa()");

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
            Log.logBd.error("ERROR SQL en mostrarEmpresa(): " + error);
            Log.logBd.error("                               SQL State - " + error.getSQLState());
            Log.logBd.error("                               ErrorCode - " + error.getErrorCode());
        }

        Log.logBd.info("Consulta realizada con éxito - mostrarEmpresa()");
        return lista_empresas;
    }

    public List mostrarProyecto() {
        ArrayList<Proyecto> lista_proyectos = new ArrayList<>();
        Log.logBd.info("CONSULTA MostrarProyecto");
        try {
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion - mostrarProyecto()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from proyecto");
            Log.logBd.info("Realizada consulta - mostrarProyecto()");

            while (resultado.next()) {
                Proyecto proyecto = new Proyecto();
                Empresa empresa = new Empresa();

                proyecto.setIdProyecto(resultado.getInt("IdProyecto"));
                empresa.setIdEmpresa(resultado.getInt("Empresa_IdEmpresa"));
                proyecto.setEmpresa(empresa);

                lista_proyectos.add(proyecto);
            }
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en mostrarProyecto(): " + error);
            Log.logBd.error("                                SQL State - " + error.getSQLState());
            Log.logBd.error("                                ErrorCode - " + error.getErrorCode());
        }

        Log.logBd.info("Consulta realizada con éxito - mostrarProyecto()");
        return lista_proyectos;
    }

    public boolean darAlta(Usuario usuario) {
        Log.logBd.info("CONSULTA DarAlta");
        if (getUsuario(usuario.getEmail()).getEmail().equalsIgnoreCase(usuario.getEmail())) {
            return false;
        } else {
            try {
                conexion = ConexionBd.getConexion();
                Log.logBd.info("Realizada conexion - darAlta()");
                Statement s = conexion.createStatement();
                s.executeQuery("INSERT INTO EmpleadoEmpresa(IdEmpleadoEmpresa, Nombre, Apellidos, Telefono, Correo, Contrasenia) VALUES ('" + usuario.getIdUsuario() + "','" + usuario.getNombre() 
                        + "','" + usuario.getApellidos() + "','" + usuario.getTelefono() + "','" + usuario.getEmail() + "','" + usuario.getContrasenna() +"')");
                Log.logBd.info("Realizada consulta - darAlta()");
                
            } catch (SQLException error) {
                Log.logBd.error("ERROR SQL en darAlta(): " + error);
                Log.logBd.error("                        SQL State - " + error.getSQLState());
            Log.logBd.error("                            ErrorCode - " + error.getErrorCode());
            }
            
            Log.logBd.info("Consulta realizada con éxito - darAlta()");
            return true;
        }
    }

    public boolean darBaja(int id) {
        boolean hecho = false;
        Log.logBd.info("CONSULTA DarBaja");
        try {
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion - darBaja()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from EmpleadoEmpresa where IdEmpleadoEmpresa=" + id);

            //Si existe le damos de baja
            if(resultado.next()){
                s.executeQuery("delete from EmpleadoEmpresa where IdEmpleadoEmpresa=" + id);
                Log.logBd.info("Realizada consulta - darBaja()");
            }else {
                Log.logBd.error("Usuario no existe en la BD - darBaja()");
            }
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en darBaja(): " + error);
            Log.logBd.error("                        SQL State - " + error.getSQLState());
            Log.logBd.error("                        ErrorCode - " + error.getErrorCode());
        }

        return hecho;
    }

    public Usuario generarId(Usuario usuario) {
        Log.logBd.info("CONSULTA GenerarId");
        try {
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion - generarId()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select max(IdEmpleadoEmpresa) from EmpleadoEmpresa");
            Log.logBd.info("Realizada consulta - generarId()");
            
            while (resultado.next()) {
                int num = resultado.getInt("max(IdEmpleadoEmpresa)");
                usuario.setIdUsuario(num + 1);
            }
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en generarId(): " + error);
            Log.logBd.error("                          SQL State - " + error.getSQLState());
            Log.logBd.error("                          ErrorCode - " + error.getErrorCode());
        }
        
        Log.logBd.info("Consulta realizada con éxito - generarId()");
        return usuario;
    }
    
    public void modificarUsuario(int idUsuario, String nombre, String apellidos, int telefono, String correo, String contrasenna){
        Log.logBd.info("CONSULTA ModificarUsuario");
        try{
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion - modificarUsuario()");
            Statement s = conexion.createStatement();
            s.executeQuery("update empleadoempresa set Nombre ='"+ nombre +"', Apellidos='"+ apellidos +"', Telefono="+ telefono
            +", Correo='"+ correo +"', Contrasenia='"+ contrasenna +"' where IdEmpleadoEmpresa="+ idUsuario +";");
            Log.logBd.info("Realizada consulta - modificarUsuario()");
            
        } catch(SQLException error){
            Log.logBd.error("ERROR SQL en modificarUsuario(): " + error);
            Log.logBd.error("                                 SQL State - " + error.getSQLState());
            Log.logBd.error("                                 ErrorCode - " + error.getErrorCode());
        }
    }
    
    public void modificarEmpresa(int idEmpresa, String nombre, String calle, int codigoPostal, String correo, int telefono){
        Log.logBd.info("CONSULTA ModificarEmpresa");
        try{
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion - modificarEmpresa()");
            Statement s = conexion.createStatement();
            s.executeQuery("update empresa set Nombre ='"+ nombre +"', Calle='"+ calle +"', CodigoPostal="+ codigoPostal
            +", Correo='"+ correo +"', Telefono="+ telefono +" where IdEmpresa="+ idEmpresa +";");
            Log.logBd.info("Realizada consulta - modificarEmpresa()");
            
        } catch(SQLException error){
            Log.logBd.error("ERROR SQL en modificarEmpresa(): " + error);
            Log.logBd.error("                                 SQL State - " + error.getSQLState());
            Log.logBd.error("                                 ErrorCode - " + error.getErrorCode());
        }
    }
    
    public boolean modificarProyecto(int idProyecto, int idEmpresa){
        boolean hecho = false;
        Log.logBd.info("CONSULTA ModificarProyecto");
        try{
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion - modificarProyecto()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select Empresa_IdEmpresa from proyecto where IdProyecto="+ idProyecto +";");
            
            //Solo si la empresa a la que se quiere cambiar el proyecto es la diferente a la que esta el proyecto se modifica
            if(resultado.getInt("Empresa_IdEmpresa") != idEmpresa){
                s.executeQuery("update proyecto set Empresa_IdEmpresa="+ idEmpresa +" where IdProyecto="+ idProyecto +";");
                hecho = true;
                Log.logBd.info("Realizada consulta - modificarProyecto()");
            }
            
        } catch(SQLException error){
            Log.logBd.error("ERROR SQL en modificarProyecto(): " + error);
            Log.logBd.error("                                  SQL State - " + error.getSQLState());
            Log.logBd.error("                                  ErrorCode - " + error.getErrorCode());
        }
        
        return hecho;
    }
    
    public void borrarProyecto(int idProyecto){
        Log.logBd.info("CONSULTA BorrarProyecto");
        try{
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion - borrarProyecto()");
            Statement s = conexion.createStatement();
            s.executeQuery("delete from proyecto where IdProyecto="+ idProyecto +";");
            Log.logBd.info("Realizada consulta - borrarProyecto()");
            
        } catch(SQLException error){
            Log.logBd.error("ERROR SQL en borrarProyecto(): " + error);
            Log.logBd.error("                               SQL State - " + error.getSQLState());
            Log.logBd.error("                               ErrorCode - " + error.getErrorCode());
        }
    }
}
