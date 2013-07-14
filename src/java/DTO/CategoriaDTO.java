/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author Sebastian
 */
public class CategoriaDTO {
    private int idCategoria;
    private String nombre;
    private Byte imagen;

    public CategoriaDTO(int idCategoria, String nombre, Byte imagen) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public int getIdCategoria() {
       System.out.println("id categoria: "+idCategoria);
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Byte getImagen() {
        return imagen;
    }

    public void setImagen(Byte imagen) {
        this.imagen = imagen;
    }
   
    @Override
    public String toString() {
        return nombre;
        //return "CategoriaDTO{" + "idCategoria=" + idCategoria + ", nombre=" + nombre + ", imagen=" + imagen + '}';
    }
    
}
