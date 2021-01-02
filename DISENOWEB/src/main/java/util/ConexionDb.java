/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDb {
    
    private static Connection conexion = null;
    
    public static Connection getConexion() {
        String driver = "com.mysql.jdbc.Driver";
        String puerto = "";
        String user = "";
        String password = "";
        String url = "jdbc:mysql://localhost:"+ puerto +"/userdb?useTimezone=true&serverTimezone=UTC";
        Log.logDb.info("Inicio de conexion en puerto["+ puerto + "]");
        if (conexion != null){
            Log.logDb.info("Ya hay una conexion activa");
            return conexion;
        }
        else{
            try{
                Log.logDb.info("Conexion establecida en puerto["+ puerto +"]");
                Class.forName("driver");
                conexion = DriverManager.getConnection(url, user, password);
            }catch(ClassNotFoundException error){
                Log.logDb.error("ERROR CONEXION: "+ error);
            }catch(SQLException error){
                Log.logDb.error("ERROR SQL: "+ error);
            }
            return conexion;
        }
    }
}
