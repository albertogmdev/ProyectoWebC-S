
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
        String nombreBd = "M2";
        String puerto = "3306";
        String user = "root";
        String password = "admin";
        String url = "jdbc:mysql://localhost:"+ puerto +"/"+ nombreBd +"?useTimezone=true&serverTimezone=UTC";
        
        Log.logBd.info("Inicio de conexion en puerto["+ puerto + "]");
        if (conexion != null){
            return conexion;
        }
        else{
            try{
                Class.forName(driver);
                conexion = DriverManager.getConnection(url, user, password);
                Log.logBd.info("Conexion establecida en puerto["+ puerto +"]");
            }catch(ClassNotFoundException error){
                Log.logBd.error("ERROR CONEXION: "+ error);
            }catch(SQLException error){
                Log.logBd.error("ERROR SQL: "+ error);
            }
            return conexion;
        }
    }
    
    public void desconectar(){
        try{
            conexion.close();
        }catch(SQLException error){
            Log.logBd.error("ERROR SQL: "+ error);
        }
    }
}
