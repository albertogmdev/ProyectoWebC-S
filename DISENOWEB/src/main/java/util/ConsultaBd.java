package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
                if(lista.isEmpty()){
                    Log.logBd.error("El usuario correo("+ correo +") no tiene proyectos");
                }
                else{
                    usuario.setEmpresa(lista.get(0).getProyecto().getEmpresa());
                }
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
            Log.logBd.error("               SQL State - " + error.getSQLState());
            Log.logBd.error("               ErrorCode - " + error.getErrorCode());
        }

        Log.logBd.info("Consulta realizada con éxito - getEmpresa()");
        return empresa;
    }
    
    public Empresa getEmpresaProyecto(int idProyecto) {
        Empresa empresa = new Empresa();
        Log.logBd.info("CONSULTA GetEmpresaProyecto");
        try {
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion - getEmpresaProyecto()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select distinct * from proyecto where IdProyecto=" + idProyecto + ";");
            Log.logBd.info("Realizada consulta - getEmpresaProyecto()");

            while (resultado.next()) {
                int idEmpresa = resultado.getInt("Empresa_IdEmpresa");
                empresa = getEmpresa(idEmpresa);
            }
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getEmpresaProyecto(): " + error);
            Log.logBd.error("                       SQL State - " + error.getSQLState());
            Log.logBd.error("                       ErrorCode - " + error.getErrorCode());
        }

        Log.logBd.info("Consulta realizada con éxito - getEmpresaProyecto()");
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
    
    public Usuario getUsuarioById(int idEmpleadoEmpresa) {
        Usuario usuario = new Usuario();
        Log.logBd.info("CONSULTA getUsuarioById");
        try {
            conexion = ConexionBd.getConexion();
            PreparedStatement preparedStatement = conexion.prepareStatement("select * from EmpleadoEmpresa where IdEmpleadoEmpresa = ?;");
            preparedStatement.setInt(1, idEmpleadoEmpresa);
            ResultSet resultado = preparedStatement.executeQuery();
            Log.logBd.info("Realizada conexion- getUsuarioById()");
           
       
             if (resultado.next()) {
                usuario.setIdUsuario(resultado.getInt("IdEmpleadoEmpresa"));
                usuario.setNombre(resultado.getString("Nombre"));
                usuario.setApellidos(resultado.getString("Apellidos"));
                usuario.setEmail(resultado.getString("Correo"));
                usuario.setTelefono(resultado.getInt("Telefono"));
                usuario.setContrasenna(resultado.getString("Contrasenia"));
               
             }
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getUsuarioById(): " + error);
            Log.logBd.error("                   SQL State - " + error.getSQLState());
            Log.logBd.error("                   ErrorCode - " + error.getErrorCode());
        }

        Log.logBd.info("Consulta realizada con éxito - getUsuarioById()");
    
        return usuario;
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
    
    public List getProyectoEmpresa(int idEmpresa) {
        ArrayList<Proyecto> lista_proyectos = new ArrayList<>();
        Log.logBd.info("CONSULTA getProyectoEmpresa");
        try {
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion - getProyectoEmpresa()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from proyecto where Empresa_IdEmpresa="+ idEmpresa);
            Log.logBd.info("Realizada consulta - getProyectoEmpresa()");

            while (resultado.next()) {
                Proyecto proyecto = new Proyecto();
                Empresa empresa = new Empresa();

                proyecto.setIdProyecto(resultado.getInt("IdProyecto"));
                empresa.setIdEmpresa(resultado.getInt("Empresa_IdEmpresa"));
                proyecto.setEmpresa(empresa);

                lista_proyectos.add(proyecto);
            }
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getProyectoEmpresa(): " + error);
            Log.logBd.error("                                SQL State - " + error.getSQLState());
            Log.logBd.error("                                ErrorCode - " + error.getErrorCode());
        }

        Log.logBd.info("Consulta realizada con éxito - getProyectoEmpresa()");
        return lista_proyectos;
    }

    public boolean darAlta(Usuario usuario) {
        Log.logBd.info("CONSULTA DarAlta");
        boolean hecho = false;
        try {
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion - darAlta()");
            
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select Correo from empleadoempresa");
            List<String> listaCorreos = new ArrayList<>();
            while(resultado.next()){
                listaCorreos.add(resultado.getString("Correo"));
            }
            
            //Si no existe el usuario lo damos de alta
            if(!listaCorreos.contains(usuario.getEmail())){
                Statement s1 = conexion.createStatement();
                s1.executeUpdate("INSERT INTO EmpleadoEmpresa(IdEmpleadoEmpresa, Nombre, Apellidos, Telefono, Correo, Contrasenia) VALUES ('" + usuario.getIdUsuario() + "','" + usuario.getNombre() 
                        + "','" + usuario.getApellidos() + "','" + usuario.getTelefono() + "','" + usuario.getEmail() + "','" + usuario.getContrasenna() +"')");
                Statement s2 = conexion.createStatement();
                //Insertamos el proyecto en el que se ha dado de alta al usuario
                Proyecto proyecto = usuario.getProyectosList().get(0).getProyecto();
                s2.executeUpdate("INSERT INTO proyecto_empleado(Horas, proyecto_id_proyecto, empleado_correo) VALUES ("+ 0 +","+ proyecto.getIdProyecto() +",'"+ usuario.getEmail() +"')");
                Log.logBd.info("Usuario correo("+ usuario.getEmail() +") dado de alta");
                hecho = true;
            }
            else{
                Log.logBd.error("Usuario correo("+ usuario.getEmail() +") ya esta dado de alta");
            }

        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en darAlta(): " + error);
            Log.logBd.error("                        SQL State - " + error.getSQLState());
            Log.logBd.error("                        ErrorCode - " + error.getErrorCode());
        }
        
        return hecho;
    }

    public boolean darBaja(String correo) {
        boolean hecho = false;
        Log.logBd.info("CONSULTA DarBaja");
        try {
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion - darBaja()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from EmpleadoEmpresa where Correo='"+ correo +"';");

            //Si existe le damos de baja y borramos sus entradas en la tabla proyecto_empleado
            if(resultado.next()){
                s.executeUpdate("delete from proyecto_empleado where empleado_correo='"+ correo +"';");
                int codigo = s.executeUpdate("delete from EmpleadoEmpresa where Correo='"+ correo +"';");
                if(codigo > 0){
                    Log.logBd.info("Usuario correo("+ correo +") dado de baja");
                    hecho = true;
                }
                else{
                    Log.logBd.info("No se han podido borrar los proyecto del usuario correo("+ correo +")");
                }
            }else {
                Log.logBd.error("Usuario no existe en la BD");
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
    
    
    public boolean modificarUsuario(int idUsuario, String nombre, String apellidos, int telefono, String correo, String contrasenna){
        Log.logBd.info("CONSULTA ModificarUsuario");
        boolean hecho = false;
        try{
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion - modificarUsuario()");
            Statement s = conexion.createStatement();
            int codigo = s.executeUpdate("update empleadoempresa set Nombre ='"+ nombre +"', Apellidos='"+ apellidos +"', Telefono="+ telefono
            +", Correo='"+ correo +"', Contrasenia='"+ contrasenna +"' where Correo='"+ correo +"';");
            
            //Si la consulta se ha realizado correctamente
            if(codigo > 0){
                hecho = true;
                Log.logBd.info("Realizada consulta - modificarUsuario()");
            }else{
                Log.logBd.info("Consulta no ha alterado la tabla o consulta errónea - modificarUsuario() - cod."+ codigo);
            }
        } catch(SQLException error){
            Log.logBd.error("ERROR SQL en modificarUsuario(): " + error);
            Log.logBd.error("                                 SQL State - " + error.getSQLState());
            Log.logBd.error("                                 ErrorCode - " + error.getErrorCode());
        }
        
        return hecho;
    }
    
    public boolean modificarEmpresa(int idEmpresa, String nombre, String calle, int codigoPostal, String correo, int telefono){
        Log.logBd.info("CONSULTA ModificarEmpresa");
        boolean hecho = false;
        try{
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion - modificarEmpresa()");
            Statement s = conexion.createStatement();
            int codigo = s.executeUpdate("UPDATE empresa set Nombre ='"+ nombre +"', Calle='"+ calle +"', CodigoPostal="+ codigoPostal
            +", Correo='"+ correo +"', Telefono="+ telefono +" where IdEmpresa="+ idEmpresa +";");
            
            //Si la consulta se ha realizado correctamente
            if(codigo > 0){
                hecho = true;
                Log.logBd.info("Realizada consulta - modificarEmpresa()");
            }else{
                Log.logBd.info("Consulta no ha alterado la tabla o consulta errónea - modificarEmpresa() - cod."+ codigo);
            }
        } catch(SQLException error){
            Log.logBd.error("ERROR SQL en modificarEmpresa(): " + error);
            Log.logBd.error("                                 SQL State - " + error.getSQLState());
            Log.logBd.error("                                 ErrorCode - " + error.getErrorCode());
        }
        
        return hecho;
    }
    
    public boolean modificarProyecto(int idProyecto, int idEmpresa){
        boolean hecho = false;
        Log.logBd.info("CONSULTA ModificarProyecto");
        try{
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion - modificarProyecto()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select Empresa_IdEmpresa from proyecto where IdProyecto="+ idProyecto +";");
            
            //Hacemos una lista con los id de las empresas para comprobar que se elige una empresa que esta en la BD
            Statement s1 = conexion.createStatement();
            ResultSet resultado1 = s1.executeQuery("select Empresa_IdEmpresa from proyecto");
            List<Integer> lista = new ArrayList<>();
            while(resultado1.next()){
                lista.add(resultado1.getInt("Empresa_IdEmpresa"));
            }
            
            //Solo si la empresa a la que se quiere cambiar el proyecto es la diferente a la que esta el proyecto se modifica
            if(resultado.next()){
                if(resultado.getInt("Empresa_IdEmpresa") != idEmpresa && lista.contains(idEmpresa)){
                    int codigo = s.executeUpdate("update proyecto set Empresa_IdEmpresa="+ idEmpresa +" where IdProyecto="+ idProyecto +";");
                    //Si la consulta se ha realizado correctamente
                    if(codigo > 0){
                        //Borramos las entradas de ese proyecto en la tabla proyecto empleado
                        codigo = s.executeUpdate("delete from proyecto_empleado where proyecto_id_proyecto="+ idProyecto);
                        if(codigo > 0){
                            Log.logBd.info("Realizada consulta - modificarProyecto()");
                        }
                        else{
                            Log.logBd.info("Consulta no ha alterado la tabla o consulta errónea - modificarProyecto() en proyecto_empleado - filas alteradas: "+ codigo);
                        }
                        hecho = true;
                    }else{
                        Log.logBd.info("Consulta no ha alterado la tabla o consulta errónea - modificarProyecto() en proyecto - filas alteradas: "+ codigo);
                    }
                }
                else{
                    hecho = true;
                    Log.logBd.info("La empresa id("+ idEmpresa +") ya tiene el proyecto id("+ idProyecto +") que se quiere cambiar");
                }
            }
            else{
                Log.logBd.error("Empresa id("+ idEmpresa +") no existe en la base de datos");
            }
        } catch(SQLException error){
            Log.logBd.error("ERROR SQL en modificarProyecto(): " + error);
            Log.logBd.error("                                  SQL State - " + error.getSQLState());
            Log.logBd.error("                                  ErrorCode - " + error.getErrorCode());
        }
        
        return hecho;
    }
    
    public boolean borrarProyecto(int idProyecto){
        Log.logBd.info("CONSULTA BorrarProyecto");
        boolean hecho = false;
        try{
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion - borrarProyecto()");
            Statement s = conexion.createStatement();
            Log.logBd.info("Borramos las entrada en la tabla proyecto_empleado");
            s.executeUpdate("delete from proyecto_empleado where proyecto_id_proyecto="+ idProyecto +";");
            
            //Se han borrado los proyectos de la tabla proyecto_empleado y borramos el proyecto de la tabla empleado
            int codigo = s.executeUpdate("delete from proyecto where IdProyecto="+ idProyecto +";");

            if(codigo > 0){
                hecho = true;
                Log.logBd.info("Proyecto con id("+ idProyecto +") borrado");
            }
            else{
                Log.logBd.error("No se ha podido o no se ha encontrado el proyecto id("+ idProyecto +") - borrarProyecto() en proyecto");
            }

            
        } catch(SQLException error){
            Log.logBd.error("ERROR SQL en borrarProyecto(): " + error);
            Log.logBd.error("                               SQL State - " + error.getSQLState());
            Log.logBd.error("                               ErrorCode - " + error.getErrorCode());
        }
        
        return hecho;
    }
    
    public boolean borrarEmpresa(int idEmpresa){
        Log.logBd.info("CONSULTA BorrarEmpresa");
        
        boolean hecho = true;
        try{
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion - borrarEmpresa()");
            Statement s = conexion.createStatement();
            
            Log.logBd.info("Dando de baja los empleados de la empresa id("+ idEmpresa +")");
            ResultSet empleados = getEmpleadoEmpresa(idEmpresa);
            //Borramos a los usuarios de la empresa
            while(empleados.next()){
                String correo = empleados.getString("Correo");
                boolean borrado = darBaja(correo);
                if(!borrado){
                    hecho = false;
                }
                else{
                    Log.logBd.error("No se ha podido borrar el empleado correo("+ correo +")");
                }
            }
            
            Log.logBd.info("Borrando los proyectos de la empresa id("+ idEmpresa +")");
            ResultSet proyectos = s.executeQuery("select IdProyecto from proyecto where Empresa_IdEmpresa="+ idEmpresa +";");
            //Borramos cada uno de los proyectos
            while(proyectos.next()){
                int idProyecto = proyectos.getInt("IdProyecto");
                boolean borrado = borrarProyecto(idProyecto);
                if(!borrado){
                    hecho = false;
                }
                else{
                    Log.logBd.error("No se ha podido borrar el proyecto id("+ idProyecto +")");
                }
            }
            
            int codigo = s.executeUpdate("delete from empresa where IdEmpresa="+ idEmpresa +";");
            //Una vez borrados los proyectos y empleados de la empresa borramos la empresa
            if(codigo > 0){
                Log.logBd.info("Proyecto con id("+ idEmpresa +") borrado - filas alteradas: "+ codigo);
            }
            else{
                Log.logBd.error("Consulta no ha alterado la tabla o consulta errónea - borrarEmpresa()");
                hecho = false;
            }
        } catch(SQLException error){
            Log.logBd.error("ERROR SQL en borrarEmpresa(): " + error);
            Log.logBd.error("                               SQL State - " + error.getSQLState());
            Log.logBd.error("                               ErrorCode - " + error.getErrorCode());
        }
        
        return hecho;
    }
    
    public boolean anadirEmpresa(Empresa empresa) {
        Log.logBd.info("CONSULTA AnadirEmpresa");
        try {
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion - anadirEmpresa()");
            Statement s = conexion.createStatement();
            
            int codigo = s.executeUpdate("INSERT INTO empresa(IdEmpresa, Nombre, Calle, CodigoPostal, Correo, Telefono) VALUES (" + empresa.getIdEmpresa() + ",'" + empresa.getNombre()
                    + "','" + empresa.getDireccion() + "'," + empresa.getCodigoPostal() + ",'" + empresa.getCorreo() + "'," + empresa.getTelefono() +")");
            if(codigo > 0){
                Log.logBd.info("Realizada consulta - anadirEmpresa()");
            }else{
                Log.logBd.info("Consulta no ha alterado la tabla o consulta errónea - anadirEmpresa()");
            }

        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en darAlta(): " + error);
            Log.logBd.error("            SQL State - " + error.getSQLState());
            Log.logBd.error("            ErrorCode - " + error.getErrorCode());
        }
        
        return true;
    }
    
    public boolean anadirProyecto(int idProyecto, int idEmpresa) {
        boolean hecho = true;
        Log.logBd.info("CONSULTA AnadirProyecto");
        try {
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion - anadirProyecto()");
            Statement s = conexion.createStatement();
            
            //Hacemos una lista con los id de las empresas para comprobar que se elige una empresa que esta en la BD
            Statement s1 = conexion.createStatement();
            ResultSet resultado1 = s1.executeQuery("select Empresa_IdEmpresa from proyecto");
            List<Integer> listaEmpresas = new ArrayList<>();
            while(resultado1.next()){
                listaEmpresas.add(resultado1.getInt("Empresa_IdEmpresa"));
            }
            
            Statement s2 = conexion.createStatement();
            ResultSet resultado2 = s2.executeQuery("select IdProyecto from proyecto");
            List<Integer> listaProyectos = new ArrayList<>();
            while(resultado2.next()){
                listaProyectos.add(resultado2.getInt("IdProyecto"));
            }
            
            if(listaEmpresas.contains(idEmpresa) && !listaProyectos.contains(idProyecto)){
                int codigo = s.executeUpdate("INSERT INTO proyecto(IdProyecto, Empresa_IdEmpresa) VALUES (" + idProyecto +","+ idEmpresa +")");
                if(codigo > 0){
                    Log.logBd.info("Realizada consulta - anadirProyecto()");
                }else{
                    Log.logBd.info("Consulta no ha alterado la tabla o consulta errónea - anadirProyecto()");
                }
            }
            else{
                hecho = false;
                Log.logBd.error("Empresa id("+ idEmpresa +") no existe o proyecto id("+ idProyecto +") duplicado en la base de datos");
            }

        } catch (SQLException error) {
            hecho = false;
            Log.logBd.error("ERROR SQL en anadirProyecto(): " + error);
            Log.logBd.error("                        SQL State - " + error.getSQLState());
            Log.logBd.error("                        ErrorCode - " + error.getErrorCode());
        }

        return hecho;
    }
    
    //Retorna los empleados de una empresa
    public ResultSet getEmpleadoEmpresa(int idEmpresa){
        ResultSet resultado = null;
        Log.logBd.info("CONSULTA GetEmpleadoEmpresa");
        try{
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion - getEmpleadoEmpresa()");
            Statement s = conexion.createStatement();
            resultado = s.executeQuery("SELECT distinct empleadoempresa.Correo from empleadoempresa INNER JOIN proyecto_empleado ON empleadoempresa.Correo=proyecto_empleado.empleado_correo " +
                                        "INNER JOIN proyecto ON proyecto.IdProyecto=proyecto_empleado.proyecto_id_proyecto INNER JOIN empresa ON empresa.IdEmpresa=proyecto.Empresa_IdEmpresa " +
                                        "WHERE empresa.IdEmpresa="+ idEmpresa +";");
            
        } catch(SQLException error){
            Log.logBd.error("ERROR SQL en getEmpleadoEmpresa(): " + error);
            Log.logBd.error("                       SQL State - " + error.getSQLState());
            Log.logBd.error("                       ErrorCode - " + error.getErrorCode());
        }
        
        return resultado;
    }
    
    public boolean ficharEmpleado(Date fecha, Time hora_entrada, Time hora_salida, String correo, int id_proyecto) throws ParseException{
        boolean hecho=false;
        
        try{
            conexion = ConexionBd.getConexion();
            Log.logBd.info("Realizada conexion - ficharEmpleado()");
            Statement s = conexion.createStatement();
            int codigo = s.executeUpdate("INSERT INTO Calendario VALUES('"+fecha+"','"+hora_entrada+"','"+hora_salida+"','"+correo+"';");
            Statement s2 = conexion.createStatement();
            ResultSet resultado = s2.executeQuery("select Horas from Proyecto_Empleado where proyecto_id_proyecto="+id_proyecto+"and empleado_correo='"+correo+"';");
           int horas;
          
           int horas_trabajadas_hoy=getHoras(hora_entrada.toString(),hora_salida.toString());
           int horas_totales=0;
           System.out.print(codigo);
            while(resultado.next()){
                horas=resultado.getInt("Horas");
                
                
                horas_totales=horas+horas_trabajadas_hoy;

            }
            Statement s3 = conexion.createStatement();
            int cod=s3.executeUpdate("UPDATE Proyecto_Empleado SET horas="+horas_totales+"where proyecto_id_proyecto="+id_proyecto+"and empleado_correo='"+correo+"';");
            System.out.print(codigo+" "+cod);
            if(codigo>0 && cod>0){
                
                
                hecho=true;
            }
            
        } catch(SQLException error){
            Log.logBd.error("ERROR SQL en ficharEmpleado(): " + error);
            Log.logBd.error("                       SQL State - " + error.getSQLState());
            Log.logBd.error("                       ErrorCode - " + error.getErrorCode());
        }
        return hecho;
    }
    
    
    
    public int getHoras(String fecha_i,String fecha_f) throws ParseException{
        String fecha_entrada="1000-01-01"+" "+fecha_i;
        String fecha_salida="1000-01-01"+" "+fecha_f;
        
       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd H:m:s");
 
        Date fechaInicial=dateFormat.parse(fecha_entrada);
        Date fechaFinal=dateFormat.parse(fecha_salida);
 
        int diferencia=(int) ((fechaFinal.getTime()-fechaInicial.getTime())/1000);
 
        int dias=0;
        int horas=0;
        int minutos=0;
        if(diferencia>86400) {
            dias=(int)Math.floor(diferencia/86400);
            diferencia=diferencia-(dias*86400);
        }
        if(diferencia>3600) {
            horas=(int)Math.floor(diferencia/3600);
            diferencia=diferencia-(horas*3600);
        }
        if(diferencia>60) {
            minutos=(int)Math.floor(diferencia/60);
            diferencia=diferencia-(minutos*60);
        }
       
    return horas;
     
     }
}
