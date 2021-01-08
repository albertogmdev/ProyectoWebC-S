/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaDAO;

import interfaces.CRUDproyecto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import logica.Empresa;
import logica.Proyecto;
import util.ConexionBd;

/**
 *
 * @author ccris
 */
public class ProyectoDAO implements CRUDproyecto{
    ConexionBd con = new ConexionBd();
    Connection conexion;
    ResultSet resultado;
    PreparedStatement consulta;

   
    public List mostrarProyecto() {
        ArrayList<Proyecto> lista_proyectos = new ArrayList<>();
        String sql = "select * from proyecto";
        

        try {
            conexion = con.getConexion();
            consulta = conexion.prepareStatement(sql);
            resultado = consulta.executeQuery();
            while (resultado.next()) {
                Proyecto proyecto = new Proyecto();
                Empresa empresa = new Empresa();
                proyecto.setIdProyecto(resultado.getInt("IdProyecto"));
                empresa.setIdEmpresa(resultado.getInt("Empresa_IdEmpresa"));
                proyecto.setEmpresa(empresa);
                
                
                
               
                lista_proyectos.add(proyecto);

            }

        } catch (Exception e) {

        }

        return lista_proyectos;
        
    }
    
    
}
