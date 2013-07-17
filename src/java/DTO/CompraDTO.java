/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Date;

/**
 *
 * @author Sebastian
 */
public class CompraDTO {

    private int idCarro;
    private int estado;
    private Date fechaCreacion;
    private Date fechaFinalizacion;
    private String idUsuario;
    private String usuario;

    public CompraDTO(int idCarro, String idUsuario, int estado, Date fechaCreacion, Date fechaFinalizacion) {
        this.idCarro = idCarro;
        this.idUsuario = idUsuario;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public CompraDTO(int idCarro, String idUsuario, int estado, Date fechaCreacion, Date fechaFinalizacion, String usuario) {
        this.idCarro = idCarro;
        this.idUsuario = idUsuario;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaFinalizacion = fechaFinalizacion;
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
    }

    public int getEstado() {
        return estado;
    }

    public String getEstadoString() {
        if (this.getEstado() == 1) {
            return "Vigente";
        } else {
            return "Finalizado";
        }
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    @Override
    public String toString() {
        return "CompraDTO{" + "idCarro=" + idCarro + ", estado=" + estado + ", fechaCreacion=" + fechaCreacion + ", fechaFinalizacion=" + fechaFinalizacion + ", idUsuario=" + idUsuario + '}';
    }
}
