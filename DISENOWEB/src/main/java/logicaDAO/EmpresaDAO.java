/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaDAO;

import interfaces.CRUDempresa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import logica.Empresa;
import util.ConexionBd;

/**
 *
 * @author ccris
 */
public class EmpresaDAO implements CRUDempresa {

    ConexionBd con = new ConexionBd();
    Connection conexion;
    ResultSet resultado;
    PreparedStatement consulta;
    

    public List mostrarEmpresa() {

        ArrayList<Empresa> lista_empresas = new ArrayList<>();
        String sql = "select * from empresa";

        try {
            conexion = con.getConexion();
            consulta = conexion.prepareStatement(sql);
            resultado = consulta.executeQuery();
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

        } catch (Exception e) {

        }

        return lista_empresas;

    }
}
