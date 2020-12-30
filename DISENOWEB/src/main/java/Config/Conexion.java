/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ccris
 */
public class Conexion {

    Connection conn;
    String url = "jdbc:mysql://localhost:3306/nombre_base_de_datos";//modificar
    String user = "usuario de la bbd"; //modificar
    String password = "contraseña bbdd";//modificar

    public Connection getConexion() {
        if (conn != null) {
            System.out.println("Ya hay una conexion"); //habria q crear log
            return conn;

        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver");//direccion del driver
                conn = DriverManager.getConnection(url, user, password);

            } catch (ClassNotFoundException e) {

            } catch (SQLException e) {
            }
            return conn;
        }
    }

}
