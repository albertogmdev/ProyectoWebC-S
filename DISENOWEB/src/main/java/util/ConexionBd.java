
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Alberto
 */
public class ConexionBd {
    
    private static Connection conexion = null;
    
    public static Connection getConexion() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String nombreBd = "base_empresa";
        String puerto = "3306";
        String user = "root";
        String password = "admin";
        String url = "jdbc:mysql://localhost:"+ puerto +"/"+ nombreBd +"?useTimezone=true&serverTimezone=UTC";
        //Log.logBd.log("Inicio de conexion en puerto["+ puerto + "]");
        if (conexion != null){
            //Log.logBd.log("Ya hay una conexion activa");
            return conexion;
        }
        else{
            try{
                //Log.logBd.log("Conexion establecida en puerto["+ puerto +"]");
                Class.forName(driver);
                conexion = DriverManager.getConnection(url, user, password);
            }catch(ClassNotFoundException error){
                //Log.logBd.log("ERROR CONEXION: "+ error);
            }catch(SQLException error){
                //Log.logBd.log("ERROR SQL: "+ error);
            }
            return conexion;
        }
    }
    
    public void desconectar(){
        try{
            conexion.close();
        }catch(SQLException error){
            Log.logBd.log("ERROR SQL: "+ error);
        }
    }
}
