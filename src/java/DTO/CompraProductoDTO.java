/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Sebastian
 */
public class CompraProductoDTO {

    private int idVentaProducto;
    private int cantidad;
    private int total;
    private int idCarro;
    private int idProducto;
    private String nombreProducto;
    private int precio;
    private int precioOferta;
    private boolean ofertaActiva;

    public CompraProductoDTO(int idVentaProducto, int cantidad, int total) {
        this.idVentaProducto = idVentaProducto;
        this.cantidad = cantidad;
        this.total = total;
    }
    public CompraProductoDTO(int idVentaProducto, int cantidad, int total, int idCarro,int idProducto) {
        this.idVentaProducto = idVentaProducto;
        this.cantidad = cantidad;
        this.total = total;
        this.idCarro=idCarro;
        this.idProducto=idProducto;
    }
    public CompraProductoDTO(int idVentaProducto, int cantidad, int total, int idCarro,int idProducto,String nombreProducto,int precio,int precioOferta,boolean ofertaActiva) {
        this.idVentaProducto = idVentaProducto;
        this.cantidad = cantidad;
        this.total = total;
        this.idCarro=idCarro;
        this.idProducto=idProducto;
        this.nombreProducto=nombreProducto;
        this.precio = precio;
        this.precioOferta=precioOferta;
        this.ofertaActiva=ofertaActiva;
    }
    public CompraProductoDTO(int idVentaProducto,String nombre,int precio,int cantidad,int total){
        this.idVentaProducto=idVentaProducto;
        this.nombreProducto=nombre;
        this.precio=precio;
        this.cantidad=cantidad;
        this.total=total;
    }

    public int getIdVentaProducto() {
        return idVentaProducto;
    }

    public void setIdVentaProducto(int idVentaProducto) {
        this.idVentaProducto = idVentaProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
     public int getIdCarro() {
        return idCarro;
    }
 public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
    }
    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getPrecioOferta() {
        return precioOferta;
    }

    public void setPrecioOferta(int precioOferta) {
        this.precioOferta = precioOferta;
    }

    public boolean isOfertaActiva() {
        return ofertaActiva;
    }

    public void setOfertaActiva(boolean ofertaActiva) {
        this.ofertaActiva = ofertaActiva;
    }

    public int getIdProducto() {
        return idProducto;
    }
    public String getNombreProducto() {
        return nombreProducto;
    }
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }


    @Override
    public String toString() {
        return "CompraProductoDTO{" + "idVentaProducto=" + idVentaProducto + ", cantidad=" + cantidad + ", total=" + total + '}';
    }
}
