/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import logica.Usuario;

/**
 *
 * @author ccris
 */
public interface CRUDempleado {
    public List mostrarEmp();
    public Usuario mostrar(int id);
    
    
}
