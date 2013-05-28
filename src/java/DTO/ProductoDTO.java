/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author Sebastian
 */
public class ProductoDTO {

    private int idProducto;
    private String nombre;
    private String descripcion;
    private String imageUrl;
    private int stock;
    private int precio;
    private int valorOferta;
    private boolean ofertaActiva;
    private int idCategoria;

    public ProductoDTO(int idProducto, String nombre, String descripcion, String imageUrl, int stock, int precio, int valorOferta, boolean ofertaActiva, int idCategoria) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
         this.imageUrl = GetImagen(idProducto);
        this.stock = stock;
        this.precio = precio;
        this.valorOferta = valorOferta;
        this.ofertaActiva = ofertaActiva;
        this.idCategoria = idCategoria;
    }

   

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    private String GetImagen(int idProducto) {
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String realPath=(String) servletContext.getRealPath("/");
        String path = "imagenes/productos/" + idProducto + ".jpg";
        
        OutputStream out = null;
        File fichero = new File(realPath+path);
        if (!fichero.exists()) {
//            try {
//                out = new BufferedOutputStream(new FileOutputStream("www/web"+path));
//                out.write(bytes);
//                System.err.println("Descargado : " +path);
//                return path;
//            } finally {
//                try {
//                    if (out != null) {
//                        out.close();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return "imagenes/productos/default.jpg";
//            }
            System.err.println("No existe : " +realPath+path);
            return "imagenes/productos/default.jpg";
        }else{
            //System.out.println("Abriendo : " +realPath+path);
            return path;
        }
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getValorOferta() {
        return valorOferta;
    }

    public void setValorOferta(int valorOferta) {
        this.valorOferta = valorOferta;
    }

    public boolean isOfertaActiva() {
        return ofertaActiva;
    }

    public void setOfertaActiva(boolean ofertaActiva) {
        this.ofertaActiva = ofertaActiva;
    }

    @Override
    public String toString() {
        return "ProductoDTO{" + "idProducto=" + idProducto + ", nombre=" + nombre + ", descripcion=" + descripcion + ", image=" + imageUrl + ", stock=" + stock + ", valorOferta=" + valorOferta + ", ofertaActiva=" + ofertaActiva + '}';
    }
}
