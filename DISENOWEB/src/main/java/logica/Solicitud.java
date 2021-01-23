/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;
import java.sql.Date;

/**
 *
 * @author ccris
 */
public class Solicitud {
    private Date fechaInicio;
    private Date fechaFin;
    private String motivo;
    private boolean leido;
    private boolean aprobado;
    private boolean tramitado;
    private Usuario usuario;

    public Solicitud() {
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public boolean isLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public boolean isTramitado() {
        return tramitado;
    }

    public void setTramitado(boolean tramitado) {
        this.tramitado = tramitado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Solicitud{" + "fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", motivo=" + motivo + ", leido=" + leido + ", aprobado=" + aprobado + ", tramitado=" + tramitado + ", usuario=" + usuario + '}';
    }
    
    
       
    
}
