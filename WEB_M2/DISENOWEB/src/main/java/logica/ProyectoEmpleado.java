package logica;

/**
 *
 * @author Alberto
 */
public class ProyectoEmpleado {

    private int horas; //objeto tipo int que representa las horas
    private Proyecto proyecto; //objeto tipo Proyecto que representa el proyecto

    /**
     * devuelve las horas del proyecto
     *
     * @return
     */
    public int getHoras() {
        return horas;
    }

    /**
     * Asigna una hora al proyecto
     *
     * @param horas
     */
    public void setHoras(int horas) {
        this.horas = horas;
    }

    /**
     * devuelve el proyecto
     *
     * @return
     */

    public Proyecto getProyecto() {
        return proyecto;
    }

    /**
     * Asigna un proyecto
     *
     * @param proyecto
     */
    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    @Override
    public String toString() {
        return "ProyectoEmpleado{" + "horas=" + horas + ", proyecto=" + proyecto + '}';
    }
}
