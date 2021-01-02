
package logica;

/**
 *
 * @author Alberto
 */
public class Proyecto {
    
    private Empresa empresa;
    private int idProyecto;

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    @Override
    public String toString() {
        return "Proyecto{" + "empresa=" + empresa + ", idProyecto=" + idProyecto + '}';
    }
}
