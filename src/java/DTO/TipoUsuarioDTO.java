/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Sebastian
 */
public class TipoUsuarioDTO {

    private int idTipousuario;
    private String nombreTipoUsuario;

    public TipoUsuarioDTO(int idTipousuario, String nombreTipoUsuario) {
        this.idTipousuario = idTipousuario;
        this.nombreTipoUsuario = nombreTipoUsuario;
    }

    public int getIdTipousuario() {
        return idTipousuario;
    }

    public void setIdTipousuario(int idTipousuario) {
        this.idTipousuario = idTipousuario;
    }

    public String getNombreTipoUsuario() {
        return nombreTipoUsuario;
    }

    public void setNombreTipoUsuario(String nombreTipoUsuario) {
        this.nombreTipoUsuario = nombreTipoUsuario;
    }

    @Override
    public String toString() {
        return "TipoUsuarioDTO{" + "idTipousuario=" + idTipousuario + ", nombreTipoUsuario=" + nombreTipoUsuario + '}';
    }
    
    
}
