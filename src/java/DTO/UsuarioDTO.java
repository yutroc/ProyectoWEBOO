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
public class UsuarioDTO {

    private int idUsuario;
    private String nombre;
    private String aPaterno;
    private String aMaterno;
    private String rut;
    private String direccion;
    private String comuna;
    private String ciudad;
    private String email;
    private Date fechaRegistro;
    private String contraseña;
    private String telefono;
    private int idTipo;

    public UsuarioDTO(int idUsuario, String nombre, String aPaterno, String aMaterno, String rut, String direccion, String comuna, String ciudad, String email, Date fechaRegistro, String contraseña, String telefono) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.aPaterno = aPaterno;
        this.aMaterno = aMaterno;
        this.rut = rut;
        this.direccion = direccion;
        this.comuna = comuna;
        this.ciudad = ciudad;
        this.email = email;
        this.fechaRegistro = fechaRegistro;
        this.contraseña = contraseña;
        this.telefono = telefono;
    }
    public UsuarioDTO(int idUsuario, String nombre, String aPaterno, String aMaterno, String rut, String direccion, String comuna, String ciudad, String email, Date fechaRegistro, String contraseña, String telefono, int idTipo) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.aPaterno = aPaterno;
        this.aMaterno = aMaterno;
        this.rut = rut;
        this.direccion = direccion;
        this.comuna = comuna;
        this.ciudad = ciudad;
        this.email = email;
        this.fechaRegistro = fechaRegistro;
        this.contraseña = contraseña;
        this.telefono = telefono;
        this.idTipo = idTipo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAPaterno() {
        return aPaterno;
    }

    public void setaPaterno(String aPaterno) {
        this.aPaterno = aPaterno;
    }

    public String getAMaterno() {
        return aMaterno;
    }

    public void setaMaterno(String aMaterno) {
        this.aMaterno = aMaterno;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" + "idUsuario=" + idUsuario + ", nombre=" + nombre
                + ", aPaterno=" + aPaterno + ", aMaterno=" + aMaterno + ", rut="
                + rut + ", direccion=" + direccion + ", comuna=" + comuna
                + ", ciudad=" + ciudad + ", email=" + email + ", fechaRegistro="
                + fechaRegistro + ", contrase\u00f1a=" + contraseña
                + ", telefono=" + telefono + ",tipo=" + idTipo +'}';
    }
}
