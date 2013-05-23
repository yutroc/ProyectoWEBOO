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

    public CompraProductoDTO(int idVentaProducto, int cantidad, int total) {
        this.idVentaProducto = idVentaProducto;
        this.cantidad = cantidad;
        this.total = total;
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

    @Override
    public String toString() {
        return "CompraProductoDTO{" + "idVentaProducto=" + idVentaProducto + ", cantidad=" + cantidad + ", total=" + total + '}';
    }
}
