package logica;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alberto
 */
public class Usuario {

    private int idUsuario; //objeto tipo int que representa el id del usuario
    private String contrasenna; //objeto tipo String que representa la contraseña
    private Empresa empresa; //objeto tipo Empresa que representa la empresa donde trabaja el empleado
    private String nombre; //objeto tipo String que representa el nombre del empleado
    private String apellidos; //objeto tipo String que representa los apellidos del empleado
    private int telefono; //objeto tipo int que representa el telefono
    private String email; //objeto tipo String que representa el correo
    private List<ProyectoEmpleado> proyectosList; //objeto tipo Lista que presenta los proyectos del empleado

    /**
     * devuelve el id del usuario
     *
     * @return
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Asigna un nuevo id al usuario
     *
     * @param idUsuario
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * devuelve la contraseña del usuario
     *
     * @return
     */
    public String getContrasenna() {
        return contrasenna;
    }

    /**
     * Asigna una nueva contraseña al usuario
     *
     * @param contrasenna
     */
    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    /**
     * devuelve la empresa asignada al usuario
     *
     * @return
     */
    public Empresa getEmpresa() {
        return empresa;
    }

    /**
     * asigna una nueva empresa al usuario
     *
     * @param empresa
     */
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    /**
     * devuelve el nombre del usuario
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Cambia el nombre del usuario por el parámetro proporcionado
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * devuelve los apellidos del usuario
     *
     * @return
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * cambia los apellidos del usuario por los nuevos apellidos proporcionados
     *
     * @param apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * devuelve el telefono del usuario
     *
     * @return
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     * cambia el telefono del usuario por el nuevo telefono proporcionado
     *
     * @param telefono
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    /**
     * devuelve el correo del usuario
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * cambia el correo del usuario por el nuevo correo proporcionado
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * devuelve una lista con los proyecto asignados al usuario
     *
     * @return
     */
    public List<ProyectoEmpleado> getProyectosList() {
        return proyectosList;
    }

    /**
     * cambia la lista de proyectos asignados al usuario por la lista
     * proporcionada
     *
     * @param proyectosList
     */
    public void setProyectosList(List<ProyectoEmpleado> proyectosList) {
        this.proyectosList = proyectosList;
    }

    @Override
    public String toString() {
        String tostring = "";
        if (this.empresa == null) {
            tostring = "Usuario{" + "idUsuario=" + idUsuario + ", contrasenna=" + contrasenna + ", empresa=" + "Error al crear" + ", nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono + ", email=" + email + ", proyectosList=" + proyectosList + '}';
        } else {
            tostring = "Usuario{" + "idUsuario=" + idUsuario + ", contrasenna=" + contrasenna + ", empresa=" + empresa.getNombre() + ", nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono + ", email=" + email + ", proyectosList=" + proyectosList + '}';
        }
        return tostring;
    }
}
