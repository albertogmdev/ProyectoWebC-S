package logica;

/**
 *
 * @author Alberto
 */
public class Proyecto {

    private Empresa empresa; //objeto tipo Empresa que representa la empresa del proyecto
    private int idProyecto; //objeto tipo int que representa el id del proyecto

    /**
     * devuelve la empresa asignada al proyecto
     *
     * @return
     */
    public Empresa getEmpresa() {
        return empresa;
    }

    /**
     * Asigna una nueva empresa al proyecto
     *
     * @param empresa
     */
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    /**
     * devuelve el id del proyecto
     *
     * @return
     */
    public int getIdProyecto() {
        return idProyecto;
    }

    /**
     * Asigna un nuevo id al proyecto
     *
     * @param idProyecto
     */
    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    @Override
    public String toString() {
        return "Proyecto{" + "empresa=" + empresa + ", idProyecto=" + idProyecto + '}';
    }
}
