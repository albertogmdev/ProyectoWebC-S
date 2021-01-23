package logica;

/**
 *
 * @author Alberto
 */
public class Empresa {

    private int idEmpresa; //objeto tipo int que representa el id de la empresa
    private String nombre; //objeto tipo String que representa el nombre de la empresa
    private String direccion;//objeto tipo String que representa la direccion de la empresa
    private int codigoPostal;//objeto tipo int que representa el codigo postal de la empresa
    private String correo;//objeto tipo String que representa el correo de la empresa
    private int telefono;//objeto tipo int que representa el telefono de la empresa

    /**
     * devuelve el id de la empresa
     *
     * @return
     */
    public int getIdEmpresa() {
        return idEmpresa;
    }

    /**
     * cambia el id de la empresa por el id proporcionado
     *
     * @param idEmpresa
     */
    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    /**
     * devuelve el nombre de la empresa
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * cambia el nombre de la empresa por el nombre proporcionado
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * devuelve la dirección de la empresa
     *
     * @return
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * cambia la dirección de la empresa por la dirección proporcionada
     *
     * @param direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * devuelve el codigo postal de la empresa
     *
     * @return
     */
    public int getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * cambia el codigo postal de la empresa por el codigo proporcionado
     *
     * @param codigoPostal
     */
    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     * devuelve el correo de la empresa
     *
     * @return
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * cambia el correo de la empresa por el correo proporcionado
     *
     * @param correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * devuelve el telefono de la empresa
     *
     * @return
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     * cambia el telefono de la empresa por el telefono proporcionado
     *
     * @param telefono
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Empresa{" + "idEmpresa=" + idEmpresa + ", nombre=" + nombre + ", direccion=" + direccion + ", codigoPostal=" + codigoPostal + ", correo=" + correo + ", telefono=" + telefono + '}';
    }
}
