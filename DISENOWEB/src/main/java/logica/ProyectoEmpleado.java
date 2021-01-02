
package logica;

/**
 *
 * @author Alberto
 */
public class ProyectoEmpleado {
    
    private int horas;
    private Proyecto proyecto;

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    @Override
    public String toString() {
        return "ProyectoEmpleado{" + "horas=" + horas + ", proyecto=" + proyecto + '}';
    }
}
