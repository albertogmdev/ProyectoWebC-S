package logica;

/**
 *
 * @author Alberto
 */
public class Empleado {

    private int idEmpleado; //objeto tipo int que representa el id del empleado
    private String contrasenna; //objeto tipo String que representa la contraseña
    private String nombre; //objeto tipo String que representa el nombre del empleado
    private String apellidos;//objeto tipo String que representa los apellidos del empleado
    private int telefono; //objeto tipo int que representa el telefono del empleado
    private String email; //objeto tipo String que representa el correo del empleado

    /**
     * devuelve el id del empleado
     *
     * @return
     */
    public int getIdEmpleado() {
        return idEmpleado;
    }

    /**
     * cambia el id del empleado por el id proporcionado
     *
     * @param idEmpleado
     */
    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    /**
     * devuelve la contraseña
     *
     * @return
     */
    public String getContrasenna() {
        return contrasenna;
    }

    /**
     * cambia la contraseña por la contraseña propocionada
     *
     * @param contrasenna
     */
    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    /**
     * devuelve el nombre del empleado
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * cambia el nombre del empleado por el nombre proporcionado
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * devuelve los apellidos del empleado
     *
     * @return
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * cambia los apellidos del empleado por los apellidos proporcionados
     *
     * @param apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * devuelve el telefono del empleado
     *
     * @return
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     * cambia el telefono del empleado por el telefono propocionado
     *
     * @param telefono
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    /**
     * devuelve el email del empleado
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * cambia el email del empleado por el email proporcionado
     *
     * @param email
     */

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Empleado{" + "idEmpleado=" + idEmpleado + ", contrasenna=" + contrasenna + ", nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono + ", email=" + email + '}';
    }
}
