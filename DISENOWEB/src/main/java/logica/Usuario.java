
package logica;

import java.util.List;

/**
 *
 * @author Alberto
 */
public class Usuario {
    
    private int idUsuario;
    private String contrasenna;
    private Empresa empresa;
    private String nombre;
    private String apellidos;
    private int telefono;
    private String email;
    private List<ProyectoEmpleado> proyectosList; //POSIBLEMENTE HAYA QUE INICIALIZAR

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ProyectoEmpleado> getProyectosList() {
        return proyectosList;
    }

    public void setProyectosList(List<ProyectoEmpleado> proyectosList) {
        this.proyectosList = proyectosList;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", contrasenna=" + contrasenna + ", empresa=" + empresa.getNombre() + ", nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono + ", email=" + email + ", proyectosList=" + proyectosList + '}';
    }
}
