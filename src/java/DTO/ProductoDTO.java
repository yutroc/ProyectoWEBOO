/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Sebastian
 */
public class ProductoDTO {
    
    private int idProducto;
    private String nombre;
    private String descripcion;
    private byte image;
    private int stock;
    private int valorOferta;
    private boolean ofertaActiva;
    private int idCategoria;

    public ProductoDTO(int idProducto, String nombre, String descripcion, byte image, int stock, int valorOferta, boolean ofertaActiva, int idCategoria) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.image = image;
        this.stock = stock;
        this.valorOferta = valorOferta;
        this.ofertaActiva = ofertaActiva;
    }
   // public ProductoDTO(){}
    
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }
     public byte getImage() {
        return image;
    }

    public void setImage(byte image) {
        this.image = image;
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
        return "ProductoDTO{" + "idProducto=" + idProducto + ", nombre=" + nombre + ", descripcion=" + descripcion + ", image=" + image + ", stock=" + stock + ", valorOferta=" + valorOferta + ", ofertaActiva=" + ofertaActiva + '}';
    }
    
}
