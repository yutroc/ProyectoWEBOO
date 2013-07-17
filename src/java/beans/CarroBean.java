/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import DTO.CompraDTO;
import DTO.CompraProductoDTO;
import Controler.Controlador;
import DTO.ProductoDTO;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import utils.DAOException;

/**
 *
 * @author Felipe
 */
@ManagedBean
@SessionScoped
public class CarroBean {

    /**
     * Creates a new instance of CarroBean
     */
     public Controlador controller = new Controlador();
    private int idCarro;
    private int estado;
    private Date fechaCreacion;
    private Date fechaFinalizacion;
    private String idUsuario;
    private int idVentaProducto;
    private int cantidad;
    private int total;
    private int idProducto;
    private CompraDTO compra;
    private ArrayList<CompraProductoDTO> compraProducto;
    private HtmlDataTable tablaProductos;
    private int totalVentaCarro;
    
    public CarroBean() {
    }
    
    public String revisarCarro(ProductoDTO prod) throws DAOException {
        System.out.println(prod.getIdProducto()+" ASDASDASDADADASDADASDADASDASDADADASDASDASDASD");
        this.idProducto = prod.getIdProducto();
        this.idUsuario = 2 + "";
         System.out.println(idProducto +" "+ idUsuario + " ASDADASDASDASD");
         int numero = controller.tieneCarro(idUsuario).size();
         String idNuevoCarro="";
         System.out.println(numero);
        if (numero > 0) {
            System.out.println(idUsuario);
            idNuevoCarro = controller.obtenerIdCarrito(idUsuario).get(0);
            System.out.println(idNuevoCarro);
            idCarro = Integer.parseInt(idNuevoCarro);
            agregarProducto(idProducto, idCarro); 
        } else {
            controller.CrearCarro(idUsuario);
            idCarro =Integer.parseInt(controller.obtenerIdCarrito(idUsuario).get(0));
            agregarProducto(idProducto, idCarro);
        }
        return "Carrito.xhtml";
    }
    public void agregarProducto(int idProducto, int idCarro) throws DAOException {
        if (controller.existeEnCarro(idProducto, idCarro).size() <= 0) {
            ArrayList<ProductoDTO> productos = controller.obtenerTodosProductos();
            controller.agregarACarro(idProducto, idCarro, productos);
        }
    }
    public String editar(){
        CompraProductoDTO compraP = (CompraProductoDTO) tablaProductos.getRowData();
        cantidad = compraP.getCantidad();
        idVentaProducto=compraP.getIdVentaProducto();
        System.out.println(cantidad+" CANTIDAD EEEASDADAWQFERQAZSWDFGHJasdflgfwhjkjhsadgfhsaEEe");
        return "editarCantidad.xhtml";
        //return "Carrito.xhtml";
    }
    public String eliminar() throws DAOException{
        CompraProductoDTO compraP = (CompraProductoDTO) tablaProductos.getRowData();
        int idVenta = compraP.getIdVentaProducto();
        controller.eliminarDeCarrito(idVenta);
        return "Carrito.xhtml";
    }
    public String updateCant() throws DAOException{
        System.out.println(cantidad+" "+idVentaProducto);
        controller.updateCant(cantidad,idVentaProducto);
        return "Carrito.xhtml";
    }
    public void cambiarCarro()throws DAOException{
        controller.cambiarCarro(idCarro);
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
    public String getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
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
    public int getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    public CompraDTO getCompra() {
        return compra;
    }
    public void setCompra(CompraDTO compra) {
        this.compra = compra;
    }
    public ArrayList<CompraProductoDTO> getCompraProducto() throws DAOException {
       ArrayList<CompraProductoDTO> prod = controller.obtenerProductos(idCarro);
       int totalVenta=0;
       totalVentaCarro=0;
       for(int i=0;i<prod.size();i++){
           if(prod.get(i).isOfertaActiva()){
               totalVenta= prod.get(i).getPrecioOferta()*prod.get(i).getCantidad();
               prod.get(i).setTotal(totalVenta);
               prod.get(i).setPrecio(prod.get(i).getPrecioOferta());
               System.out.println(totalVenta);
           }else{
               totalVenta= prod.get(i).getCantidad() * prod.get(i).getPrecio();
               prod.get(i).setTotal(totalVenta);
               System.out.println(totalVenta);
           }
       }
       for(int i=0;i<prod.size();i++){
           totalVentaCarro = totalVentaCarro + prod.get(i).getTotal();
       }
        return prod;
    }
    public void setCompraProducto(ArrayList<CompraProductoDTO> compraProducto) {
        this.compraProducto = compraProducto;
    }
    public HtmlDataTable getTablaProductos() {
        return tablaProductos;
    }
    public void setTablaProductos(HtmlDataTable tablaProductos) {
        this.tablaProductos = tablaProductos;
    }
    public int getTotalVentaCarro() {
        return totalVentaCarro;
    }
    public void setTotalVentaCarro(int totalVenta) {
        this.totalVentaCarro = totalVenta;
    }
    
}
